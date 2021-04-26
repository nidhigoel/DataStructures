package LinkedLists;/* Name of the class has to be "Main" only if the class is public. */
import java.util.*;
import java.lang.*;
import java.io.*;

class Merge2LinkedList {
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
	Merge2LinkedList ll1 = new Merge2LinkedList();
		ll1.insert(1);
		ll1.insert(3);
		ll1.insert(4);
		ll1.insert(9);
		// ll1.print();
	Merge2LinkedList ll2 = new Merge2LinkedList();
		ll2.insert(2);
		ll2.insert(5);
		ll2.insert(6);
		ll2.insert(10);
		// ll2.print();
		// LinkedList ll3 = mergeIterative(ll1.head, ll2.head);
		// LinkedList ll3 = mergeRec1(ll1.head, ll2.head);
	Merge2LinkedList ll3 = mergeRec2(ll1.head, ll2.head);
		ll3.print();
	}
	
	public static Merge2LinkedList mergeRec1(Node head1, Node head2){
		Merge2LinkedList ll = new Merge2LinkedList();
		
		if(head1==null){ ll.head = head2; return ll;}
		if(head2==null){ ll.head = head1; return ll;}
		
		Node node1 = head1, node2 = head2;
		
		if(head1.value<head2.value){
			mergeRec(head1.next, head2, head1);
			ll.head = head1;
		}else {
			mergeRec(head1, head2.next, head2);
			ll.head = head2;
		}
		return ll;
	}
	
	public static Merge2LinkedList mergeRec2(Node head1, Node head2){
		Merge2LinkedList ll = new Merge2LinkedList();
		ll.head = ll.mergeRecV2(head1, head2);
		return ll;
	}
	
	public static void mergeRec(Node head1, Node head2, Node head3){
		if(head1==null){
			head3.next = head2;
			return;
		}
		if(head2==null){
			head3.next = head1;
			return;
		}
		if(head1.value<head2.value){
			head3.next = head1;
			mergeRec(head1.next, head2,head3.next);
		}else {
			head3.next = head2;
			mergeRec(head1, head2.next,head3.next);
		}
	}
	
	public Node mergeRecV2(Node head1, Node head2){
		if(head1==null) return head2;
		if(head2==null) return head1;
		Node result = null;
		if(head1.value<head2.value){
			result = head1;
			result.next = mergeRecV2(head1.next, head2);
		}else{
			result = head2;
			result.next = mergeRecV2(head1, head2.next);
		}
		return result;
	}

	public static Merge2LinkedList mergeIterative(Node head1, Node head2){

		Merge2LinkedList ll = new Merge2LinkedList();
		
		if(head1==null){ ll.head = head2; return ll;}
		if(head2==null){ ll.head = head1; return ll;}
		
		Node node1 = head1, node2 = head2;
		
		if(node1.value<node2.value){
				ll.head = node1;
				node1 = node1.next;
		}else {
				ll.head = node2;
				node2 = node2.next;
		}

		Node temp = ll.head;
		while(node1!=null && node2!=null){

			if(node1.value<node2.value){
				temp.next=node1;
				temp = temp.next;
				node1 = node1.next;
			}else {
				temp.next=node2;
				temp = temp.next;
				node2 = node2.next;
			}
		}
		if(node1!=null){
			temp.next=node1;
		}else {
			temp.next=node2;
		}
		return ll;
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
