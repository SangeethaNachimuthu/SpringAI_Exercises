package se.lexicon.springai_exercises.dto;

import jakarta.validation.constraints.NotBlank;

public record ApplicationParameters(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Background cannot be blank")
        String background,

        @NotBlank(message = "Experience cannot be blank")
        String experience,

        @NotBlank(message = "Motivation cannot be blank")
        String motivation,

        @NotBlank(message = "Skills cannot be blank")
        String skills

) {
}
