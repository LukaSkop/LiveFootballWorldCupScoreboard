package com.scoreboard;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        // Start matches with specific start times
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(5));
        scoreboard.startMatch("Spain", "Brazil", LocalDateTime.now().minusMinutes(4));
        scoreboard.startMatch("Germany", "France", LocalDateTime.now().minusMinutes(3));
        scoreboard.startMatch("Uruguay", "Italy", LocalDateTime.now().minusMinutes(2));
        scoreboard.startMatch("Argentina", "Australia", LocalDateTime.now().minusMinutes(1));


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
