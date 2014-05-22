import java.util.Arrays;
import org.jdom2.*;
/**
 * The Racer class
 * 
 * Note that this class handles an individual who could be competing in a race
 *
 */
public class Racer {

	boolean isFinished = false;
	boolean isStarted = false;
	int racerID;
	int runNumber = 0;
	int start[] = { 0, 0, 0 };
	int finish[] = { 0, 0, 0 };
	int runtime[] = { 0, 0, 0 };

		/**
	 * isStarted  returns if a race has been started
	 * 
	 * @param   none
	 * @return  boolean		Race has started
	 */
	public boolean isStarted() {
		return isStarted;
	}
	
		/**
	 * setStarted  sets a race to state
	 * 
	 * @param   boolean 	state to set
	 * @return  void
	 */
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/**
	 * Racer  sets racer ID
	 * 
	 * @param   int 	ID for racer 
	 * @return  void
	 */
	public Racer(int racerID) {
		this.racerID = racerID;
	}

	/**
	 * elapsedTime  returns lap times 
	 * 
	 * @param   none
	 * @return  int[3]	lap times
	 */
	public int[] elapsedtime() {
		int[] t = new int[3];
		int[] toReturn = new int[3];
		t = Time.elapsedTime(start, finish);
		toReturn[0] = t[0];
		toReturn[1] = t[1];
		toReturn[2] = t[2];
		return toReturn;
	}

	/**
	 * setElapsedTime  sets Racer elapsed time to runtime
	 * 
	 * @param   none
	 * @return  void
	 */
	public void setElapsedTime() {
		this.runtime = this.elapsedtime();
	}

	/**
	 * isFinished  returns if Racer is finished
	 * 
	 * @param   none
	 * @return  boolean		Racer is finished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * setFinished  sets a racer to finished state
	 * 
	 * @param   boolean 	state to set
	 * @return  void
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * getRacerID  returns Racer ID
	 * 
	 * @param   none
	 * @return  int 	ID of racer
	 */
	public int getRacerID() {
		return racerID;
	}

	/**
	 * setRacerID  sets a ID of Racer
	 * 
	 * @param   int 	ID to set to Racer
	 * @return  void
	 */
	public void setRacerID(int racerID) {
		this.racerID = racerID;
	}

	/**
	 * getStart  returns Racers start time
	 * 
	 * @param   void
	 * @return  int[] start time
	 */
	public int[] getStart() {
		return start;
	}

		/**
	 * setStart sets a racer start time
	 * 
	 * @param   int[] 	racer start time
	 * @return  void
	 */
	public void setStart(int[] start) {
		this.start = start;
	}

		/**
	 * getFinish 	gets Racer finish time
	 * 
	 * @param   none
	 * @return  int[]	finish time
	 */
	public int[] getFinish() {
		return finish;
	}

	/**
	 * setFinish  sets a racer's finish time
	 * 
	 * @param   int[]	 time to set as finish time
	 * @return  void
	 */
	public void setFinish(int[] finish) {
		this.finish = finish;
	}

	/**
	 * getRuntime  gets racers runtime
	 * 
	 * @param   none
	 * @return  int[]	runtime
	 */
	public int[] getRuntime() {
		return runtime;
	}

	/**
	 * setRuntime  sets a racer's runtime
	 * 
	 * @param   int[]	runtime to set
	 * @return  void
	 */
	public void setRuntime(int[] runtime) {
		this.runtime = runtime;
	}

	/**
	 * getRunNumber  gets racer's run number
	 * 
	 * @param  none
	 * @return  int	runners run number
	 */
	public int getRunNumber() {
		return runNumber;
	}

		/**
	 * setRunNumber sets a racer's run number
	 * 
	 * @param   int		run number to set
	 * @return  void	
	 */
	public void setRunNumber(int runNumber) {
		this.runNumber = runNumber;
	}

	/**
	 * toString  converts Racer's data to string
	 * 
	 * @param   none
	 * @return  String	Racer's data (state)
	 */
	public String toString() {
		return "RacerID: " + this.getRacerID() + "\t isStarted: "
				+ this.isStarted() + "\t isFinished: " + this.isFinished()
				+ "\t Racer Start Time: " + toTimeFormat(this.getStart())
				+ "\t Racer Finish Time: " + toTimeFormat(this.getFinish())
				+ "\t Racer Run Time: " + toTimeFormat(this.getRuntime())
				+ "\t Run Number: " + this.getRunNumber();
	}

			/**
	 * equals  
	 * 
	 * @param   object	to compare
	 * @return  boolean		object given enquals this Racer
	 */
	@Override
	public boolean equals(Object obj) {
		Racer temp;
		if (obj instanceof Racer)
			temp = (Racer) obj;
		else
			throw new IllegalArgumentException();

		if (this.getRacerID() == temp.getRacerID())
			return true;
		else
			return false;
	}

	/**
	 * deepEqualityTest  safely tests if object equals this racer
	 * 
	 * @param   Object	to compare
	 * @return  boolean		Object is equuivalent to this Racer
	 */
	public boolean deepEqualityTest(Object obj) {
		Racer temp;
		if (obj instanceof Racer)
			temp = (Racer) obj;
		else
			throw new IllegalArgumentException();

		if (this.getRacerID() == temp.getRacerID()
				&& this.getRunNumber() == temp.getRunNumber()
				&& Arrays.equals(this.getStart(), temp.getStart())
				&& Arrays.equals(this.getFinish(), temp.getFinish())
				&& Arrays.equals(this.getRuntime(), temp.getRuntime()))
			return true;
		else
			return false;
	}

	/**
	 * toTimeFormat  returns string of a time in String format
	 * 
	 * @param   int[]	a time to convert
	 * @return  String	given time converted to String
	 */
	public String toTimeFormat(int[] s) {
		return Integer.toString(s[0]) + ":" + Integer.toString(s[1]) + ":"
				+ Integer.toString(s[2]);
	}

			/**
	 * fromTimeFormat  converts time given as String
	 * 
	 * @param   String	time to convert
	 * @return  int[]	converted time
	 */
	public int[] fromTimeFormat(String s) {
		String[] temp = s.split(":");
		int[] re = { 00, 00, 00 };
		re[0] = Integer.parseInt(temp[0]);
		re[1] = Integer.parseInt(temp[1]);
		re[2] = Integer.parseInt(temp[2]);
		return re;
	}

			/**
	 * XMLExport  exports Racer state to XML element
	 * 
	 * @param   Element	to export
	 * @return  void
	 */
	public void XMLExport(Element element) {
		Element Racer = new Element("Racer");
		Racer.setAttribute("RacerID", Integer.toString(this.getRacerID()));
		Element isFinished = new Element("isFinished");
		Element Start = new Element("Start");
		Element Finish = new Element("Finish");
		Element RunTime = new Element("RunTime");
		Element RunNumber = new Element("RunNumber");

		isFinished.addContent(new Text(Boolean.toString(this.isFinished())));
		Start.addContent(new Text(toTimeFormat(this.getStart())));
		Finish.addContent(new Text(toTimeFormat(this.getFinish())));
		RunTime.addContent(new Text(toTimeFormat(this.getRuntime())));
		RunNumber.addContent(new Text(Integer.toString(this.getRunNumber())));

		Racer.addContent(isFinished);
		Racer.addContent(Start);
		Racer.addContent(Finish);
		Racer.addContent(RunTime);
		Racer.addContent(RunNumber);

		element.addContent(Racer);
	}

			/**
	 * XMLImport  imports XML Element for this Racer
	 * 
	 * @param   Element	XML Element to convert
	 * @return  void
	 */
	public void XMLImport(Element element) {
		this.setFinished(Boolean.parseBoolean(element
				.getChildText("isFinished")));
		this.setFinish(this.fromTimeFormat(element.getChildText("Finish")));
		this.setStart(this.fromTimeFormat(element.getChildText("Start")));
		this.setRuntime(this.fromTimeFormat(element.getChildText("RunTime")));
		this.setRunNumber(Integer.parseInt(element.getChildText("RunNumber")));

	}

}
