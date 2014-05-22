import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.jdom2.Element;

/**
 * The Race class
 * 
 * Note that this class handles all objects and methods required to handle a
 * Race
 * 
 */
public class Race {

	int raceID;
	int num_racers;
	boolean isFinished = false;
	boolean isStarted = false;
	int _RACE_TYPE = 0;

	// _RACE_TYPE --> 0 Multiple Start Multiple Finish
	// _RACE_TYPE --> 1 Single Start Multiple Finish
	// _RACE_TYPE --> 2 Single Start Single Finish

	Queue<Racer> racers = new LinkedList<Racer>();

	/**
	 * Constructor Methods
	 * 
	 */
	public Race(int raceID) {
		this.num_racers = 0;
		this.raceID = raceID;
	}

	public Race(int raceID, int _RACE_TYPE) {
		this(raceID);
		set_RACE_TYPE(_RACE_TYPE);
	}

	/**
	 * getNumRacers returns the number of racers in race
	 * 
	 * @param none
	 * @return int number of racers
	 */
	public int getNumRacers() {
		return num_racers;
	}

	/**
	 * getRaceID returns the race ID
	 * 
	 * @param none
	 * @return int race's ID
	 */
	public int getRaceID() {
		return raceID;
	}

	/**
	 * getNumRacers returns the number of racers in race
	 * 
	 * @param none
	 * @return int number of racers
	 */
	public int num_racers() {
		return this.num_racers;
	}

	/**
	 * getRacerByRacerID returns the racer who owns given ID
	 * 
	 * @param int a racer ID number
	 * @return Racer whose Id belongs
	 */
	public Racer getRacerByRacerID(int i) {
		Iterator<Racer> it = racers.iterator();
		Racer r;
		while (it.hasNext()) {
			r = it.next();
			if (r.getRacerID() == i)
				return r;
		}
		return null;

	}

	/**
	 * addRacer adds a racer to race
	 * 
	 * @param Racer
	 *            to add to race
	 * @return boolean racer added successfully
	 */
	public boolean addRacer(Racer r) {
		Iterator<Racer> it = racers.iterator();
		while (it.hasNext()) {
			if (r.equals(it.next()))
				return false;
		}
		racers.add(r);
		r.runNumber++;
		this.num_racers++;
		return true;
	}

	/**
	 * isExistByRacerID checks if racer exists in race
	 * 
	 * @param Racer
	 *            to check if included in race
	 * @return boolean racer exists
	 */
	public boolean isExistByRacerID(int id) {
		Iterator<Racer> it = racers.iterator();
		while (it.hasNext()) {
			if (id == it.next().getRacerID())
				return true;
		}
		return false;
	}

	/**
	 * removeRacer removes selected racer from race
	 * 
	 * @param Racer
	 *            to be removed
	 * @return boolean racer removed successfully
	 */
	public boolean removeRacer(Racer r) {
		if (!this.isExistByRacerID(r.getRacerID()))
			return false;
		else {
			racers.remove(r);
			this.num_racers--;
			return true;
		}
	}

	/**
	 * toString returns string of Race information
	 * 
	 * @param none
	 * @return String of state of Race
	 */
	public String toString() {
		Iterator<Racer> it = racers.iterator();
		String s = "RaceID:" + this.getRaceID() + "\t Number Of Racers: "
				+ this.getNumRacers() + "\t isStarted: " + this.isStarted()
				+ "\t isFinished: " + this.isFinished() + "\t Race Type: "
				+ ((this.get_RACE_TYPE() == 0) ? "PARIND::0" : "GRP::1") + "\n";
		while (it.hasNext()) {
			s = s + it.next().toString() + "\n";
		}
		return s;
	}

	/**
	 * XMLExport handles export to XML
	 * 
	 * @param Race
	 *            to export
	 * @param Element
	 *            XML Element for export
	 * @return void
	 */
	public static void XMLExport(Race race, Element element) {
		try {
			Element theRoot = new Element("Race");
			theRoot.setAttribute("RaceID", Integer.toString(race.getRaceID()));
			theRoot.setAttribute("NumberOfRacers",
					Integer.toString(race.getNumRacers()));
			theRoot.setAttribute("isFinished",
					Boolean.toString(race.isFinished()));
			theRoot.setAttribute("isStarted",
					Boolean.toString(race.isStarted()));
			theRoot.setAttribute("RaceType",
					Integer.toString(race.get_RACE_TYPE()));

			Iterator<Racer> it = race.racers.iterator();

			while (it.hasNext())
				it.next().XMLExport(theRoot);

			element.addContent(theRoot);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * XMLImport handles Race import to XML
	 * 
	 * @param Element
	 *            XML Element for import
	 * @return Race added to system
	 */
	public static Race XMLImport(Element element) {

		try {
			Race race = new Race(Integer.parseInt(element.getAttributeValue(
					"RaceID").toString()));
			race.setFinished(Boolean.parseBoolean(element
					.getAttributeValue("isFinished")));
			race.setStarted(Boolean.parseBoolean(element
					.getAttributeValue("isStarted")));
			race.set_RACE_TYPE(Integer.parseInt(element
					.getAttributeValue("RaceType")));
			List<Element> list = element.getChildren();

			Iterator<Element> it = list.iterator();
			while (it.hasNext()) {
				Element tempEl = it.next();
				Racer tempRa = new Racer(Integer.parseInt(tempEl
						.getAttributeValue("RacerID")));
				tempRa.XMLImport(tempEl);
				tempRa.setRunNumber(tempRa.getRunNumber() - 1);
				race.addRacer(tempRa);
			}
			if (Integer.parseInt(element.getAttributeValue("NumberOfRacers")) != race
					.getNumRacers())
				throw new IllegalStateException();
			return race;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * equals if Race object equals this Race object
	 * 
	 * @param Race
	 *            check
	 * @return boolean races are equal
	 */
	@Override
	public boolean equals(Object obj) {
		Race temp;
		if (obj instanceof Race)
			temp = (Race) obj;
		else
			throw new IllegalArgumentException();

		if (temp.getNumRacers() != this.getNumRacers()
				&& temp.isFinished() != this.isFinished()
				&& temp.isStarted() != this.isStarted)
			return false;
		else {
			Iterator<Racer> it1 = this.racers.iterator();
			Iterator<Racer> it2 = temp.racers.iterator();
			while (it1.hasNext()) {
				if (!it1.next().equals(it2.next()))
					return false;
			}
			return true;
		}
	}

	/**
	 * deepEqualityTest if generic object equals this Race object
	 * 
	 * @param object
	 *            to be checked
	 * @return boolean is of type race and is equal with this Race
	 */
	public boolean deepEqualityTest(Object obj) {
		Race temp;
		if (obj instanceof Race)
			temp = (Race) obj;
		else
			throw new IllegalArgumentException();

		if (temp.getNumRacers() != this.getNumRacers()
				&& temp.isFinished() != this.isFinished()
				&& temp.isStarted() != this.isStarted
				&& temp.get_RACE_TYPE() != this.get_RACE_TYPE())
			return false;
		else {
			Iterator<Racer> it1 = this.racers.iterator();
			Iterator<Racer> it2 = temp.racers.iterator();
			while (it1.hasNext()) {
				if (!it1.next().deepEqualityTest((it2.next())))
					return false;
			}
			return true;
		}
	}

	/**
	 * setNum_racers sets number of racers to specified value
	 * 
	 * @param int number to set
	 * @return void
	 */
	public void setNum_racers(int num_racers) {
		this.num_racers = num_racers;
	}

	/**
	 * isStarted gets isStarted
	 * 
	 * @param none
	 * @return boolean isStarted value
	 */
	public boolean isStarted() {
		return isStarted;
	}

	/**
	 * setStarted sets boolean value isStarted
	 * 
	 * @param boolean value to set
	 * @return void
	 */
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/**
	 * isFinished gets isFinished
	 * 
	 * @param none
	 * @return boolean isFinished value
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * setFinish sets boolean value setFinish
	 * 
	 * @param boolean value to set
	 * @return void
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * setRaceID sets raceID to value given
	 * 
	 * @param int raceID to set
	 * @return void
	 */
	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}

	/**
	 * get_RACE_TYPE returns this race's type
	 * 
	 * @param none
	 * @return int this race's type
	 */
	public int get_RACE_TYPE() {
		return _RACE_TYPE;
	}

	/**
	 * set_RACE_TYPE sets race type to value given
	 * 
	 * @param int race type to set
	 * @return void
	 */
	public void set_RACE_TYPE(int _RACE_TYPE) {
		this._RACE_TYPE = _RACE_TYPE;
	}

	/**
	 * startRace handles the start of a race
	 * 
	 * @param none
	 * @return void
	 */
	public void startRace() {
		if (_RACE_TYPE == 0) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isStarted) {
					temp.setStart(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(false);
					break;
				}
			}
			this.setFinished(false);
			this.setStarted(true);

		} else if (_RACE_TYPE == 1) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isStarted) {
					temp.setStart(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(false);
				}
			}
			this.setFinished(false);
			this.setStarted(true);
		}

		else if (_RACE_TYPE == 2) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isStarted) {
					temp.setStart(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(false);
				}
			}
			this.setFinished(false);
			this.setStarted(true);
		}
	}

	/**
	 * finishRace handles the finish of a race
	 * 
	 * @param none
	 * @return void
	 */
	public void finishRace() {
		if (_RACE_TYPE == 0) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isFinished) {
					temp.setFinish(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(true);
					temp.setElapsedTime();

					if (!it.hasNext())
						this.setFinished(true);
					break;
				}
			}
			this.setStarted(true);
		}

		else if (_RACE_TYPE == 1) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isFinished) {
					temp.setFinish(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(true);
					temp.setElapsedTime();

					if (!it.hasNext())
						this.setFinished(true);
					break;
				}
			}
			this.setStarted(true);
		}

		else if (_RACE_TYPE == 2) {
			Iterator<Racer> it = racers.iterator();
			Racer temp;
			while (it.hasNext()) {
				temp = it.next();
				if (!temp.isFinished) {
					temp.setFinish(Time.getTime());
					temp.setStarted(true);
					temp.setFinished(true);
					temp.setElapsedTime();
				}
			}
			this.setFinished(true);
			this.setStarted(true);
		}

	}

	/**
	 * specialCase_DNF handles the special case race is DNF
	 * 
	 * @param none
	 * @return void
	 */
	public void specialCase_DNF() {

		Iterator<Racer> it = racers.iterator();
		Racer temp;
		int[] ar = { 0, 0, 0 };
		while (it.hasNext()) {
			temp = it.next();
			if (!temp.isFinished) {
				temp.setFinish(ar);
				temp.setStarted(true);
				temp.setFinished(true);
				if (!it.hasNext())
					this.setFinished(true);
				break;
			}
		}
		this.setStarted(true);
	}

	/**
	 * isThereRacerThatHasNotBeenStarted checks if a racer has not yet started
	 * this race
	 * 
	 * @param none
	 * @return boolean a single racer has not started
	 */
	public boolean isThereRacerThatHasNotBeenStarted() {
		Iterator<Racer> it = this.racers.iterator();
		while (it.hasNext())
			if (!it.next().isStarted())
				return true;
		return false;
	}

}
