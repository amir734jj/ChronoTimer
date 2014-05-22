import static org.junit.Assert.*;
import org.junit.Test;

public class testTrigTest {
	@Test
	public void testTrig() {
		RaceCollection.initialize();
		NewRun.newRace();
		
		Channel.initializeEveryThing();
		Channel.connect(0);
		Channel.enable(0);
		Channel.connect(1);
		Channel.enable(1);
		Channel.connect(2);
		Channel.enable(2);
		Channel.connect(3);
		Channel.enable(3);
		Channel.connect(4);
		Channel.enable(4);
		Channel.connect(5);
		Channel.enable(5);
		Channel.connect(6);
		Channel.enable(6);
		Channel.connect(7);
		Channel.enable(7);
		Channel.connect(8);
		Channel.enable(8);
		
		Race test = RaceCollection.CurrentRace;
		
		Racer r = new Racer(99);
		Racer r1 = new Racer(100);
		Racer r2 = new Racer(101);
		Racer r3 = new Racer(102);
		Racer r4 = new Racer(103);
		Racer r5 = new Racer(104);
		Racer r6 = new Racer(105);
		Racer r7 = new Racer(106);
		test.addRacer(r);
		test.addRacer(r1);
		test.addRacer(r2);
		test.addRacer(r3);
		test.addRacer(r4);
		test.addRacer(r5);
		test.addRacer(r6);
		test.addRacer(r7);
		
		//Test Race created with racer that has not started or finished
		assertTrue(!(r.isStarted || r.isFinished));
		assertTrue(!(r1.isStarted || r1.isFinished));
		assertTrue(!(r2.isStarted || r2.isFinished));
		assertTrue(!(r3.isStarted || r3.isFinished));
		assertTrue(!(r4.isStarted || r4.isFinished));
		assertTrue(!(r5.isStarted || r5.isFinished));
		assertTrue(!(r6.isStarted || r6.isFinished));
		assertTrue(!(r7.isStarted || r7.isFinished));	
		Trig.triggerTrig(0);
		Trig.triggerTrig(1);
		Trig.triggerTrig(2);
		Trig.triggerTrig(3);
		Trig.triggerTrig(4);
		Trig.triggerTrig(5);
		Trig.triggerTrig(6);
		Trig.triggerTrig(7);
		
		//Test Race created with racer that has started but has not finished
		assertTrue(r.isStarted && !r.isFinished);
		assertTrue(r1.isStarted && !r1.isFinished);
		assertTrue(r2.isStarted && !r2.isFinished);
		assertTrue(r3.isStarted && !r3.isFinished);
		assertTrue(r4.isStarted && !r4.isFinished);
		assertTrue(r5.isStarted && !r5.isFinished);
		assertTrue(r6.isStarted && !r6.isFinished);
		assertTrue(r7.isStarted && !r7.isFinished);
		Trig.triggerTrig(0);
		Trig.triggerTrig(1);
		Trig.triggerTrig(2);
		Trig.triggerTrig(3);
		Trig.triggerTrig(4);
		Trig.triggerTrig(5);
		Trig.triggerTrig(6);
		Trig.triggerTrig(7);
		
		//Test Race created with racer that has started and finished
		assertTrue(r.isStarted && r.isFinished);
		assertTrue(r1.isStarted && r1.isFinished);
		assertTrue(r2.isStarted && r2.isFinished);
		assertTrue(r3.isStarted && r3.isFinished);
		assertTrue(r4.isStarted && r4.isFinished);
		assertTrue(r5.isStarted && r5.isFinished);
		assertTrue(r6.isStarted && r6.isFinished);
		assertTrue(r7.isStarted && r7.isFinished);
		
	}
	}


