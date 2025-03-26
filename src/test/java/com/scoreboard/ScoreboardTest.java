package com.scoreboard;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardTest {

    @Test
    public void testStartMatch() {
        // Test adding a match
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(6));

        assertEquals(1, scoreboard.getSummary().size());
        assertEquals("Mexico 0 - Canada 0", scoreboard.getSummary().getFirst());
    }


    @Test
    public void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(6));

        // Score update
        scoreboard.updateScore("Mexico", "Canada", 3, 0);
        assertEquals("Mexico 3 - Canada 0", scoreboard.getSummary().getFirst());

        // Test updating a non-existent match
        Exception noMatchException = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Argentina", "Brazil", 1, 1)
        );
        assertEquals("No ongoing match found for Argentina vs Brazil!", noMatchException.getMessage());
    }


    @Test
    public void testFinishMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(6));

        scoreboard.finishMatch("Mexico", "Canada");
        assertEquals(0, scoreboard.getSummary().size());

        // Test updating a finished match
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Mexico", "Canada", 1, 1)
        );
        assertEquals("No ongoing match found for Mexico vs Canada!", exception.getMessage());

        // Test finishing a match that doesn't exist
        Exception noMatchException = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.finishMatch("Argentina", "Brazil")
        );
        assertEquals("No ongoing match found for Argentina vs Brazil!", noMatchException.getMessage()); // Match the actual exception message
    }


    @Test
    public void testGetSummary() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(3));
        scoreboard.updateScore("Mexico", "Canada", 3, 2);

        scoreboard.startMatch("Spain", "Brazil", LocalDateTime.now().minusMinutes(2)); // Earlier time
        scoreboard.updateScore("Spain", "Brazil", 3, 2);

        scoreboard.startMatch("Germany", "France", LocalDateTime.now().minusMinutes(1));
        scoreboard.updateScore("Germany", "France", 5, 1);

        List<String> summary = scoreboard.getSummary();

        assertEquals("Germany 5 - France 1", summary.get(0)); // Highest score match first
        assertEquals("Spain 3 - Brazil 2", summary.get(1)); // Earlier match comes first in case of a tie
        assertEquals("Mexico 3 - Canada 2", summary.get(2)); // Later match appears after
    }

    @Test
    public void testStartMatchWithInvalidInputs() {
        Scoreboard scoreboard = new Scoreboard();
        
        Exception nullTeams = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.startMatch(null, "Canada", LocalDateTime.now())
        );
        assertEquals("Invalid team names: Names cannot be empty.", nullTeams.getMessage());

        Exception emptyTeams = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.startMatch(" ", "Canada", LocalDateTime.now())
        );
        assertEquals("Invalid team names: Names cannot be empty.", emptyTeams.getMessage());

        Exception sameTeam = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.startMatch("Mexico", "Mexico", LocalDateTime.now())
        );
        assertEquals("A team cannot play against itself.", sameTeam.getMessage());

        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().minusMinutes(5));
        Exception duplicateMatch = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now())
        );
        assertEquals("Match already exists.", duplicateMatch.getMessage());
    }

    @Test
    public void testUpdateScoreInvalidCases() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.now().plusMinutes(10)); // Future match

        Exception negativeScore = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Mexico", "Canada", -1, 2)
        );
        assertEquals("Scores cannot be negative.", negativeScore.getMessage());

        Exception futureMatch = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Mexico", "Canada", 1, 1)
        );
        assertEquals("Cannot update score before match start.", futureMatch.getMessage());

        Exception nonExistentMatch = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Argentina", "Brazil", 2, 2)
        );
        assertEquals("No ongoing match found for Argentina vs Brazil!", nonExistentMatch.getMessage());
    }

    @Test
    public void testFinishMatchInvalidCases() {
        Scoreboard scoreboard = new Scoreboard();

        Exception noMatch = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.finishMatch("Argentina", "Brazil")
        );
        assertEquals("No ongoing match found for Argentina vs Brazil!", noMatch.getMessage());

        scoreboard.startMatch("Germany", "France", LocalDateTime.now().plusMinutes(10));
        Exception futureMatchFinish = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.finishMatch("Germany", "France")
        );
        assertEquals("Cannot finish a match that hasn't started yet.", futureMatchFinish.getMessage());
    }


}
