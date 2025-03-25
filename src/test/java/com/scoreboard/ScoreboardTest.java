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
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 26, 10, 0, 0, 0));

        assertEquals(1, scoreboard.getSummary().size());
        assertEquals("Mexico 0 - Canada 0", scoreboard.getSummary().get(0));
    }


    @Test
    public void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 24, 10, 0, 0, 0));

        // Score update
        scoreboard.updateScore("Mexico", "Canada", 3, 0);
        assertEquals("Mexico 3 - Canada 0", scoreboard.getSummary().get(0));

        // Test updating a non-existent match
        Exception noMatchException = assertThrows(IllegalArgumentException.class, () ->
                scoreboard.updateScore("Argentina", "Brazil", 1, 1)
        );
        assertEquals("No ongoing match found for Argentina vs Brazil!", noMatchException.getMessage());
    }


    @Test
    public void testFinishMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 3, 24, 10, 0, 0, 0));

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
        assertEquals("No such match is ongoing.", noMatchException.getMessage());
    }

    @Test
    public void testGetSummary() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 26, 10, 0, 0, 0));
        scoreboard.updateScore("Mexico", "Canada", 3, 2);

        scoreboard.startMatch("Spain", "Brazil", LocalDateTime.of(2025, 4, 26, 9, 0, 0, 0)); // Earlier time
        scoreboard.updateScore("Spain", "Brazil", 3, 2);

        scoreboard.startMatch("Germany", "France", LocalDateTime.of(2025, 4, 26, 11, 0, 0, 0));
        scoreboard.updateScore("Germany", "France", 5, 1);

        List<String> summary = scoreboard.getSummary();

        assertEquals("Germany 5 - France 1", summary.get(0)); // Highest score match first
        assertEquals("Spain 3 - Brazil 2", summary.get(1)); // Earlier match comes first in case of a tie
        assertEquals("Mexico 3 - Canada 2", summary.get(2)); // Later match appears after
    }

}
