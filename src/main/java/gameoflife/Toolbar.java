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

    }

    private void handleStop(ActionEvent actionEvent) {

    }

    private void handleNext(ActionEvent actionEvent) {
        this.view.getBoard().next();
        this.bottombar.setGenCount(this.board.getGenerationcount());
        this.view.draw();
    }

    private void handleClear(ActionEvent actionEvent) {

    }

}
