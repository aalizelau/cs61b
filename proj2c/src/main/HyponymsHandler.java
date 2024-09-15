package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordNet;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap map;

    public HyponymsHandler(WordNet wn,NGramMap map){
        this.wn = wn;
        this.map= map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        if (words.size() == 1) {
            String word = words.get(0);
            Set<String> hyponym = wn.hyponym(word);
            Set<String> topKWords = selectTopK(hyponym,startYear,endYear,k);
            return "[" + String.join(", ", wn.sortedHyponym(topKWords)) + "]";
        }
        else{
            Set<String> commonHyponyms = wn.commonHyponym(words);
            Set<String> topKWords = selectTopK(commonHyponyms,startYear,endYear,k);
            return "[" + String.join(", ", wn.sortedHyponym(topKWords)) + "]";
        }

    }
    public Set<String> selectTopK (Set<String> words, int startYear, int endYear, int k) {
        Map<String, Double> summedFrequencies = map.summedCount(words, startYear, endYear);
        PriorityQueue<Map.Entry<String, Double>> sortedQueue = map.topHyponym(summedFrequencies);

        Set<String> topKWords = new HashSet<>();
        for (int i=0; i<k;i++){
            String word = (sortedQueue.poll()).getKey();
            topKWords.add(word);
        }
        return topKWords;
    }

}
