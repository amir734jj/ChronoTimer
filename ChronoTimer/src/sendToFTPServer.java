import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * The sendToFTPServer class
 * 
 * Note that this class handles export to FTPServer
 *
 */
public class sendToFTPServer {

	FTPClient ftp = null;
	/**
	 * sendToFTPServer  Constructor
	 * 
	 * @param   String	host
	 * @param   String	user name
 	 * @param   String	password
	 * @return  void
	 */
	public sendToFTPServer(String host, String user, String pwd)
			throws Exception {
		ftp = new FTPClient();

		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}

		/**
	 * uploadFile  creates new file at local file name
	 * 
	 * @param   String	local file name
	 * @param   String	name of file
	 * @return  void
	 */
	public void uploadFile(String localFileFullName, String fileName,
			String hostDir) throws Exception {
		try (InputStream input = new FileInputStream(
				new File(localFileFullName))) {
			this.ftp.storeFile(hostDir + fileName, input);
		}
	}

	/**
	 * disconnect  disconnects from FTP Client
	 * 
	 * @param   none
	 * @return  void
	 */
	public void disconnect() {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
			}
		}
	}

			/**
	 * sendHTML  sends HTML code to FTP Server
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void sendHTML() {
		try {
			sendToFTPServer ftpUploader = new sendToFTPServer(
					"ftp.junit.freeiz.com", "a1542091", "amir1372");

			ftpUploader.uploadFile(Main.TempFolder + "\\HTML_OUT.html",
					"index.html", "/public_html/");

			FileInputStream fisTargetFile = new FileInputStream(new File(
					Main.TempFolder + "\\System.XML"));
			String targetFileStr = IOUtils.toString(fisTargetFile);
			targetFileStr = "<html><body><textarea rows=\"20\" cols=\"40\" style=\"border:none;\">"
					+ targetFileStr + "</textarea></body></html>";

			FileUtils.writeStringToFile(new File(Main.TempFolder
					+ "\\System.html"), targetFileStr);
			ftpUploader.uploadFile(Main.TempFolder + "\\System.xml",
					"System.xml", "/public_html/");

			ftpUploader.uploadFile(Main.TempFolder + "\\Result.txt",
					"Result.txt", "/public_html/");

			ftpUploader.uploadFile(Main.TempFolder + "\\System.log",
					"System.log", "/public_html/");

			ftpUploader.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}