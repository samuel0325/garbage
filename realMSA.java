package buza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class realMSA {

	public static void main(String[] args) {
		File file3 = new File("/Users/kangsung-gon/etc/input.txt");
		File file4 = new File("/Users/kangsung-gon/etc/output.txt");
		try {
			FileWriter output = new FileWriter(file4, true);
			FileReader assign = new FileReader(file3);
			BufferedReader bufReader = new BufferedReader(assign);
			String line = "";
			String[] please = new String[2];
			int start = 0;
			
			while ((line = bufReader.readLine()) != null) {
				please[start] = line;
				start++;
			}
			String abc;
			String efg;
			abc = please[0];
			efg = please[1];
			String ddd = abc.substring(3, abc.length() - 1);
			String eee = efg.substring(3, efg.length() - 1);
			String[] aaa = ddd.split(", ");
			String[] bbb = eee.split(", ");
			double maxscore = -9999.0;
			int[] maxindex = new int[2];
			for (int i = 0; i < aaa.length - 1; i++) {
				for (int k = i + 1; k < aaa.length; k++) {
					map aa = new map(aaa[i], aaa[k]);
					aa.start();
					if (maxscore < aa.getBestScore()) {
						maxscore = aa.getBestScore();
						maxindex[0] = i;
						maxindex[1] = k;
					}
				}
			}
			map best = new map(aaa[maxindex[0]], aaa[maxindex[1]]);
			best.start();
			best.please(aaa[maxindex[0]].length(), aaa[maxindex[1]].length());
			profile thebest = new profile(best.SortedA, best.SortedB);
			for (int i = 0; i < aaa.length; i++) {
				if (i != maxindex[0] && i != maxindex[1]) {
					thebest.makeTable();
					thebest.SequenceToProfile(aaa[i]);
					thebest.align(aaa[i].length(), thebest.AllSeq.get(0).size());
					thebest.dashPlusToOrigin();
				}
			}
//			output.write("Profile for 1" + "\n");
			System.out.println("Profile for 1");
			thebest.makeTable();
			int j = 1;
			thebest.printTable(j);
			j++;

			double maxscore2 = -9999.0;
			int[] maxindex2 = new int[2];
			for (int i = 0; i < bbb.length - 1; i++) {
				for (int k = i + 1; k < bbb.length; k++) {
					map bb = new map(bbb[i], bbb[k]);
					bb.start();
					if (maxscore2 < bb.getBestScore()) {
						maxscore2 = bb.getBestScore();
						maxindex2[0] = i;
						maxindex2[1] = k;
					}
				}
			}
			map best2 = new map(bbb[maxindex2[0]], bbb[maxindex2[1]]);
			best2.start();
			best2.please(bbb[maxindex2[0]].length(), bbb[maxindex2[1]].length());
			profile thebest2 = new profile(best2.SortedA, best2.SortedB);
			for (int i = 0; i < bbb.length; i++) {
				if (i != maxindex2[0] && i != maxindex2[1]) {
					thebest2.makeTable();
					thebest2.SequenceToProfile(bbb[i]);
					thebest2.align(bbb[i].length(), thebest2.AllSeq.get(0).size());
					thebest2.dashPlusToOrigin();
				}
			}
//			output.write("Profile for 2" + "\n");
			System.out.println("Profile for 2");
			thebest2.makeTable();
			thebest2.printTable(j);
//			output.write("Dynamic table" + "\n");
			System.out.println("Dynamic table");
			thebest.P2P(thebest.makeTable(), thebest2.makeTable());
			thebest.P2PAlign(thebest.makeTable()[0].length, thebest2.makeTable()[0].length);
			thebest2.SortedProfileTable = thebest.toFinal();
			thebest2.profile2profile = false;
			thebest.dashPlusToOrigin();
			thebest2.dashPlusToOrigin();
			output.write("Alignment result" + "\n");
			System.out.println("Alignment result");
			for (List<String> aa : thebest.AllSeq) {
				output.write(aa + "\n");
				System.out.println(aa);
			}
			for (List<String> aa : thebest2.AllSeq) {
				output.write(aa + "\n");
				System.out.println(aa);
			}

			bufReader.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class nodeMSA {
	String A;
	String B;
	double score;
	nodeMSA down;
	nodeMSA right;
	nodeMSA cross;

	nodeMSA() {
		this.score = -1111.1;
		this.down = null;
		this.right = null;
		this.cross = null;
	}
}

class profile {
	double[][] profileTable;
	double[][] profileTable2;
	int num=1;
	List<String> Atoprofile = new ArrayList<String>();
	List<String> Btoprofile = new ArrayList<String>();
	String[] newSequence;
	List<String> SortedSequence = new ArrayList<String>();
	String[] SortedProfile;
	List<String> sortProfileTable = new ArrayList<String>();
	List<String> sortProfileTable2 = new ArrayList<String>();
	List<String> SortedProfileTable = new ArrayList<String>();
	List<String> SortedProfileTable2 = new ArrayList<String>();
	boolean profile2profile = true;
	nodeMSA[][] map;
	double Acount = 0;
	double Ccount = 0;
	double Gcount = 0;
	double Tcount = 0;
	double GapCount = 0;
	List<String> sortA = new ArrayList<String>();
	List<String> sortProfile = new ArrayList<String>();
	List<List<String>> AllSeq = new ArrayList<List<String>>();

	profile(String[] A, String[] B) {
		for (int i = 0; i < A.length; i++) {
			Atoprofile.add(A[i]);
			Btoprofile.add(B[i]);
		}
		AllSeq.add(Atoprofile);
		AllSeq.add(Btoprofile);

	}

	List<String> toFinal() {
		return SortedProfileTable2;
	}

	void dashPlusToOrigin() { // 프로파일&시퀀스 정렬 후 Gap - 추가하기
		for (int i = 0; i < SortedProfileTable.size(); i++) {
			if (SortedProfileTable.get(i).equals("-")) {
				for (List<String> all : AllSeq) {
					all.add(i, "-");
				}
			}
		}
		if (profile2profile) {
			AllSeq.add(SortedSequence);
		}
		sortA = new ArrayList<String>();
		sortProfile = new ArrayList<String>();
		SortedProfileTable = new ArrayList<String>();
		SortedProfileTable2 = new ArrayList<String>();
		sortProfileTable = new ArrayList<String>();
		sortProfileTable2 = new ArrayList<String>();
		SortedSequence = new ArrayList<String>();
	}

	double[][] makeTable() { // 프로파일 테이블 만들기
		profileTable = new double[4][AllSeq.get(0).size()];
		for (int i = 0; i < AllSeq.get(0).size(); i++) {
			for (List<String> seq : AllSeq) {

				if (seq.get(i).equals("A")) {
					Acount++;
				} else if (seq.get(i).equals("C")) {
					Ccount++;
				} else if (seq.get(i).equals("G")) {
					Gcount++;
				} else if (seq.get(i).equals("T")) {
					Tcount++;
				} else if (seq.get(i).equals("-")) {
					GapCount++;
				} else {
				}
			}
			profileTable[0][i] = Acount / (Acount + Ccount + Gcount + Tcount + GapCount);
			profileTable[1][i] = Ccount / (Acount + Ccount + Gcount + Tcount + GapCount);
			profileTable[2][i] = Gcount / (Acount + Ccount + Gcount + Tcount + GapCount);
			profileTable[3][i] = Tcount / (Acount + Ccount + Gcount + Tcount + GapCount);
			Acount = 0;
			Ccount = 0;
			Gcount = 0;
			Tcount = 0;
			GapCount = 0;
		}
		return profileTable;
	}

	void printTable(int j) { // 프로파일 테이블 출력
		File file4 = new File("/Users/kangsung-gon/etc/output.txt");
		try {
			FileWriter output = new FileWriter(file4, true);
			output.write("Profile for " +j + "\n");
			for (int i = 0; i < 4; i++) {
				if (i == 0) {
					output.write("A   ");
					System.out.print("A   ");
					for (int k = 0; k < AllSeq.get(0).size(); k++) {
						output.write((int) (profileTable[0][k] * 100) / 100.0 + "   ");
						System.out.print((int) (profileTable[0][k] * 100) / 100.0 + "   ");
					}
					output.write("\n");
					System.out.println("");
				} else if (i == 1) {
					output.write("C   ");
					System.out.print("C   ");
					for (int k = 0; k < AllSeq.get(0).size(); k++) {
						output.write((int) (profileTable[1][k] * 100) / 100.0 + "   ");
						System.out.print((int) (profileTable[1][k] * 100) / 100.0 + "   ");
					}
					output.write("\n");
					System.out.println("");
				} else if (i == 2) {
					output.write("G   ");
					System.out.print("G   ");
					for (int k = 0; k < AllSeq.get(0).size(); k++) {
						output.write((int) (profileTable[2][k] * 100) / 100.0 + "   ");
						System.out.print((int) (profileTable[2][k] * 100) / 100.0 + "   ");
					}
					output.write("\n");
					System.out.println("");
				} else if (i == 3) {
					output.write("T   ");
					System.out.print("T   ");
					for (int k = 0; k < AllSeq.get(0).size(); k++) {
						output.write((int) (profileTable[3][k] * 100) / 100.0 + "   ");
						System.out.print((int) (profileTable[3][k] * 100) / 100.0 + "   ");
					}
					output.write("\n");
					System.out.println("");
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void SequenceToProfile(String sequence) { // 프로파일과 시퀀스 점수 계산 DP
		newSequence = sequence.split("");
		int sequenceLength = sequence.length();
		String[] SortSequence = sequence.split("");
		map = new nodeMSA[sequence.length() + 1][AllSeq.get(0).size() + 1];
		for (int i = 0; i < sequence.length() + 1; i++) {
			for (int k = 0; k < AllSeq.get(0).size() + 1; k++) {
				map[i][k] = new nodeMSA();
			}
		}
		for (int i = 0; i < sequenceLength + 1; i++) {
			if (i == sequenceLength) {
				map[i][0].score = i * (-0.5);
			} else {
				map[i][0].score = i * (-0.5);
				map[i][0].down = map[i + 1][0];
			}
		}
		for (int i = 0; i < AllSeq.get(0).size() + 1; i++) {
			if (i == AllSeq.get(0).size()) {
				map[0][i].score = i * (-0.5);
			} else {
				map[0][i].score = i * (-0.5);
				map[0][i].right = map[0][i + 1];
			}
		}
		map[0][0].score = 0;
		double crossPoint = 0;
		for (int i = 1; i < sequenceLength + 1; i++) {
			for (int k = 1; k < AllSeq.get(0).size() + 1; k++) {
				if (SortSequence[i - 1].equals("A")) {
					crossPoint += profileTable[0][k - 1] * 3;
					crossPoint += profileTable[1][k - 1] * (-2);
					crossPoint += profileTable[2][k - 1] * (-2);
					crossPoint += profileTable[3][k - 1] * (-2);
				} else if (SortSequence[i - 1].equals("C")) {
					crossPoint += profileTable[0][k - 1] * (-2);
					crossPoint += profileTable[1][k - 1] * 3;
					crossPoint += profileTable[2][k - 1] * (-2);
					crossPoint += profileTable[3][k - 1] * (-2);
				} else if (SortSequence[i - 1].equals("G")) {
					crossPoint += profileTable[0][k - 1] * (-2);
					crossPoint += profileTable[1][k - 1] * (-2);
					crossPoint += profileTable[2][k - 1] * 3;
					crossPoint += profileTable[3][k - 1] * (-2);

				} else if (SortSequence[i - 1].equals("T")) {
					crossPoint += profileTable[0][k - 1] * (-2);
					crossPoint += profileTable[1][k - 1] * (-2);
					crossPoint += profileTable[2][k - 1] * (-2);
					crossPoint += profileTable[3][k - 1] * 3;
				}
				double match = map[i - 1][k - 1].score + crossPoint;
				double updash = map[i - 1][k].score - 0.5;
				double downdash = map[i][k - 1].score - 0.5;
				crossPoint = 0;
				double[] max = new double[3];
				max[0] = match;
				max[1] = updash;
				max[2] = downdash;
				double maxNum = -1111.7;
				int index = -1;
				for (int j = 0; j < 3; j++) {
					if (maxNum < max[j]) {
						maxNum = max[j];
						index = j;
					}
				}
				if (index == 0) {
					map[i - 1][k - 1].cross = map[i][k];
				} else if (index == 1) {
					map[i - 1][k].down = map[i][k];
				} else if (index == 2) {
					map[i][k - 1].right = map[i][k];

				}
				map[i][k].score = maxNum;
			}
		}
		for (int i = 0; i < newSequence.length + 1; i++) {
			for (int j = 0; j < AllSeq.get(0).size() + 1; j++) {
				if (j == AllSeq.get(0).size()) {
				}
			}
		}
	}

	void printDPT(int a, int b) { // 각종 DP 출력

		File file4 = new File("/Users/kangsung-gon/etc/output.txt");
		try {
			FileWriter output = new FileWriter(file4, true);
			output.write("Dynamic table" + "\n");
			for (int i = 0; i < a + 1; i++) {
				for (int j = 0; j < b + 1; j++) {
					output.write((int)(map[i][j].score * 100) / 100.0  + "   ");
					System.out.print((int)(map[i][j].score * 100) / 100.0  + "   ");
					if (j == b) {
						output.write("\n");
						System.out.println();
					}
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void align(int sequenceL, int profileL) { // 프로파일 to 시퀀스 백트래킹

		String[] profileAlign = new String[Atoprofile.size()];
		for (int i = 1; i < Atoprofile.size() + 1; i++) {
			profileAlign[i - 1] = Integer.toString(i);
		}
		if (sequenceL != 0 && profileL != 0) {
			if (map[sequenceL - 1][profileL - 1].cross != null) {
				sortA.add(newSequence[sequenceL - 1]);
				sortProfileTable.add(profileAlign[profileL - 1]);
				align(sequenceL - 1, profileL - 1);
			} else if (map[sequenceL - 1][profileL].down != null) {
				sortA.add(newSequence[sequenceL - 1]);
				sortProfileTable.add("-");
				align(sequenceL - 1, profileL);
			} else if (map[sequenceL][profileL - 1].right != null) {
				sortA.add("-");
				sortProfileTable.add(profileAlign[profileL - 1]);
				align(sequenceL, profileL - 1);
			}
		} else if (profileL == 0 && sequenceL != 0) {
			if (map[sequenceL - 1][profileL].down != null) {
				sortA.add(newSequence[sequenceL - 1]);
				sortProfileTable.add("-");
				align(sequenceL - 1, profileL);
			}
		} else if (sequenceL == 0 && profileL != 0) {
			if (map[sequenceL][profileL - 1].right != null) {
				sortA.add("-");
				sortProfileTable.add(profileAlign[profileL - 1]);
				align(sequenceL, profileL - 1);
			}
		} else {
			SortedProfile = new String[SortedProfileTable.size()];
			for (int i = 0; i < sortA.size(); i++) {
				SortedSequence.add(sortA.get(sortA.size() - i - 1));
				SortedProfileTable.add(sortProfileTable.get(sortProfileTable.size() - i - 1));
			}
		}
	}

	void P2P(double[][] ProfileTable, double[][] ProfileTable2) { // 두개의 프로파일 정렬(다이다믹 테이블 만들기)DP
		profile2profile = false;
		this.profileTable = new double[4][ProfileTable[0].length];
		this.profileTable2 = new double[4][ProfileTable2[0].length];
		profileTable = ProfileTable;
		profileTable2 = ProfileTable2;

		int a = profileTable[1].length;
		int b = profileTable2[1].length;
		map = new nodeMSA[a + 1][b + 1];
		for (int i = 0; i < a + 1; i++) {
			for (int k = 0; k < b + 1; k++) {
				map[i][k] = new nodeMSA();
			}
		}
		for (int i = 0; i < a + 1; i++) {
			if (i == a) {
				map[i][0].score = i * (-0.5);
			} else {
				map[i][0].score = i * (-0.5);
				map[i][0].down = map[i + 1][0];
			}
		}
		for (int i = 0; i < b + 1; i++) {
			if (i == b) {
				map[0][i].score = i * (-0.5);
			} else {
				map[0][i].score = i * (-0.5);
				map[0][i].right = map[0][i + 1];
			}
		}
		map[0][0].score = 0;
		double crossPoint = 0;

		for (int i = 1; i < a + 1; i++) {
			for (int k = 1; k < b + 1; k++) {
				crossPoint += profileTable[0][i - 1] * profileTable2[0][k - 1] * 3;
				crossPoint += profileTable[0][i - 1] * profileTable2[1][k - 1] * (-2);
				crossPoint += profileTable[0][i - 1] * profileTable2[2][k - 1] * (-2);
				crossPoint += profileTable[0][i - 1] * profileTable2[3][k - 1] * (-2);

				crossPoint += profileTable[1][i - 1] * profileTable2[0][k - 1] * (-2);
				crossPoint += profileTable[1][i - 1] * profileTable2[1][k - 1] * 3;
				crossPoint += profileTable[1][i - 1] * profileTable2[2][k - 1] * (-2);
				crossPoint += profileTable[1][i - 1] * profileTable2[3][k - 1] * (-2);

				crossPoint += profileTable[2][i - 1] * profileTable2[0][k - 1] * (-2);
				crossPoint += profileTable[2][i - 1] * profileTable2[1][k - 1] * (-2);
				crossPoint += profileTable[2][i - 1] * profileTable2[2][k - 1] * 3;
				crossPoint += profileTable[2][i - 1] * profileTable2[3][k - 1] * (-2);

				crossPoint += profileTable[3][i - 1] * profileTable2[0][k - 1] * (-2);
				crossPoint += profileTable[3][i - 1] * profileTable2[1][k - 1] * (-2);
				crossPoint += profileTable[3][i - 1] * profileTable2[2][k - 1] * (-2);
				crossPoint += profileTable[3][i - 1] * profileTable2[3][k - 1] * 3;
				double match = map[i - 1][k - 1].score + crossPoint;
				double updash = map[i - 1][k].score - 0.5;
				double downdash = map[i][k - 1].score - 0.5;
				crossPoint = 0;
				double[] max = new double[3];
				max[0] = match;
				max[1] = updash;
				max[2] = downdash;
				double maxNum = -1111.7;
				int index = -1;
				for (int j = 0; j < 3; j++) {
					if (maxNum < max[j]) {
						maxNum = max[j];
						index = j;
					}
				}
				if (index == 0) {
					map[i - 1][k - 1].cross = map[i][k];
				} else if (index == 1) {
					map[i - 1][k].down = map[i][k];
				} else if (index == 2) {
					map[i][k - 1].right = map[i][k];

				}
				map[i][k].score = maxNum;
			}
		}
		printDPT(a, b);
	}

	void P2PAlign(int profileTableL, int profileTable2L) {// P2P 정렬 후 백트래킹
		profile2profile = false;
		String[] profileAlign = new String[profileTable[0].length];
		String[] profileAlign2 = new String[profileTable2[0].length];

		for (int i = 1; i < profileTable[0].length + 1; i++) {
			profileAlign[i - 1] = Integer.toString(i);
		}
		for (int i = 1; i < profileTable2[0].length + 1; i++) {
			profileAlign2[i - 1] = Integer.toString(i);
		}
		if (profileTableL != 0 && profileTable2L != 0) {
			if (map[profileTableL - 1][profileTable2L - 1].cross != null) {
				sortProfileTable.add(profileAlign[profileTableL - 1]);
				sortProfileTable2.add(profileAlign2[profileTable2L - 1]);
				P2PAlign(profileTableL - 1, profileTable2L - 1);
			} else if (map[profileTableL - 1][profileTable2L].down != null) {
				sortProfileTable.add(profileAlign[profileTableL - 1]);
				sortProfileTable2.add("-");
				P2PAlign(profileTableL - 1, profileTable2L);
			} else if (map[profileTableL][profileTable2L - 1].right != null) {
				sortProfileTable.add("-");
				sortProfileTable2.add(profileAlign2[profileTable2L - 1]);
				P2PAlign(profileTableL, profileTable2L - 1);
			}
		} else if (profileTable2L == 0 && profileTableL != 0) {
			if (map[profileTableL - 1][profileTable2L].down != null) {
				sortProfileTable.add(profileAlign[profileTableL - 1]);
				sortProfileTable2.add("-");
				P2PAlign(profileTableL - 1, profileTable2L);
			}
		} else if (profileTableL == 0 && profileTable2L != 0) {
			if (map[profileTableL][profileTable2L - 1].right != null) {
				sortProfileTable.add("-");
				sortProfileTable2.add(profileAlign2[profileTable2L - 1]);
				P2PAlign(profileTableL, profileTable2L - 1);
			}
		} else {
			for (int i = 0; i < sortProfileTable.size(); i++) {
				SortedProfileTable.add(sortProfileTable.get(sortProfileTable.size() - i - 1));
			}
			for (int i = 0; i < sortProfileTable2.size(); i++) {
				SortedProfileTable2.add(sortProfileTable2.get(sortProfileTable2.size() - i - 1));
			}
		}
	}

}

class map { // 초기 시작
	nodeMSA[][] map;
	String a, b;
	String[] A;
	String[] B;
	String[] SortedA;
	String[] SortedB;
	List<String> sortA = new ArrayList<String>();
	List<String> sortB = new ArrayList<String>();

	map(String a, String b) {
		A = a.split("");
		B = b.split("");
		this.map = new nodeMSA[a.length() + 1][b.length() + 1];
		this.a = a;
		this.b = b;
		for (int i = 0; i < a.length() + 1; i++) {
			for (int k = 0; k < b.length() + 1; k++) {
				if (i == 0 && k == 0) {
					map[i][k] = new nodeMSA();
				} else if (i == 0) {
					map[i][k] = new nodeMSA();
				} else if (k == 0) {
					map[i][k] = new nodeMSA();
				} else {
					map[i][k] = new nodeMSA();
				}
			}
		}
	}

	double getBestScore() {
		return map[a.length()][b.length()].score;
	}

	void start() { // 정렬 DP
		for (int i = 0; i <= a.length(); i++) {
			if (i == a.length()) {
				map[i][0].score = i * (-0.5);
			} else {
				map[i][0].score = i * (-0.5);
				map[i][0].down = map[i + 1][0];
			}
		}
		for (int i = 0; i <= b.length(); i++) {
			if (i == b.length()) {
				map[0][i].score = i * (-0.5);
			} else {
				map[0][i].score = i * (-0.5);
				map[0][i].right = map[0][i + 1];
			}
		}
		map[0][0].score = 0;
		for (int i = 1; i < a.length() + 1; i++) {
			for (int k = 1; k < b.length() + 1; k++) {
				double match = map[i - 1][k - 1].score + 3;
				double dismatch = map[i - 1][k - 1].score - 2;
				double updash = map[i - 1][k].score - 0.5;
				double downdash = map[i][k - 1].score - 0.5;
				if (a.substring(i - 1, i).equals(b.substring(k - 1, k))) {
					double[] max = new double[3];
					max[0] = match;
					max[1] = updash;
					max[2] = downdash;
					double maxNum = -1111.7;
					int index = -1;
					for (int j = 0; j < 3; j++) {
						if (maxNum < max[j]) {
							maxNum = max[j];
							index = j;
						}
					}
					if (index == 0) {
						map[i - 1][k - 1].cross = map[i][k];

					} else if (index == 1) {

						map[i - 1][k].down = map[i][k];
					} else if (index == 2) {

						map[i][k - 1].right = map[i][k];

					}
					map[i][k].score = maxNum;
				} else {
					double[] max = new double[3];
					max[0] = dismatch;
					max[1] = updash;
					max[2] = downdash;
					double maxNum = -1111.7;
					int index = -1;
					for (int j = 0; j < 3; j++) {
						if (maxNum < max[j]) {
							maxNum = max[j];
							index = j;
						}
					}
					if (index == 0) {
						map[i - 1][k - 1].cross = map[i][k];

					} else if (index == 1) {

						map[i - 1][k].down = map[i][k];

					} else if (index == 2) {

						map[i][k - 1].right = map[i][k];

					}
					map[i][k].score = maxNum;
				}
			}
		}
	}

	void please(int lengthA, int lengthB) { // 백트래킹
		if (lengthA != 0 && lengthB != 0) {
			if (map[lengthA - 1][lengthB - 1].cross != null) {
				sortA.add(A[lengthA - 1]);
				sortB.add(B[lengthB - 1]);
				please(lengthA - 1, lengthB - 1);
			} else if (map[lengthA - 1][lengthB].down != null) {
				sortA.add(A[lengthA - 1]);
				sortB.add("-");
				please(lengthA - 1, lengthB);
			} else if (map[lengthA][lengthB - 1].right != null) {
				sortA.add("-");
				sortB.add(B[lengthB - 1]);
				please(lengthA, lengthB - 1);
			}
		} else if (lengthB == 0 && lengthA != 0) {
			if (map[lengthA - 1][lengthB].down != null) {
				sortA.add(A[lengthA - 1]);
				sortB.add("-");
				please(lengthA - 1, lengthB);
			}
		} else if (lengthA == 0 && lengthB != 0) {
			if (map[lengthA][lengthB - 1].right != null) {
				sortA.add("-");
				sortB.add(B[lengthB - 1]);
				please(lengthA, lengthB - 1);
			}
		} else {
			SortedA = new String[sortA.size()];
			SortedB = new String[sortB.size()];
			for (int i = 0; i < sortA.size(); i++) {
				SortedA[i] = sortA.get(sortA.size() - i - 1);
				SortedB[i] = sortB.get(sortB.size() - i - 1);
			}
		}
	}
}