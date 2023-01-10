package com.project.createtask;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Words {
    public static ArrayList<String> getWords() {
        InputStream ist = Words.class.getResourceAsStream("words.txt");
        assert ist != null;
        Scanner scanner = new Scanner(ist);
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        return words;
    }
}
