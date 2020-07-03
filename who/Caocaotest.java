package buza;

import java.util.Scanner;

public class Caocaotest {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int howmany = kb.nextInt();
		for(int i=0; i<howmany; i++) {
			int first = kb.nextInt();
			int second = kb.nextInt();
			System.out.println(cashback(first, second));
		}
		kb.close();
	}
	static int cashback(int a, int b) {
		int cashback=0;
		int test1 = test1(a)-1;
		int test2 = test2(b)-1;
		if(test1==1) {
			cashback+=500;
		}else if(test1==2) {
			cashback+=300;
		}else if(test1==3) {
			cashback+=200;
		}else if(test1==4) {
			cashback+=50;
		}else if(test1==5) {
			cashback+=30;
		}else if(test1==6) {
			cashback+=10;
		}else {
			cashback+=0;
		}
		
		if(test2==1) {
			cashback+=512;
		}else if(test2==2) {
			cashback+=256;
		}else if(test2==3) {
			cashback+=128;
		}else if(test2==4) {
			cashback+=64;
		}else if(test2==5) {
			cashback+=32;
		}else {
			cashback+=0;
		}
		return cashback*10000;
	}
	static int test1(int a) {
		int start=0;
		int count=1;
		while(start<a) {
			start+=count;
			count++;
		}
		return count;
	}
	static int test2(int b) {
		int start=0;
		int count=1;
		while(start<b) {
			start+=start+1;
			count++;
		}
		return count;
	}

}
