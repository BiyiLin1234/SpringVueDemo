package com.example.springdemo.tools;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KVTools  {
    private static final String kvPath = "src/main/resources/notes/store_map.txt";
    private static KVTools kvTools;
    private static final String splitter = "\\|";

    private HashMap<String, String> map = new HashMap<>();

    private KVTools() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                kvPath
                        )
                )
        );

        String s;
        while ((s = br.readLine()) != null) {
            String[] arr = s.split(splitter);
            map.put(arr[0], arr[1]);
        }
        br.close();

    }

    public static KVTools getKVInstance() {
        try {
            if (kvTools == null) {
                synchronized (KVTools.class) {
                    if (kvTools == null) {
                        kvTools = new KVTools();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kvTools;
    }

    public String getStrKeyStrValue(String key) {
        return map.getOrDefault(key, "");
    }

    public void putStrKeyStrValue(String key, String value) {
        map.put(key, value);
        save();
    }

    private void save() {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(kvPath, false)
            );
            for (Map.Entry<String, String> e : map.entrySet()) {
                bw.write(e.getKey() + '|' + e.getValue() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
