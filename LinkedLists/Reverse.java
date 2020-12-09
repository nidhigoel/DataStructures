/* Name of the class has to be "Main" only if the class is public. */
import java.util.*;
import java.lang.*;
import java.io.*;

class LinkedList {
	Node head;

	class Node {
		int value;
		Node next;
		Node(int data){
			this.value =  data;
			this.next = null;
		}
	}
	
	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.insert(4);
		ll.print();
		ll.reverserec(ll.head);
		ll.print();
		ll.reverse();
		ll.print();
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
	
	public void reverse() {

		Node prev = null; Node next = null;
		while(head!=null){
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		head = prev;
	}
	
	public Node reverserec(Node node){
		if(node==null) return null;
		
		if(node.next==null)return node;
		
		Node secondNode = node.next;
		node.next = null;
		Node reverse = reverserec(secondNode);
		
		
		secondNode.next=node;
		head = reverse;
		return reverse;
		
	}
}
