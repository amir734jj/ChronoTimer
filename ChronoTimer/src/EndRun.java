/**
 * EndRun Class 
 * 
 * Note that this class handles the end of a run
 *
 */
public class EndRun {

	/**
	 * triggerEndRun sets current race to finished and sets current race to null
	 * 
	 * @param   none
	 * @param   void
	 */
	public static void triggerEndRun() {
		if (specialCaseRace.weJustHadAnonymousRaceRunning) {
			RaceCollection.floatRacersToCurrentRace();
			RaceCollection.floatingRacers = null;
		}
		RaceCollection.justFinished = RaceCollection.CurrentRace;
		RaceCollection.CurrentRace = null;

	}
}
