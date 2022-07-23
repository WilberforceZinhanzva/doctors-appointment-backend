package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class UIController {
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String patientUi(){
        return "patient/index";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String adminUi(){
        return "admin/index";
    }

}
