/**
 * The On class
 * 
 * Note that this class handles startup of system using off switch/trigger
 *
 */
public class On {
	/**
	 * triggerOn  handles the event trigger switch On was used
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void triggerOn() {
		Main.isOn = true;
		RaceCollection.CurrentRace = null;
		if (RaceCollection.array == null)
			RaceCollection.firstTimeInitialize();
		NewRun.newRace();

	}

}
