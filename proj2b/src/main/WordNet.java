package main;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

public class WordNet {
    private Graph graph;
    private Map<Integer, String[]> wordMap;

    public WordNet(String synsetFilename, String hyponymFilename){
        int lineCount = loadSynsetFile(synsetFilename);
        loadHyponymFile(hyponymFilename);

        graph = new Graph(lineCount);
        wordMap = new HashMap<>();
    }

    private void loadHyponymFile(String hyponymFilename) {
        In hyponymIn = new In(hyponymFilename);

        while (!hyponymIn.isEmpty()) {
            String nextLine = hyponymIn.readLine();
            String[] splitLine = nextLine.split(",");
            int parentNode = Integer.parseInt(splitLine[0]);

            for (int i = 1; i < splitLine.length; i++) {
                int neighborNode = Integer.parseInt(splitLine[i]);
                graph.addEdge(parentNode, neighborNode);
            }
        }
    }

    private int loadSynsetFile(String synsetFilename) {
        In synsetIn = new In(synsetFilename);
        int lineCount = 0;

        while (!synsetIn.isEmpty()) {
            String nextLine = synsetIn.readLine();
            String[] splitLine = nextLine.split(",");
            int synsetID = Integer.parseInt(splitLine[0]);
            String synset = splitLine[1];

            String[] synsetList = synset.split(" ");
            wordMap.put(synsetID,synsetList);
            lineCount++;
        }
        return lineCount;
    }
}
