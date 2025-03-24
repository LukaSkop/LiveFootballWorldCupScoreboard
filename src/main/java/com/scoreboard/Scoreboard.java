package com.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final List<Match> matches;

    public Scoreboard(){
        matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam){
        for (Match match : matches){
             if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                 System.out.println("Match already exists.");
                 return;
             }
            }
        matches.add(new Match(homeTeam, awayTeam));
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


}

