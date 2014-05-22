import static org.junit.Assert.*;

import org.junit.Test;

public class testEventTest {

	@Test
	public void test() {
		Channel.initializeEveryThing();
		RaceCollection.initialize();
		NewRun.newRace();
		RaceCollection.CurrentRace.set_RACE_TYPE(1);
		assertTrue(RaceCollection.CurrentRace.get_RACE_TYPE() == 1);
		RaceCollection.initialize();
		NewRun.newRace();
		RaceCollection.CurrentRace.set_RACE_TYPE(2);
		assertTrue(RaceCollection.CurrentRace.get_RACE_TYPE() == 2);
		RaceCollection.initialize();
		NewRun.newRace();
		RaceCollection.CurrentRace.set_RACE_TYPE(3);
		assertTrue(RaceCollection.CurrentRace.get_RACE_TYPE() == 3);
		/*
		 * RaceCollection.initialize(); NewRun.newRace();
		 * 
		 * Channel.initializeEveryThing(); Channel.connect(0);
		 * Channel.enable(0); Channel.connect(1); Channel.enable(1);
		 * Channel.connect(2); Channel.enable(2); Channel.connect(3);
		 * Channel.enable(3);
		 * 
		 * Race test = RaceCollection.CurrentRace; test.set_RACE_TYPE(2);
		 * 
		 * Racer r = new Racer(99); Racer r1 = new Racer(100); Racer r2 = new
		 * Racer(101);
		 * 
		 * test.addRacer(r); test.addRacer(r1); test.addRacer(r2);
		 * 
		 * int[] startTime = new int[3]; startTime[0] = 1; startTime[1] = 1;
		 * startTime[2] = 1;
		 * 
		 * r.setStart(startTime); r1.setStart(startTime);
		 * r2.setStart(startTime);
		 * 
		 * Trig.triggerTrig(0);
		 * 
		 * System.out.println(RaceCollection.getRaceByRaceID(test.getRaceID()));
		 * assertTrue(r.getStart()==r1.getStart());
		 * assertTrue(r1.getStart()==r2.getStart());
		 */
	}

}
