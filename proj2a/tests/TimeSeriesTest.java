import ngrams.TimeSeries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }
    @Test
    public void testDividedBy() {
        // Create two TimeSeries objects
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();

        // Add some values to ts1
        ts1.put(2000, 100.0);
        ts1.put(2001, 150.0);
        ts1.put(2002, 200.0);

        // Add corresponding values to ts2
        ts2.put(2000, 10.0);
        ts2.put(2001, 30.0);
        ts2.put(2002, 50.0);

        // Expected result after division (ts1 / ts2)
        TimeSeries expected = new TimeSeries();
        expected.put(2000, 10.0);  // 100 / 10 = 10
        expected.put(2001, 5.0);   // 150 / 30 = 5
        expected.put(2002, 4.0);   // 200 / 50 = 4

        // Perform the dividedBy operation
        TimeSeries result = ts1.dividedBy(ts2);

        // Check that the result is as expected
        Assertions.assertEquals(expected, result);
    }

} 