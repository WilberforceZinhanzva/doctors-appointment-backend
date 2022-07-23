package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.TimeSlot;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sort-time")
    public void sortTime() {
        List<LocalTime> timeList = Arrays.asList(LocalTime.of(23, 59), LocalTime.of(00, 02));
        List<LocalTime> sortedList = timeList.stream().sorted().collect(Collectors.toList());

        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setStartTime(LocalTime.of(12, 9));

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setStartTime(LocalTime.of(00, 02));

        TimeSlot timeSlot3 = new TimeSlot();
        timeSlot3.setStartTime(LocalTime.of(23, 59));


        List<TimeSlot> timeSlots = Arrays.asList(timeSlot1, timeSlot2, timeSlot3);
        List<TimeSlot> sortedTime = timeSlots.stream().sorted(Comparator.comparing(TimeSlot::getStartTime)).collect(Collectors.toList());
        System.out.println(sortedTime);
    }
}
