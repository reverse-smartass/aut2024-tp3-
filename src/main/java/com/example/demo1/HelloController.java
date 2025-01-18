package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Objects;

public class HelloController {

    private HelloApplication application;


    public HelloController (HelloApplication app){
        this.application = app;
    }

    private CheckBox checkBox = new CheckBox("L'adresse de facturation est la" +
            " meme que l'adresse de livraison");

    private Label deliveryMode = new Label("Option de livraison:");


    private ObservableList<String> options =
            FXCollections.observableArrayList(new String[]{"Livraison en main" +
                    " propre", "Se retrouver à l’extérieur",
                     "Laisser à la porte"});

    private ObservableList<String> options2 =
            FXCollections.observableArrayList(new String[]{"Livraison en main" +
                    " propre", "Se retrouver à l’extérieur"});

    private ComboBox<String> deliveryOptions = new ComboBox<>(options);

    private Label af = new Label("Addresse de facturation:");

    private TextField afTextField = new TextField();



    public void mediatePayment(paymentType type, GridPane grid) {
        grid.getChildren().remove(checkBox);
        grid.getChildren().remove(deliveryMode);
        grid.getChildren().remove(deliveryOptions);
        grid.getChildren().remove(af);
        grid.getChildren().remove(afTextField);
        for (int i = 0; i < type.getLabels().size()+3; i++) {
            if (i < type.getLabels().size()){
                grid.add(type.getLabels().get(i), 0, (i)+3);
                grid.add(type.getTextboxes().get(i), 1, (i)+3);
                application.getPaymentComponents().add(type.getLabels().get(i));
                application.getPaymentComponents().add(type.getTextboxes().get(i));
            }
            else if (i == type.getLabels().size()){
                grid.add(this.checkBox, 0, (i)+4,2,1);
            } else if (i == type.getLabels().size()+1) {
                grid.add(this.af,0, i+5);
                grid.add(this.afTextField, 1, i+5);
            } else {
                grid.add(this.deliveryMode,0, i+6);
                grid.add(this.deliveryOptions, 1, i+6);
            }
        }
    }

    public void mediateCheckbox(TextField adTextField){
        if(this.checkBox.isSelected()){
            adTextField.textProperty().bindBidirectional(this.afTextField.textProperty());
            this.afTextField.setEditable(false);
        }
        else {
            adTextField.textProperty().unbindBidirectional(this.afTextField.textProperty());
            this.afTextField.setEditable(true);
        }
    }

    public void mediateDelivery(paymentType type, GridPane grid){
        if(type instanceof paymentHand){
            this.deliveryOptions.setItems(options2);
        } else {
            this.deliveryOptions.setItems(options);
        }
    }

    public CheckBox getCheckbox(){
        return this.checkBox;
    }

    public ComboBox<String> getDeliveryOptions() {
        return this.deliveryOptions;
    }
}