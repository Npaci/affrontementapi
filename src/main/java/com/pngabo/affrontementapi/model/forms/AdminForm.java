package com.pngabo.affrontementapi.model.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminForm {
    private long id;
    private String nom;
    private String prenom;
}
