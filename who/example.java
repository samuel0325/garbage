package buza;

public class example {
	
	static void abc() {
		
	}
	
	
	public static void main(String[] args) {
		int a = 100;
		int b = 100;
		int c = 200;
		int d = 200;
		while(c!=3) {
			a= a-1;
			b= b-1;
		}
		while(c>2&&d>2) {
			c= c-a;
			d= d-b;
		}
		System.out.println(c+" "+d);
			
		
		
	}
}
