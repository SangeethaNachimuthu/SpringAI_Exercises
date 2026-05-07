package se.lexicon.springai_exercises.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springai_exercises.dto.ApplicationParameters;
import se.lexicon.springai_exercises.dto.MeetingNotes;
import se.lexicon.springai_exercises.service.OpenAIService;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Validated
public class OpenAIController {

    private final OpenAIService service;

    @GetMapping("/chat")
    public String processSimpleChatQuery(@RequestParam @NotNull(message = "Question cannot be null") String question) {

        return service.processSimpleChatQuery(question);
    }

    @PostMapping("/app-status")
    public String generateApplicationStatus(@RequestBody @Validated ApplicationParameters params) {

        return service.generateApplicationStatus(params);
    }

    @PostMapping("/notes")
    public String generateMeetingNotes(@RequestBody @Validated MeetingNotes input) {

        return service.generateMeetingNotes(input);
    }
}
