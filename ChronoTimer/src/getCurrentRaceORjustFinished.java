/**
 * The getCurrentRaceORjustFinished class 
 * 
 * Note that this class handles the return of current race or one which has just finished
 *
 */
public class getCurrentRaceORjustFinished {
	/**
	 * get  returns the current race 
	 * 
	 * @param   none
	 * @return  race   Either the current race (if currently running) or one that just finished
	 */
	public static Race get() {
		if (!specialCaseRace.weJustHadAnonymousRaceRunning)
			return RaceCollection.CurrentRace;
		else
			return RaceCollection.justFinished;
	}
}
