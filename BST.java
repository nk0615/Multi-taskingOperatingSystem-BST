/*	Program: Multi-tasking Operating System
 * 	Language: Java
 * 	Date: Apr 2018
 * 
 * 	Simulation of a simple CPU Process Scheduling System using the BINARY SEARCH TREE approach
 * 	
 */
package binarySearchTree;

import java.util.*;

public class BST {
    
    private Node root;
    
    public BST() {
	root = null;
    }
    
    //Find an element in the BST whose key is min
    public Node treeMinimum(Node x) {
	while(x.left != null) {
	    x = x.left;
	}
	return x;
    }
    
    // insert a task to the BST
    public String treeInsert(Node z) {
	Node y = null;
	Node x = this.root;
	while(x != null) {
	    y = x;
	    if(z.priorityCode <x.priorityCode) {
		x = x.left;
	    }
	    else {
		x = x.right;
	    }
	}
	z.parent = y;
	if( y == null) {
	    root = z;
	}
	else if (z.priorityCode < y.priorityCode) {
	    y.left = z;
	}
	else {
	    y.right = z;
	}
	return z.name;
    }
    
    // transplant the tree if deletion occur on the internal node
    public void transplant(Node u, Node v) {
	if(u.parent == null) {
	    root = v;
	}
	else if(u == u.parent.left) {
	    u.parent.left = v;
	}
	else {
	    u.parent.right = v;
	}
	if(v != null) {
	    v.parent = u.parent;
	}
    }
    
    //Search through the tree to find the node matches the give priority code
    public Node iterTreeSearch(int priorityCode) {
	Node x = root;
	while(x != null && priorityCode != x.priorityCode) {
	    if(priorityCode < x.priorityCode) {
		x = x.left;
	    }
	    else{
		x = x.right;
	    }
	}
	return x;
    }
	
    //delete a node from the tree
    public String treeDelete(int priorityCode) {
	Node z = iterTreeSearch(priorityCode);

	if(z == null) {
	    return "(FAILED) No such node: priority code ";
	}
	
	String name = z.name;
	if(z.left == null) {
	    transplant(z,z.right);
	}
	else if (z.right == null) {
	    transplant(z, z.left);
	}
	else {
	    Node y = treeMinimum(z.right);
	    if(y.parent != z) {
		transplant(y, y.right);
		y.right = z.right;
		y.right.parent = y;
	    }
	    transplant(z, y);
	    y.left = z.left;
	    y.left.parent = y;
	}
	return name;
    }
    
    // print out the sorted list of process according to the priority codes
    public Node inOrderTreeWalk(Node x) {
	if(x != null) {
	    inOrderTreeWalk(x.left);
	    System.out.println(x.name + "("+x.priorityCode+")");
	    inOrderTreeWalk(x.right);
	}
	return x;
    }
    
    // creation of simple user interface 
    public static void main(String[] argu) {
	Scanner reader = new Scanner(System.in);
	BST tree = new BST();
	String c;
	
	do {
                	System.out.println("Please insert instruction by inputting:");
                	System.out.println("INSERT - insert a task to the database");
                	System.out.println("DELETE - delete a task from the database");
                	System.out.println("SHOWME - shows all the tasks from the database");
                	System.out.println("EXIT   - quit the program");
                	
                	c = reader.next();
                	
                	
                	if(c.equals("INSERT")) {
                	    System.out.println("Insert a task: please give it a name.");
                	    String d = reader.next();
                	    Node n = new Node(d);
                	    System.out.println("Tasks inserted: " + tree.treeInsert(n) + " ("+n.priorityCode+")");
                	    System.out.println(" ");
                	}
        	
                	if(c.equals("DELETE")) {
                	    boolean success = false;
                	    int f = 0;
                	    
                	    while (!success) {
                    	    System.out.println("Delete a node: please enter the priority code of the task");
                    	    
                    	    String s = reader.next();
                    	    try {
                    		success = true;
                    		f = Integer.parseInt(s);
                    	    } catch (NumberFormatException e){
                    		System.out.println("Bad number, please retry");
                    		success = false;
                    	    }
                	    }
                	    System.out.println("Task deleted: " +  tree.treeDelete(f)+" ("+f+")");
                	    System.out.println(" ");
                	}
                	if(c.equals("SHOWME")) {
                	    System.out.println("-----------Existing Tasks in the system:-----------");
                	    tree.inOrderTreeWalk(tree.root);
                	    System.out.println(" ");
                	}
                	if(c.equals("EXIT")) {
                	    System.out.println("Thank you, Bye Bye.");
                	    break;
                	}
	}
                	
        while(c.equals("INSERT") || c.equals("DELETE") || c.equals("SHOWME") ||
        		!c.equals("INSERT") || !c.equals("SHOWME") || !c.equals("DELETE") );    	

   	reader.close();
    }
    
}
