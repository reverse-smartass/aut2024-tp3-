package com.example.demo1;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public abstract class paymentType {

    private final String text;

    ArrayList<Label> Labels = new ArrayList<>();

    ArrayList<Node> Textboxes = new ArrayList<>();

    public paymentType(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public void addLabel(Label label){
        this.Labels.add(label);
    }

    public void addTextbox(TextField tf){
        this.Textboxes.add(tf);
    }

    public ArrayList<Label> getLabels(){
        return this.Labels;
    }

    public ArrayList<Node> getTextboxes(){
        return this.Textboxes;
    }

    public abstract void initialize();

    //private Label af = new Label("Addresse de facturation:");

    //private TextField afTextField = new TextField();

    /*public void initializeDelivery(){

        this.Labels.add(af);

        this.Textboxes.add(afTextField);
    }

    public TextField getAfTextField(){
        return this.afTextField;
    }*/

}
