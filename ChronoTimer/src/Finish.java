/**
 * The Finish class 
 * 
 * Note that this class handles the Finish of a race
 *
 */
public class Finish {

	/**
	 * triggerFin sets CurrentRace to finished
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void triggerFin() {
		
		RaceCollection.CurrentRace.finishRace();

	}
}
