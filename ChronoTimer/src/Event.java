/**
 * The Event class 
 * 
 * Note that this class handles the triggering of an even
 *
 */
public class Event {

	/**
	 * triggerEvent 
	 * 
	 * @param   int  event type current race to be set
	 * @param   void
	 */
	public static void triggerEvent(int i) {
		RaceCollection.CurrentRace.set_RACE_TYPE(i);
	}
}
