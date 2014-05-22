import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * The Export class 
 * 
 * Note that this class handles the export to file
 *
 */
public class Export {

	/**
	 * TriggerExport triggers a system export
	 * 
	 * @param   none
	 * @return   void
	 */
	public static void triggerExport() {
		RaceCollection.Clean();
		ImportExport.ExportSystemToXML();

		BufferedWriter writerx, writer;
		String formatted, formatted_temp;

		try {
			formatted = Main.outPut + "\\Result.txt";
			formatted_temp = Main.TempFolder + "\\Result.txt";
			writerx = new BufferedWriter(new FileWriter(formatted_temp));
			Iterator<Racer> it = getCurrentRaceORjustFinished.get().racers
					.iterator();
			Racer r;

			writerx.write("OUTPUT FOR "
					+ Main.fileName.substring(
							Main.fileName.lastIndexOf("/") + 1,
							Main.fileName.lastIndexOf(".txt")) + "\n\n");
			writerx.write("RUN	BIB	TIME\n");
			while (it.hasNext()) {
				r = it.next();
				writerx.write(r.getRunNumber() + "\t" + r.getRacerID() + "\t");
				if (Time.ArraytoSecond(r.getRuntime()) == 0)
					writerx.write("DNF\n");
				else
					writerx.write(new DecimalFormat("#.00")
							.format((double) Time.ArraytoSecond(r.getRuntime()))
							+ "\n");

			}

			if (RaceCollection.array.size() != 1) {
				writerx.write("_________________________________\n");
				Iterator<Race> itx = RaceCollection.array.iterator();
				while (itx.hasNext()) {

					Race temp = itx.next();
					if (getCurrentRaceORjustFinished.get() != temp) {

						Iterator<Racer> itw = temp.racers.iterator();

						writerx.write("OUTPUT FOR "
								+ Main.fileName.substring(
										Main.fileName.lastIndexOf("/") + 1,
										Main.fileName.lastIndexOf(".txt"))
								+ "\n\n");
						writerx.write("RUN	BIB	TIME\n");
						while (itw.hasNext()) {
							r = itw.next();
							writerx.write(r.getRunNumber() + "\t"
									+ r.getRacerID() + "\t");
							if (Time.ArraytoSecond(r.getRuntime()) == 0)
								writerx.write("DNF\n");
							else
								writerx.write(new DecimalFormat("#.00")
										.format((double) Time.ArraytoSecond(r
												.getRuntime()))
										+ "\n");

						}

					}
					writerx.write("_________________________________\n");
				}
			}

			writerx.flush();
			writerx.close();
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(formatted_temp);
				os = new FileOutputStream(formatted);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			} finally {
				is.close();
				os.close();
			}
			HTMLGenerator.generateHTML();
			sendToFTPServer.sendHTML();

			writer = new BufferedWriter(new FileWriter(Main.outPut
					+ "\\Result_RAW.txt"));
			writer.write(RaceCollection.toStringOverrided());
			writer.write(Channel.toStringOverrided());
			writer.flush();
			writer.close();
			try {
				is = new FileInputStream(Main.TempFolder + "\\System.XML");
				os = new FileOutputStream(Main.outPut + "\\System.XML");
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			} finally {
				is.close();
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ExportToPost.POST();
	}
}
