/**
 * The Start class
 * 
 * Note that this class handles event Start a race
 *
 */
public class Start {
	/**
	 * triggerStart  starts a race
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void triggerStart() {

		RaceCollection.CurrentRace.startRace();

	}
}
