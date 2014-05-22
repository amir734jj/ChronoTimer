import java.util.Iterator;
import java.util.Random;

/**
 * The IDGenerator class
 * 
 * Note that this class handles the generation of a new racers id number
 *
 */
public class IDGenerator {
	/**
	 * idGen  generates new id for racer
	 * 
	 * @param   none
	 * @return  int  a new racer's id
	 */
	public static int idGen() {
		Random ran = new Random();
		int x;
		x = ran.nextInt(100000) + 1;
		while (RaceCollection.isExistByRaceID(x) && isExitInSomeRace(x))
			x = ran.nextInt(100) + 1;
		return x;
	}

	/**
	 * isExitInSomeRace  determines if ID already owned by a racer
	 * 
	 * @param   int ID to check
	 * @return  boolean is already owned by another racer
	 */
	public static boolean isExitInSomeRace(int i) {
		Iterator<Race> it = RaceCollection.array.iterator();

		while (it.hasNext())
			if (it.next().getRacerByRacerID(i) != null)
				return true;
		return false;
	}
}
