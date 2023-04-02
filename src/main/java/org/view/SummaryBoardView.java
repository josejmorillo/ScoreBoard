package org.view;

import org.controller.ScoreBoardController;
import org.model.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SummaryBoardView extends JFrame {
    private final ScoreBoardController controller;
    private JTable summaryTable;
    private JScrollPane scrollPane;
    private JPanel btnPanel;
    private JButton backButton;

    public SummaryBoardView(ScoreBoardController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Summary Board");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createTable();
        createBtnPanel();
        contentPane();
        loadData();
    }

    private void createTable() {
        String[] columnNames = {"Home Team", "Score", "Away Team", "Score"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        summaryTable = new JTable(tableModel);
        scrollPane = new JScrollPane(summaryTable);
    }

    private void createBtnPanel() {
        btnPanel = new JPanel(new GridLayout(1, 1));
        // Buttons
        backButton = new JButton("Back");
        btnPanel.add(backButton);
        backBtnListener();
    }

    private void contentPane() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(btnPanel, BorderLayout.SOUTH);

        // set window size and visibility
        setSize(600, 400);
        setVisible(true);
    }


    private void loadData() {
        DefaultTableModel tableModel = (DefaultTableModel) summaryTable.getModel();
        tableModel.setRowCount(0); //clear table
        for (Game game : controller.getSummaryByTotalScore()) {
            Object[] row = {game.getHomeTeam(), game.getHomeTeamScore(), game.getAwayTeam(), game.getAwayTeamScore()};
            tableModel.addRow(row);
        }
    }

    private void backBtnListener() {
        backButton.addActionListener(listener -> {
            dispose();
        });
    }

}
