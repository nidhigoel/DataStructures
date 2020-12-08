import java.util.*;
import java.lang.*;
import java.io.*;

class DoublyLinkedList
{
	private Node head;
	
	static class Node {
		int value;
		Node next;
		Node prev;
		Node(int value) {
			this.value = value;
			this.next = null;
			this.prev = null;
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insert_front(1);
		dll.insert(2,1);
		dll.insert(3,2);
		dll.insert(4,3);
		dll.insert(5,4);
		dll.insert_end(6);
		dll.insert_front(8);
		dll.delete(3);
		dll.delete(5);
		dll.delete(dll.head.next);
		dll.insertBefore(dll.head, 3);
		dll.print();
	}
	public void print(){
		Node temp = head;
		while (temp!=null){
			System.out.println(String.format(" %s %s %s", temp.value , (temp.prev==null?"null":temp.prev.value) , (temp.next==null?"null":temp.next.value)));
			temp = temp.next;
		}
	}
	public void insert(int new_data, int position) {
		Node new_node = new Node(new_data);
		if(position == 0){
			if(head!=null){
				new_node.next = head;
				head.prev = new_node;
			}
			head = new_node;
			return;
		}
		Node prev_node = head;
		for (int i=0; prev_node!=null && i<position-1 ; i++){
			prev_node = prev_node.next;
		}
		if(prev_node == null){
			System.out.println("Linked List in not long enough");
			return;
		}
		Node next_node = prev_node.next;
		if(next_node!=null) {
		new_node.next = next_node;
		next_node.prev = new_node;}
		prev_node.next = new_node;
		new_node.prev = prev_node;
		
	}
	
	//insert in front
	public void insert_front(int new_data){
		Node new_node = new Node(new_data);
		new_node.next = head;
		head = new_node;
		if(new_node.next!=null) {
			new_node.next.prev = new_node;
		}
	}
	
	// insert at end
	public void insert_end(int new_data){
		Node temp = head;
		while(temp!=null && temp.next!=null){
			temp=temp.next;
		}
		Node new_node = new Node(new_data);
		if(temp == null){
			head = new_node;
			return;
		}
		temp.next = new_node;
		new_node.prev = temp;
	}
	
	//insert after given node
	public void insertAfter(Node node, int new_data){
		if(node==null)return;
		Node new_node =  new Node(new_data);
		if(node.next!=null){
			node.next.prev = new_node;
		}
		new_node.next = node.next;
		new_node.prev = node;
		node.next = new_node;
	}
	
	//insert before given node
	public void insertBefore(Node node, int new_data) {
		if(node == null)return;
		Node new_node = new Node(new_data);
		if(node==head){
			head = new_node;
		}
		new_node.next = node;
		new_node.prev = node.prev;
		node.prev = new_node;
		if(new_node.prev!=null){
			new_node.prev.next = new_node;
		}
	}
	
	// delete node by position
	public void delete(int pos){
		if(head == null) return;
		
		if(pos == 0){
			if(head.next!=null){
				head.next.prev = null;
			}
			head = head.next;
		}
		Node prev_node = head;
		for(int i = 0; prev_node!=null && i<pos-1; i++){
			prev_node = prev_node.next;
		}
		if(prev_node ==  null || prev_node.next == null){
			System.out.println("Linked list is not long enough");
			return;
		}
		Node cur_node = prev_node.next;
		Node next_node = cur_node.next;
		if(next_node!=null){
			next_node.prev = prev_node;
		}
		prev_node.next = next_node;
	}
	
	// delete a given node
	public void delete(Node node){
		if(node == null || head == null) return;
		if(node == head){
			head = node.next;
		}
		if(node.prev != null){
			node.prev.next = node.next;
		}
		if(node.next != null){
			node.next.prev = node.prev;
		}
	}
}
