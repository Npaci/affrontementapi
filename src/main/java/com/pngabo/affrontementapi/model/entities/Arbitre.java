package com.pngabo.affrontementapi.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ARBITRE")
@Getter
@Setter
public class Arbitre extends Utilisateur {
    @OneToMany(mappedBy = "arbitre")
    private List<Affrontement> participations;
}
