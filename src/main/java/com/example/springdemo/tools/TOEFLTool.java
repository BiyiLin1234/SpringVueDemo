package com.example.springdemo.tools;

import java.io.*;
import java.util.*;

public class TOEFLTool {
    private static Random random = new Random(System.currentTimeMillis());
    private static Scanner scanner = new Scanner(System.in);

    private static final String path = "src/main/resources/notes/toefl.txt";

    public static void main(String[] args) throws IOException {
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        ArrayList<String[]> words = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            StringBuilder word = new StringBuilder();
            int idx = 0;
            while (idx < line.length() && line.charAt(idx) != ' ') {
                word.append(line.charAt(idx));
                idx++;
            }
            words.add(new String[] {
               word.toString(),
               line.substring(idx + 1)
            });
            line = br.readLine();
        }
        while (true) {
            int next = random.nextInt(words.size());
            System.out.println(words.get(next)[0]);
            scanner.nextLine();
            System.out.println(words.get(next)[1]);
            scanner.nextLine();
        }
    }
}
