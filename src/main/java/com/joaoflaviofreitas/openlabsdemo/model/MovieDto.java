package com.joaoflaviofreitas.openlabsdemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDto(

        @NotBlank
        String name,

        @NotNull
        Double rating

) {
}
