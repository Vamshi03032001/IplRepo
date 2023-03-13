package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/route")
public class RoutingController {

    @GetMapping("/all")
    public String allPage(){
        return "Everyone can view this Page";
    }

    @GetMapping("/adminlogin")
    @PreAuthorize("hasAuthority('Admin')")
    public String adminPage(){
        return "Only can admin  view this page ";
    }

    @GetMapping("/ownerlogin")
    @PreAuthorize("hasAuthority('Owner')")
    public String userPage(){
        return " Only can onwer  view this page ";
    }

}
