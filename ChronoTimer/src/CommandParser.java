import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * CommandParser Class 
 * 
 * Note that this class converts lines of strings from test file to commands for chronoTimer
 *
 */
public class CommandParser {
	public ArrayList<String> s;
	public int lineCounter = 0;
	public boolean isFromFile;

	/**
	 * CommandParser 
	 * 
	 * @param   string  file to be parsed
	 * @param   boolean  isFromFile
	 */
	public CommandParser(String file, boolean isFromFile) {
		s = new ArrayList<String>();
		this.isFromFile = isFromFile;
		if (isFromFile) {
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str;
				while ((str = br.readLine()) != null && !str.isEmpty())
					s.add(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				s.add(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * process splits strings taken from file. Calls respective chronoTimer command
	 * 
	 * @param   none
	 * @return  void
	 */
	public void process() {
		Iterator<String> it = s.iterator();
		String[] string;

		try {
			while (it.hasNext()) {
				if (isFromFile)
					lineCounter++;
				if (Main.TimeisON)
					Main.initializeTimeRefresh();
				string = it.next().split("\\t");
				Time.setTime(TimeParser.parser(string[0]));

				if (!checkCommand(string[1].split(" ")[0])) {
					System.out.println(Arrays.toString(string));
					Log.write(string[1] + " IS UNKNOWN");
					throw new IllegalArgumentException();
				} else {
					Log.write(Arrays.toString(string));
					if (string[1].split(" ")[0].equals("TIME")) {
						Log.write("TIME COMMAND");
						if (!Arrays.equals(
								TimeParser.parser(string[1].split(" ")[1]),
								Time.getTime()))
							Time.setTime(TimeParser.parser(string[1].split(" ")[1]));
						else
							Time.setTime(TimeParser.parser(string[0]));
					} else if (string[1].split(" ")[0].equals("START")) {
						Log.write("START COMMAND");
						Start.triggerStart();
					} else if (string[1].split(" ")[0].equals("ON")) {
						Log.write("ON COMMAND");
						On.triggerOn();
					} else if (string[1].split(" ")[0].equals("OFF")) {
						Log.write("OFF COMMAND");
						Off.triggerOff();
					} else if (string[1].split(" ")[0].equals("EXIT")) {
						Log.write("EXIT COMMAND");
						Exit.triggerExit();
					} else if (string[1].split(" ")[0].equals("RESET")) {
						Log.write("RESET COMMAND");
						Reset.trigegrReset();
					} else if (string[1].split(" ")[0].equals("TOGGLE")) {
						int tog = Integer.parseInt(string[1].split(" ")[1]) - 1;
						Log.write("TOGGLE COMMAND");
						Toggle.toggleChannel(tog);
					} else if (string[1].split(" ")[0].equals("CONN")) {
						int con = Integer.parseInt(string[1].split(" ")[2]) - 1;
						Log.write("CONN COMMAND");
						if (string[1].split(" ")[1].equals("GATE")) {
							Channel.connect(con);
						} else if (string[1].split(" ")[1].equals("EYE")) {
							Channel.connect(con);
						} else {
							Log.write("CONN ARGUMENT NOT SPECIFIIED");
						}
					} else if (string[1].split(" ")[0].equals("DISC")) {
						int dis = Integer.parseInt(string[1].split(" ")[2]) - 1;
						Log.write("DISC COMMAND");
						if (string[1].split(" ")[1].equals("GATE")) {
							Channel.disconnect(dis);
						} else if (string[1].split(" ")[1].equals("EYE")) {
							Channel.disable(dis);
						} else {
							Log.write("DISC ARGUMENT NOT SPECIFIIED");
						}
					} else if (string[1].split(" ")[0].equals("EVENT")) {
						Log.write("EVENT COMMAND");
						if (string[1].split(" ")[1].equals("PARIND")) {
							Event.triggerEvent(0);
						} else if (string[1].split(" ")[1].equals("GRP")) {
							Event.triggerEvent(1);
						} else
							Log.write("EVENT ARGUMENT NOT SPECIFIIED");
					} else if (string[1].split(" ")[0].equals("DNF")) {
						Log.write("DNF COMMAND");
						Dnf.triggerDnf();
					} else if (string[1].equals("NEWRUN")) {
						Log.write("NEWRUN COMMAND");
						NewRun.newRace();
					} else if (string[1].equals("ENDRUN")) {
						Log.write("ENDRUN COMMAND");
						EndRun.triggerEndRun();
					} else if (string[1].split(" ")[0].equals("PRINT")) {
						Log.write("PRINT COMMAND");
						Print.printCurrentRace();
					} else if (string[1].split(" ")[0].equals("EXPORT")) {
						Log.write("EXPORT COMMAND");
						Export.triggerExport();
					} else if (string[1].split(" ")[0].equals("NUM")) {
						int num = Integer.parseInt(string[1].split(" ")[1]);
						Log.write("NUM COMMAND");
						RaceCollection.CurrentRace.addRacer(new Racer(num));
					} else if (string[1].split(" ")[0].equals("FIN")) {
						Log.write("FIN COMMAND");
						Finish.triggerFin();
					} else if (string[1].split(" ")[0].equals("TRIG")) {
						Log.write("TRIG COMMAND");
						Trig.triggerTrig(Integer.parseInt(string[1].split(" ")[1]) - 1);
					} else if (string[1].split(" ")[0].equals("CLR")) {
						Log.write("CLR COMMAND");
						Clr.triggrtClr(Integer.parseInt(string[1].split(" ")[1]));
					} else
						throw new IllegalStateException();
				}

			}
		} catch (Exception e) {
			if (this.isFromFile)
				Log.write("ERROR IN COMMAND AT LINE: " + this.lineCounter);
			else
				Log.write("ERROR IN COMMAND");
			e.printStackTrace();
		}
	}
	
	/**
	 * checkCommand checks string if is legal command
	 * 
	 * @param   string  command to check
	 * @return  boolean is legal command
	 */
	public boolean checkCommand(String s) {
		if ((s != null)
				&& (s.equals("ON") || s.equals("OFF") || s.equals("EXIT")
						|| s.equals("RESET") || s.equals("TIME")
						|| s.equals("TOGGLE") || s.equals("CONN")
						|| s.equals("DISC") || s.equals("EVENT")
						|| s.equals("NEWRUN") || s.equals("ENDRUN")
						|| s.equals("PRINT") || s.equals("EXPORT")
						|| s.equals("NUM") || s.equals("CLR")
						|| s.equals("SWAP") || s.equals("RCL")
						|| s.equals("START") || s.equals("FIN")
						|| s.equals("TRIG") || s.equals("DNF")))
			return true;
		else
			return false;

	}
}
