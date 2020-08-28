package gameoflife;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class Toolbar extends ToolBar {
    // === === === === === FIELDS === === === === ===//
    private View view;
    private Board board;
    private Bottombar bottombar;
    private Button start, stop, next, clear, sampleGlider, sampleSpaceship, sampleAcorn, greenCell;

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

        this.sampleGlider = new Button("Sample Glider");
        sampleGlider.setOnAction(this::handleSampleGlider);

        this.sampleSpaceship = new Button("Sample Spaceship");
        sampleSpaceship.setOnAction(this::handleSampleSpaceship);

        this.sampleAcorn = new Button("Sample Acorn");
        sampleAcorn.setOnAction(this::handleSampleAcorn);

        this.greenCell = new Button("Green Cells");
        greenCell.setOnAction(this::handleGreenCell);

        this.getItems().addAll(this.start, this.stop, this.next, this.clear, new Separator(),
                this.sampleGlider, this.sampleSpaceship, this.sampleAcorn, new Separator(),
                this.greenCell);
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

        this.board.setGenerationcount(this.view.getInitialboard().getGenerationcount());
        this.bottombar.setGenCount(this.board.getGenerationcount());
        this.view.draw();

        this.view.setAppstate(View.EDITING);
    }

    private void handleClear(ActionEvent actionEvent) {
        Board blankBoard = new Board(26, 26, 0);
        this.view.setAppstate(View.EDITING);
        this.view.setInitialboard(blankBoard);
        this.board.setGenerationcount(0);
        this.bottombar.setGenCount(0);
        this.view.draw();
    }

    private void handleSampleGlider(ActionEvent actionEvent) {
        Board newBoard = new Board(26, 26, 0);

        newBoard.setAlive(12, 12);
        newBoard.setAlive(13, 13);
        newBoard.setAlive(13, 14);
        newBoard.setAlive(13, 14);
        newBoard.setAlive(12, 14);
        newBoard.setAlive(11, 14);

        this.board.setGenerationcount(0);
        this.bottombar.setGenCount(0);
        this.view.setInitialboard(newBoard);
        this.view.draw();
    }

    private void handleSampleSpaceship(ActionEvent actionEvent) {
        Board newBoard = new Board(26, 26, 0);

        newBoard.setAlive(12, 12);
        newBoard.setAlive(15, 12);
        newBoard.setAlive(11, 13);
        newBoard.setAlive(11, 14);
        newBoard.setAlive(11, 15);
        newBoard.setAlive(12, 15);
        newBoard.setAlive(13, 15);
        newBoard.setAlive(14, 15);
        newBoard.setAlive(15, 14);

        this.board.setGenerationcount(0);
        this.bottombar.setGenCount(0);
        this.view.setInitialboard(newBoard);
        this.view.draw();
    }

    private void handleSampleAcorn(ActionEvent actionEvent) {
        Board newBoard = new Board(26, 26, 0);

        newBoard.setAlive(12, 12);
        newBoard.setAlive(14, 13);
        newBoard.setAlive(12, 14);
        newBoard.setAlive(11, 14);
        newBoard.setAlive(15, 14);
        newBoard.setAlive(16, 14);
        newBoard.setAlive(17, 14);

        this.board.setGenerationcount(0);
        this.bottombar.setGenCount(0);
        this.view.setInitialboard(newBoard);
        this.view.draw();
    }

    private void handleGreenCell(ActionEvent actionEvent) {
        this.view.setColor(Color.GREEN);
        this.view.draw();
    }

}
