import static org.junit.Assert.*;

import org.junit.Test;

public class testEvent {
	@Test
	public void testEventType() {
		RaceCollection.initialize();
		Channel.initializeEveryThing();

		Racer r1 = new Racer(1);
		r1.setRunNumber(2);
		int[] r1start = { 1, 2, 3 };
		r1.setStart(r1start);
		int[] r1finish = { 3, 2, 1 };
		r1.setFinish(r1finish);
		r1.setElapsedTime();
		r1.setStarted(true);
		r1.setFinished(false);

		Racer r2 = new Racer(2);
		r2.setRunNumber(4);
		int[] r2start = { 3, 0, 2 };
		r2.setStart(r2start);
		int[] r2finish = { 8, 4, 4 };
		r2.setFinish(r2finish);
		r2.setElapsedTime();
		r2.setStarted(true);
		r2.setFinished(true);

		Racer r3 = new Racer(3);
		r3.setRunNumber(1);
		int[] r3start = { 4, 3, 1 };
		r3.setStart(r3start);
		int[] r3finish = { 4, 5, 7 };
		r3.setFinish(r3finish);
		r3.setElapsedTime();
		r3.setStarted(false);
		r3.setFinished(false);

		Racer r4 = new Racer(4);
		r3.setRunNumber(3);
		int[] r4start = { 4, 1, 1 };
		r4.setStart(r4start);
		int[] r4finish = { 4, 2, 7 };
		r4.setFinish(r4finish);
		r4.setElapsedTime();
		r4.setStarted(false);
		r4.setFinished(false);

		Racer r5 = new Racer(5);
		r5.setRunNumber(4);
		int[] r5start = { 1, 0, 8 };
		r5.setStart(r5start);
		int[] r5finish = { 3, 4, 0 };
		r5.setFinish(r5finish);
		r5.setElapsedTime();
		r5.setStarted(false);
		r5.setFinished(true);

		Racer r6 = new Racer(6);
		r6.setRunNumber(5);
		int[] r6start = { 4, 3, 0 };
		r3.setStart(r6start);
		int[] r6finish = { 4, 3, 0 };
		r6.setFinish(r6finish);
		r6.setElapsedTime();
		r6.setStarted(false);
		r6.setFinished(false);

		Race race1 = new Race(10);
		race1.addRacer(r1);
		race1.addRacer(r2);
		race1.addRacer(r3);

		Race race2 = new Race(11);
		race2.addRacer(r4);
		race2.addRacer(r5);
		race2.addRacer(r6);

		RaceCollection.addRace(race1);
		RaceCollection.addRace(race2);
		RaceCollection.CurrentRace = race2;

		Event.triggerEvent(0);
		assertEquals(RaceCollection.CurrentRace.get_RACE_TYPE(), 0);
		Event.triggerEvent(1);
		assertEquals(RaceCollection.CurrentRace.get_RACE_TYPE(), 1);

	}
}
