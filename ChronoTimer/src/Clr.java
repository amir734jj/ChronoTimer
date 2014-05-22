import java.util.Iterator;
/**
 * The Clear class 
 * 
 * Note that this class removes racers in RaceCollection
 *
 */
public class Clr {

	/**
	 * triggerClr clears a racer
	 * 
	 * @param   int  racer Id to be removed
	 * @return  void
	 */
	public static void triggrtClr(int i) {
		if (RaceCollection.isExistByRacerID(i)) {
			Iterator<Race> it = RaceCollection.array.iterator();
			Race temp;
			while (it.hasNext()) {
				temp = it.next();
				if (temp.isExistByRacerID(i)) {
					RaceCollection.addFloatingRacer(temp.getRacerByRacerID(i));
					temp.removeRacer(temp.getRacerByRacerID(i));
				}
			}
		}
	}
}
