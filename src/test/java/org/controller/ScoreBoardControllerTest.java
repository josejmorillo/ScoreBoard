package org.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.model.Game;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardControllerTest {
    ScoreBoardController scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new ScoreBoardController();
    }

    @Test
    void testStartGame() {
        scoreboard.startGame("HomeTeam", "AwayTeam");
        List<Game> games = scoreboard.getSummaryByTotalScore();
        assertEquals(1, games.size());
        assertEquals("HomeTeam", games.get(0).getHomeTeam());
        assertEquals("AwayTeam", games.get(0).getAwayTeam());
    }

    @Test
    void testStartGameDuplicate() {
        scoreboard.startGame("HomeTeam", "AwayTeam");
        scoreboard.startGame("HomeTeam", "awayTeam");
        List<Game> games = scoreboard.getSummaryByTotalScore();
        assertEquals(1, games.size());
    }

    @Test
    void testFinishGame() {
        scoreboard.startGame("HomeTeam", "AwayTeam");
        scoreboard.finishGame("HomeTeam", "AwayTeam");
        Game game = scoreboard.getSummaryByTotalScore().get(0);
        assertTrue(game.isFinished());
    }

    @Test
    void testUpdateScore() {
        scoreboard.startGame("HomeTeam", "AwayTeam");
        List<Game> games = scoreboard.getSummaryByTotalScore();
        Game game = games.get(0);
        scoreboard.updateScore("HomeTeam", "AwayTeam", 1, 0);
        assertEquals(1, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
    }

    @Test
    void testGetSummaryByTotalScore() {
        Game game1 = new Game("Mexico", "Canada");
        scoreboard.startGame(game1.getHomeTeam(), game1.getAwayTeam());
        scoreboard.updateScore(game1.getHomeTeam(), game1.getAwayTeam(), 0, 5);

        Game game2 = new Game("Spain", "Brazil");
        scoreboard.startGame(game2.getHomeTeam(), game2.getAwayTeam());
        scoreboard.updateScore(game2.getHomeTeam(), game2.getAwayTeam(), 10, 2);

        Game game3 = new Game("Germany", "France");
        scoreboard.startGame(game3.getHomeTeam(), game3.getAwayTeam());
        scoreboard.updateScore(game3.getHomeTeam(), game3.getAwayTeam(), 2, 2);

        Game game4 = new Game("Uruguay", "Italy");
        scoreboard.startGame(game4.getHomeTeam(), game4.getAwayTeam());
        scoreboard.updateScore(game4.getHomeTeam(), game4.getAwayTeam(), 6, 6);

        Game game5 = new Game("Argentina", "Australia");
        scoreboard.startGame(game5.getHomeTeam(), game5.getAwayTeam());
        scoreboard.updateScore(game5.getHomeTeam(), game5.getAwayTeam(), 3, 1);

        List<Game> sortedGames = scoreboard.getSummaryByTotalScore();
        sortedGames.forEach(System.out::println);

        assertEquals(5, sortedGames.size());
        assertEquals("Uruguay", sortedGames.get(0).getHomeTeam());
        assertEquals("Italy", sortedGames.get(0).getAwayTeam());
        assertEquals(6, sortedGames.get(0).getHomeTeamScore());
        assertEquals(6, sortedGames.get(0).getAwayTeamScore());

        assertEquals("Spain", sortedGames.get(1).getHomeTeam());
        assertEquals("Brazil", sortedGames.get(1).getAwayTeam());
        assertEquals(10, sortedGames.get(1).getHomeTeamScore());
        assertEquals(2, sortedGames.get(1).getAwayTeamScore());

        assertEquals("Mexico", sortedGames.get(2).getHomeTeam());
        assertEquals("Canada", sortedGames.get(2).getAwayTeam());
        assertEquals(0, sortedGames.get(2).getHomeTeamScore());
        assertEquals(5, sortedGames.get(2).getAwayTeamScore());

        assertEquals("Argentina", sortedGames.get(3).getHomeTeam());
        assertEquals("Australia", sortedGames.get(3).getAwayTeam());
        assertEquals(3, sortedGames.get(3).getHomeTeamScore());
        assertEquals(1, sortedGames.get(3).getAwayTeamScore());

        assertEquals("Germany", sortedGames.get(4).getHomeTeam());
        assertEquals("France", sortedGames.get(4).getAwayTeam());
        assertEquals(2, sortedGames.get(4).getHomeTeamScore());
        assertEquals(2, sortedGames.get(4).getAwayTeamScore());
    }
}
