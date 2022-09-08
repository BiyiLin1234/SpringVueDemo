package com.example.springdemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random(System.currentTimeMillis());
    static Scanner sc = new Scanner(System.in);
    private static final String EXIT = "EXIT";

    public static void main(String[] args) throws IOException {
        // get current path: System.getProperty("user.dir")
        String path =
                "./src/main/resources/notes/the_importance_of_handwashing.txt";
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file)
        );
        BufferedReader br = new BufferedReader(reader);
        StringBuilder article = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            article.append(line);
            line = br.readLine();
        }
        cycleArticle(article.toString());
    }

    private static void cycleArticle(String article) {
        int idx = 0;
        List<Node> nodes = new ArrayList<>();
        while (idx < article.length()) {
            if (article.charAt(idx) == '[') {
                Node curNode = new Node();
                nodes.add(curNode);

                while (idx < article.length() && article.charAt(idx) != ']') {
                    while (idx < article.length() && (article.charAt(idx) != '(' && article.charAt(idx) != ']')) idx++;
                    if (idx >= article.length() || article.charAt(idx) == ']') {
                        idx++;
                        break;
                    }
                    idx++;
                    StringBuilder curS = new StringBuilder();
                    while (idx < article.length() && article.charAt(idx) != ')') {
                        curS.append(article.charAt(idx));
                        idx++;
                    }
                    idx++;
                    curNode.insert(curS.toString());
                }
            }
            idx++;
        }
        while (true) {
            Node node = nodes.get(random.nextInt(nodes.size()));
            System.out.println(node.getRandomSentence());
            if (sc.nextLine().equals(EXIT)) {
                break;
            }
            System.out.println(node.getAll());
            if (sc.nextLine().equals(EXIT)) {
                break;
            }
        }
    }

    static class Node {
        List<String> sencentes = new ArrayList<>();

        public String getRandomSentence() {
            return sencentes.get(random.nextInt(sencentes.size()));
        }

        public String getAll() {
            return sencentes.toString();
        }

        public void insert(String s) {
            sencentes.add(s);
        }
    }
}
