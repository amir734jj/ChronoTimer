/**
 * Dnf Class 
 * 
 * Class handles Dnf cases
 *
 */
public class Dnf {

	/**
	 * triggerDnf sets current race to DNF case
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void triggerDnf() {

		RaceCollection.CurrentRace.specialCase_DNF();
	}
}