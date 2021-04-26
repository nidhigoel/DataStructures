package LinkedLists;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
import java.util.*;
import java.lang.*;
import java.io.*;

class KthToLastNode {
	Node head;

	class Node {
		int value;
		Node next;
		Node(int data){
			this.value =  data;
			this.next = null;
		}
	}

	void printKthToLastNode(int k){
		if(head==null){
			return;
		}
		Node temp1 = head;
		Node temp2 = head;
		int index;
		for(index=0; temp1!=null && index<k; index++) {
			temp1=temp1.next;
		}
		if(index<k) return;
		while(temp1!=null){
			temp1=temp1.next;
			temp2 = temp2.next;
		}
		System.out.println(temp2.value);

	}
	
	int printKthToLastNodeRec(Node head, int k){
		if(head==null){
			return 0;
		}
		int index = printKthToLastNodeRec(head.next, k)+1;
		if(index == k){
			System.out.println(head.value);
		}
		return index;
	}
	
	public static void main(String[] args){
		KthToLastNode ll = new KthToLastNode();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.insert(4);
		ll.print();
		ll.printKthToLastNode(3);
		ll.printKthToLastNodeRec(ll.head, 4);
	}
	
	public void insert(int data){
		Node node = new Node(data);
		if(head ==  null){
			head = node;
			return;
		}
		Node temp = head;
		while(temp.next!=null){
			temp = temp.next;
		}
		temp.next = node;
	}
	
	public void print(){
		Node temp = head;
		while(temp != null){
			System.out.println(temp.value);
			temp=temp.next;
		}
	}

}
