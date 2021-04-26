package LinkedLists;

import java.util.*;
import java.lang.*;
import java.io.*;

class CircularLinkedList {
	
	Node head;

	class Node {
		int value;
		Node next;
		Node(int data) {
			this.value = data;
			this.next = null;
		}
	}

	public static void main(String[] args) {
		CircularLinkedList cll = new CircularLinkedList();
		cll.insert_end(3);
		cll.insert(4);
		cll.insert_end(2);
		// cll.insert_end(1);
		cll.insert(5);
		cll.deleteLastNode();
		// cll.deleteLastNode();
		// cll.deleteLastNode();
		cll.insert_end(1);
		cll.deleteLastNode();
		cll.deleteFirstNode();
		cll.print();
		System.out.println(cll.count());
	}
	// insert before head -> O(1)
	public void insertOptimised(int new_data) {
		Node new_node = new Node(new_data);
		if(head == null){
			head = new_node;
			new_node.next = new_node;
			return;
		} 
		new_node.next = head.next;
		head.next = new_node;
		new_node.value = head.value;
		head.value = new_data;
		
	}
	
	// insert before head -> O(n)
	public void insert(int new_data) {
		Node new_node = new Node(new_data);
		new_node.next = new_node;
		if(head != null) {
			Node temp = head;
			while(temp.next!=head){
				temp = temp.next;
			}
			temp.next = new_node;
			new_node.next=head;
		}
		head=new_node;
	}
	
	// insert at end
	public void insert_end(int new_data){
		Node new_node  =  new Node(new_data);
		if(head ==  null){
			head = new_node;
			new_node.next = new_node;
			return;
		}
		
		Node temp=head;
		while(temp.next!=head){
			temp = temp.next;
		}
		temp.next = new_node;
		new_node.next=head;
	}
	
	public void print(){
		if(head ==  null)return;
		Node temp = head;
		do{
			System.out.println(temp.value);
			temp = temp.next;
		}while(temp!=head);
	}
	
	public void deleteLastNode() {
		if(head==null)return;
		if(head.next==head){head = null; return;}
		Node last=head.next;
		Node prev=head;
		while(last.next!=head){
			prev=last;
			last=last.next;
		}
		prev.next=head;
	}
	
	public void deleteFirstNode() {
		if(head==null)return;
		if(head.next==head){head = null; return;}
		Node last=head;
		while(last.next!=head){
			last=last.next;
		}
		last.next=head.next;
		head=head.next;
	}
	
	public int count() {
		if(head == null) return 0;
		int ans = 1;
		Node temp = head;
		while(temp.next!=head) {
		  ans++;
		  temp = temp.next;
		}
		return ans;
	}
}  
