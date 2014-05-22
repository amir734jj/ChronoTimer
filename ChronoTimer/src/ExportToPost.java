import java.awt.Desktop;
import java.io.*;
import java.net.*;

import org.apache.commons.io.IOUtils;

/**
 * The ExportToPost class
 * 
 * Note that this class handles the export to webpage
 * 
 */
public class ExportToPost {
	public static String PATH = "http://localhost:8000/sendresults";
	public static boolean PATHisON = false;

	/**
	 * getURL
	 * 
	 * @param none
	 * @return string url of export
	 */
	public static String getURL() {
		String str = "";

		FileInputStream fisTargetFile;
		try {
			fisTargetFile = new FileInputStream(new File(Main.TempFolder
					+ "\\System.XML"));
			str = IOUtils.toString(fisTargetFile, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

	}

	/**
	 * Post information to website
	 * 
	 * @param none
	 * @return void
	 */
	public static void POST() {
		try {
			String url = PATH;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = getURL();

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			System.out
					.println("Response Message : " + con.getResponseMessage());

			Desktop.getDesktop().browse(
					new URI("http://localhost:8000/results"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
