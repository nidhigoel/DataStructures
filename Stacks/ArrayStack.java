import java.util.*;
import java.io.*;

class ArrayStack<T> {

	List<T> stack = new ArrayList<>();
	int top = -1;
	int maxSize = 1000;

	public static void main(String[] args){
		ArrayStack<Integer> stack = new ArrayStack<>();
		stack.push(1);
		System.out.println(stack.size());
		System.out.println(stack.top());
		stack.pop();
		System.out.println(stack.size());
		System.out.println(stack.top());
	}

	public void push(T ele){
		if(top==maxSize-1){
			System.out.println("Stack is full");
			return;
		}
		stack.add(ele);
		top++;
	}

	public T pop(){
		if(top<0){
			System.out.println("Stack is empty");
			return null;
		}
		T currTop = stack.get(top);
		top--;
		return currTop;
	}

	public int size(){
		return top+1;
	}

	public T top(){
		if(top<0){
			System.out.println("Stack is empty");
			return null;
		}
		return stack.get(top);
	}
	
}
