package com.cinematic.cinematic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/controller")
public class DemoController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/role-user")
    public ResponseEntity<String> sayHelloUser(){
        return ResponseEntity.ok("hello I'm not token free for only user with role user");
    }


    @GetMapping(path = "/role-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok("hello I'm not token free for only user with role Admin");
    }
}
