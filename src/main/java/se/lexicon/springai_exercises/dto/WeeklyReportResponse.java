package se.lexicon.springai_exercises.dto;

import java.util.List;

public record WeeklyReportResponse(
        List<String> completedTasks,
        List<String> blockers,
        List<String> timeSpent,
        List<String> notes
) {
}
