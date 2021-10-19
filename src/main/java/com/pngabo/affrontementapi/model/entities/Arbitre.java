package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ARB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Arbitre extends Utilisateur {
    @OneToMany(mappedBy = "arbitre")
    private List<Affrontement> participations;
}
