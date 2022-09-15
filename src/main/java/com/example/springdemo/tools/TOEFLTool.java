package com.example.springdemo.tools;

import java.io.*;
import java.util.*;

public class TOEFLTool {
    private static Random random = new Random(System.currentTimeMillis());
    private static Scanner scanner = new Scanner(System.in);
    private static final String TOEFL_KEY = "TOEFL";
    private static final String STOP_CMD = "stop";
    private static final String ADD_CMD_A = "a"; // 添加到生词表
    private static final String ADD_CMD_1 = "1";

    private static final String path = "src/main/resources/notes/toefl.txt";
    private static final String path_me = "src/main/resources/notes/toefl_me.txt";

    public static void main(String[] args) throws IOException {
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        ArrayList<String[]> words = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            StringBuilder word = new StringBuilder();
            int idx = 0;
            while (idx < line.length() && line.charAt(idx) != '\t') {
                word.append(line.charAt(idx));
                idx++;
            }
            words.add(new String[] {
               word.toString(),
               line.substring(idx + 1)
            });
            line = br.readLine();
        }

        int idx = Integer.parseInt(KVTools.getKVInstance().getStrKeyStrValue(TOEFL_KEY));
        idx++;
        System.out.println("本次开始单词索引"  + idx);
        while (idx < words.size()) {
            System.out.println("==================================");
            printWordAndDescribe(words.get(idx), idx);
            String next = scanner.nextLine();
            if (next.equals(STOP_CMD)) {
                KVTools.getKVInstance().putStrKeyStrValue(TOEFL_KEY, String.valueOf(idx));
                return;
            } else if (next.equals(ADD_CMD_A) || next.equals(ADD_CMD_1)) {
                saveMyWord(words.get(idx));
            }
            idx++;
            System.out.println("===================================");
        }
    }

    private static void printWordAndDescribe(String[] arr, int idx) {
        System.out.println(arr[0]);
        scanner.nextLine();
        System.out.println(arr[1]);
    }

    private static void saveMyWord(String[] arr) {
        try {
            FileWriter fileWriter = new FileWriter(path_me, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.append(arr[0]).append(" ").append(arr[1]).append("\n");
            bw.flush();
            fileWriter.close();
            bw.close();
            System.out.println("added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
