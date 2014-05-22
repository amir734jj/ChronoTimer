/**
 * The LevenshteinDistance class
 * 
 * Note that this class handles the computation of Levenstein Distance
 *
 */
public class LevenshteinDistance {
	/**
	 * similarity  computation between two strings
	 * 
	 * @param   string	first string to use
	 * @param   string	second string to compare
	 * @return  double	similarity between two strings
	 */
	public static double similarity(String s1, String s2) {
		if (s1.length() < s2.length()) {
			String swap = s1;
			s1 = s2;
			s2 = swap;
		}
		int bigLen = s1.length();
		if (bigLen == 0) {
			return 1.0;
		}
		return (bigLen - computeEditDistance(s1, s2)) / (double) bigLen;
	}

	/**
	 * computeEditDistance  computation between two strings
	 * 
	 * @param   string	first string to use
	 * @param   string	second string to compare
	 * @return  int	distance found
	 */
	public static int computeEditDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue),
									costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}
	
	/**
	 * printDistance prints computeEditDistance found
	 * 
	 * @param   string	first string to use
	 * @param   string	second string to compare
	 * @return  void
	 */
	public static void printDistance(String s1, String s2) {
		System.out.println(s1 + "-->" + s2 + ": " + computeEditDistance(s1, s2)
				+ " (" + similarity(s1, s2) + ")");
	}

}