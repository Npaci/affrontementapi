package com.pngabo.affrontementapi.controllers;

import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import com.pngabo.affrontementapi.repositories.JoueurRepository;
import com.pngabo.affrontementapi.services.JoueurServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/joueur")
public class JoueurController {
    private final JoueurServiceImpl service;
    private final JoueurRepository repository;
    private final PasswordEncoder encoder;

    public JoueurController(JoueurServiceImpl jService, JoueurRepository repository, PasswordEncoder encoder) {
        this.service = jService;
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<JoueurDTO> getAll() {
        return service.getAll();
    }

    //PAGINATION
//    Page<Joueur> getall(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
//        return repository.findAll(PageRequest.of(
//                page.orElse(0),
//                2,
//                Sort.Direction.ASC,
//                sortBy.orElse("id")
//        ));
//    }

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
        form.setPassword(encoder.encode(form.getPassword()));
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
    public JoueurDTO inscription(@RequestParam Long idjoueur, @RequestParam Long idligue) {
        return service.inscriptionAffrontement(idjoueur, idligue);
    }
}

