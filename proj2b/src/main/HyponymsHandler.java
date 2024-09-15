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
            List<String> hypernym = wn.sortedHypernym(wn.hypernym(word));
            return "[" + String.join(", ", hypernym) + "]";
        }
        else{
            List<String> commonHyponyms = wn.commonHyponyms(words);
            return "[" + String.join(", ", commonHyponyms) + "]";
        }
    }
}
