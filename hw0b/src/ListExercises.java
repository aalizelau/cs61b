import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int i=0; i<L.size(); i++)
        {
            sum = sum + L.get(i);
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenlist = new ArrayList<Integer>();
        for (Integer num : L) {
            if (num % 2 == 0) {
                evenlist.add(num);
            }
        }
        return evenlist;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonlist = new ArrayList<Integer>();
        for (Integer i : L1)
        {
            for (Integer j : L2)
            {
                if (i.equals(j))
                {
                    commonlist.add(j);
                }
            }
        }
        return commonlist;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word : words )
        {
            for (int i = 0; i < word.length(); i++)
            {
                if (c == word.charAt(i))
                {
                    count ++;
                }
            }
        }
        return count;
    }
}
