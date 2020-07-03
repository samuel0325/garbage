package datastructure;

import java.util.Scanner;

public class TopologicalSort {
	int howmanynode;//몇개의 노드 만들것이냐
	node[] topo;// edge 저장할 배열 무엇을 가르키고 있는지 알려줌
	TopologicalSort(int howmanynode){
		this.howmanynode = howmanynode;
		this.topo = new node[howmanynode];
		for(int i=0;i<howmanynode;i++){//초기화
			topo[i] = null;
		}
		}
	void linking(int from, int to){
		node newnode = new node(to); //가르킴 받고 있는 노드 생성
		newnode.link = topo[from-1]; //from이 무엇을 가르킬 것 인가 저장
		topo[from-1] = newnode;//무엇이 날 가르키고 있는가 newnode.link에 저장
	}
	boolean emptycheck(){
		if(topo[0]==null&&topo[1]==null&&topo[2]==null&&topo[3]==null&&
				topo[4]==null&&topo[5]==null){ // 수정 필요
		return true;
		}else{
			return false;
		}
	}
	void maketopo(){
		boolean[] printed = new boolean[howmanynode]; //출력되었으면 true 저장하는 배열
		boolean printcheck = false;
		for(int i = 0;i<howmanynode;i++){
			printed[i]=false;
		}
		if(printed[0]==true&&printed[1]==true&&printed[2]==true&&
				printed[3]==true&&printed[4]==true&&printed[5]==true){
			printcheck = true;  // 수정 필요
		}
		boolean[] check = new boolean[howmanynode];//지나왔는지 
		while(printcheck!=true){
		for(int i =0; i<howmanynode; i++){
			check[i] = check(i+1);
		}
		for(int i =0; i<howmanynode; i++){
			if(check[i]==true){
				topo[i]=null; // 날린다
				if(printed[i]==false){//프린트 된적이 없어야 출력(중복출력 방지)
				System.out.print((i+1)+" ");
				printed[i] = true;
				}
			}
		}
	}
	}
	boolean check(int i){//이 노드를 가르키고 있는 것이 있는지 확인
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