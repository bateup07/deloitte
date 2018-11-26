import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * @author Matthew Bateup matthew.bateup@gmail.com
 * Test file
 */

public class ActivityTest {
    @Test
    public void testDuration() {
        Activity act = new Activity("Lunch Break", "60min");
        assertEquals(act.getDuration(), "60min");
        assertEquals(act.getDurationInMins(), 60);

        // test sprint conversion
        act = new Activity("Salsa & Pickles", "sprint");
        assertEquals(act.getDurationInMins(), 15);
    }

    @Test
    public void testStartTime() {
        Activity act;

        act = new Activity("Lunch Break", "60min");

        // test morning
        act.setStartTime(540); // 09:00 am
        assertEquals(act.getStartTime(), "09:00 am");

        // test noon
        act.setStartTime(720);
        assertEquals(act.getStartTime(), "12:00 pm");

        // test afternoon
        act.setStartTime(1020);
        assertEquals(act.getStartTime(), "05:00 pm");
    }
}
