package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int i=startYear; i<=endYear; i++){
            if (ts.containsKey(i)){
                this.put(i, ts.get(i));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        Set<Integer> allkeys = this.keySet();
        return new ArrayList<>(allkeys);
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        Collection<Double> allvalues = this.values();
        return new ArrayList<>(allvalues);
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries addedTimeseries = new TimeSeries();

        for (Integer year : this.keySet()) {
            double value = this.get(year);
            if (ts.containsKey(year)) {
                value += ts.get(year);
            }
            addedTimeseries.put(year, value);
        }
        for (Integer year : ts.keySet()) {
            if (!this.containsKey(year)) {
                addedTimeseries.put(year, ts.get(year));
            }
        }
        return addedTimeseries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries dividedTimeseries = new TimeSeries();

        for (Integer year : this.keySet()) {
            Double value = this.get(year);
            Double divisor = ts.get(year);
            if (value == null || divisor == null) {
                throw new IllegalArgumentException();
            }
            dividedTimeseries.put(year, value / divisor);
        }
        return dividedTimeseries;
    }
}
