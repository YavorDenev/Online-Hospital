package DigRz.OnlineHospital.controllers;

import DigRz.OnlineHospital.services.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grouping")
public class GroupingController {

    @Autowired
    private GroupingService groupingService;

    @GetMapping("/by_doctor")
    private String groupByDoctor (Model m) {

        m.addAttribute("doctorList", groupingService.findPatientsCountByDoctor());
        return "grouping/by_doctor";
    }
    @GetMapping("/by_date")
    private String groupByDate (Model m) {

        m.addAttribute("dateList", groupingService.findPatientsCountByDate());
        return "grouping/by_date";
    }

    @GetMapping("/by_department")
    private String groupByDepartment (Model m) {

        m.addAttribute("departmentList", groupingService.findPatientsCountByDepartment());
        return "grouping/by_department";
    }
}
