import static org.junit.Assert.*;

import org.junit.Test;

public class testChannel {

	@Test
	public void testChannels() {
		Channel.initializeEveryThing();
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getState(i) == false);
	}

	@Test
	public void testConnect() {
		Channel.initializeEveryThing();
		assertFalse(Channel.getConnectState(0));
		Channel.connect(0);
		assertTrue(Channel.getConnectState(0));
		for (int i = 0; i < 12; i++)
			Channel.connect(i);
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getConnectState(i) == true);
		for (int i = 0; i < 12; i++)
			Channel.disconnect(i);
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getConnectState(i) == false);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalEanble() {
		Channel.initializeEveryThing();
		Channel.toggle(1);
		Channel.disable(1);
		Channel.enable(1);
	}

	@Test
	public void testEanble() {
		Channel.initializeEveryThing();
		for (int i = 0; i < 12; i++)
			Channel.connect(i);
		for (int i = 0; i < 12; i++)
			Channel.enable(i);
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getEnableState(i) == true);
		for (int i = 0; i < 12; i++)
			Channel.disable(i);
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getEnableState(i) == false);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalToggle() {
		Channel.initializeEveryThing();
		Channel.disconnect(0);
		Channel.toggle(0);
	}

	@Test
	public void testToggle() {
		Channel.initializeEveryThing();
		for (int i = 0; i < 12; i++)
			Channel.connect(i);
		for (int i = 0; i < 12; i++)
			Channel.toggle(i);
		for (int i = 0; i < 12; i++)
			assertTrue(Channel.getEnableState(i) == true);

	}

}
