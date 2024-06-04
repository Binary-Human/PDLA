import java.util.List;

public class CountCharacters {


    public static class Counter {
        private int n = 0;

        public int getN() {
            return n;
        }

        public void increment() {
            synchronized (this){
                this.n++;
            }
        }
    }

    public static void updateCounterFromString(String str, char character, Counter c) {
        for (int i = 0 ; i < str.length() ; i++) {
            if (str.charAt(i) == character) {
                c.increment();
            }
        }
    }

    /** Returns the number of characters in the string.
     *  MUST use the updateCounterFromString method. */
    public static int countOccurrences(String str, char character) {
        Counter counter = new Counter();
        updateCounterFromString(str, character, counter);
        return counter.getN();
    }

    /** Returns the number of characters in the string.
     *  MUST use the updateCounterFromString method.
     *  MUST do the computation in a new thread.
     *  */
    public static int countOccurrencesInThread(String str, char character) {
        Counter counter = new Counter();
        Thread t = new Thread(() -> {
            updateCounterFromString(str, character, counter);
        });
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return counter.getN();
    }

    /** Returns the total number of characters in all strings.
     *  MUST use the updateCounterFromString method.
     *  MUST use a single counter
     * */
    public static int countOccurrencesSequential(List<String> strings, char character) {
        Counter counter = new Counter();
        for (String s : strings) {
            updateCounterFromString(s, character, counter);
        }
        return counter.getN();
    }

    /** Returns the total number of characters in all strings.
     *  MUST use the updateCounterFromString method.
     *  MUST use a single counter
     *  MUST process each string concurrently with the others.
     * */
    public static int countOccurrencesParallel(List<String> strings, char character) {
        Counter counter = new Counter();
        Thread[] threads = new Thread[strings.size()];
        int i = 0;
        for (String s : strings) {
            Thread t = new Thread(() -> {
                updateCounterFromString(s, character, counter);
            });
            threads[i] = t;
            i++;
        }
        for (Thread t : threads) {
            t.start();
        }
        // On join les thread pour ne pas envoyer avant l'execution
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return counter.getN();
    }
}
