package org.example.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.example.controller.Controller;
import org.example.data.BMX;
import org.example.data.Bike;
import org.example.data.BikeType;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

public class Main<E> extends Application {
    private Controller<Bike> controller;
    private ObservableList<Bike> bikesObsrv;
    private ListView<Bike> bikesListView;
    private BikeType bikeTypeFilter;
    private ComboBox box;
    private boolean klicked = true;

    final static String txtFile = "Bikes.txt";
    final static String binFile = "Bikes.bin";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        controller = new Controller<>();

        Comparator<Bike> comparator = Comparator.comparing(Bike::getNum);
        Consumer<String> consumer = (s -> new Alert(Alert.AlertType.ERROR, s, ButtonType.OK).show());

        controller.setErrorLog(consumer);
        controller.setComparator(comparator);

        FlowPane flowPane = new FlowPane(createListView(), creatVbox(), creatHbox());
        flowPane.setPrefSize(1080, 700);
        Scene scene = new Scene(flowPane);
        stage.setTitle("Bikes evidence");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public HboxPane creatHbox() {
        HboxPane hboxPane = new HboxPane();
        hboxPane.newButton("Generate", generateAction());
        hboxPane.newButton("Save", saveAction());
        hboxPane.newButton("Load", readAction());
        hboxPane.newLabel("New:");
        hboxPane.newComboBox("New", newComboAction());
        hboxPane.newLabel("Filter:");
        hboxPane.newComboBox("Filter", filterComboAction());
        hboxPane.newButton("Find", findAction());
        hboxPane.newButton("Save bin", saveBinAction());
        hboxPane.newButton("Read bin", readBinAction());
        hboxPane.newButton("Clear", clearAction());
        return hboxPane;
    }


    public VboxPane creatVbox() {
        VboxPane vboxPane = new VboxPane();
        vboxPane.newLabel("Navigation:");
        vboxPane.newButton("First", firstAction());
        vboxPane.newButton("Last", lastAction());
        vboxPane.newButton("Next", nextAction());
        vboxPane.newButton("Before", beforeAction());
        vboxPane.newLabel("Commands:");
        vboxPane.newButton("Show", showAction());
        vboxPane.newButton("Edit", editAction());
        vboxPane.newButton("Remove", removeAction());
        return vboxPane;
    }

    private EventHandler<ActionEvent> clearAction() {
        return actionEvent -> {
            controller.clear();
            obnovList();
        };
    }

    private void obnovList() {
        klicked = false;
        bikesListView.getItems().clear();
        controller.stream().filter(s -> filter(s.getType())).forEach(bikesListView.getItems()::add);
        klicked = true;
    }

    private void obnovList(Bike bike) {
        klicked = false;
        bikesListView.getItems().clear();
        controller.stream().filter(s -> filter(s.getType())).forEach(bikesListView.getItems()::add);
        bikesListView.getSelectionModel().select(bike);
        bikesListView.scrollTo(bike);
        klicked = true;
    }

    private EventHandler<ActionEvent> showAction() {
        return actionEvent -> {
            if (controller.get() != null) {
                //    System.out.println(spravce.dej());
                Stage stage;
                stage = DialogShowEdit.getBikeType(controller.get(), false);
//                stage.setTitle("Aktualni kolo");
                stage.showAndWait();
            }
        };
    }

    private EventHandler<ActionEvent> removeAction() {
        return actionEvent -> {
            if (controller.get() != null) {
                controller.remove();
                obnovList();
            }
        };
    }

    private EventHandler<ActionEvent> editAction() {

        return actionEvent -> {
            if (controller.get() != null) {
                Stage dialog;
                dialog = DialogShowEdit.getBikeType(controller.get(), true);
                dialog.showAndWait();
                if (DialogShowEdit.getVysledek() != null) {
                    controller.edit(DialogShowEdit.getVysledek());
                    obnovList();
                }
            }
        };

    }

    private EventHandler<ActionEvent> lastAction() {
        return actionEvent -> {
            controller.last();
            obnovList(controller.get());
        };

    }

    private EventHandler<ActionEvent> beforeAction() {
        return actionEvent -> {
            if (controller.get() != null) {
                controller.before();
                obnovList(controller.get());
            }
        };

    }

    private EventHandler<ActionEvent> nextAction() {
        return actionEvent -> {
            if (controller.get() != null) {
                controller.next();
                obnovList(controller.get());
            }
        };

    }

    private EventHandler<ActionEvent> firstAction() {
        return actionEvent -> {
            controller.first();
            obnovList(controller.get());
        };

    }

    private EventHandler<ActionEvent> readBinAction() {
        return actionEvent -> {
            controller.readBinFile(binFile);
            obnovList();
        };
    }


    private EventHandler<ActionEvent> saveBinAction() {
        return actionEvent -> {
            controller.saveBinFile(binFile);
        };

    }

    private EventHandler<ActionEvent> findAction() {
        return actionEvent -> {
            if (controller.count() != 0) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Find");
                dialog.setHeaderText("Enter number:");
                dialog.setContentText("Number:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Bike bike = controller.get(new BMX(0, result.get(), null, null, 0, null));
                    if (bike != null) obnovList(bike);
                    else alert("Nothin is found");
                }
            } else alert("List is empty");
        };

    }

    private EventHandler<ActionEvent> filterComboAction() {
        return actionEvent -> {
            BikeType typ = ((ComboBox<BikeType>) actionEvent.getSource()).getValue();
            if (typ != null) {
                bikeTypeFilter = typ;
            }
            if (typ == BikeType.ALL) bikeTypeFilter = null;
            obnovList();
            box = (ComboBox) actionEvent.getSource();
        };

    }

    private EventHandler<ActionEvent> newComboAction() {

        return actionEvent -> {
            if (controller.count() != 0) {
                Bike bike = null;
                BikeType types = ((ComboBox<BikeType>) actionEvent.getSource()).getValue();
                if (types == null) return;
                Stage dialog;
                dialog = DialogNew.getBikeType(types, controller.count());
                dialog.showAndWait();
                if (DialogNew.getResult() != null) {
                    controller.addNew(DialogNew.getResult());
                    obnovList();
                }
            }
        };

    }

    private EventHandler<ActionEvent> readAction() {
        return actionEvent -> {
            controller.readTextFile(txtFile);
            obnovList();
        };

    }

    private EventHandler<ActionEvent> saveAction() {
        return actionEvent -> {
            controller.saveTextFile(txtFile);
        };

    }

    private EventHandler<ActionEvent> generateAction() {
        return actionEvent -> {
            TextInputDialog dlg = new TextInputDialog("");
            dlg.setTitle("Generate");
            dlg.setHeaderText("Enter count");
            dlg.setContentText("Count:");
            Optional<String> vysledek = dlg.showAndWait();
            try {
                if (vysledek.isPresent()) {
                    controller.generateData(Integer.parseInt(vysledek.get()));
                    obnovList();
                }
            } catch (NumberFormatException e) {
                alert("Input value is wrong or empty");
            }
        };

    }

    private ListView<Bike> createListView() {
        bikesObsrv = FXCollections.observableArrayList();
        bikesListView = new ListView(bikesObsrv);
        bikesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        bikesListView.setPrefSize(980, 680);
        MultipleSelectionModel<Bike> multipleSelectionModel = bikesListView.getSelectionModel();
        multipleSelectionModel.selectedItemProperty().addListener((observableValue, letadlo, t1) -> {
            if (klicked) controller.current(t1);
        });


        return bikesListView;
    }

    private void alert(String name) {
        new Alert(Alert.AlertType.ERROR, name, ButtonType.OK).show();
    }

    private boolean filter(BikeType obj) {
        if (obj == bikeTypeFilter || bikeTypeFilter == null) return true;
        else return false;
    }
}
