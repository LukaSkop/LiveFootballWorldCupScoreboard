package com.scoreboard;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardTest {

    @Test
    public void testStartMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 26, 10, 0, 0, 0));
    }

    @Test
    public void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 24, 10, 0, 0, 0));
        scoreboard.updateScore("Mexico", "Canada", 3, 0);
        assertEquals("Mexico 3 - Canada 0", scoreboard.getSummary().get(0));
    }

    @Test
    public void testFinishMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 24, 10, 0, 0, 0));
        scoreboard.finishMatch("Mexico", "Canada");
        assertEquals(0, scoreboard.getSummary().size());
    }

    @Test
    public void testGetSummary() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada", LocalDateTime.of(2025, 4, 26, 10, 0, 0, 0));
        scoreboard.updateScore("Mexico", "Canada", 3, 2);
        scoreboard.startMatch("Spain", "Brazil", LocalDateTime.of(2025, 4, 26, 10, 0, 0, 0));
        scoreboard.updateScore("Spain", "Brazil", 5, 1);
        List<String> summary = scoreboard.getSummary();
        assertEquals("Spain 5 - Brazil 1", summary.get(0));
    }
}
