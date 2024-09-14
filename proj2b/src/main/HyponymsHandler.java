package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymsHandler(WordNet wn){
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return "Hello";
    }
}
