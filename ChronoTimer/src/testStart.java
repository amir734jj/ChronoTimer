import static org.junit.Assert.*;

import org.junit.Test;

public class testStart {

	@Test
	public void test() {
		RaceCollection.initialize();
		NewRun.newRace();
		Channel.initializeEveryThing();
		Channel.connect(0);
		Channel.connect(1);
		Channel.enable(0);
		Channel.enable(1);

		assertTrue(!RaceCollection.CurrentRace.isStarted());
		//Start.triggerStart();
		RaceCollection.CurrentRace.startRace();
		assertTrue(RaceCollection.CurrentRace.isStarted());

	}

}
