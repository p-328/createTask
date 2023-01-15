package com.project.createtask;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Words {
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
