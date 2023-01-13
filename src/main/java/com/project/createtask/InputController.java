package com.project.createtask;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.util.ArrayList;

import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InputController implements Initializable {
    @FXML
    private Label guessResult;
    @FXML
    private TextField inputText;
    private final int attemptsAllowed = 10;
    private int mistakes = 0;
    private final ArrayList<String> words = Words.getWords();
    private String wordGuess;
    @FXML
    private Label wordDisplay;
    @FXML
    private Label mistakeMsg;
    @FXML
    private VBox gameBox;
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        wordGuess = words.get((int)(Math.random() * words.size()));
        mistakeMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
        StringBuilder initialString = new StringBuilder();
        for (int i = 0; i < wordGuess.length(); i++) {
            initialString.append("_");
        }
        wordDisplay.setText(initialString.toString());
    }
    private static void gameOver() {
      guessResult.setText("Game over!");
      wordDisplay.setText(wordGuess);
      inputText.clear();
    }
    @FXML
    protected int submitLetter() {
        if (Objects.equals(inputText.getText(), wordGuess)) {
            guessResult.setText("You win!");
            inputText.clear();
            return 0;
        }
        if (mistakes > attemptsAllowed) {
            InputController.gameOver();
            return 0;
        }
        if (Objects.equals(inputText.getText(), "")) {
            guessResult.setText("You need to input a letter to guess a letter, fool!");
            inputText.clear();
            return 1;
        }
        char letter = inputText.getText().toCharArray()[0];
        ArrayList<Integer> letterIndices = Words.findInstancesOfChar(wordGuess, letter);
        if (letterIndices.size() == 0) {
            guessResult.setText(String.format("No characters found of %c", letter));
            inputText.clear();
            mistakes++;
            mistakeMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
            return 0;
        }
        guessResult.setText("Letters found!");
        char[] displayText = wordDisplay.getText().toCharArray();
        for (var i : letterIndices) {
            displayText[i] = letter;
        }
        StringBuilder newDisplay = new StringBuilder();
        for (char c : displayText) {
            newDisplay.append(c);
        }
        wordDisplay.setText(newDisplay.toString());
        inputText.clear();
        return 0;
    }
    @FXML
    protected int submitWord() {
        if (mistakes > attemptsAllowed) {
            InputController.gameOver();
            return 0;
        }
        if (Objects.equals(inputText.getText(), "")) {
            guessResult.setText("You need to input a word to guess a word, fool!");
            inputText.clear();
            return 1;
        }
        String guessedWord = inputText.getText();
        if (Objects.equals(guessedWord, wordGuess)) {
            guessResult.setText("You win!");
            wordDisplay.setText(wordGuess);
            inputText.clear();
            return 0;
        }
        guessResult.setText("That's not the word!");
        mistakes++;
        mistakeMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
        inputText.clear();
        return 0;
    }
}
