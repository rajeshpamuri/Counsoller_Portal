package com.rajesh.__Mini_Project.controllers;

import com.rajesh.__Mini_Project.dto.ViewEnqiFilterRequest;
import com.rajesh.__Mini_Project.entity.Enquiry;
import com.rajesh.__Mini_Project.services.EnquriService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EnquyriController {

    @Autowired
    private EnquriService enquriService;

    @GetMapping("/enquirieForm")
    public String enquirieForm(Model model){
        model.addAttribute("enq",new Enquiry());
        return "enq";
    }

    @GetMapping("/view-enq")
    public  String viewEnq( HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        Integer counsollerId = (Integer) session.getAttribute("counsollerId");
        List<Enquiry> allEnquiries = enquriService.getAllEnquiries(counsollerId);
        model.addAttribute("enq",allEnquiries);

        model.addAttribute("enqs",new ViewEnqiFilterRequest());
        return "viewenq";
    }

    @PostMapping("/filter")
    public String viewEnqWithFilter(@ModelAttribute("enqs") ViewEnqiFilterRequest viewEnqiFilterRequest,HttpServletRequest request, Model model ){
        HttpSession session = request.getSession(false);
        Integer counsollerId = (Integer) (Integer) session.getAttribute("counsollerId");
        List<Enquiry> enquiresWithFilter = enquriService.getEnquiresWithFilter(viewEnqiFilterRequest, counsollerId);
        model.addAttribute("enq",enquiresWithFilter);
        return"viewenq";
    }

    @PostMapping("/save")
    public String addEnquiri(@ModelAttribute("enq") Enquiry enquiry, Model model, HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession(false);
        Integer counsollerId = (Integer) session.getAttribute("counsollerId");
        boolean b = enquriService.addEnquiry(enquiry, counsollerId);
        if (b){
            model.addAttribute("smsg","New Enquirey Added");
            return "enq";
        }else {
            model.addAttribute("Emsg","Error To Add");
            return "enq";
        }

    }

}
