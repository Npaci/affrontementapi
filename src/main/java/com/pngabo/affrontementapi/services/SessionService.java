package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.model.entities.Utilisateur;
import com.pngabo.affrontementapi.model.forms.LoginForm;
import com.pngabo.affrontementapi.repositories.UtilisateurRepository;
import com.pngabo.affrontementapi.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final UtilisateurRepository repository;
    private final AuthenticationManager auth;
    private final JwtProvider jwtProvider;

    public SessionService(UtilisateurRepository repository, AuthenticationManager auth, JwtProvider jwtProvider) {
        this.repository = repository;
        this.auth = auth;
        this.jwtProvider = jwtProvider;
    }

    public String login(LoginForm form) {
        // créer l'authentification
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        //Tester l'authentication
        auth.authenticate(authentication);
        //-> Ok: Créer token et renvoyer
        Utilisateur user = repository.findByUsername(form.getUsername())
                .orElseThrow( () -> new UsernameNotFoundException("L'utilisateur n'existe pas"));;

        return jwtProvider.createToken(user.getUsername(), user.getRoles());
    }

}
