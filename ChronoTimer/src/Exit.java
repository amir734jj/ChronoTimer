import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("unused")
/**
 * The Exit class 
 * 
 * Note that this class handles the chronoTimer system exit
 */
public class Exit {

	/**
	 * TriggerExit triggers a system exit including the write of "Program Terminated" to Log file
	 * 
	 * @param   none
	 * @param   void
	 */
	public static void triggerExit() {
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				Log.write("PROGRAM TERMINATED");

				Desktop d = Desktop.getDesktop();
				try {
					d.browse(new URI("http://junit.rr.nu"));
				} catch (IOException | URISyntaxException e) {
					Log.write("ERROR IN OPENING THE BROWSER");
					System.exit(0);
				}
				System.exit(0);
			}
		}, 5000);
	}

}
