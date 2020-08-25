package gameoflife;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private View view;

    public Toolbar(View view) {
        this.view = view;

        Button start = new Button("Start");
        start.setOnAction(this::handleStart);

        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);

        Button next = new Button("Next");
        next.setOnAction(this::handleNext);


        this.getItems().addAll(start, stop, next);
    }

    private void handleStart(ActionEvent actionEvent) {

    }

    private void handleStop(ActionEvent actionEvent) {

    }

    private void handleNext(ActionEvent actionEvent) {
        this.view.getBoard().next();
        this.view.draw();
    }

}
