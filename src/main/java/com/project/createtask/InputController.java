package com.project.createtask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
    private Label livesMsg;
    @FXML
    private Label guessedLetters;
    @FXML
    private VBox gameBox;
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        assert words != null;
        wordGuess = words.get((int)(Math.random() * words.size()));
        livesMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
        StringBuilder initialString = new StringBuilder();
        for (int i = 0; i < wordGuess.length(); i++) {
            initialString.append("_");
        }
        wordDisplay.setText(initialString.toString());
    }
    private void makeResetButton() {
        Button resetBtn = new Button("Reset");
        gameBox.getChildren().add(resetBtn);
        resetBtn.setOnAction(actionEvent -> {
            mistakes = 0;
            wordGuess = words.get((int)(Math.random() * words.size()));
            livesMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
            StringBuilder initialString = new StringBuilder();
            for (int i = 0; i < wordGuess.length(); i++) {
                initialString.append("_");
            }
            wordDisplay.setText(initialString.toString());
            guessedLetters.setText("Guessed letters: ");
            gameBox.getChildren().remove(gameBox.getChildren().size() - 1);
        });
    }
    private void winState() {
        guessResult.setText("You win!");
        inputText.clear();
        makeResetButton();
    }
    private void gameOver() {
      guessResult.setText("Game over!");
      wordDisplay.setText(wordGuess);
      inputText.clear();
      makeResetButton();
    }
    @FXML
    protected int submitLetter() {

        if (attemptsAllowed - mistakes <= 0) {
            gameOver();
            return 0;
        }
        if (Objects.equals(wordDisplay.getText(), wordGuess)) {
            winState();
            return 0;
        }
        if (Objects.equals(inputText.getText(), "")) {
            guessResult.setText("You need to input a letter to guess a letter, fool!");
            inputText.clear();
            return 1;
        }
        char letter = inputText.getText().toCharArray()[0];
        final String[] alreadyGuessed = guessedLetters.getText().split(" ");
        if (alreadyGuessed.length == 3) {
            if (alreadyGuessed[2].contains(""+letter)) {
                guessResult.setText("You already guessed that letter. Try another one!");
                inputText.clear();
                return 1;
            }
        }

        ArrayList<Integer> letterIndices = Words.findInstancesOfChar(wordGuess, letter);
        if (letterIndices.size() == 0) {
            guessResult.setText(String.format("No characters found of %c", letter));
            inputText.clear();
            mistakes++;
            livesMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
            guessedLetters.setText(String.format("%s%c", guessedLetters.getText(), letter));
            return 0;
        }
        guessedLetters.setText(String.format("%s%c", guessedLetters.getText(), letter));
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
        if (Objects.equals(wordDisplay.getText(), wordGuess)) {
            winState();
        }
        return 0;
    }
    @FXML
    protected int submitWord() {
        if (mistakes > attemptsAllowed) {
            gameOver();
            return 0;
        }
        if (Objects.equals(wordDisplay.getText(), wordGuess)) {
            winState();
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
            makeResetButton();
            return 0;
        }
        guessResult.setText("That's not the word!");
        mistakes++;
        livesMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
        inputText.clear();
        return 0;
    }
}
