package datastructure;

import java.util.Scanner;

public class TopologicalSort {
	int howmanynode;//��� ��� ������̳�
	node[] topo;// edge ������ �迭 ������ ����Ű�� �ִ��� �˷���
	TopologicalSort(int howmanynode){
		this.howmanynode = howmanynode;
		this.topo = new node[howmanynode];
		for(int i=0;i<howmanynode;i++){//�ʱ�ȭ
			topo[i] = null;
		}
		}
	void linking(int from, int to){
		node newnode = new node(to); //����Ŵ �ް� �ִ� ��� ����
		newnode.link = topo[from-1]; //from�� ������ ����ų �� �ΰ� ����
		topo[from-1] = newnode;//������ �� ����Ű�� �ִ°� newnode.link�� ����
	}
	boolean emptycheck(){
		if(topo[0]==null&&topo[1]==null&&topo[2]==null&&topo[3]==null&&
				topo[4]==null&&topo[5]==null){ // ���� �ʿ�
		return true;
		}else{
			return false;
		}
	}
	void maketopo(){
		boolean[] printed = new boolean[howmanynode]; //��µǾ����� true �����ϴ� �迭
		boolean printcheck = false;
		for(int i = 0;i<howmanynode;i++){
			printed[i]=false;
		}
		if(printed[0]==true&&printed[1]==true&&printed[2]==true&&
				printed[3]==true&&printed[4]==true&&printed[5]==true){
			printcheck = true;  // ���� �ʿ�
		}
		boolean[] check = new boolean[howmanynode];//�����Դ��� 
		while(printcheck!=true){
		for(int i =0; i<howmanynode; i++){
			check[i] = check(i+1);
		}
		for(int i =0; i<howmanynode; i++){
			if(check[i]==true){
				topo[i]=null; // ������
				if(printed[i]==false){//����Ʈ ������ ����� ���(�ߺ���� ����)
				System.out.print((i+1)+" ");
				printed[i] = true;
				}
			}
		}
	}
	}
	boolean check(int i){//�� ��带 ����Ű�� �ִ� ���� �ִ��� Ȯ��
		int count=0;
		for(node k : topo){
			node temp = k;
			while(temp !=null){
				if(temp.number==i){
					count++;
				}
				temp = temp.link;
			}		
		}
		if(count==0){
		return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String node = kb.nextLine();
		String[] totalNode = node.split(" ");
		TopologicalSort start = new TopologicalSort(totalNode.length);
		String connection = kb.nextLine();
		String[] totalConnection = connection.split(" ");
		for(String i :totalConnection){
			String[] put = i.split("-");
			start.linking(Integer.parseInt(put[0]),Integer.parseInt(put[1]));
		}
		start.maketopo();		
		kb.close();
	}
}
class node{
	int number;
	node link;
	node(int number){
		this.number = number;
		link = null;
	}   //2020-01-14 11:40 start DAY10
}		//2020-01-14 20:58 done DAY10 9/11 81%