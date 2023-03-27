package org.controller;

import org.model.Game;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardController {
    List<Game> games;

    public ScoreBoardController() {
        this.games = new ArrayList<>();
    }

    public void startGame (String homeTeam, String awayTeam) {
        if (findGame(homeTeam, awayTeam) == null) {
            Game game = new Game(homeTeam, awayTeam);
            games.add(game);
        } else {
            System.out.println("There is already a game with the same teams.");
        }
    }

    public boolean finishGame (String homeTeam, String awayTeam) {
        Game gameToRemove = findGame(homeTeam, awayTeam);
        if (gameToRemove != null) {
            return games.remove(gameToRemove);
        } else {
            System.out.println("Game not found.");
            return false;
        }
    }

    public boolean finishGame (Game game) {
       return finishGame(game.getHomeTeam(), game.getAwayTeam());
    }

    public Game findGame(String homeTeam, String awayTeam) {
        return games.stream()
                .filter(game -> game.getHomeTeam().equalsIgnoreCase(homeTeam) && game.getAwayTeam().equalsIgnoreCase(awayTeam))
                .findFirst()
                .orElse(null);
    }

    public void updateScore (String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        Game gameToUpdate = findGame(homeTeam, awayTeam);
        if (gameToUpdate != null) {
            gameToUpdate.updateScore(homeTeamScore, awayTeamScore);
        } else {
            System.out.println("Game not found.");
        }
    }

    public void updateScore (Game game, int homeTeamScore, int awayTeamScore) {
        updateScore (game.getHomeTeam(), game.getAwayTeam(), homeTeamScore, awayTeamScore);
    }

    /**
     * Returns a sorted list of games based on their total score in descending order.
     * Games with higher total scores appear first in the list.
     * If two games have the same total score, compare their indices in the original list
     * @return a List<Game> sorted in descending order
     */
    public List <Game> getSummaryByTotalScore() {
        List <Game> sortedGames = new ArrayList<>(games);

        sortedGames.sort((g1,g2) -> {
            int totalScore = g2.getTotalScore() - g1.getTotalScore();
            if(totalScore != 0) {
                return totalScore;
            } else {
                return games.indexOf(g2) - games.indexOf(g1);
            }
        });

        return sortedGames;
    }

}
