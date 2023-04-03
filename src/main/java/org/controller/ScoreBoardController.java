package org.controller;

import org.model.Game;
import org.view.ScoreBoardView;
import org.view.SummaryBoardView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ScoreBoardController {
    List<Game> games;
    List<Game> finishedGames;
    private ScoreBoardView scoreView;
    private SummaryBoardView summaryView;
    private static final Logger logger = Logger.getLogger(ScoreBoardController.class.getName());


    public ScoreBoardController() {
        this.games = new ArrayList<>();
        this.finishedGames = new ArrayList<>();
        this.scoreView = new ScoreBoardView();
        addBtnListener();
        updateBtnListener();
        finishBtnListener();
        summaryBtnListener();
    }

    public void startGame (String homeTeam, String awayTeam) {
        if (findGame(homeTeam, awayTeam) == null) {
            Game game = new Game(homeTeam, awayTeam);
            games.add(game);
        } else {
            logger.warning("There is already a game with the same teams.");
        }
    }

    public boolean finishGame (String homeTeam, String awayTeam) {
        Game gameFinished = findGame(homeTeam, awayTeam);
        if (gameFinished != null) {
            finishedGames.add(gameFinished);
            return games.remove(gameFinished);
        } else {
            logger.warning("Game not found.");
            return false;
        }
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
            logger.warning("Game not found.");
        }
    }

    /**
     * Returns a sorted list of games based on their total score in descending order.
     * Games with higher total scores appear first in the list.
     * If two games have the same total score, compare their indices in the original list
     * @return a List<Game> sorted in descending order
     */
    public List <Game> getSummaryByTotalScore() {
        List <Game> sortedGames = new ArrayList<>(games);
        sortedGames.addAll(finishedGames);

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

    private void addBtnListener() {
        scoreView.getAddButton().addActionListener(listener -> {
            String homeTeam = scoreView.getHomeTeamField().getText();
            String awayTeam = scoreView.getAwayTeamField().getText();
            if(!homeTeam.isEmpty() && !awayTeam.isEmpty()) {
                startGame(homeTeam, awayTeam);
                scoreView.resetAddBtn();
                refreshTable();
            } else {
                scoreView.showDialog("Please fill in both team fields");
            }
        });
    }

    private void updateBtnListener() {
        scoreView.getUpdateButton().addActionListener(listener -> {
            JTable gamesTable = scoreView.getGamesTable();
            int selectedRow = gamesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String homeTeam = (String) gamesTable.getValueAt(selectedRow, 0);
                String awayTeam = (String) gamesTable.getValueAt(selectedRow, 2);
                int homeScore;
                int awayScore;
                try {
                    homeScore = Integer.parseInt(scoreView.getHomeScoreField().getText());
                    awayScore = Integer.parseInt(scoreView.getAwayScoreField().getText());
                } catch (NumberFormatException e) {
                    scoreView.showDialog("Please enter valid scores");
                    return;
                }
                updateScore(homeTeam, awayTeam, homeScore, awayScore);
                scoreView.resetUpdateBtn();
                refreshTable();
            } else {
                scoreView.showDialog("Please select a game to update the score");
            }
        });
    }

    private void finishBtnListener() {
        scoreView.getFinishButton().addActionListener(listener -> {
            JTable gamesTable = scoreView.getGamesTable();
            int selectedRow = gamesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String homeTeam = (String) gamesTable.getValueAt(selectedRow, 0);
                String awayTeam = (String) gamesTable.getValueAt(selectedRow, 2);
                finishGame(homeTeam, awayTeam);
                refreshTable();
            } else {
                scoreView.showDialog("Please select the game to finish");
            }
        });
    }

    private void summaryBtnListener() {
        scoreView.getSummaryButton().addActionListener(listener -> {
            if(getSummaryByTotalScore().isEmpty()) {
                scoreView.showDialog ("There are no games to show!");
            } else {
                summaryView = new SummaryBoardView();
                loadSummaryData();
            }
        });
    }

    public void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) scoreView.getGamesTable().getModel();
        tableModel.setRowCount(0); //clear table
        for (Game game : games) {
            Object[] row = {game.getHomeTeam(), game.getHomeTeamScore(), game.getAwayTeam(), game.getAwayTeamScore()};
            tableModel.addRow(row);
        }
    }

    private void loadSummaryData() {
        DefaultTableModel tableModel = (DefaultTableModel) summaryView.getSummaryTable().getModel();
        tableModel.setRowCount(0); //clear table
        for (Game game : getSummaryByTotalScore()) {
            Object[] row = {game.getHomeTeam(), game.getHomeTeamScore(), game.getAwayTeam(), game.getAwayTeamScore()};
            tableModel.addRow(row);
        }
    }


}
