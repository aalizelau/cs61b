package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private Graph graph;
    private Map<Integer, String[]> wordMap;

    public WordNet(String synsetFilename, String hyponymFilename){
        wordMap = new HashMap<>();
        int lineCount = loadSynsetFile(synsetFilename);

        graph = new Graph(lineCount);
        loadHyponymFile(hyponymFilename);
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

    public static List<Integer> getSynsetIDs(Map<Integer, String[]> wordMap, String word) {
        List<Integer> SynsetID = new ArrayList<>();

        for (Map.Entry<Integer, String[]> entry : wordMap.entrySet()) {
            for (String s : entry.getValue()){
                if (s.equals(word)) {
                    SynsetID.add(entry.getKey());
                    break;
                }
            }
        }
        return SynsetID;
    }

    public Set<String> hypernym(String word){
        List<Integer> SynsetIDs = getSynsetIDs(wordMap, word);

        Set<Integer> visitedNodeID = new HashSet<>();
        for (Integer id: SynsetIDs){
            visitedNodeID.addAll(graph.visitedNodes(id));
        }

        Set<String> hypernym = new HashSet<>();
        for (Integer id: visitedNodeID){
            String[] words = wordMap.get(id);
            if (words != null) {
                hypernym.addAll(Arrays.asList(words));
            }
        }
        return hypernym;
    }

    public List<String> sortedHypernym(Set<String> hypernym){
        List<String> sortedHypernyms = new ArrayList<>(hypernym);
        Collections.sort(sortedHypernyms);
        return sortedHypernyms;
    }

}
