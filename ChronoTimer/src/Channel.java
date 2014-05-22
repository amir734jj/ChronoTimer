import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * The Channel class contains channel objects
 * 
 * Note that this class handles initialization, enable, connect, disconnect, and
 * toggle functions
 * 
 */
public class Channel {

	public static boolean[] channelEnabled = new boolean[12];
	public static boolean[] channelConnected = new boolean[12];
	public static boolean isSucceed = false;

	/**
	 * Initializes all channels:enable to false
	 * 
	 * @param void
	 * @return void
	 */
	public static void initialize() {
		channelEnabled = new boolean[12]; // java initializes to false by
											// default
		isSucceed = true;
	}

	/**
	 * Initializes all channels: enable and connected to false Sets isSucceed to
	 * true
	 * 
	 * @param void
	 * @return void
	 */
	public static void initializeEveryThing() {
		channelEnabled = new boolean[12];
		channelConnected = new boolean[12];
		isSucceed = true;
	}

	/**
	 * Connect a channel
	 * 
	 * @param int channel to be connected
	 * @return void
	 */
	public static void connect(int change) {
		channelConnected[change] = true;
		isSucceed = true;
	}

	/**
	 * Disconnect a channel
	 * 
	 * @param int channel to be disconnected
	 * @return void
	 */
	public static void disconnect(int change) {
		channelConnected[change] = false;
		isSucceed = true;
	}

	/**
	 * Enables a channel if is already connected
	 * 
	 * @param int channel to be enabled
	 * @return void
	 */
	public static void enable(int change) {
		if (channelConnected[change]) {
			channelEnabled[change] = true;
			isSucceed = true;
		} else {
			isSucceed = false;
			Log.write("ERROR:  Channel "
					+ Integer.toString(change)
					+ " attempts to enable though it is not connected"
							.toUpperCase());
			new UIManager();
			UIManager.put("OptionPane.background", new ColorUIResource(192,
					192, 192));
			UIManager.put("Panel.background",
					new ColorUIResource(192, 192, 192));
			JOptionPane.showMessageDialog(
					null,
					"ERROR:  Channel "
							+ Integer.toString(change)
							+ " attempts to enable though it is not connected"
									.toUpperCase(), "PROGRAM INFO\n",
					JOptionPane.PLAIN_MESSAGE);
			throw new IllegalStateException();
		}
	}

	/**
	 * Disables a channel if channel is connected
	 * 
	 * @param int channel to be disabled
	 * @return void
	 */
	public static void disable(int change) {
		if (channelConnected[change]) {
			channelEnabled[change] = false;
			isSucceed = true;
		} else {
			isSucceed = false;
			Log.write("ERROR:  Channel "
					+ Integer.toString(change)
					+ " attempts to disable though it is not connected"
							.toUpperCase());
			new UIManager();
			UIManager.put("OptionPane.background", new ColorUIResource(192,
					192, 192));
			UIManager.put("Panel.background",
					new ColorUIResource(192, 192, 192));

			JOptionPane.showMessageDialog(
					null,
					"ERROR:  Channel "
							+ Integer.toString(change)
							+ " attempts to disable though it is not connected"
									.toUpperCase(), "PROGRAM INFO\n",
					JOptionPane.PLAIN_MESSAGE);
			throw new IllegalStateException();
		}
	}

	/**
	 * Toggles a channel if channel is connected Notice does inverse of previous
	 * state (enables or disables)
	 * 
	 * @param int channel to be toggled
	 * @return void
	 */
	public static void toggle(int change) {
		if (channelConnected[change]) {
			isSucceed = true;
			if (channelEnabled[change])
				channelEnabled[change] = false;
			else
				channelEnabled[change] = true;

		}

		else {
			isSucceed = false;
			Log.write("ERROR:  Channel "
					+ Integer.toString(change)
					+ " attempts to toggle though it is not connected"
							.toUpperCase());
			new UIManager();
			UIManager.put("OptionPane.background", new ColorUIResource(192,
					192, 192));
			UIManager.put("Panel.background",
					new ColorUIResource(192, 192, 192));
			JOptionPane.showMessageDialog(
					null,
					"ERROR:  Channel "
							+ Integer.toString(change)
							+ " attempts to toggle though it is not connected"
									.toUpperCase(), "PROGRAM INFO\n",
					JOptionPane.PLAIN_MESSAGE);
			throw new IllegalStateException();
		}

	}

	/**
	 * Gets state of channel
	 * 
	 * @param int channel to be got
	 * @return boolean channel is connected and enabled
	 */
	public static boolean getState(int state) {
		isSucceed = true;
		return (channelConnected[state] && channelEnabled[state]);
	}

	/**
	 * Gets connected state of channel
	 * 
	 * @param int channel to be got
	 * @return boolean channel is connected
	 */
	public static boolean getConnectState(int state) {
		isSucceed = true;
		return channelConnected[state];
	}

	/**
	 * Gets enabled state of channel
	 * 
	 * @param int channel to be got
	 * @return boolean channel is enabled
	 */
	public static boolean getEnableState(int state) {
		isSucceed = true;
		return channelEnabled[state];
	}

	/**
	 * Set channel to enabled state
	 * 
	 * @param int channel to be set
	 * @param boolean state to be set as
	 * @return void
	 */
	public static void setChannelEnabled(int i, boolean b) {
		isSucceed = true;
		channelEnabled[i] = b;
	}

	/**
	 * Set channel to connected state
	 * 
	 * @param int channel to be set
	 * @param boolean state to be set as
	 * @return void
	 */
	public static void setChannelConnected(int i, boolean b) {
		isSucceed = true;
		channelConnected[i] = b;
	}

	/**
	 * Overrided toString Method
	 * 
	 * @return string "Connected Channels ...  Enabled Channels...."
	 */
	public static String toStringOverrided() {
		String s1 = "Connected Channels: "
				+ Arrays.toString(Channel.channelConnected);
		String s2 = "Enabled Channels: "
				+ Arrays.toString(Channel.channelEnabled);
		return s1 + "\n" + s2;
	}

	/**
	 * Disables all channels
	 * 
	 * @return void
	 */
	public static void disableAllChannels() {
		channelEnabled = new boolean[12];
	}

}
