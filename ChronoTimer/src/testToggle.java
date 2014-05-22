import static org.junit.Assert.*;

import org.junit.Test;


public class testToggle {
	@Test
	public void test() {
		Channel.initialize();
		Channel.connect(0);
		Channel.enable(0);
		assertTrue(Channel.getState(0));
		Toggle.toggleChannel(0);
		assertTrue(!Channel.getState(0));
		Toggle.toggleChannel(0);
		assertTrue(Channel.getState(0));
	}

}


