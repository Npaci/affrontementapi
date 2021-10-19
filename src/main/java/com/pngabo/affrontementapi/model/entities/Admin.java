package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin extends Utilisateur {

}
