package org.controller;

import org.model.Game;

import java.util.List;

public class ScoreBoardController {
    List<Game> games;

    private void startGame (String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        games.add(game);
    }

    private void finishGame (Game game) {
        games.remove(game);
    }

    private void updateScore (Game game, int homeTeamScore, int awayTeamScore) {
        game.updateScore(homeTeamScore, awayTeamScore);
    }

}
