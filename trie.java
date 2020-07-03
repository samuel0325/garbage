package buza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class trie {
	node root;
	int totalnum = 0;

	void input(String RNAsequence) {
		node tmp = root;
		String[] RNA = RNAsequence.split("");
		if (root == null) {
			node rootnode = new node("root", ++totalnum);
			root = rootnode;
			root.number = 1;
			tmp = rootnode;
		}
		for (String seq : RNA) {
			if (seq.equals("A")) {
				if (tmp.A == null) {
					node node = new node(seq, ++totalnum);
					tmp.A = node;
					node.prev = tmp;
					tmp = node;
				} else {

					tmp = tmp.A;
				}

			} else if (seq.equals("T")) {
				if (tmp.T == null) {
					node node = new node(seq, ++totalnum);
					tmp.T = node;
					node.prev = tmp;
					tmp = node;
				} else {
					tmp = tmp.T;
				}
			} else if (seq.equals("C")) {
				if (tmp.C == null) {
					node node = new node(seq, ++totalnum);
					tmp.C = node;
					node.prev = tmp;
					tmp = node;
				} else {
					tmp = tmp.C;
				}
			} else if (seq.equals("G")) {
				if (tmp.G == null) {
					node node = new node(seq, ++totalnum);
					tmp.G = node;
					node.prev = tmp;
					tmp = node;
				} else {
					tmp = tmp.G;
				}
			}

		}
	}

	void preorder(node focus) {
		File file4 = new File("C:\\Users\\owner\\Documents\\카카오톡 받은 파일\\새 폴더\\rosalind_prob2_output_small_set2.txt");
		try {
			FileWriter output = new FileWriter(file4, true);
			if (focus != null) {
				if (focus == root) {
					preorder(focus.A);
					preorder(focus.T);
					preorder(focus.C);
					preorder(focus.G);
				} else {
					output.write(focus.prev.number + " " + focus.number + " " + focus.ATCG + "\n");
					System.out.println(focus.prev.number + " " + focus.number + " " + focus.ATCG);
					preorder(focus.A);
					preorder(focus.T);
					preorder(focus.C);
					preorder(focus.G);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	void preorderOrigin(node focus) {
		
			if (focus != null) {
				if (focus == root) {
					preorderOrigin(focus.A);
					preorderOrigin(focus.T);
					preorderOrigin(focus.C);
					preorderOrigin(focus.G);
				} else {
					System.out.println(focus.prev.number + " " + focus.number + " " + focus.ATCG);
					preorderOrigin(focus.A);
					preorderOrigin(focus.T);
					preorderOrigin(focus.C);
					preorderOrigin(focus.G);
				}
			}
			

	}

	class node {
		String ATCG;
		int number;
		node A, T, C, G;
		node prev;

		node(String ATCG, int num) {
			this.ATCG = ATCG;
			this.number = num;
			this.prev = null;
			this.A = null;
			this.T = null;
			this.C = null;
			this.G = null;
		}
	}

	public static void main(String[] args) {
		trie letmesee = new trie();
		File file3 = new File("C:\\Users\\owner\\Documents\\카카오톡 받은 파일\\새 폴더\\rosalind_prob2_small_set.txt");
		File file4 = new File("C:\\Users\\owner\\Documents\\카카오톡 받은 파일\\새 폴더\\rosalind_prob2_output_small_set2.txt");

		try {
			FileWriter output = new FileWriter(file4, true);
			FileReader assign2 = new FileReader(file3);
			BufferedReader bufReader = new BufferedReader(assign2);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				letmesee.input(line);
			}
			bufReader.close();
			letmesee.preorder(letmesee.root);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}