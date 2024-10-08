package main;

import browser.NgordnetServer;
import org.slf4j.LoggerFactory;
import wordnet.WordNet;

public class Main {
    static {
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        String synsetFile = "./proj2b/data/wordnet/synsets.txt";
        String hyponymFile = "./proj2b/data/wordnet/hyponyms.txt";

        WordNet wn = new WordNet(synsetFile,hyponymFile);

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(wn));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}