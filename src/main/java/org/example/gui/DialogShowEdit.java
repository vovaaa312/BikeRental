package org.example.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.data.*;

public class DialogShowEdit extends Stage {
    private TextField id;
    private TextField num;
    private TextField name;
    private ComboBox<Color> color;
    private TextField mass;
    private static Color colorList;
    private static Bike bike;
    private static boolean editable;
    private final Bike bikeEdit;


    public static DialogShowEdit getBikeType(Bike _bikeEdit, boolean _editable) {
        return new DialogShowEdit(_bikeEdit, _editable);
    }

    private DialogShowEdit(Bike _bikeEdit, boolean _editable) {
        DialogShowEdit.editable = _editable;
        this.bikeEdit = _bikeEdit;
        if (_editable) setTitle("Edit");
        else setTitle("Current");
        initModality(Modality.APPLICATION_MODAL);
        setScene(get_Scene());
    }

    private Scene get_Scene() {
        VBox vBox = new VBox(12);
        vBox.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(7);
        gridPane.setVgap(20);
        vBox.getChildren().addAll(gridPane);
        int radek = 0;
        id = addRow(gridPane, "Id", radek++);
        id.setText(String.valueOf(bikeEdit.getId()));
        id.setEditable(false);
        num = addRow(gridPane, "Num", radek++);
        num.setText(bikeEdit.getNum());
        name = addRow(gridPane, "Name", radek++);
        name.setText(bikeEdit.getName());
        color = addRowComboBox(gridPane, "Color", radek++, colorList);
        color.setValue(bikeEdit.getColour());
        color.setEditable(false);
        mass = addRow(gridPane, "Mass", radek++);
        mass.setText(String.valueOf(bikeEdit.getMass()));
        if (editable) {
            Button buttonOk = new Button("Ok");
            buttonOk.setPrefSize(75, 25);
            buttonOk.setOnAction(e -> {
                update();
                hide();
            });
            gridPane.add(buttonOk, 0, ++radek);
            Button buttonCancel = new Button("Cancel");
            gridPane.add(buttonCancel, 1, radek);
            buttonCancel.setOnAction(e -> hide());
            buttonCancel.setPrefSize(75, 25);
        }
        return new Scene(vBox);

    }

    private void update() {
        try {
            if (check()) {
                switch (bikeEdit.getType()) {
                    case BMX -> bike = new BMX(Integer.parseInt(id.getText()), num.getText(), name.getText(), color.getValue(), Double.parseDouble(mass.getText()), BikeType.BMX);
                    case MOUNTAIN -> bike = new Mountain(Integer.parseInt(id.getText()), num.getText(), name.getText(), color.getValue(), Double.parseDouble(mass.getText()), BikeType.MOUNTAIN);
                    case TREK -> bike = new Trek(Integer.parseInt(id.getText()), num.getText(), name.getText(), color.getValue(), Double.parseDouble(mass.getText()), BikeType.TREK);
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Zadejte cislo", ButtonType.OK).show();
        }

    }

    private TextField addRow(GridPane grid, String nazev, int radek) {
        grid.add(new Label(nazev), 0, radek);
        TextField textField = new TextField();
        textField.setEditable(editable);
        grid.add(textField, 1, radek);
        return textField;
    }

    private static <T> ComboBox<T> addRowComboBox(GridPane grid, String name, int radek, Color enumList) {
        grid.add(new Label(name), 0, radek);
        ComboBox<T> comboBox = new ComboBox(
                FXCollections.observableArrayList(Color.values()));
        comboBox.setEditable(editable);
        grid.add(comboBox, 1, radek);
        return comboBox;
    }

    public static Bike getVysledek() {
        return bike;
    }

    private boolean check() {
        if (!(num.getText() == null || num.getText().equals("") || name.getText() == null || name.getText().equals("") || mass.getText() == null || mass.getText().equals("")))
            return true;
        else {
            String nulovaPolozka = "";
            if(num.getText() == null || num.getText().equals("")){
                nulovaPolozka+= "cislo\n";
            }
            if(name.getText() == null || name.getText().equals("") ){
                nulovaPolozka+= "nazev\n";
            }
            if(mass.getText() == null || mass.getText().equals("")){
                nulovaPolozka+= "hmotnost\n";
            }
            new Alert(Alert.AlertType.ERROR, "Zadejte vsechny polozky:\n" + nulovaPolozka, ButtonType.OK).show();
            return false;
        }
    }

}

