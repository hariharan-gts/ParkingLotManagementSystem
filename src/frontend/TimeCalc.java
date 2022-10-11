package frontend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeCalc {
	public String fAns="";
	
	public String getfAns() {
		return fAns;
	}

	public void setfAns(String fAns) {
		this.fAns = fAns;
	}

	public  String  getTotalTime(String IN,String Out,String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
		long ans = 0;
		try {
			Date d1 = sdf.parse(IN);
            Date d2 = sdf.parse(Out);
            long difference_In_Time = d2.getTime() - d1.getTime();
           // System.out.println(difference_In_Time);
            long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;
            long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;
            ans = difference_In_Days*24 + difference_In_Hours;
		}catch(ParseException e) {
			e.printStackTrace();
		}
		setfAns(new BillGeneration().BillCalculation(ans, type));
		 return getfAns();
		
	}
}