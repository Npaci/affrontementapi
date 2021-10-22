package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends Utilisateur {

}
