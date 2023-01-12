package com.project.createtask;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.util.ArrayList;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;
public class InputController implements Initializable {
    @FXML
    private Label incorrectGuess;
    @FXML
    private TextField inputText;
    private int mistakes = 0;
    private final ArrayList<String> words = Words.getWords();
    private String wordGuess;
    @FXML
    private VBox gameBox;
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        wordGuess = words.get((int)(Math.random() * words.size()));
        System.out.println(wordGuess);
    }
    @FXML
    protected void onSubmit() {
        System.out.println(inputText.getText());
        inputText.clear();
    }
}