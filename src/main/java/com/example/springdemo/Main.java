package com.example.springdemo;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

}
class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        TreeMap<String, Integer> authorScore = new TreeMap<>();
        HashSet<String> authorSet = new HashSet<>();
        HashMap<String, List<Composition>> author = new HashMap<>();
        for (int i = 0; i < creators.length; i++) {
            String curAuthor = creators[i];
            String id = ids[i];
            int view = views[i];
            authorScore.put(curAuthor, authorScore.getOrDefault(curAuthor, 0) + view);
            List<Composition> compositionList = author.getOrDefault(curAuthor, new ArrayList<>());
            author.put(curAuthor, compositionList);
            compositionList.add(new Composition(id, view));
            authorSet.add(curAuthor);
        }
        ArrayList<String> authorList = new ArrayList<>(authorSet);
        authorList.sort((arg1, arg2) -> authorScore.get(arg2) - authorScore.get(arg1));
        List<String>  resAuthor = new ArrayList<>();
        int maxScore = authorScore.get(authorList.get(0));
        for (String curA : authorSet) {
            if (authorScore.get(curA).equals(maxScore)) {
                resAuthor.add(curA);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String curA : resAuthor) {
            List<String> temp = new ArrayList<>();
            temp.add(curA);
            List<Composition> compositionList = author.get(curA);
            Collections.sort(compositionList, new Comparator<Composition>() {
                @Override
                public int compare(Composition t1, Composition t2) {
                    if (t1.views != t2.views) {
                        return t2.views - t1.views;
                    }
                    return t2.id.compareTo(t1.id);
                }
            });
            temp.add(compositionList.get(0).id);
            res.add(temp);
        }
        return res;
    }

    static class Composition {
        String id;
        int views;
        public Composition(String id, int views) {
            this.id = id;
            this.views = views;
        }
    }
}