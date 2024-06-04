import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CountCharactersTest {

    private static final List<String> strings = Arrays.asList(
            "",
            "aa",
            "ababab".repeat(50)
    );

    private static int countA(String s) {
        CountCharacters.Counter c = new CountCharacters.Counter();
        CountCharacters.updateCounterFromString(s, 'a', c);
        return c.getN();
    }

    private static int countA(List<String> s) {
        return s.stream().mapToInt(CountCharactersTest::countA).sum();

    }

    @Test
    public void testCountA() {
        assert countA("") == 0;
        assert countA("aa") == 2;
        assert countA("a".repeat(1000)) == 1000;
        assert countA("abab") == 2;
        assert countA("abab".repeat(500)) == 1000;
    }

    // TODO: implement CountCharacters::countOccurrences
    @Test
    public void testSingleString() {
        for (String s : strings) {
            assert CountCharacters.countOccurrences(s, 'a') == countA(s);
        }
    }


    // TODO: implement CountCharacters::countOccurrencesInThread
     @Test
    public void testSingleStringInThread() {
        for (String s : strings) {
            assert CountCharacters.countOccurrencesInThread(s, 'a') == countA(s);
        }
    }

    // TODO: implement CountCharacters::countOccurrencesSequential
    @Test
    public void testManyStrings() {
        assert CountCharacters.countOccurrencesSequential(strings, 'a') == countA(strings);
    }

    // TODO: make the Counter class thread-safe
    @Test
    public void testCounterThreadSafety() {
        var c = new CountCharacters.Counter();
        for (int threadId = 0 ; threadId < 100 ; threadId++) {
            new Thread(() -> {
                for (int i = 0 ; i < 1000 ; i++) {
                    c.increment();
                }
            }).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assert c.getN() == 100 * 1000;
    }

    // TODO: implement CountCharacters::countOccurrencesParallel
    @Test
    public void testManyStringsParallel() {
        var strings = Arrays.asList(
                "",
                "a".repeat(100000),
                "b".repeat(100000),
                "c".repeat(100000),
                "d".repeat(100000),
                "a".repeat(100000),
                "a".repeat(100000)
        );
        assert CountCharacters.countOccurrencesParallel(strings, 'a') == countA(strings);
    }


}
