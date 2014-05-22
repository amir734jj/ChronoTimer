/**
 * The specialCaseRace class
 * 
 * Note that this class handles race types of special type
 *
 */
public class specialCaseRace {
	private static int[] startTimeForAnonymousRace = null;
	public static boolean weJustHadAnonymousRaceRunning = false;

	/**
	 * triggerSpecialCase  triggers a special case race
	 * 
	 * @param   int		
	 * @return  void
	 */
	public static void triggerSpecialCase(int i) {
		weJustHadAnonymousRaceRunning = true;
		if (i == 0) {
			specialCaseRace.startTimeForAnonymousRace = Time.getTime();
		} else if (i == 1) {
			Racer temp = new Racer(IDGenerator.idGen());
			temp.setStart(specialCaseRace.startTimeForAnonymousRace);
			temp.setFinish(Time.getTime());
			temp.setStarted(true);
			temp.setFinished(true);
			temp.setElapsedTime();
			RaceCollection.addFloatingRacer(temp);
		} else {
			throw new IllegalStateException();
		}

	}
}
