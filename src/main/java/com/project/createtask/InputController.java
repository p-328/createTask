package com.project.createtask;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import java.util.ArrayList;

public class InputController {
    @FXML
    private TextField inputText;
    private final ArrayList<String> words = Words.getWords();

    @FXML
    protected void printInput() {
        System.out.println(inputText.getText());
        inputText.clear();
    }
}