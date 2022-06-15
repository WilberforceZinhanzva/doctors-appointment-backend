package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.*;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.AppointmentRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.AppointmentTypeRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.BlacklistedTimeRangeRepository;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.DoctorRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimeSlotsService {


    private AppointmentRepository appointmentRepository;
    private BlacklistedTimeRangeRepository blacklistedTimeRangeRepository;
    private DoctorRepository doctorRepository;
    private AppointmentTypeRepository appointmentTypeRepository;

    public TimeSlotsService(AppointmentRepository appointmentRepository, BlacklistedTimeRangeRepository blacklistedTimeRangeRepository, DoctorRepository doctorRepository, AppointmentTypeRepository appointmentTypeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.blacklistedTimeRangeRepository = blacklistedTimeRangeRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
    }

    public Set<TimeSlot> timeSlots(String doctorId, LocalDate localDate, String appointmentTypeId){
        //Fetch Doctor
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isEmpty())
            throw new ResourceNotFoundException("Doctor not found!");

        Optional<AppointmentType> appointmentType = appointmentTypeRepository.findById(appointmentTypeId);
        if(appointmentType.isEmpty())
            throw new ResourceNotFoundException("Appointment type not found!");

        return availableTimeSlots(doctor.get(),localDate,appointmentType.get());

    }



    public Optional<TimeSlot> grabTimeSlotForAppointment(Doctor doctor, LocalDateTime dateAndTime, AppointmentType appointmentType){
        Set<TimeSlot> allAvailableTimeSlots = availableTimeSlots(doctor,dateAndTime.toLocalDate(),appointmentType);
        LocalTime startTime = LocalTime.of(dateAndTime.getHour(),dateAndTime.getMinute());
        return allAvailableTimeSlots.stream().filter(slot -> slot.getStartTime()==startTime && slot.getEndTime() == startTime.plusMinutes(appointmentType.getDuration())).findFirst();
    }
    public Set<TimeSlot> availableTimeSlots(Doctor doctor, LocalDate date, AppointmentType appointmentType){

        Set<TimeSlot> timeSlots = new HashSet<>();

        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        LocalDateTime startDate = LocalDateTime.of(year,month,day,00,00);
        LocalDateTime endDate = LocalDateTime.of(year,month,day,23,59);

        List<Appointment> appointments = appointmentRepository.findAllByDoctor_IdAndAppointmentTime_DateBetween(doctor.getId(),startDate,endDate);

        //GRAB ALL BLACKLISTED TIME
        List<BlacklistedTimeRange> blacklistedTimeRanges = blacklistedTimeRangeRepository.findAll();



        if(appointments.isEmpty()){
            //NO APPOINTMENTS
            do{
                LocalTime appointmentStartTime = LocalTime.of(startDate.getHour(),startDate.getMinute());
                startDate = startDate.plus(Duration.ofMinutes(appointmentType.getDuration()));
                LocalTime appointmentEndTime = LocalTime.of(startDate.getHour(),startDate.getMinute());
                if(!isBlacklisted(blacklistedTimeRanges,appointmentStartTime,appointmentEndTime)){
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setStartTime(appointmentStartTime);
                    timeSlot.setEndTime(appointmentEndTime);
                    timeSlot.setTaken(false);

                    timeSlots.add(timeSlot);

                }
            }while(startDate.isBefore(endDate));
        }else{
            //1 OR MORE APPOINTMENTS

            //EXTRACT TIMESLOTS FROM APPOINTMENTS
            List<TimeSlot> takenTimeSlots = new ArrayList<>();
            appointments.stream().forEach(appointment -> {
                TimeSlot timeSlot = new TimeSlot();
                LocalDateTime appointmentDate = appointment.getAppointmentTime().getDate();
                timeSlot.setStartTime(LocalTime.of(appointmentDate.getHour(),appointmentDate.getMinute()));
                timeSlot.setEndTime(timeSlot.getStartTime().plusMinutes(appointment.getAppointmentTime().getDuration()));
                timeSlot.setTaken(true);
                takenTimeSlots.add(timeSlot);

            });
            List<TimeSlot> sortedTakenTimeSlots = new ArrayList<>();
            sortedTakenTimeSlots = takenTimeSlots.stream().sorted(Comparator.comparing(TimeSlot::getStartTime)).collect(Collectors.toList());

            do{
                LocalTime appointmentStartTime = LocalTime.of(startDate.getHour(),startDate.getMinute());
                startDate = startDate.plus(Duration.ofMinutes(appointmentType.getDuration()));
                LocalTime appointmentEndTime = LocalTime.of(startDate.getHour(),startDate.getMinute());
                if(!isBlacklisted(blacklistedTimeRanges,appointmentStartTime,appointmentEndTime) && !isTaken(sortedTakenTimeSlots,appointmentStartTime,appointmentEndTime)){
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setStartTime(appointmentStartTime);
                    timeSlot.setEndTime(appointmentEndTime);
                    timeSlot.setTaken(false);
                    timeSlots.add(timeSlot);
                }
            }while (startDate.isBefore(endDate));

        }

        timeSlots = processFillIns(timeSlots,appointmentType.getDuration());

        //[Remove blacklisted timeslots]
       return timeSlots.stream().filter(slot->!isBlacklisted(blacklistedTimeRanges,slot.getStartTime(),slot.getEndTime())).collect(Collectors.toSet());

    }
    public boolean isBlacklisted(List<BlacklistedTimeRange> blacklistedTimeRanges, LocalTime startTime, LocalTime endTime){
        return blacklistedTimeRanges.stream().anyMatch(bTime -> {
            if(startTime.isAfter(bTime.getStartTime()) && endTime.isBefore(bTime.getEndTime())){
                return true;
            }
            else{
                return false;
            }
        });
    }
    public boolean isTaken(List<TimeSlot> timeSlots, LocalTime startTime, LocalTime endTime){
        return timeSlots.stream().anyMatch(ts ->{
            if(startTime.isAfter(ts.getStartTime()) && endTime.isBefore(ts.getEndTime()) ||
                    startTime.isAfter(ts.getStartTime()) && startTime.isBefore(ts.getEndTime()) ||
                    endTime.isAfter(ts.getStartTime()) && endTime.isBefore(ts.getEndTime())
            ){
                return true;
            }
            return false;
        });
    }
    public Set<TimeSlot> processFillIns(Set<TimeSlot> timeSlots, int duration){
        List<TimeSlot> sortedTimeSlots = timeSlots.stream().sorted(Comparator.comparing(TimeSlot::getStartTime)).collect(Collectors.toList());
        List<TimeSlot> inFills = new ArrayList<>();

        int count = 0;
        for(TimeSlot slot : sortedTimeSlots){
            if(count == sortedTimeSlots.size()-2){
                break;
            }

            TimeSlot nextTimeSlot = sortedTimeSlots.get(count+1);
            int minutesApart = minutesBetween(slot.getEndTime(),nextTimeSlot.getStartTime());
            int possibleSlotsCount = minutesApart /duration;

            for(int i=0; i<possibleSlotsCount; i++){
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setStartTime(slot.getStartTime().plusMinutes(i*duration));
                timeSlot.setEndTime(timeSlot.getStartTime().plusMinutes(duration));
                timeSlot.setTaken(false);
                inFills.add(timeSlot);
            }

            count++;
        }

        sortedTimeSlots.addAll(inFills);

        return sortedTimeSlots.stream().sorted(Comparator.comparing(TimeSlot::getStartTime)).collect(Collectors.toSet());



    }
    public int minutesBetween(LocalTime startTime, LocalTime endTime){
        int hoursBetween = endTime.getHour() - startTime.getHour();
        return (60 * hoursBetween) - (60 - startTime.getMinute()) + endTime.getMinute();
    }
}
