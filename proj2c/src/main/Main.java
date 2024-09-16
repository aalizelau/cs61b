package main;

import browser.NgordnetServer;
import ngrams.NGramMap;
import org.slf4j.LoggerFactory;
import wordnet.WordNet;

public class Main {
    static {
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();


        String wordFile = "./proj2c/data/ngrams/top_49887_words.csv";
        String countFile = "./proj2c/data/ngrams/total_counts.csv";
        String synsetFile = "./proj2c/data/wordnet/synsets16.txt";
        String hyponymFile = "./proj2c/data/wordnet/hyponyms16.txt";

        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wn = new WordNet(synsetFile,hyponymFile);

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsAncestorHandler(wn,ngm));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
