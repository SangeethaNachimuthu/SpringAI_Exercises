package se.lexicon.springai_exercises.dto;

import jakarta.validation.constraints.NotBlank;

public record MeetingNotes(

        @NotBlank(message = "Input cannot be null")
        String input
) {
}
