package org.view;

import org.controller.ScoreBoardController;
import org.model.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreBoardView extends JFrame {

    private final ScoreBoardController controller;
    private JTable gamesTable;

    //Fields
    private JTextField homeTeamField;
    private JTextField awayTeamField;
    private JTextField homeScoreField;
    private JTextField awayScoreField;

    //Buttons
    private JButton updateButton;
    private JButton finishButton;
    private JButton summaryButton;
    private JButton addButton;

    //Labels
    private JLabel awayTeamLabel;
    private JLabel homeTeamLabel;
    private JLabel homeScoreLabel;
    private JLabel awayScoreLabel;
    private JScrollPane scrollPane;

    public ScoreBoardView(ScoreBoardController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createTable();
        createLabels();
        createButtons();

        // create panel for input and buttons
        JPanel inputPanel = new JPanel(new GridLayout(3, 4));
        inputPanel.add(homeTeamLabel);
        inputPanel.add(homeTeamField);
        inputPanel.add(awayTeamLabel);
        inputPanel.add(awayTeamField);
        inputPanel.add(homeScoreLabel);
        inputPanel.add(homeScoreField);
        inputPanel.add(awayScoreLabel);
        inputPanel.add(awayScoreField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(finishButton);
        inputPanel.add(summaryButton);

        // add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        // set window size and visibility
        setSize(600, 400);
        setVisible(true);
    }

    private void createButtons() {
        // Buttons
        addButton = new JButton("Add Game");
        updateButton = new JButton("Update Score");
        finishButton = new JButton("Finish Game");
        summaryButton = new JButton("Games Summary");
        addBtnListener();
        updateBtnListener();
        finishBtnListener();
        summaryBtnListener();
    }

    private void createLabels() {
        // create labels and text fields for input
        homeTeamLabel = new JLabel("Home Team");
        homeTeamField = new JTextField();
        awayTeamLabel = new JLabel("Away Team");
        awayTeamField = new JTextField();
        homeScoreLabel = new JLabel("Home Score");
        homeScoreField = new JTextField();
        awayScoreLabel = new JLabel("Away Score");
        awayScoreField = new JTextField();
    }

    private void createTable() {
        // create table with model
        String[] columnNames = {"Home Team", "Score", "Away Team", "Score"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        gamesTable = new JTable(tableModel);
        // create scroll pane for table
        scrollPane = new JScrollPane(gamesTable);
    }

    private void summaryBtnListener() {
        summaryButton.addActionListener(listener -> {
            summaryTable();
        });
    }

    private void finishBtnListener() {
        finishButton.addActionListener(listener -> {
            int selectedRow = gamesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String homeTeam = (String) gamesTable.getValueAt(selectedRow, 0);
                String awayTeam = (String) gamesTable.getValueAt(selectedRow, 2);
                controller.finishGame(homeTeam, awayTeam);
                refreshTable();
            } else {
                showDialog("Please select the game to finish");
            }
        });
    }

    private void addBtnListener() {
        addButton.addActionListener(listener -> {
            String homeTeam = homeTeamField.getText();
            String awayTeam = awayTeamField.getText();
            if(!homeTeam.isEmpty() && !awayTeam.isEmpty()) {
                controller.startGame(homeTeam, awayTeam);
                resetAddBtn();
                refreshTable();
            } else {
                showDialog("Please fill in both team fields");
            }
        });
    }

    private void resetAddBtn() {
        homeTeamField.setText("");
        awayTeamField.setText("");
    }

    private void updateBtnListener() {
        updateButton.addActionListener(listener -> {
            int selectedRow = gamesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String homeTeam = (String) gamesTable.getValueAt(selectedRow, 0);
                String awayTeam = (String) gamesTable.getValueAt(selectedRow, 2);
                int homeScore;
                int awayScore;
                try {
                    homeScore = Integer.parseInt(homeScoreField.getText());
                    awayScore = Integer.parseInt(awayScoreField.getText());
                } catch (NumberFormatException e) {
                    showDialog("Please enter valid scores");
                    return;
                }
                controller.updateScore(homeTeam, awayTeam, homeScore, awayScore);
                resetUpdateBtn();
                refreshTable();
            } else {
                showDialog("Please select a game to update the score");
            }
        });
    }

    private void resetUpdateBtn() {
        homeScoreField.setText("");
        awayScoreField.setText("");
    }

    private void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    private void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) gamesTable.getModel();
        tableModel.setRowCount(0); //clear table
        for (Game game : controller.getGames()) {
            Object[] row = {game.getHomeTeam(), game.getHomeTeamScore(), game.getAwayTeam(), game.getAwayTeamScore()};
            tableModel.addRow(row);
        }
    }

    private void summaryTable() {
        if(controller.getSummaryByTotalScore().isEmpty()) {
            showDialog ("There are no games to show!");
        } else {
            SummaryBoardView summaryView = new SummaryBoardView(controller);
        }
    }

}
