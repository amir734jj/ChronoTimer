import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class testTime {

	@Test
	public void test() {
		Time.initializeFromComputerClock();
		assertTrue(Arrays.equals(Time.getTime(), Time.getCurrentTime()));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		assertFalse(Arrays.equals(Time.getTime(), Time.getCurrentTime()));

		Time.initializeFromComputerClock();
		Time.timeScheduler();
		assertTrue(Arrays.equals(Time.getTime(), Time.getCurrentTime()));

		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
		assertTrue(Time.elapsedTime(Time.getTime(), Time.getCurrentTime())[2] <= 1);
	}

	@Test
	public void testElapsedTime() {
		int[] t1 = { 2, 33, 10 };
		int[] t2 = { 3, 10, 5 };
		int[] result = { 0, 36, 55 };
		assertTrue(Arrays.equals(Time.elapsedTime(t1, t2), result));

	}

	@Test
	public void testArray() {
		int[] it = Time.getTime();

		assertEquals(it[0], Time.getTime()[0]);
		assertEquals(it[1], Time.getTime()[1]);
		assertEquals(it[2], Time.getTime()[2]);

		it[0] = 1;
		it[1] = 2;
		it[2] = 3;

		Time.setTime(it);
		assertTrue(Arrays.equals(it, Time.getTime()));
	}

	@Test
	public void testStopClock() {
		Time.initializeFromComputerClock();
		assertTrue(Arrays.equals(Time.getTime(), Time.getCurrentTime()));
		Time.setIssuedStop(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		assertFalse(Arrays.equals(Time.getTime(), Time.getCurrentTime()));
	}
}
