public class TimeParser {

	public static int[] parser(String s) {
		int[] time = new int[3];
		try {
			String[] temp = s.split(":");
			time[0] = Integer.parseInt(temp[0]);
			time[1] = Integer.parseInt(temp[1]);
			time[2] = Integer.valueOf((int) Double.parseDouble(temp[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;

	}

	public static int[] parserSpecialCase(String s) {
		int[] time = new int[3];
		try {
			String[] temp = s.split(":");
			time[0] = Integer.parseInt(temp[0]);
			time[1] = Integer.parseInt(temp[1]);
			time[2] = Integer.parseInt(temp[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;

	}
}
