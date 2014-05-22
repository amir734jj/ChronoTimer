/**
 * The NewRun class
 * 
 * Note that this class handles event a new run is created
 *
 */
public class NewRun {
	/**
	 * newRace  generates and adds a new race to RaceCollection
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void newRace() {
		specialCaseRace.weJustHadAnonymousRaceRunning = false;
		RaceCollection.addRace(new Race(IDGenerator.idGen()));
	}

}
