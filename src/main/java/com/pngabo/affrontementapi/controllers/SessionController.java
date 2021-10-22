package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.forms.LoginForm;
import com.pngabo.affrontementapi.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> longin(@Valid @RequestBody LoginForm form){
        return ResponseEntity.ok(service.login(form));
    }
}
