package gameoflife;

import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

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

        this.canvas.setOnMousePressed(this::handleFill);

        this.getChildren().addAll(this.nextButton, this.canvas);

        this.affine.appendScale(500 / 26f, 500 / 26f);

        this.board.setAlive(12, 12);
        this.board.setAlive(13, 13);
        this.board.setAlive(13, 14);
        this.board.setAlive(13, 14);
        this.board.setAlive(12, 14);
        this.board.setAlive(11, 14);

        this.board.setAlive(2, 1);
        this.board.setAlive(2, 2);
        this.board.setAlive(2, 3);
    }

    // === === === === === METHODS === === === === ===//
    public void draw() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setTransform(affine);
        graphics.setFill(Color.GRAY);
        graphics.fillRect(0, 0, 500, 500);

        graphics.setFill(Color.BLACK);
        for (int x = 0; x < this.board.getWidth(); x++) {
            for (int y = 0; y < this.board.getHeight(); y++) {
                if (this.board.isCellAlive(x, y) == 1)
                    graphics.fillRect(x, y, 1, 1);
            }
        }

        graphics.setStroke(Color.DARKGRAY);
        graphics.setLineWidth(0.05f);
        for (int x = 0; x <= this.board.getWidth(); x++) {
            graphics.strokeLine(x, 0, x, 26);
        }
        for (int y = 0; y <= this.board.getHeight(); y++) {
            graphics.strokeLine(0, y, 26, y);
        }
    }

    public void handleFill(MouseEvent event) {
        double mousex = event.getX();
        double mousey = event.getY();

        try {
            Point2D coordinates = this.affine.inverseTransform(mousex, mousey);

            int xcoord = (int) coordinates.getX();
            int ycoord = (int) coordinates.getY();

            if (this.board.isCellAlive(xcoord, ycoord) == 0) {
                this.board.setAlive(xcoord, ycoord);
                draw();
            } else {
                this.board.setDead(xcoord, ycoord);
                draw();
            }

        } catch (NonInvertibleTransformException exception) {
            System.out.println("The Transform could not be inverted");
        }
    }
}