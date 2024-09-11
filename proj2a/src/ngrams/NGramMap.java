package ngrams;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private Map<String, TimeSeries> wordMap;
    private TimeSeries countTimeSeries;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordMap = new HashMap<>();
        countTimeSeries = new TimeSeries();

        loadWordsFile(wordsFilename);
        loadCountsFile(countsFilename);
    }

    private void loadWordsFile(String wordsFilename) {
        In wordsIn = new In(wordsFilename);

        while (!wordsIn.isEmpty()) {
            String nextLine = wordsIn.readLine();
            String[] splitLine = nextLine.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);

            // Add word's data to the map
            wordMap.putIfAbsent(word,new TimeSeries());
            wordMap.get(word).put(year,count);
        }
    }
    private void loadCountsFile(String countsFilename){
        In countsIn = new In(countsFilename);

        while (!countsIn.isEmpty()) {
            String nextLine = countsIn.readLine();
            String[] splitLine = nextLine.split(",");
            int year = Integer.parseInt(splitLine[0]);
            double count = Double.parseDouble(splitLine[1]);

            countTimeSeries.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries oldtimeseries= this.wordMap.get(word);
        if (oldtimeseries == null) {
            return new TimeSeries();
        }
        return new TimeSeries(oldtimeseries, startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries oldtimeseries= this.wordMap.get(word);
        if (oldtimeseries == null) {
            return new TimeSeries();
        }
        return new TimeSeries(oldtimeseries, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return countTimeSeries;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!wordMap.containsKey(word)){
            return new TimeSeries();
        }
        return countHistory(word,startYear,endYear).dividedBy(totalCountHistory());
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!wordMap.containsKey(word)){
            return new TimeSeries();
        }
        return countHistory(word,MIN_YEAR, MAX_YEAR).dividedBy(totalCountHistory());
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();

        for (String word : words) {
            TimeSeries singleWord = weightHistory(word, startYear, endYear);

            if (singleWord != null) {
                result = result.plus(singleWord);
            }
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();

        for (String word : words){
            TimeSeries singleWord = weightHistory(word, MIN_YEAR, MAX_YEAR);

            if (singleWord!=null){
                result = result.plus(singleWord);
            }
        }
        return result;
    }

}
