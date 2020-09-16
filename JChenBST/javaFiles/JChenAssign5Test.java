package assign5;
/**
 * COSC 2100 - Spring 2020 
 * Assignment 5
 * This program tests methods of 'JChenBinarySearchTree' to see the second largest value, each value in a level,
 * every leaf node, all paths, and whether the BST is a 'full' BST
 * @author Jimmy Chen
 */
public class JChenAssign5Test {
	public static void main(String[] args) {
		JChenBinarySearchTree<String> test = new JChenBinarySearchTree<String>();
		test.add("3");
		test.add("2");
		test.add("5");
		test.add("1");
		test.add("4");
		test.add("6");
		test.add("19");
		test.add("12");

		System.out.println("What's the second largest node of the BST?");
		System.out.println("------------------------------------------");
		System.out.println(test.getSecondLargest());
		System.out.println(" ");
		
		System.out.println("Depth-First Traversal of BST");
		System.out.println("----------------------------");
		test.printByLevel();
		System.out.println(" ");
		
		System.out.println("Leaf Nodes of BST");
		System.out.println("-----------------");
		test.printLeafNodes();
		System.out.println(" ");
		
		System.out.println("All Paths in BST");
		System.out.println("----------------");
		test.printPaths();
		System.out.println(" ");
		
		System.out.println("Is this BST a 'full' BST?");
		System.out.println("-------------------------");
		System.out.println(test.isFullBinarySearchTree());
		System.out.println(" ");
	}
}
