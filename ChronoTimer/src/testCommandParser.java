import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class testCommandParser {

	@Test
	public void testCheckCommand() {
		assertTrue(new CommandParser(null, false).checkCommand("ON"));
		assertTrue(new CommandParser(null, false).checkCommand("OFF"));
		assertTrue(new CommandParser(null, false).checkCommand("EXIT"));
		assertTrue(new CommandParser(null, false).checkCommand("RESET"));
		assertTrue(new CommandParser(null, false).checkCommand("TIME"));
		assertTrue(new CommandParser(null, false).checkCommand("TOGGLE"));
		assertTrue(new CommandParser(null, false).checkCommand("CONN"));
		assertTrue(new CommandParser(null, false).checkCommand("DISC"));
		assertTrue(new CommandParser(null, false).checkCommand("EVENT"));
		assertTrue(new CommandParser(null, false).checkCommand("NEWRUN"));
		assertTrue(new CommandParser(null, false).checkCommand("ENDRUN"));
		assertTrue(new CommandParser(null, false).checkCommand("EVENT"));
		assertTrue(new CommandParser(null, false).checkCommand("PRINT"));
		assertTrue(new CommandParser(null, false).checkCommand("EXPORT"));
		assertTrue(new CommandParser(null, false).checkCommand("CLR"));
		assertTrue(new CommandParser(null, false).checkCommand("SWAP"));
		assertTrue(new CommandParser(null, false).checkCommand("RCL"));
		assertTrue(new CommandParser(null, false).checkCommand("START"));
		assertTrue(new CommandParser(null, false).checkCommand("FIN"));
		assertTrue(new CommandParser(null, false).checkCommand("TRIG"));

		assertFalse(new CommandParser(null, false).checkCommand("test1"));
		assertFalse(new CommandParser(null, false).checkCommand("test2"));
		assertFalse(new CommandParser(null, false).checkCommand("test3"));
		assertFalse(new CommandParser(null, false).checkCommand(null));
	}

	@Test
	public void testConstructor() {
		CommandParser cp = new CommandParser("./src/CHRONO TEST DATA.txt", true);
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader("./src/CHRONO TEST DATA.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str;
		ArrayList<String> st = new ArrayList<String>();
		try {
			while ((str = br.readLine()) != null && !str.isEmpty())
				st.add(str);
		} catch (IOException e) {

			e.printStackTrace();
		}
		assertTrue(st.equals(cp.s));
		assertEquals(st.toString(), cp.s.toString());
	}

}
