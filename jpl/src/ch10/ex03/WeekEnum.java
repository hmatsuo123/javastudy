package ch10.ex03;

public class WeekEnum {
	enum Week {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY
	}

	public static boolean isWorkday(Week day) {
        if ((day == Week.SATURDAY) || (day == Week.SUNDAY)) {
        	return false;
        }
        return true;
    }

	public static boolean isWorkdaySwitch(Week day) {
        switch (day) {
        case SATURDAY:
        	return false;
        case SUNDAY:
            return false;
        default:
            return true;
        }
    }

	public static void main(String[] args) {
        System.out.println("MONDAY: " + isWorkday(Week.MONDAY));
        System.out.println("SATURDAY: " + isWorkday(Week.SATURDAY));

        System.out.println("TUESDAY: " + isWorkdaySwitch(Week.TUESDAY));
        System.out.println("SUNDAY: " + isWorkdaySwitch(Week.SUNDAY));
    }
}
