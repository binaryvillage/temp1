package prj1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int NumberOfItems = 100;
//		int FailedItems = 9;
//
//		if ((NumberOfItems * 0.1) <= FailedItems) {
//			System.out.println("FAIL");
//			System.out.println("NumberOfItems " + NumberOfItems);
//			System.out.println("FailedItems " + FailedItems);
//			System.out.println((NumberOfItems * 0.1) <= FailedItems);
//		} else
//			System.out.println("PASS");

		timeDiff();
	}

	public static void timeDiff() {

		String BatchProcess = "Activity XXXX";
		LocalDateTime ExcpectedLastRunDateTime = null;
		String returnValue = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.US);
		LocalDateTime ActualLastRunDateTime = LocalDateTime.parse("06/18/2020 09:20:01 AM", formatter);
		System.out.println("Actual LastRunDateTime: " + ActualLastRunDateTime.format(formatter));
		LocalDateTime now = LocalDateTime.now();

		String schedule = "Monthly";

		// Check boundary values here
		now = LocalDateTime.parse("07/15/2020 09:20:01 AM", formatter);

		System.out.println("Today's date/time: " + now.format(formatter));

		if (schedule.equalsIgnoreCase("Daily"))
			ExcpectedLastRunDateTime = now.minus(1, ChronoUnit.DAYS);
		else if (schedule.equalsIgnoreCase("Weekly"))
			ExcpectedLastRunDateTime = now.minus(1, ChronoUnit.WEEKS);
		else if (schedule.equalsIgnoreCase("Monthly"))
			ExcpectedLastRunDateTime = now.minus(1, ChronoUnit.MONTHS);

		System.out.println("Excpected LastRunDateTime should be after: " + ExcpectedLastRunDateTime.format(formatter));

		boolean diff = ExcpectedLastRunDateTime.isBefore(ActualLastRunDateTime)
				|| ExcpectedLastRunDateTime.isEqual(ActualLastRunDateTime);
		System.out.println(BatchProcess + " - ExcpectedLastRunDateTime <= ActualLastRunDateTime = " + diff);

		if (diff == true) {
			returnValue = "PASS";
			// Report.updateTestLog(Action,
			// BatchProcess + " - Last run date/time is as expected. As per it's schedule
			// frequency - '" + schedule
			// + "', it should be executed after: " + ExcpectedLastRunDateTime
			// + ". Actual Last Run Date/Time: " + ActualLastRunDateTime,
			// Status.PASS);
		} else {
			returnValue = BatchProcess + " - Last run date/time is not as expected. As per it's schedule frequency - '"
					+ schedule + "', it should be executed after: " + ExcpectedLastRunDateTime
					+ ". But Actual Last Run Date/Time: " + ActualLastRunDateTime;
			// Report.updateTestLog(Action,
			// BatchProcess + " - Last run date/time is not as expected. As per it's
			// schedule frequency - '"
			// + schedule + "', it should be executed after: " + ExcpectedLastRunDateTime
			// + ". But Actual Last Run Date/Time: " + ActualLastRunDateTime,
			// Status.FAIL);
		}

		// return returnValue;
	}

}