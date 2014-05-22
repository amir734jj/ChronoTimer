import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Time {

	public static int curSecond = 0;
	public static int curMinute = 0;
	public static int curHour = 0;
	public static boolean issuedStop = false;

	public static int getCurSecond() {
		return curSecond;
	}

	public static void setCurSecond(int curSecond) {
		Time.curSecond = curSecond;
	}

	public static int getCurMinute() {
		return curMinute;
	}

	public static void setCurMinute(int curMinute) {
		Time.curMinute = curMinute;
	}

	public static int getCurHour() {
		return curHour;
	}

	public static void setCurHour(int curHour) {
		Time.curHour = curHour;
	}

	public static int[] getCurrentTime() {
		int[] rv = new int[3];
		Calendar rightNow = Calendar.getInstance();
		rv[0] = rightNow.get(Calendar.HOUR_OF_DAY);
		rv[1] = rightNow.get(Calendar.MINUTE);
		rv[2] = rightNow.get(Calendar.SECOND);

		return rv;
	}

	public static boolean isIssuedStop() {
		return issuedStop;
	}

	public static void setIssuedStop(boolean issuedStop) {
		Time.issuedStop = issuedStop;
	}

	public static int[] elapsedTime(int[] t1, int[] t2) {
		int p1 = t1[0] * 3600 + t1[1] * 60 + t1[2];
		int p2 = t2[0] * 3600 + t2[1] * 60 + t2[2];

		int result = Math.abs(p2 - p1);

		int second = 1;
		int minute = second * 60;
		int hour = minute * 60;

		int elapsedHours = result / hour;
		result = result % hour;
		int elapsedMinutes = result / minute;
		result = result % minute;
		int elapsedSeconds = result / second;

		int[] p3 = { elapsedHours, elapsedMinutes, elapsedSeconds };
		return p3;

	}

	public static int[] toArray() {
		int[] array = { Time.curHour, Time.curMinute, Time.curSecond };
		return array;
	}

	public static String toStringOverrided() {
		return Time.getCurHour() + " : " + Time.getCurMinute() + " : "
				+ Time.getCurSecond();
	}

	public static void incrementTime() {
		Time.curSecond++;
		if (Time.curSecond == 60) {
			Time.curSecond = 0;
			Time.curMinute++;
		}

		if (Time.curMinute == 60) {
			Time.curMinute = 0;
			Time.curHour++;
		}
	}

	public static void timeScheduler() {
		new Timer().schedule(new TimerTask() {
			public void run() {
				incrementTime();
				if (issuedStop)
					this.cancel();
			}
		}, 1, 600);
	}

	public static void setTime(int[] t) {
		Time.setCurHour(t[0]);
		Time.setCurMinute(t[1]);
		Time.setCurSecond(t[2]);
	}

	public static int[] getTime() {
		int[] time = { Time.getCurHour(), Time.getCurMinute(),
				Time.getCurSecond() };
		return time;
	}

	public static void initializeFromComputerClock() {
		Time.setTime(Time.getCurrentTime());
	}

	public static void initialize() {
		Time.setCurSecond(0);
		Time.setCurMinute(0);
		Time.setCurHour(0);
	}

	public static int ArraytoSecond(int[] temp) {
		return temp[0] * 3600 + temp[1] * 60 + temp[2];
	}

	public static void setTimeGui() {
		Main.TimeVal = Time.toStringOverrided();
	}
}
