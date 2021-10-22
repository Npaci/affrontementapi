package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.dtos.ArbitreDTO;
import com.pngabo.affrontementapi.model.forms.ArbitreForm;
import com.pngabo.affrontementapi.services.ArbitreService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/arbitre")
public class ArbitreController {
    private final ArbitreService service;
    private final PasswordEncoder encoder;

    public ArbitreController(ArbitreService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<ArbitreDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ArbitreDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public ArbitreDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public ArbitreDTO insert(@Valid @RequestBody ArbitreForm form) {
        form.setPassword(encoder.encode(form.getPassword()));
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public ArbitreDTO update(@Valid @RequestBody ArbitreForm form) {
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public ArbitreDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
