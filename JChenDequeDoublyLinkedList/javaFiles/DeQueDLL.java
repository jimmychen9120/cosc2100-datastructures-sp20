package ch04.queues;

import ch04.queues.*;
import support.DLLNode;
import support.LLNode;
/**
 * COSC 1020 - Spring 2020 Assignment #4 
 * This is the class where the Assign4 application will pull methods from and create DLL's from
 * @author Jimmy Chen
 */
public class DeQueDLL<T> implements DequeInterface<T> {

	protected DLLNode<T> front, rear; // reference to the front and rear of this deque
	protected int numElements; // number of elements in this deque

	public DeQueDLL() {
		front = null;
		rear = null;
	}

	public void enqueueFront(T element) {
		// TODO Auto-generated method stub
		if (isFull()) {
			throw new QueueOverflowException("Queue is full.");
		} else {
			DLLNode<T> newNode = new DLLNode<T>(element);
			newNode.setForward(front);
			if(front!=null) {
				front.setBack(newNode);
			}
			if(front==null) {
				rear=newNode;
			}
			front=newNode;
			numElements++;
		}
	}

	public void enqueueRear(T element) {
		// TODO Auto-generated method stub
		if (isFull()) {
			throw new QueueOverflowException("Queue is full.");
		} else {
			DLLNode<T> newNode = new DLLNode<T>(element);
			newNode.setBack(rear);
	        if(rear != null) {
	        	rear.setForward(newNode);
	        }
	        if(rear == null) {
	        	front = newNode;
	        }
	        rear = newNode;
			numElements++;
		}
	}

	public T dequeueFront() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new QueueUnderflowException("Queue is empty.");
		} else {
			DLLNode<T> temp=front.getForward();
			if(temp!=null) {
				temp.setBack(null);
			}
			if(temp==null) {
				rear=null;
			}
			T oldFront=front.getInfo();
			front=temp;
			numElements--;
			return oldFront;
			}
		}

	public T dequeueRear() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new QueueUnderflowException("Queue is empty.");
		} else {
			DLLNode<T> temp=rear.getBack();
			if(temp!=null) {
				temp.setForward(null);
			}
			if(temp==null) {
				front=null;
			}
			T oldRear=rear.getInfo();
			rear=temp;
			numElements--;
			return oldRear;
		}
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (front==null);
	}

	public int size() {
		// TODO Auto-generated method stub
		return numElements;
	}
	
	public String toString() {
		String str="";
		DLLNode<T> node = front;
		for(int i=0; i<numElements; i++) {
			if(node.getBack()!=null) {
				str+="<-->";
			}
			str+=node.getInfo();
			node=node.getForward();
		}
		return str;
	}
	
	//This method will return the data of the front element in the application
	public T getFront() {
		return front.getInfo();
	}
	
	//This method will return the data of the rear element in the application
	public T getRear() {
		return rear.getInfo();
	}
	
	//Search method is used to find a certain integer in the deque
	public boolean search(T elem){
		DLLNode<T> temp=front;
		while(temp!=null) {
			if(temp.getInfo()==elem) {
				return true;
			}else {
				temp=temp.getForward();
			}
		}
		return false;
	}
}
