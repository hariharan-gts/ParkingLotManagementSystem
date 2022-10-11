package frontend;

public class BillGeneration {
	public String  BillCalculation(long TotalTime,String vType) {
		long amount = 0;
		if(vType=="car") {
			amount = TotalTime*10;
		}
		else {
			amount  = TotalTime*5;
		}
		return String.valueOf(amount);
		
	}
}