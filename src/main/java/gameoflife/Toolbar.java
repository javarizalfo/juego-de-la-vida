package gameoflife;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
    // === === === === === FIELDS === === === === ===//
    private View view;
    private Board board;
    private Bottombar bottombar;
    private Button start, stop, next, clear;

    // === === === === === CONSTRUCTOR === === === === ===//
    public Toolbar(View view, Board board, Bottombar bottombar) {
        this.view = view;
        this.board = board;
        this.bottombar = bottombar;

        this.start = new Button("Start");
        start.setOnAction(this::handleStart);

        this.stop = new Button("Stop");
        stop.setOnAction(this::handleStop);

        this.next = new Button("Next");
        next.setOnAction(this::handleNext);

        this.clear = new Button("Clear");
        clear.setOnAction(this::handleClear);


        this.getItems().addAll(this.start, this.stop, this.next, this.clear);
    }

    // === === === === === METHODS === === === === ===//
    private void handleStart(ActionEvent actionEvent) {
        this.view.setAppstate(View.RUNNING);
        this.view.getSimulator().start();
    }

    private void handleStop(ActionEvent actionEvent) {
        this.view.setAppstate(View.EDITING);
        this.view.getSimulator().stop();
        this.view.setInitialboard(this.view.getBoard());
        this.view.draw();
    }

    private void handleNext(ActionEvent actionEvent) {
        this.view.setAppstate(View.RUNNING);

        this.view.getBoard().next();
        this.view.setInitialboard(this.view.getBoard());
        this.bottombar.setGenCount(this.board.getGenerationcount());
        this.view.draw();

        this.view.setAppstate(View.EDITING);
    }

    private void handleClear(ActionEvent actionEvent) {
        Board blankBoard = new Board(26, 26);
        this.view.setAppstate(View.EDITING);
        this.view.setInitialboard(blankBoard);
        this.view.draw();
    }

}
