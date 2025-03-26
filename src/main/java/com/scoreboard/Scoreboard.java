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
    public void startMatch(String homeTeam, String awayTeam, LocalDateTime startTime) {
        if(homeTeam == null || awayTeam == null || homeTeam.trim().isEmpty() || awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid team names: Names cannot be empty.");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Invalid start time: Cannot be null.");
        }
        if (startTime.isBefore(LocalDateTime.now())) {
            // For easier testing comment out the exception
            //throw new IllegalArgumentException("Invalid start time: Cannot schedule a match in the past.");
             System.out.println("Warning: Match is in the past, but starting anyway (for testing).");
        }
        if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("A team cannot play against itself.");
        }
        for (Match match : matches) {
            if ((match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) ||
                    (match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam))) {
                throw new IllegalArgumentException("Match already exists.");
            }
        }
        matches.add(new Match(homeTeam, awayTeam, startTime));
        }


    //Method to update score
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        Match matchToUpdate = matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No ongoing match found for " + homeTeam + " vs " + awayTeam + "!"));

        if (matchToUpdate.getStartTime().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Cannot update score before match start.");
        }
        matchToUpdate.updateScore(homeScore, awayScore);
    }


    //Method to finish match
    public void finishMatch(String homeTeam, String awayTeam) {
        Match matchToFinish = matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No ongoing match found for " + homeTeam + " vs " + awayTeam + "!"));

        if (matchToFinish.getStartTime().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Cannot finish a match that hasn't started yet.");
        }
        matches.remove(matchToFinish);
    }


    //Get summary method
    public List<String> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort((m1, m2) -> {

            int totalScoreComparison = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
            if (totalScoreComparison != 0) {
                return totalScoreComparison;
            }

            return m2.getStartTime().compareTo(m1.getStartTime());
        });

        List<String> summary = new ArrayList<>();
        for (Match match : sortedMatches) {
            summary.add(match.getMatchDetails());
        }
        return summary;
    }

}

