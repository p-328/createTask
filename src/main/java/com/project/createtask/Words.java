package com.project.createtask;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Words {
    // This method gets all of the potential words of the game from words.txt
    public static ArrayList<String> getWords() {
        InputStream ist = Words.class.getResourceAsStream("words.txt");
        assert ist != null;
        BufferedReader bf = new BufferedReader(new InputStreamReader(ist, StandardCharsets.UTF_8));
        ArrayList<String> words;
        try {
            words = bf.lines().collect(Collectors.toCollection(ArrayList::new));
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return words;
    }
    // This method finds all of the indices of ch in str, and returns all of the indices of ch. Useful in the submitLetter method in InputController.java 
    public static ArrayList<Integer> findInstancesOfChar(String str, char ch) {
        ArrayList<Integer> instances = new ArrayList<>();
        int count = 0;
        for (var i : str.toCharArray()) {
            if (ch == i) {
                instances.add(count);
            }
            count++;
        }
        return instances;
    }
}
