package se.lexicon.springai_exercises.service;

import se.lexicon.springai_exercises.dto.ApplicationParameters;
import se.lexicon.springai_exercises.dto.MeetingNotes;
import se.lexicon.springai_exercises.dto.WeeklyReportInputs;
import se.lexicon.springai_exercises.dto.WeeklyReportResponse;

public interface OpenAIService {

    String processSimpleChatQuery(String query);

    String generateApplicationStatus(ApplicationParameters params);

    String generateMeetingNotes(MeetingNotes input);

    WeeklyReportResponse generateWeeklyReportJson(WeeklyReportInputs inputs);
}
