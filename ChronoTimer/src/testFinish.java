import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class testFinish {

	@Test
	public void testTriggerFin() {
		RaceCollection.initialize();
		Channel.initializeEveryThing();
		int[] startTime = new int[3];
		startTime[0] = 1;
		startTime[1] = 2;
		startTime[2] = 3;
		int[] finishTime = new int[3];
		finishTime[0] = 2;
		finishTime[1] = 3;
		finishTime[2] = 4;
		int[] tTime = new int[3];
		tTime[0] = 1;
		tTime[1] = 1;
		tTime[2] = 1;
		Racer test1 = new Racer(121);
		Race race1 = new Race(100);
		race1.addRacer(test1);
		RaceCollection.initialize();
		RaceCollection.addRace(race1);
		Time.setTime(startTime);
		Start.triggerStart();
		assertTrue(Arrays.equals(test1.elapsedtime(), startTime));
		Time.setTime(finishTime);
		Finish.triggerFin();
		assertTrue(test1.isStarted());
		assertTrue(test1.isFinished());
	}
}
