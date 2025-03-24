package com.scoreboard;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        // Start matches
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

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

        // Finish a match
        scoreboard.finishMatch("Mexico", "Canada");

        // Get updated summary
        System.out.println("\nAfter finishing Mexico vs Canada:");
        summary = scoreboard.getSummary();
        for (String match : summary) {
            System.out.println(match);
        }
    }
}

