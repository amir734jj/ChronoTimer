import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.rendersnake.HtmlCanvas;

/**
 * The HTMLGenerator class
 * 
 * Note that this class handles the generation and export of HTML 
 *
 */
public class HTMLGenerator {
	public static String HTML_OUT = Main.TempFolder + "\\HTML_OUT.html";

	/**
	 * generateHTML  generates HTML code base on current system state
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void generateHTML() {

		try {
			HtmlCanvas html = new HtmlCanvas();
			html.head().macros().stylesheet("./style.css")._head();
			html.html().body().h1().content("ChronoTimer, Team JUnit");
			html.p().content(
					"Current Race's ID: " + getCurrentRaceORjustFinished.get().raceID);
			html.p().content(
					"Number of Races: " + RaceCollection.array.size() + "\n\n");
			html.hr();
			Iterator<Race> it = RaceCollection.array.iterator();
			while (it.hasNext()) {
				String[] arr = it.next().toString().split("\n");
				for (int i = 0; i < arr.length; i++)
					html.p().content(arr[i]);
				html.hr();
			}
			html.p().content(Channel.toStringOverrided().split("\n")[0]);
			html.p().content(Channel.toStringOverrided().split("\n")[1]);
			html.hr();
			html.a(html.attributes().href("./System.xml"))
					.content("System.xml");
			html.br();
			html.a(html.attributes().href("./Result.html")).content(
					"Result File");
			html.br();
			html.a(html.attributes().href("./System.log")).content("Log File");
			html.hr();
			html.address().content("Group JUnit, Spring 2013");
			html.address().content(
					new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar
							.getInstance().getTime()));

			html._body()._html();
			write_To_HTML_File(html.toHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		/**
	 * write_To_HTML_File writes HTML to given file
	 * 
	 * @param   string	to be written out to file
	 * @return  void
	 */
	public static void write_To_HTML_File(String st) throws IOException {
		StringWriter out = new StringWriter();
		out.append(st);
		FileUtils.writeStringToFile(new File(HTML_OUT), out.toString(), false);
	}
}
