import static org.junit.Assert.*;

import org.junit.Test;

public class testRcl {

	@Test
	public void testRecall() {
		Rcl.setChannel(0);
		assertTrue(Rcl.getChannel() == 0);
		Rcl.setChannel(1);
		assertTrue(Rcl.getChannel() == 1);
		assertFalse(Rcl.getChannel() == 2);
		assertFalse(Rcl.getChannel() == 3);
	}

}
