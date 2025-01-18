package com.example.demo1;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class paymentGiftCard extends paymentType{


    public paymentGiftCard(String text) {
        super(text);
    }

    @Override
    public void initialize() {
        Label cc = new Label("Numéro de carte de cadeau:");
        this.Labels.add(cc);


        TextField ccTextField = new TextField();
        this.Textboxes.add(ccTextField);
    }
}
