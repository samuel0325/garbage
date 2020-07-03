package buza;

import java.util.Scanner;

public class fibo {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int number = kb.nextInt();
		System.out.println(gofibo(number));
		kb.close();
	}
static int gofibo(int fibonum) {
	if(fibonum==0) {
		return 0;
	}else if(fibonum==1) {
		return 1;
	}else {
		return gofibo(fibonum-1)+gofibo(fibonum-2);
	}
}
}
