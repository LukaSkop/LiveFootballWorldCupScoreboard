package com.scoreboard;

import java.time.LocalDateTime;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam, LocalDateTime startTime ) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public String getMatchDetails() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getHomeTeam(){
        return homeTeam;
    }

    public String getAwayTeam(){
        return awayTeam;
    }
}

