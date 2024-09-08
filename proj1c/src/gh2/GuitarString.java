package gh2;

// TODO: maybe more imports

import deque.ArrayDeque61B;
import deque.Deque61B;
import java.util.LinkedList;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
   private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque61B<>();

        for (int i=0; i<capacity; i++){
            buffer.addLast(0.0);
        }

        // TODO: Initialize the buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer with zeros.
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int capacity = buffer.size();
        while (!buffer.isEmpty()) {
            buffer.removeFirst(); // This dequeues the existing values
        }
        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double newdouble = + (buffer.removeFirst() + buffer.get(0))/2 * DECAY;
        buffer.addLast(newdouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
