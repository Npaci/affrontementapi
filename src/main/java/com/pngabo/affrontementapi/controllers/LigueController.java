package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.dtos.LigueDTO;
import com.pngabo.affrontementapi.model.forms.LigueForm;
import com.pngabo.affrontementapi.services.LigueServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ligue")
public class LigueController {
    private final LigueServiceImpl service;

    public LigueController(LigueServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<LigueDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LigueDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public LigueDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public LigueDTO insert(@Valid @RequestBody LigueForm form) {
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public LigueDTO update(@Valid @RequestBody LigueForm form) {
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public LigueDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
