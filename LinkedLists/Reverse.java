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
		// ll.reverserec(ll.head);
		// ll.print();
		// ll.reverse();
		// ll.print();
		// ll.reverseInBlocks(3);
		// ll.reverseInPairs();
		ll.head = ll.reverseInPairsRec(ll.head);
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
	
	public Node reverseInPairsRec(Node head){
		if(head==null||head.next==null)return head;
		Node temp = head;
		Node temp1=head.next;
		Node temp2 = head.next.next;
		
		temp1.next=temp;
		temp.next=reverseInPairsRec(temp2);
		return temp1;
	}
	
	public void reverseInPairs(){
		// if(head==null || head.next==null) return;
		Node temp=head;
		Node prev_last =null, cur_last=null;
		Boolean flag=true;
		while(temp!=null && temp.next!=null){
			prev_last=cur_last;
			cur_last = temp;
			Node next = temp.next;
			Node next2 = temp.next.next;
			next.next=temp;
			temp.next=null;
			if(flag){
			head = next;
			flag=false;}
			temp = next2;
			if(prev_last!=null){
				prev_last.next=next;
			}
			
		}
		if(temp!=null){
			prev_last.next=temp;
		}
	}
	
	public void reverseInBlocks(int blockSize){

		Node temp = head;
		boolean flag = true;
		Node prev_head=null, prev_last=null, cur_head=null, cur_last = null;
		while(temp!=null){
			Node temp1 = temp;
			Node prev = null;
			for(int i=0; temp!=null && i<blockSize; i++){
				Node next = temp.next;
				temp.next = prev;
				prev = temp;
				temp = next;
			}
			if(flag){
			head = prev; flag=false;}
			prev_head=cur_head;
			prev_last=cur_last;
			cur_head = prev;
			cur_last = temp1;
			if(prev_last!=null){
			prev_last.next = cur_head;}
		}
	}
}
