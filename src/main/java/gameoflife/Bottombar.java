package gameoflife;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Bottombar extends HBox {
    // === === === === === FIELDS === === === === ===//
    private static String generationcountformat = "Generation: %d";

    private Label generationcount;

    // === === === === === CONSTRUCTOR === === === === ===//
    public Bottombar() {
        this.generationcount = new Label();

        this.getChildren().add(generationcount);
    }

    public void setGenCount(int count) {
        this.generationcount.setText(String.format(generationcountformat, count));
    }

}
