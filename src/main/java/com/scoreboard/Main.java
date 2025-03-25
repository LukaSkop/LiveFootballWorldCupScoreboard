package com.scoreboard;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        // Start matches with specific start times
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2026, 3, 26, 10, 0, 0, 0));
        scoreboard.startMatch("Spain", "Brazil", LocalDateTime.of(2026, 3, 26, 9, 0, 0, 0));
        scoreboard.startMatch("Germany", "France", LocalDateTime.of(2026, 3, 24, 11, 0, 0, 0));
        scoreboard.startMatch("Uruguay", "Italy", LocalDateTime.of(2026, 3, 24, 8, 0, 0, 0));
        scoreboard.startMatch("Argentina", "Australia", LocalDateTime.of(2026, 3, 24, 12, 0, 0, 0));

        // Update scores
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        // Get summary
        List<String> summary = scoreboard.getSummary();
        for (String match : summary) {
            System.out.println(match);
        }

    }
}
