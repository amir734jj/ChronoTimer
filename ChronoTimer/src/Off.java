/**
 * The Off class
 * 
 * Note that this class handles shutdown of system using off switch/trigger
 *
 */
public class Off {
	/**
	 * triggerOff  handles the event trigger switch Off was used
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void triggerOff() {
		Main.isOn = false;
		Channel.disableAllChannels();
		RaceCollection.CurrentRace = null;
	}

}
