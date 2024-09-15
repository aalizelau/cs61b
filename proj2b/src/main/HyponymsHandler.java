package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymsHandler(WordNet wn){
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();

        if (words.size() == 1) {
            String word = words.get(0);
            List<String> hyponym = wn.sortedHyponym(wn.hyponym(word));
            return "[" + String.join(", ", hyponym) + "]";
        }
        else{
            List<String> commonHyponyms = wn.sortedHyponym(wn.commonHyponym(words));
            return "[" + String.join(", ", commonHyponyms) + "]";
        }
    }
}
