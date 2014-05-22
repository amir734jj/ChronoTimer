import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class testLog {

	@Test
	public void testLogFormaat() {
		Log.resetLog();

		Log.write("a1");
		Log.write("a2");
		Log.write("a3");
		Log.write("a4");
		Log.write("a5");
		Log.write("a6");
		Log.write("a7");

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(Main.TempFolder
					+ "\\System.log"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			assertEquals(everything.split("\n").length, 7);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.resetLog();
	}

}
