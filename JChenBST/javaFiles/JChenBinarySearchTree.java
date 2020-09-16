package assign5;
//----------------------------------------------------------------------------

//BinarySearchTree.java          by Dale/Joyce/Weems                Chapter 7
//
//Defines all constructs for a reference-based BST
//----------------------------------------------------------------------------

import ch04.queues.*;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import ch02.stacks.*;
import support.BSTNode;
/**
 * COSC 2100 - Spring 2020 
 * Assignment 5
 * This class contains methods which aids in figuring out qualities of a given BST
 * @author Jimmy Chen
 */
public class JChenBinarySearchTree<T extends Comparable<T>> implements JChenBSTInterface<T> {
	protected BSTNode<T> root; // reference to the root of this BST

	boolean found; // used by remove

	// for traversals
	protected LinkedQueue<T> inOrderQueue; // queue of info
	protected LinkedQueue<T> preOrderQueue; // queue of info
	protected LinkedQueue<T> postOrderQueue; // queue of info

	public JChenBinarySearchTree()
	// Creates an empty BST object.
	{
		root = null;
	}

	public boolean isEmpty()
	// Returns true if this BST is empty; otherwise, returns false.
	{
		return (root == null);
	}

	private int recSize(BSTNode<T> node)
	// Returns the number of elements in tree.
	{
		if (node == null)
			return 0;
		else
			return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
	}

	public int size()
	// Returns the number of elements in this BST.
	{
		return recSize(root);
	}

	public int size2()
	// Returns the number of elements in this BST.
	{
		int count = 0;
		if (root != null) {
			LinkedStack<BSTNode<T>> hold = new LinkedStack<BSTNode<T>>();
			BSTNode<T> currNode;
			hold.push(root);
			while (!hold.isEmpty()) {
				currNode = hold.top();
				hold.pop();
				count++;
				if (currNode.getLeft() != null)
					hold.push(currNode.getLeft());
				if (currNode.getRight() != null)
					hold.push(currNode.getRight());
			}
		}
		return count;
	}

	private boolean recContains(T element, BSTNode<T> node)
	// Returns true if tree contains an element e such that
	// e.compareTo(element) == 0; otherwise, returns false.
	{
		if (node == null)
			return false; // element is not found
		else if (element.compareTo(node.getInfo()) < 0)
			return recContains(element, node.getLeft()); // Search left subtree
		else if (element.compareTo(node.getInfo()) > 0)
			return recContains(element, node.getRight()); // Search right subtree
		else
			return true; // element is found
	}

	public boolean contains(T element)
	// Returns true if this BST contains an element e such that
	// e.compareTo(element) == 0; otherwise, returns false.
	{
		return recContains(element, root);
	}

	private T recGet(T element, BSTNode<T> node)
	// Returns an element e from tree such that e.compareTo(element) == 0;
	// if no such element exists, returns null.
	{
		if (node == null)
			return null; // element is not found
		else if (element.compareTo(node.getInfo()) < 0)
			return recGet(element, node.getLeft()); // get from left subtree
		else if (element.compareTo(node.getInfo()) > 0)
			return recGet(element, node.getRight()); // get from right subtree
		else
			return node.getInfo(); // element is found
	}

	public T get(T element)
	// Returns an element e from this BST such that e.compareTo(element) == 0;
	// if no such element exists, returns null.
	{
		return recGet(element, root);
	}

	private BSTNode<T> recAdd(T element, BSTNode<T> node)
	// Adds element to tree; tree retains its BST property.
	{
		if (node == null)
			// Addition place found
			node = new BSTNode<T>(element);
		else if (element.compareTo(node.getInfo()) <= 0)
			node.setLeft(recAdd(element, node.getLeft())); // Add in left subtree
		else
			node.setRight(recAdd(element, node.getRight())); // Add in right subtree
		return node;
	}

	public void add(T element)
	// Adds element to this BST. The tree retains its BST property.
	{
		root = recAdd(element, root);
	}

	private T getPredecessor(BSTNode<T> node)
	// Returns the information held in the rightmost node in tree
	{
		while (node.getRight() != null)
			node = node.getRight();
		return node.getInfo();
	}

	private BSTNode<T> removeNode(BSTNode<T> node)
	// Removes the information at the node referenced by tree.
	// The user's data in the node referenced by tree is no
	// longer in the tree. If tree is a leaf node or has only
	// a non-null child pointer, the node pointed to by tree is
	// removed; otherwise, the user's data is replaced by its
	// logical predecessor and the predecessor's node is removed.
	{
		T data;

		if (node.getLeft() == null)
			return node.getRight();
		else if (node.getRight() == null)
			return node.getLeft();
		else {
			data = getPredecessor(node.getLeft());
			node.setInfo(data);
			node.setLeft(recRemove(data, node.getLeft()));
			return node;
		}
	}

	private BSTNode<T> recRemove(T element, BSTNode<T> node)
	// Removes an element e from tree such that e.compareTo(element) == 0
	// and returns true; if no such element exists, returns false.
	{
		if (node == null)
			found = false;
		else if (element.compareTo(node.getInfo()) < 0)
			node.setLeft(recRemove(element, node.getLeft()));
		else if (element.compareTo(node.getInfo()) > 0)
			node.setRight(recRemove(element, node.getRight()));
		else {
			node = removeNode(node);
			found = true;
		}
		return node;
	}

	public boolean remove(T element)
	// Removes an element e from this BST such that e.compareTo(element) == 0
	// and returns true; if no such element exists, returns false.
	{
		root = recRemove(element, root);
		return found;
	}

	private void inOrder(BSTNode<T> node)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (node != null) {
			inOrder(node.getLeft());
			inOrderQueue.enqueue(node.getInfo());
			inOrder(node.getRight());
		}
	}

	private void preOrder(BSTNode<T> node)
	// Initializes preOrderQueue with tree elements in preOrder order.
	{
		if (node != null) {
			preOrderQueue.enqueue(node.getInfo());
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	private void postOrder(BSTNode<T> node)
	// Initializes postOrderQueue with tree elements in postOrder order.
	{
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			postOrderQueue.enqueue(node.getInfo());
		}
	}

	public int reset(int orderType)
	// Initializes current position for an iteration through this BST
	// in orderType order. Returns current number of nodes in the BST.
	{
		int numNodes = size();

		if (orderType == INORDER) {
			inOrderQueue = new LinkedQueue<T>();
			inOrder(root);
		} else if (orderType == PREORDER) {
			preOrderQueue = new LinkedQueue<T>();
			preOrder(root);
		}
		if (orderType == POSTORDER) {
			postOrderQueue = new LinkedQueue<T>();
			postOrder(root);
		}
		return numNodes;
	}

	public T getNext(int orderType)
	// Preconditions: The BST is not empty
	// The BST has been reset for orderType
	// The BST has not been modified since the most recent reset
	// The end of orderType iteration has not been reached
	//
	// Returns the element at the current position on this BST for orderType
	// and advances the value of the current position based on the orderType.
	{
		if (orderType == INORDER)
			return inOrderQueue.dequeue();
		else if (orderType == PREORDER)
			return preOrderQueue.dequeue();
		else if (orderType == POSTORDER)
			return postOrderQueue.dequeue();
		else
			return null;
	}

	//This method determines the second largest node in the BST
	public T getSecondLargest() {
		//Resets to inOrder 
		this.reset(1);
		T node = null;
		for (int i = 1; i < this.size(); i++) {
			//Gets the next node in inOrder order
			node = this.getNext(1);
		}
		return node;
	}

	// This method aids in getting the height/level of a BST
	private int recHeight(BSTNode<T> node) {
		if (node == null) {
			return -1;
		} else {
			return Math.max(recHeight(node.getLeft()), recHeight(node.getRight())) + 1;
		}

	}

	// This method gets the height/levels of a BST
	public int height() {
		// calls the method recHeight
		return recHeight(root);
	}

	//This method prints the nodes in Depth-First Traversal order
	public void printByLevel() {
		//Initialize a LinkedList in order to hold each level's nodes
		LinkedList<BSTNode<T>> hold = new LinkedList<BSTNode<T>>();
		hold.add(root);

		//Runs this for-loop for each level
		for (int i = 0; i <= height(); i++) {
			int count = hold.size();
			//While there is still nodes in the LinkedList
			while (count > 0) {
				//Node holds the first/front value of the LinkedList
				BSTNode<T> node = hold.getFirst();
				//Prints out the Node
				System.out.print(node.getInfo() + " ");
				//Removes the first value of the LinkedList
				hold.remove();

				//Add the left node from the first node (if any)
				if (node.getLeft() != null) {
					hold.add(node.getLeft());
				}
				//Add the right node from the first node (if any)
				if (node.getRight() != null) {
					hold.add(node.getRight());
				}
				//Decreases the count by 1 in order to gather each new node in the level to the LinkedList
				count--;
			}
			System.out.println();
		}
	}

	//This method aids in finding and printing each leaf node in the BST
	private void recPrintLeafNodes(BSTNode<T> node) {
		//prints if there is a leaf node
		if (node.getLeft() == null && node.getRight() == null) {
			System.out.println(node.getInfo());
		}
		//Continues down the left subtree of BST to find leaf node
		if (node.getLeft() != null) {
			recPrintLeafNodes(node.getLeft());
		}
		//Continues down the right subtree of BST to find leaf node
		if (node.getRight() != null) {
			recPrintLeafNodes(node.getRight());
		}
	}

	//This method prints out all leaf nodes based off of the starting root
	public void printLeafNodes() {
		recPrintLeafNodes(root);
	}
	
	//This method aids in printing out all the paths in the BST
	private void recPrintPaths(BSTNode<T> node, ArrayDeque<T> path) {
		//adds the node from argument into Deque
		path.addLast(node.getInfo());

		//If the node is a leaf node, print out the entire path in Deque
		if (node.getLeft() == null && node.getRight() == null) {
			System.out.println(path);
		} else { //If not a leaf node, keep searching left & right subtrees for paths to leaf nodes
			if (node.getLeft() != null) {
				recPrintPaths(node.getLeft(), path);
			}
			if (node.getRight() != null) {
				recPrintPaths(node.getRight(), path);
			}
		}
		//Remove the last node in Deque
		path.removeLast();
	}

	//This method prints out all the paths in the BST
	public void printPaths() {
		//initialize a ArrayDequq in order to store each path by adding nodes
		ArrayDeque<T> path = new ArrayDeque<T>();
		recPrintPaths(root, path);
	}
	
	//This method returns a boolean value that determines whether the BST is a "full" BST, by definition of
	//BST size in relation to height
	public boolean isFullBinarySearchTree() {
		return (this.size()==Math.pow((this.height()+1), 2));
	}
	
}
