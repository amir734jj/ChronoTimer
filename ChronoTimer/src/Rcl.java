/**
 * The Rcl class
 * 
 * Note that this class got single channel
 *
 */
public class Rcl {

	public static int channel;

	/**
	 * getChannel  returns the channel
	 * 
	 * @param   none
	 * @return  int 	channel number
	 */
	public static int getChannel() {
		return channel;
	}

		/**
	 * setChannel  sets the channel
	 * 
	 * @param   int		channel to set
	 * @return  void
	 */
	public static void setChannel(int channel) {
		Rcl.channel = channel;
	}

}
