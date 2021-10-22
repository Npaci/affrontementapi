package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.forms.AffrontementForm;
import com.pngabo.affrontementapi.services.AffrontementServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/affrontement")
public class AffrontementController {
    private final AffrontementServiceImpl service;

    public AffrontementController(AffrontementServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<AffrontementDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AffrontementDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public AffrontementDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public AffrontementDTO insert(@Valid @RequestBody AffrontementForm form) {
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public AffrontementDTO update(@Valid @RequestBody AffrontementForm form) {
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public AffrontementDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
