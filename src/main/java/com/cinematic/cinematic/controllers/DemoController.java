package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Film;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/controller")
@Tag(name = "Auth Tests", description = "controller to test authentication")
public class DemoController {

    @Operation(summary = "Get a String only  if the role is USER")
    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/role-user")
    public ResponseEntity<String> sayHelloUser(){
        return ResponseEntity.ok("hello I'm not token free for only user with role user");
    }

    @Operation(summary = "Get a String only  if the role is ADMIN")
    @GetMapping(path = "/role-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok("hello I'm not token free for only user with role Admin");
    }
}
