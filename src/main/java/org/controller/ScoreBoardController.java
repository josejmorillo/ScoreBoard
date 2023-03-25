package org.controller;

import org.model.Game;

import java.util.List;

public class ScoreBoardController {
    List<Game> games;

    private void startGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        games.add(game);
    }


}
