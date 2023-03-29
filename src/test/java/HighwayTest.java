import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HighwayTest {
    Highway highway = new Highway();

    @Test
    public void calculateDistanceBetweenTwoInterchange_test1() {
        String startingInterchange = "Appleby Line";
        String endInterchange = "Britannia Road";

        double expectedDistance = 19.523;
        double actualDistance = highway.calculateDistanceBetweenTwoInterchange(startingInterchange, endInterchange);
        assertEquals(expectedDistance, actualDistance, 0);
    }

    @Test
    public void calculateDistanceBetweenTwoInterchange_test2() {
        String startingInterchange = "Derry Road";
        String endInterchange = "QEW";

        double expectedDistance = 32.504999999999995;
        double actualDistance = highway.calculateDistanceBetweenTwoInterchange(startingInterchange, endInterchange);
        assertEquals(expectedDistance, actualDistance, 0);
    }
}
