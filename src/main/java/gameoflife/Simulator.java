package gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator {
    private Timeline timeline;
    private View view;
    private Board board;

    public Simulator(View view, Board board) {
        this.view = view;
        this.board = board;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void doStep(ActionEvent actionEvent) {
        this.board.next();
        this.view.draw();
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

}
