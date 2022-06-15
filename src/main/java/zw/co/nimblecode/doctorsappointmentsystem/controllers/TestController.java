package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sort-time")
    public void sortTime(){
        List<LocalTime> timeList = Arrays.asList(LocalTime.of(23,59), LocalTime.of(00,02));
        List<LocalTime> sortedList = timeList.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);
    }
}
