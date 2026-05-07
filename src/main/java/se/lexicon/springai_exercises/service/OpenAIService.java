package se.lexicon.springai_exercises.service;

import se.lexicon.springai_exercises.dto.ApplicationParameters;
import se.lexicon.springai_exercises.dto.MeetingNotes;

public interface OpenAIService {

    String processSimpleChatQuery(String query);

    String generateApplicationStatus(ApplicationParameters params);

    String generateMeetingNotes(MeetingNotes input);
}
