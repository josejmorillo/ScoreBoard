package org.controller;

import org.view.ScoreBoardView;

public class App {

    public static void main(String[] args) {
        ScoreBoardController controller = new ScoreBoardController();
        ScoreBoardView view = new ScoreBoardView(controller);
    }
}
