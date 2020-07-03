package buza;

import java.util.Scanner;

public class BinarySearchTree {
	int data = 0;
	BinarySearchTree right = null;
	BinarySearchTree left = null;
	
	void input(int input) {
		
	}
	void delete(int delete) {
		
	}
	void find(int fine) {
		
	}
	void inorder() {
		
	}
	void preorder() {
		
	}
	void postorder() {
		
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		BinarySearchTree BST = new BinarySearchTree();
		boolean start = true;
		while(start) {
			String menu = kb.next();
			if(menu.equals("i")) {
				int input = kb.nextInt();
				BST.input(input);
			}
			else if(menu.equals("d")) {
				int delete = kb.nextInt();
				BST.delete(delete);
			}
			else if(menu.equals("f")) {
				int find = kb.nextInt();
				BST.find(find);
			}
			else if (menu.equals("pi")) {
				BST.inorder();
			}
			else if (menu.equals("pr")) {
				BST.preorder();
			}
			else if (menu.equals("po")) {
				BST.postorder();
			}
		}
		kb.close();
	}
	

}


