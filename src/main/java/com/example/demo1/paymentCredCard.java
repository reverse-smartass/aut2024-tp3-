package com.example.demo1;


import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class paymentCredCard extends paymentType{


    public paymentCredCard(String text) {
        super(text);
    }

    @Override
    public void initialize() {
        Label cc = new Label("Numéro de carte de crédit:");
        this.Labels.add(cc);


        TextField ccTextField = new TextField();
        this.Textboxes.add(ccTextField);



        Label pw = new Label("Date d'expiration de la carte:");
        this.Labels.add(pw);


        DatePicker dtBox = new DatePicker();
        this.Textboxes.add(dtBox);

        Label csc = new Label("Code de securite de la carte:");
        this.Labels.add(csc);


        TextField cscTextField = new TextField();
        this.Textboxes.add(cscTextField);
    }
}
