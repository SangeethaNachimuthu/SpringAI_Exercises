package se.lexicon.springai_exercises.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
