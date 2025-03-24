package com.scoreboard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> matches;

    public Scoreboard(){
        matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam, LocalDateTime startTime){
        for (Match match : matches){
             if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                 System.out.println("Match already exists.");
                 return;
             }
            }
        matches.add(new Match(homeTeam, awayTeam, startTime));
        }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore){
        for (Match match : matches) {
            if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                match.updateScore(homeScore, awayScore);
                return;
            }
        }
        System.out.println("No ongoing match found for " + homeTeam + " vs " + awayTeam +"!");
    }

    public void finishMatch(String homeTeam, String awayTeam){
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    public List<String> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort((m1, m2) -> {

            int totalScoreComparison = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
            if (totalScoreComparison != 0) {
                return totalScoreComparison;
            }

            return m1.getStartTime().compareTo(m2.getStartTime());
        });

        List<String> summary = new ArrayList<>();
        for (Match match : sortedMatches) {
            summary.add(match.getMatchDetails());
        }
        return summary;
    }

}

