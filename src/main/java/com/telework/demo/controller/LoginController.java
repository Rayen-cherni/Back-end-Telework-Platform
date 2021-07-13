package com.telework.demo.controller;

import com.telework.demo.configuration.securityConfiguration.ExtendedUser;
import com.telework.demo.configuration.securityConfiguration.UserDetailsServiceImpl;
import com.telework.demo.configuration.securityConfiguration.jwt.JwtProvider;
import com.telework.demo.domain.dto.UserDto;
import com.telework.demo.domain.model.AuthenticationRequest;
import com.telework.demo.domain.model.AuthenticationResponse;
import com.telework.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.telework.demo.utils.Constants.LOGIN_ENDPOINT;

@RestController
@RequestMapping(LOGIN_ENDPOINT)
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtProvider provider;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        final String jwt = provider.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .accessToken(jwt)
                        .user(((ExtendedUser) userDetails).getUserDto())
                        .build()
        );
    }
}
