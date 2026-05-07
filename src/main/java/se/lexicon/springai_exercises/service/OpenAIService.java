package se.lexicon.springai_exercises.service;

import se.lexicon.springai_exercises.dto.ApplicationParameters;

public interface OpenAIService {

    String processSimpleChatQuery(String query);

    String generateApplicationStatus(ApplicationParameters params);
}
