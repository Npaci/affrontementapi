package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import com.pngabo.affrontementapi.services.JoueurService;
import com.pngabo.affrontementapi.services.JoueurServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/joueur")
public class JoueurController {
    private final JoueurServiceImpl service;
    private final PasswordEncoder encoder;

    public JoueurController(JoueurServiceImpl jService, PasswordEncoder encoder) {
        this.service = jService;
        this.encoder = encoder;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<JoueurDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public JoueurDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public JoueurDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public JoueurDTO insert(@Valid @RequestBody JoueurForm form) {
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public JoueurDTO update(@Valid @RequestBody JoueurForm form) {
        form.setPassword(encoder.encode(form.getPassword()));
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public JoueurDTO delete(@PathVariable Long id, Authentication auth) {
        return service.deleteSecure(id, auth);
        //return service.delete(id);
    }

    @GetMapping(path = "/inscription", params = {"idjoueur","idligue"})
    public JoueurDTO inscription(@RequestParam Long idjoueur, @RequestParam Long idligue, HttpRequest req) {
//        req.getHeaders().geta
        return service.inscriptionAffrontement(idjoueur, idligue);
    }
}

