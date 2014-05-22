import static org.junit.Assert.*;

import org.junit.Test;

public class testEndRun {

	@Test
	public void testTriggerEndRun() {

		RaceCollection.initialize();
		NewRun.newRace();
		RaceCollection.CurrentRace.set_RACE_TYPE(2);
		assertTrue(!RaceCollection.CurrentRace.isFinished());
		EndRun.triggerEndRun();
		assertTrue(RaceCollection.CurrentRace == null);
	}

}
