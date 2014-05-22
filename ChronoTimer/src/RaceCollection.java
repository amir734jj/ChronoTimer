import java.util.ArrayList;
import java.util.Iterator;

/**
 * The RaceCollection class
 * 
 * Note that this class handles methods for collection of racers
 *
 */
public class RaceCollection {

	public static ArrayList<Race> array;
	public static ArrayList<Racer> floatingRacers;
	public static Race CurrentRace = null;
	public static Race justFinished = null;

	/**
	 * initialize  handles the initialization of of a new race (all parameters)
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void initialize() {
		CurrentRace = null;
		array = null;
		array = new ArrayList<Race>();
		floatingRacers = null;
		floatingRacers = new ArrayList<Racer>();
	}

		/**
	 * firstTimeInitialize  handles the initialization of of a new race (less safe as "initialize" method)
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void firstTimeInitialize() {
		floatingRacers = new ArrayList<Racer>();
		array = new ArrayList<Race>();
	}

	/**
	 * addRace  adds given race to RaceCollection
	 * 
	 * @param   Race	race to add
	 * @return  boolean	race added successfully 
	 */
	public static boolean addRace(Race e) {
		Iterator<Race> it = array.iterator();
		while (it.hasNext()) {
			if (e.getRaceID() == it.next().getRaceID())
				return false;
		}
		array.add(e);
		if (CurrentRace == null)
			CurrentRace = e;
		return true;
	}

	/**
	 * removeRace  handles the removal of a Race from RaceCollection
	 * @param   Race	to be removed
	 * @return  boolean		race removed successfully
	 */
	public static boolean removeRace(Race e) {
		Iterator<Race> it = array.iterator();
		while (it.hasNext()) {
			if (e.getRaceID() == it.next().getRaceID()) {
				it.remove();
				if (CurrentRace == e)
					CurrentRace = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * isExistByRaceID  returns if a race exists by matching ID
	 * 
	 * @param   int 	Race ID	
	 * @return  boolean		Race was found in RaceCollection
	 */
	public static boolean isExistByRaceID(int id) {
		Iterator<Race> it = array.iterator();
		while (it.hasNext()) {
			if (it.next().raceID == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * isExistByRacerID  returns if there exists a racer in RaceCollection given Racer ID
	 * 
	 * @param   int 	Racer ID 
	 * @return  boolean		Racer was found
	 */
	public static boolean isExistByRacerID(int id) {
		Iterator<Race> it = array.iterator();
		while (it.hasNext()) {
			if (it.next().isExistByRacerID(id)) {
				return true;
			}
		}
		return false;
	}

			/**
	 * addFloatingRacer  handles the addition of a floating racer
	 * 
	 * @param   Racer	to add
	 * @return  void
	 */
	public static void addFloatingRacer(Racer r) {
		floatingRacers.add(r);
	}

	/**
	 * toStringOverrided  is an override returns RaceCollection state in form of a String
	 * 
	 * @param   none
	 * @return  String		RaceCollection State
	 */
	public static String toStringOverrided() {
		String s = "";
		try {
			s = "Current Race's ID: "
					+ getCurrentRaceORjustFinished.get().getRaceID()
					+ "\tNumber of Races: " + array.size() + "\n";
			Iterator<Race> it = RaceCollection.array.iterator();
			while (it.hasNext())
				s = s + it.next().toString() + "\n";

		} catch (Exception e) {

		}
		return s;
	}

	/**
	 * getRaceByRaceID  returns Race with matching ID
	 * 
	 * @param   int 	Race ID
	 * @return  Race	matching Race
	 */
	public static Race getRaceByRaceID(int id) {
		Iterator<Race> it = RaceCollection.array.iterator();
		while (it.hasNext()) {
			Race temp = it.next();
			if (temp.getRaceID() == id)
				return temp;
		}
		return null;

	}

		/**
	 * floatRacersToCurrentRace  takes floatingRacers and adds to new Race
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void floatRacersToCurrentRace() {
		Iterator<Racer> it = RaceCollection.floatingRacers.iterator();
		Race temp = new Race(IDGenerator.idGen());
		while (it.hasNext()) {
			temp.addRacer(it.next());
		}
		temp.set_RACE_TYPE(1);
		temp.setStarted(true);
		temp.setFinished(true);
		Iterator<Racer> itx = temp.racers.iterator();
		while (itx.hasNext())
			itx.next().setElapsedTime();
		RaceCollection.addRace(temp);
		RaceCollection.CurrentRace = temp;
	}

		/**
	 * Clean  removes all Races from RaceCollection that are empty
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void Clean() {
		Iterator<Race> it = RaceCollection.array.iterator();
		while (it.hasNext()) {
			Race temp = it.next();
			if (temp.num_racers() == 0 && temp != RaceCollection.CurrentRace)
				it.remove();
		}
	}
}
