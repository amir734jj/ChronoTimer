import java.io.*;
/**
 * The Log class
 * 
 * Note that this class handles the write to a log file
 *
 */
public class Log {

	protected static boolean hasChanged = false;

	/**
	 * write  given string to log file
	 * 
	 * @param   string	to be written to log file
	 * @return  void
	 */
	public static void write(String s) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter(Main.TempFolder + "\\System.log", true)));
			writer.print(s);
			writer.print(System.lineSeparator());
			writer.close();
			hasChanged = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * resetLog  resets log file to initial state
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void resetLog() {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter(Main.TempFolder + "\\System.log", false)));
			writer.print("");
			writer.close();
			hasChanged = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
