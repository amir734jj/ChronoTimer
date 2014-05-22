import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class testRace {

	@Test
	public void testRaceConst() {
		Race r = new Race(0);
		assertTrue(r.getRaceID() == 0);
		assertTrue(r.getNumRacers() == 0);
	}

	@Test
	public void testNumRacers() {
		Race r = new Race(0);
		r.setNum_racers(0);
		assertTrue(r.getNumRacers() == 0);
		r.setNum_racers(1000);
		assertTrue(r.getNumRacers() == 1000);
	}

	@Test
	public void testRaceID() {
		Race r = new Race(0);
		r.setRaceID(0);
		assertTrue(r.getRaceID() == 0);
		r.setRaceID(1000);
		assertTrue(r.getRaceID() == 1000);
	}

	@Test
	public void testIsExistByRacerID() {
		Race r = new Race(0);
		Racer s = new Racer(1111);
		Racer t = new Racer(9999);
		r.addRacer(s);
		assertTrue(r.isExistByRacerID(1111));
		assertTrue(!r.isExistByRacerID(9999));
		r.addRacer(t);
		assertTrue(r.isExistByRacerID(9999));
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.addRacer(test1);
		race1.addRacer(test2);

		Race race2 = new Race(100);
		race2.addRacer(test1);
		race2.addRacer(test2);

		assertEquals(race1, race2);
		assertTrue(race1.deepEqualityTest(race2));
	}

	@Test
	public void testStartRace() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.set_RACE_TYPE(1);
		race1.addRacer(test1);
		race1.addRacer(test2);

		race1.startRace();
		assertTrue(Arrays.equals(race1.getRacerByRacerID(121).getStart(),
				Time.getTime()));
		assertTrue(Arrays.equals(race1.getRacerByRacerID(122).getStart(),
				Time.getTime()));
	}

	@Test
	public void testEndRace() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.set_RACE_TYPE(2);
		race1.addRacer(test1);
		race1.addRacer(test2);

		race1.finishRace();
		assertTrue(Arrays.equals(race1.getRacerByRacerID(121).getFinish(),
				Time.getTime()));
		assertTrue(Arrays.equals(race1.getRacerByRacerID(122).getFinish(),
				Time.getTime()));
	}

	@Test
	public void testGetRaceByRaceID() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.addRacer(test1);
		race1.addRacer(test2);

		assertEquals(race1.getRacerByRacerID(121), test1);
		assertEquals(race1.getRacerByRacerID(122), test2);
	}

	@Test
	public void testRemoveRacer() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.addRacer(test1);
		race1.addRacer(test2);

		assertTrue(race1.removeRacer(test1));
		assertFalse(race1.removeRacer(test1));
		assertEquals(race1.getNumRacers(), 1);
	}

	@Test
	public void testAddRacer() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		assertTrue(race1.addRacer(test1));
		assertTrue(race1.addRacer(test2));
		assertFalse(race1.addRacer(test2));
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.addRacer(test1);
		race1.addRacer(test2);

		Race race2 = new Race(100);
		race2.addRacer(test1);
		race2.addRacer(test2);

		assertEquals(race1.toString(), race2.toString());
	}

	@Test
	public void isThereRacerThatHasNotBeenStarted() {
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

		Racer test2 = new Racer(122);
		test2.setStart(startTime);
		test2.setFinish(finishTime);
		test2.setRunNumber(1);
		test2.setRuntime(runTime);

		Race race1 = new Race(100);
		race1.addRacer(test1);
		race1.addRacer(test2);

		assertTrue(race1.isThereRacerThatHasNotBeenStarted());
		assertTrue(race1.isThereRacerThatHasNotBeenStarted());
		assertTrue(race1.isThereRacerThatHasNotBeenStarted());

		race1.startRace();
		assertTrue(race1.isThereRacerThatHasNotBeenStarted());
		race1.startRace();
		assertTrue(!race1.isThereRacerThatHasNotBeenStarted());

	}

}
