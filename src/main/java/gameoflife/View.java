package gameoflife;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class View extends VBox {
    // === === === === === FIELDS === === === === ===//
    public static final int EDITING = 0;
    public static final int RUNNING = 1;
    private Canvas canvas;
    private Affine affine;

    private Board board;
    private Board initialboard;
    private Simulator simulator;

    private Toolbar toolbar;
    private Bottombar bottombar;

    private int alive;
    private int dead;
    private int appstate = EDITING;

    // === === === === === CONSTRUCTOR === === === === ===//
    public View() {
        this.canvas = new Canvas(500, 500);
        this.affine = new Affine();
        this.initialboard = new Board(26, 26);
        this.board = Board.copyBoard(initialboard);
        this.bottombar = new Bottombar();
        this.toolbar = new Toolbar(this, this.board, this.bottombar);
        this.alive = 1;
        this.dead = 0;

        this.canvas.setOnMousePressed(this::handleFill);

        this.getChildren().addAll(this.toolbar, this.canvas, this.bottombar);

        this.bottombar.setGenCount(this.board.getGenerationcount());

        this.affine.appendScale(500 / 26f, 500 / 26f);

        this.initialboard.setAlive(12, 12);
        this.initialboard.setAlive(13, 13);
        this.initialboard.setAlive(13, 14);
        this.initialboard.setAlive(13, 14);
        this.initialboard.setAlive(12, 14);
        this.initialboard.setAlive(11, 14);
    }

    // === === === === === GETTERS/SETTERS === === === === ===//
    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Affine getAffine() {
        return affine;
    }

    public void setAffine(Affine affine) {
        this.affine = affine;
    }

    public void setAppstate(int appstate) {
        if (appstate == this.appstate) {
            return;
        }

        if (appstate == RUNNING) {
            this.board = Board.copyBoard(this.initialboard);
            this.simulator = new Simulator(this, this.board);
        }

        this.appstate = appstate;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    // === === === === === METHODS === === === === ===//
    public void draw() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setTransform(affine);
        graphics.setFill(Color.GRAY);
        graphics.fillRect(0, 0, 500, 500);

        if (this.appstate == EDITING) {
            drawBoard(this.initialboard);
        } else {
            drawBoard(this.board);
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

    public void drawBoard(Board boardToDraw) {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setFill(Color.BLACK);
        for (int x = 0; x < boardToDraw.getWidth(); x++) {
            for (int y = 0; y < boardToDraw.getHeight(); y++) {
                if (boardToDraw.isCellAlive(x, y) == alive)
                    graphics.fillRect(x, y, 1, 1);
            }
        }
    }

    public void handleFill(MouseEvent event) {
        double mousex = event.getX();
        double mousey = event.getY();

        if (this.appstate == RUNNING) {
            return;
        }

        try {
            Point2D coordinates = this.affine.inverseTransform(mousex, mousey);

            int xcoord = (int) coordinates.getX();
            int ycoord = (int) coordinates.getY();

            if (this.initialboard.isCellAlive(xcoord, ycoord) == dead) {
                this.initialboard.setAlive(xcoord, ycoord);
                draw();
            } else {
                this.initialboard.setDead(xcoord, ycoord);
                draw();
            }

        } catch (NonInvertibleTransformException exception) {
            System.out.println("The Transform could not be inverted");
        }
    }
}
