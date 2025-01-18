package com.example.demo1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {

    private ArrayList<Node> paymentComponents = new ArrayList<>();

    private ArrayList<Object> deliveryComponents = new ArrayList<>();



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Paiement de la commande");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 325);
        primaryStage.setScene(scene);


        Text scenetitle = new Text("Formulaire du paiement");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        paymentCredCard credCard = new paymentCredCard("Carte de crédit");
        paymentGiftCard giftCard = new paymentGiftCard("Carte cadeau");
        paymentHand hand = new paymentHand("Paiement a la livraison");

        credCard.initialize();
        giftCard.initialize();
        hand.initialize();

        ObservableList<String> options = FXCollections.observableArrayList(new String[]{credCard.getText(),
                giftCard.getText(),hand.getText()});

        //mode de paiement

        Label paymentMode = new Label("Mode de paiement:");
        grid.add(paymentMode, 0, 1);

        ComboBox<String> paymentOptions = new ComboBox<>(options);
        grid.add(paymentOptions, 1,1);

        Label al = new Label("Addresse de livraison:");

        TextField adTextField = new TextField();

        grid.add((Node) al, 0, 2);
        grid.add((Node) adTextField, 1, 2);

        HelloController controller = new HelloController(this);



        paymentOptions.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for (Object paymentComponent : paymentComponents) {
                    //implementation possible
                    /*if(paymentComponent instanceof TextField){
                        ((TextField) paymentComponent).setText("");
                    }*/
                    grid.getChildren().remove(paymentComponent);
                }
                //implementation possible
                //adTextField.setText("");
                controller.getCheckbox().setSelected(false);
                paymentType type;
                if (paymentOptions.getValue().equals(credCard.getText())){
                    type = credCard;
                }else if(paymentOptions.getValue().equals(hand.getText())) {
                    type = hand;
                }else{
                    type = giftCard;
                }
                controller.mediatePayment(type, grid);
                controller.mediateDelivery(type, grid);
            }
        });


        controller.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                paymentType type;
                if (paymentOptions.getValue().equals(credCard.getText())){
                    type = credCard;
                }else if(paymentOptions.getValue().equals(hand.getText())) {
                    type = hand;
                }else{
                    type = giftCard;
                }
                controller.mediateCheckbox(adTextField);
            }
        });

        controller.getDeliveryOptions().valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                paymentType type;
                if (paymentOptions.getValue().equals(credCard.getText())){
                    type = credCard;
                }else if(paymentOptions.getValue().equals(hand.getText())) {
                    type = hand;
                }else{
                    type = giftCard;
                }
                controller.mediateDelivery(type, grid);

                boolean aMain =
                        (controller.getDeliveryOptions().getValue().equals(
                                "Laisser à la porte"));


                for (Object paymentComponent : paymentComponents) {
                    grid.getChildren().remove(paymentComponent);
                }

                controller.mediatePayment(aMain? hand : type, grid);
            }
        });


        primaryStage.show();
    }

    public ArrayList<Node> getPaymentComponents(){
        return this.paymentComponents;
    }

    public static void main(String[] args) {
        launch();
    }
}