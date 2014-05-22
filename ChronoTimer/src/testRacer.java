import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class testRacer {
	@Test
	public void testElapsedtime() {
		int[] startTime = new int[3];
		startTime[0] = 1;
		startTime[1] = 1;
		startTime[2] = 1;
		int[] finishTime = new int[3];
		finishTime[0] = 3;
		finishTime[1] = 3;
		finishTime[2] = 3;
		int[] elapsedTime = new int[3];
		elapsedTime[0] = 2;
		elapsedTime[1] = 2;
		elapsedTime[2] = 2;
		int[] testTime = new int[3];
		Racer test1 = new Racer(121);
		test1.setStart(startTime);
		test1.setFinish(finishTime);
		testTime = test1.elapsedtime();
		assertTrue(Arrays.equals(testTime, elapsedTime));
	}

	@Test
	public void testSetFinished() {
		Racer r = new Racer(543);
		r.setFinished(true);
		assertTrue(r.isFinished());
		r.setFinished(false);
		assertTrue(!r.isFinished());
	}

	@Test
	public void testSetRacerID() {
		Racer r = new Racer(0);
		r.setRacerID(99999);
		assertTrue(r.getRacerID() == 99999);
		r.setRacerID(0);
		assertTrue(r.getRacerID() == 0);
	}

	@Test
	public void testSetStart() {
		Racer r = new Racer(0);
		int[] startTime = new int[3];
		startTime[0] = 1;
		startTime[1] = 1;
		startTime[2] = 1;
		r.setStart(startTime);
		assertTrue(Arrays.equals(startTime, r.getStart()));
	}

	@Test
	public void testSetFinish() {
		Racer r = new Racer(0);
		int[] finishTime = new int[3];
		finishTime[0] = 1;
		finishTime[1] = 1;
		finishTime[2] = 1;
		r.setFinish(finishTime);
		assertTrue(Arrays.equals(finishTime, r.getFinish()));
	}

	@Test
	public void testSetRuntime() {
		Racer r = new Racer(0);
		int[] runTime = new int[3];
		runTime[0] = 1;
		runTime[1] = 1;
		runTime[2] = 1;
		r.setRuntime(runTime);
		assertTrue(Arrays.equals(runTime, r.getRuntime()));
	}

	@Test
	public void testSetRunNumber() {
		Racer r = new Racer(99999);
		r.setRunNumber(0);
		assertTrue(r.getRunNumber() == 0);
		r.setRunNumber(10);
		assertTrue(r.getRunNumber() == 10);
	}

	@Test
	public void testEquals() {
		int[] startTime = new int[3];
		startTime[0] = 0;
		startTime[1] = 1;
		startTime[2] = 2;
		int[] finishTime = new int[3];
		finishTime[0] = 0;
		finishTime[1] = 1;
		finishTime[2] = 2;
		int[] runTime = new int[3];
		runTime[0] = 0;
		runTime[1] = 1;
		runTime[2] = 2;

		Racer test1 = new Racer(121);
		test1.setStart(startTime);
		test1.setFinish(finishTime);
		test1.setRunNumber(1);
		test1.setRuntime(runTime);

		Racer test2 = new Racer(121);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		assertEquals(test1, test2);
		assertTrue(test1.deepEqualityTest(test2));
	}

	@Test
	public void testToString() {
		int[] startTime = new int[3];
		startTime[0] = 0;
		startTime[1] = 1;
		startTime[2] = 2;
		int[] finishTime = new int[3];
		finishTime[0] = 0;
		finishTime[1] = 1;
		finishTime[2] = 2;
		int[] runTime = new int[3];
		runTime[0] = 0;
		runTime[1] = 1;
		runTime[2] = 2;

		Racer test1 = new Racer(121);
		test1.setStart(startTime);
		test1.setFinish(finishTime);
		test1.setRunNumber(1);
		test1.setRuntime(runTime);

		Racer test2 = new Racer(121);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		assertEquals(test1.toString(), test2.toString());
	}

	@Test
	public void testParsing() {
		int[] startTime = new int[3];
		startTime[0] = 0;
		startTime[1] = 1;
		startTime[2] = 2;
		int[] finishTime = new int[3];
		finishTime[0] = 0;
		finishTime[1] = 1;
		finishTime[2] = 2;
		int[] runTime = new int[3];
		runTime[0] = 0;
		runTime[1] = 1;
		runTime[2] = 2;

		Racer test1 = new Racer(121);
		test1.setStart(startTime);
		test1.setFinish(finishTime);
		test1.setRunNumber(1);
		test1.setRuntime(runTime);

		assertTrue(Arrays.equals(
				test1.fromTimeFormat(test1.toTimeFormat(startTime)), startTime));
		assertTrue(Arrays.equals(
				test1.fromTimeFormat(test1.toTimeFormat(finishTime)),
				finishTime));
	}

}
