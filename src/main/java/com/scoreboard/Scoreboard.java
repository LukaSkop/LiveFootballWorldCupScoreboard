package com.scoreboard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> matches;

    public Scoreboard(){
        matches = new ArrayList<>();
    }

    //Method to start match
    public void startMatch(String homeTeam, String awayTeam, LocalDateTime startTime){
        for (Match match : matches){
             boolean isSameMatch =
                     (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) || (match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam));
             if (isSameMatch) {
                 throw new IllegalArgumentException("Match already exists.");
             }
             if(homeTeam == null || awayTeam == null || homeTeam.trim().isEmpty() || awayTeam.trim().isEmpty()){
                 throw new IllegalArgumentException("Invalid team names: Names cannot be empty.");
             }
             if (startTime == null) {
                throw new IllegalArgumentException("Invalid start time: Cannot be null.");
             }
            if (startTime.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Invalid start time: Cannot schedule a match in the past.");
            }
            if(homeTeam.equals(awayTeam)){
                 throw new IllegalArgumentException("A team cannot play against itself.");
             }
            }
        matches.add(new Match(homeTeam, awayTeam, startTime));
        }

    //Method to update score
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore){
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        for (Match match : matches) {
            if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                match.updateScore(homeScore, awayScore);
                return;
            }
        }
        throw new IllegalArgumentException("No ongoing match found for " + homeTeam + " vs " + awayTeam +"!");
    }

    //Method to finish match
    public void finishMatch(String homeTeam, String awayTeam){
        for (Match match : matches) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                if (match.getStartTime().isAfter(LocalDateTime.now())) {
                    throw new IllegalArgumentException("Cannot finish a match that hasn't started yet.");
                }
                matches.remove(match);
                return;
            }
        }
        throw new IllegalArgumentException("No such match is ongoing.");
    }

    //Get summary method
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

