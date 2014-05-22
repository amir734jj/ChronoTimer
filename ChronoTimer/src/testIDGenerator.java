import static org.junit.Assert.*;

import org.junit.Test;

public class testIDGenerator {

	@Test
	public void testIdGen() {
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

		Race race2 = new Race(200);
		race2.addRacer(test1);
		race2.addRacer(test2);
		RaceCollection.initialize();

		RaceCollection.addRace(race1);
		RaceCollection.addRace(race2);

		for (int i = 0; i < 10000; i++) {
			assertFalse(RaceCollection.isExistByRaceID(IDGenerator.idGen()));
		}
	}

}
