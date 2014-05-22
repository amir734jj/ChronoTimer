/**
 * The Print class
 * 
 * Note that this class handles the Print state when called
 *
 */
public class Print {
	/**
	 * printCurrentRace  handles the print of the current race
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void printCurrentRace() {

		try {
			System.out.println("Result:\n"
					+ getCurrentRaceORjustFinished.get().toString());
			System.out.println("\n" + Channel.toStringOverrided());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
