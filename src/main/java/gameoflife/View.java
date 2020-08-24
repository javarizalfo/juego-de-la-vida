package gameoflife;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class View extends VBox {
    // === === === === === FIELDS === === === === ===//
    private Button nextButton;
    private Canvas canvas;
    private Board board;
    private Affine affine;

    // === === === === === CONSTRUCTOR === === === === ===//
    public View() {
        this.nextButton = new Button("Next");
        this.canvas = new Canvas(500, 500);
        this.board = new Board(26, 26);
        this.affine = new Affine();

        this.nextButton.setOnAction(actionEvent -> {
            board.next();
            draw();
        });

        this.getChildren().addAll(this.nextButton, this.canvas);

        this.affine.appendScale(500 / 26f, 500 / 26f);

        this.board.setAlive(12, 12);
        this.board.setAlive(13, 13);
        this.board.setAlive(13, 14);
        this.board.setAlive(13, 14);
        this.board.setAlive(12, 14);
        this.board.setAlive(11, 14);
    }

    // === === === === === METHODS === === === === ===//
    public void draw() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setTransform(affine);
        graphics.setFill(Color.GRAY);
        graphics.fillRect(0, 0, 500, 500);

        graphics.setFill(Color.BLACK);
        for (int x = 0; x < this.board.width; x++) {
            for (int y = 0; y < this.board.height; y++) {
                if (this.board.isCellAlive(x, y) == 1)
                    graphics.fillRect(x, y, 1, 1);
            }
        }

        graphics.setStroke(Color.DARKGRAY);
        graphics.setLineWidth(0.05f);
        for (int x = 0; x <= this.board.width; x++) {
            graphics.strokeLine(x, 0, x, 26);
        }
        for (int y = 0; y <= this.board.height; y++) {
            graphics.strokeLine(0, y, 26, y);
        }
    }

}