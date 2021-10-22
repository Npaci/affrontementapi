package com.pngabo.affrontementapi.model.forms;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
public class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
