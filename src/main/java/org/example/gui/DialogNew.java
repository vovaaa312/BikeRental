package org.example.gui;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.data.*;
import org.example.data.Color;

import java.awt.*;


public class DialogNew extends Stage {
    private BikeType type;
    private TextField id;
    private TextField num;
    private TextField name;
    private ComboBox<Color> colorComboBox;
    private TextField mass;
    private int count;
    private static Color colorList;
    private static Bike bike;

    public static DialogNew getBikeType(BikeType type, int count) {
        return new DialogNew(type, count);
    }

    private DialogNew(BikeType typ, int count) {
        this.type = typ;
        this.count = count;
        setTitle("New bike");
        initModality(Modality.APPLICATION_MODAL);
        setScene(getScene1());
    }

    private Scene getScene1() {
        VBox vBox = new VBox(12);
        vBox.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(7);
        gridPane.setVgap(20);
        vBox.getChildren().addAll(gridPane);
        int line = 0;
        id = addRow(gridPane, "Id", line++);
        id.setText(String.valueOf(++count));
        id.setEditable(false);
        num = addRow(gridPane, "Num", line++);
        name = addRow(gridPane, "Name", line++);
        colorComboBox = addRowComboBox(gridPane, "Color", line++, colorList);
        mass = addRow(gridPane, "Mass", line++);
        Button buttonSave = new Button("Ok");
        buttonSave.setPrefSize(75, 25);
        buttonSave.setOnAction(e -> {
            update();
            hide();
        });
        gridPane.add(buttonSave, 0, ++line);
        Button buttonCancel = new Button("Cancel");
        gridPane.add(buttonCancel, 1, line);
        buttonCancel.setOnAction(e -> hide());
        buttonCancel.setPrefSize(75, 25);
        return new Scene(vBox);

    }

    private void update() {
        try {
            if (check()) {
                switch (type) {
                    case BMX -> bike = new BMX(Integer.parseInt(id.getText()), num.getText(), name.getText(), colorComboBox.getValue(), Double.parseDouble(mass.getText()), BikeType.BMX);
                    case MOUNTAIN -> bike = new Mountain(Integer.parseInt(id.getText()), num.getText(), name.getText(), colorComboBox.getValue(), Double.parseDouble(mass.getText()), BikeType.MOUNTAIN);
                    case TREK -> bike = new Trek(Integer.parseInt(id.getText()), num.getText(), name.getText(), colorComboBox.getValue(), Double.parseDouble(mass.getText()), BikeType.TREK);
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Zadejte cislo", ButtonType.OK).show();
        }

    }

    private static TextField addRow(GridPane grid, String name, int line) {
        grid.add(new Label(name), 0, line);
        TextField textField = new TextField();
        grid.add(textField, 1, line);
        return textField;
    }

    private static <T> ComboBox<T> addRowComboBox(GridPane grid, String name, int line, Color enumList) {
        grid.add(new Label(name), 0, line);
        ComboBox<T> comboBox = new ComboBox(FXCollections.observableArrayList(colorList.values()));
        comboBox.setValue((T) Color.BLACK);
        grid.add(comboBox, 1, line);
        return comboBox;
    }

    public static Bike getResult() {
        return bike;
    }

    private boolean check() {
        if (!(num.getText() == null || num.getText().equals("") || name.getText() == null || name.getText().equals("") || mass.getText() == null || mass.getText().equals("")))
            return true;
        else {
            new Alert(Alert.AlertType.ERROR, "Zadejte vsechny polozky", ButtonType.OK).show();
            return false;
        }
    }
}
