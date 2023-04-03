package org.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreBoardView extends JFrame {

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

    public ScoreBoardView() {
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

    public void resetAddBtn() {
        homeTeamField.setText("");
        awayTeamField.setText("");
    }

    public void resetUpdateBtn() {
        homeScoreField.setText("");
        awayScoreField.setText("");
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public JTable getGamesTable() {
        return gamesTable;
    }

    public JTextField getHomeTeamField() {
        return homeTeamField;
    }

    public JTextField getAwayTeamField() {
        return awayTeamField;
    }

    public JTextField getHomeScoreField() {
        return homeScoreField;
    }

    public JTextField getAwayScoreField() {
        return awayScoreField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public JButton getSummaryButton() {
        return summaryButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JLabel getAwayTeamLabel() {
        return awayTeamLabel;
    }

    public JLabel getHomeTeamLabel() {
        return homeTeamLabel;
    }

    public JLabel getHomeScoreLabel() {
        return homeScoreLabel;
    }

    public JLabel getAwayScoreLabel() {
        return awayScoreLabel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }


}
