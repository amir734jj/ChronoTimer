import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class testTimeParser {
	@Test
	public void test() {
		int[] time1 = { 12, 0, 0 };
		assertTrue(Arrays.equals(TimeParser.parser("12:00:00.0"), time1));

		int[] time2 = { 12, 2, 10 };
		assertTrue(Arrays.equals(TimeParser.parser("12:02:10.0"), time2));

		int[] time3 = { 12, 1, 4 };
		assertTrue(Arrays.equals(TimeParser.parser("12:01:04.0"), time3));

		int[] time4 = { 12, 4, 52 };
		assertTrue(Arrays.equals(TimeParser.parser("12:04:52.0"), time4));

		int[] time5 = { 12, 0, 0 };
		assertTrue(Arrays.equals(TimeParser.parser("12:00:00.1"), time5));

		int[] time6 = { 12, 2, 10 };
		assertTrue(Arrays.equals(TimeParser.parser("12:02:10.9"), time6));

		int[] time7 = { 12, 1, 4 };
		assertTrue(Arrays.equals(TimeParser.parser("12:01:04.1"), time7));

		int[] time8 = { 12, 4, 52 };
		assertTrue(Arrays.equals(TimeParser.parser("12:04:52.4"), time8));

	}

}
