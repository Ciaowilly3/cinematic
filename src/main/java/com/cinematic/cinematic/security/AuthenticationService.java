package com.cinematic.cinematic.security;

import com.cinematic.cinematic.dtos.AuthenticationRequest;
import com.cinematic.cinematic.dtos.AuthenticationResponse;
import com.cinematic.cinematic.dtos.RegisterRequest;
import com.cinematic.cinematic.models.Role;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CinemaRepository cinemaRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        val user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .cinema(cinemaRepository.findById(request.getCinemaId()).orElse(null))
                .build();
        userRepository.save(user);
        val jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
    val user = userRepository.findByUserName(request.getUserName())
            .orElseThrow();
    log.info("IN AUTHENTICATION THE USER FOUND IS : {}", user);
    val jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
