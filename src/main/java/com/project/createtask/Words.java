package com.project.createtask;
import java.io.InputStream;
import java.util.ArrayList;
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
