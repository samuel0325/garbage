package buza;

public class CoronaVirus {

	public static void main(String[] args) {
		double totalConfirmed = 5588299.0;
		double totalConfirmedUS = 1680625.0;
		double totalConfirmedWithoutUS = totalConfirmed-totalConfirmedUS;
		double totalDeath = 350423.0;
		double totalDeathUS = 98902.0;
//		double totalDeathKorea = 269.0;
//		double totalConfirmedKorea = 11225.0;
		double totalDeathWithoutUS = totalDeath-totalDeathUS;
		System.out.println("2020-05-27");
		System.out.println("World total confirmed : "+totalConfirmed);
		System.out.println("US total confirmed : "+totalConfirmedUS);
//		System.out.println("Korea total confirmed : "+totalConfirmedKorea);
		System.out.println("Total confirmed without US  : "+totalConfirmedWithoutUS);
		System.out.println("US confirmed/World confirmed  : "+String.format("%,.2f", (totalConfirmedUS/totalConfirmed)*100)+"%");
		System.out.println("Total confirmed without US/World confirmed : "+String.format("%,.2f", (totalConfirmedWithoutUS/totalConfirmed)*100)+"%");
		System.out.println("Global Deaths : "+totalDeath);
//		System.out.println("Korea Deaths : "+totalDeathKorea);
		System.out.println("US Deaths : "+totalDeathUS);
		System.out.println("Global Deaths without US : "+totalDeathWithoutUS);
		System.out.println("Death rate of US : "+String.format("%,.2f", (totalDeathUS/totalConfirmedUS)*100)+"%");
		System.out.println("Death rate of Global without US : "+String.format("%,.2f", (totalDeathWithoutUS/totalConfirmedWithoutUS)*100)+"%");
		System.out.println("Death rate of Global : "+String.format("%,.2f", (totalDeath/totalConfirmed)*100)+"%");
//		System.out.println("Death rate of Korea : "+String.format("%,.2f", (totalDeathKorea/totalConfirmedKorea)*100)+"%");
	}
}
