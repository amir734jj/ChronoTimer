import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class testDnf {

	@Test
	public void testTriggerDnf() {
		int[] startTime = new int[3];
		startTime[0] = 4;
		startTime[1] = 5;
		startTime[2] = 6;
		int[] finTime = new int[3];
		finTime[0] = 9;
		finTime[1] = 9;
		finTime[2] = 9;
		int[] tTime = new int[3];
		tTime[0] = 0;
		tTime[1] = 0;
		tTime[2] = 0;
		Racer test1 = new Racer(121);
		Race race1 = new Race(100);
		race1.addRacer(test1);
		RaceCollection.initialize();
		RaceCollection.addRace(race1);
		Time.setTime(startTime);
		Start.triggerStart();

		assertTrue(Arrays.equals(startTime, test1.getStart()));
		test1.isFinished = false;
		Dnf.triggerDnf();

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				Arrays.toString(Time.getTime());
			}
		}, 5000);
		assertTrue(Arrays.equals(test1.getFinish(), tTime));
		assertTrue(test1.isFinished());
	}
}
