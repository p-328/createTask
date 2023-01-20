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
    // Code that is called when this controller is initialized, it initializes the game state of the program
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
    /* 
      When the game is over, this method gets called, it makes a reset button, and attaches 
      an event handler to the button that initializes a completely new game state, shows the  
      input field again, clears the text of guessResult and deletes the reset button
    */
    private void makeResetButton() {
        Button resetBtn = new Button("Reset");
        gameBox.getChildren().add(resetBtn);
        resetBtn.setOnAction(actionEvent -> {
            guessResult.setText("");
            inputText.setVisible(true);
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
    // This is a function that is called whenever the game is won, it makes a button to play again, hides the input field and sets the game response message to "You win!"  
    private void winState() {
        guessResult.setText("You win!");
        inputText.clear();
        inputText.setVisible(false);
        makeResetButton();
    }
    // This function is called whenever the player fails to guess the word, and sets the game response message to game over, reveals the actual word, and clears the current input text
    private void gameOver() {
      guessResult.setText("Game over!");
      wordDisplay.setText(wordGuess);
      inputText.clear();
      inputText.setVisible(false);
      makeResetButton();
    }
    // This function is a callback called when a button with the text "Guess letter" is clicked, it checks to make sure the user's lives are not less than 0, checks to see if the letters if the user already guessed are hidden
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
    // This method is called whenever a button with the text "Submit word" is clicked
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
            wordDisplay.setText(wordGuess);
            winState();
            return 0;
        }
        guessResult.setText("That's not the word!");
        mistakes++;
        livesMsg.setText(String.format("Lives: %d", attemptsAllowed-mistakes));
        inputText.clear();
        return 0;
    }
}
