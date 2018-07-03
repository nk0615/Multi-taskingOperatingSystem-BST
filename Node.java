package binarySearchTree;

public class Node {
    
    public String name;      //data
    public int priorityCode; //key
    public Node parent;
    public Node left;
    public Node right;

    public Node (String name) {
	this.name = name;
	this.priorityCode = (int)(Math.random()*1000);
	this.parent = null;
	this.left = null;
	this.right = null;
    }
}
