package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import ngrams.NGramMap;
import wordnet.WordNet;

import java.util.*;

public class HyponymsAncestorHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap map;

    public HyponymsAncestorHandler(WordNet wn, NGramMap map){
        this.wn = wn;
        this.map= map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        NgordnetQueryType queryType = q.ngordnetQueryType();

        if (queryType == NgordnetQueryType.HYPONYMS) {
            return handleHyponyms(q);
        } else if (queryType == NgordnetQueryType.ANCESTORS) {
            return handleAncestors(q);
        }else {
            return "Invalid query type";
        }
    }

    private String handleAncestors(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        if (words.size() == 1) {
            String word = words.get(0);
            Set<String> ancestor = wn.ancestor(word);
            Set<String> topKWords = selectTopK(ancestor,startYear,endYear,k);
            return "[" + String.join(", ", wn.sorted(topKWords)) + "]";
        }
        else{
            Set<String> commonAncestor = wn.commonAncestor(words);
            Set<String> topKWords = selectTopK(commonAncestor,startYear,endYear,k);
            return "[" + String.join(", ", wn.sorted(topKWords)) + "]";
        }
    }

    private String handleHyponyms(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        if (words.size() == 1) {
            String word = words.get(0);
            Set<String> hyponym = wn.hyponym(word);
            Set<String> topKWords = selectTopK(hyponym,startYear,endYear,k);
            return "[" + String.join(", ", wn.sorted(topKWords)) + "]";
        }
        else{
            Set<String> commonHyponyms = wn.commonHyponym(words);
            Set<String> topKWords = selectTopK(commonHyponyms,startYear,endYear,k);
            return "[" + String.join(", ", wn.sorted(topKWords)) + "]";
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
