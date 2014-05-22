/**
 * The Reset class
 * 
 * Note that this class handles event a Reset is triggered
 *
 */
public class Reset {

	/**
	 * trigegrReset  handles the event reset switch/signal was triggered
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void trigegrReset() {
		Off.triggerOff();
		On.triggerOn();
	}
}
