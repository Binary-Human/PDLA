import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PointTest {


    // TODO: complete the constructor of Point
    @Test
    public void testCreation() {
        var p = new Point(3, 4);
        assert p.x == 3;
        assert p.y == 4;
    }


    // TODO: complete the implementation of Point::add to fulfill the test
    @Test
    public void testAdd() {
        var p1 = new Point(1, 2);
        var p2 = new Point(4, 5);
        var sum = p1.add(p2);
        assert p1.x == 1 && p1.y == 2;
        assert p2.x == 4 && p2.y == 5;
        assert sum.x == 5 && sum.y == 7;
    }

    // TODO: complete the implementation of Point to validate the following tests
    @Test
    public void testEquals() {
        var p1 = new Point(1, 2);
        var p2 = new Point(1, 3);
        var p3 = new Point(1, 2);
        var p4 = new Point(2, 2);

        assert p1.equals(p1);
        assert !p1.equals(p2);
        assert p1.equals(p3);
        assert !p1.equals(p4);
    }

    // TODO: ensure that the computed hash is coherent with equals
    @Test
    public void testHash() {
        var points = Arrays.asList(
                new Point(1, 2),
                new Point(1, 2),
                new Point(2, 2),
                new Point(1, 3)
        );

        for (var p1 : points) {
            for (var p2 : points) {
                assert !p1.equals(p2) || p1.hashCode() == p2.hashCode();
            }
        }
    }

    // TODO: Implement the string formatting of Point
    @Test
    public void testToString() {
        assert (new Point(3, 6)).toString().equals("(3, 6)");
        assert (new Point(-89, 23)).toString().equals("(-89, 23)");
    }

}
