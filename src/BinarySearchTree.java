public class BinarySearchTree{
	
	class Node{
		Node left;
		Node right;
		
		Student student;
		public Node(Student student) {
			this.student = student;
		}
		public Node(int key, String value) {
			student = new Student(key, value);
		}
		
		
		public int getKey() {
			return student.getKey();
		}
		
		public String getValues() {
			return student.getValues();
		}
		
		public Student getStudent() {
			return student;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public Node getRight() {
			return right;
		}
	}
	
	public Node root;
	int count;
	
	public void add(int key, String value) {	
		//Add student starting from root using recursions
		root = add(root, key, value);
		count++;
	}
	
	private Node add(Node node, int key, String value) {
		//Base case
		if (node == null) {
			node = new Node(key, value);
			} 
		else {
			//If key is smaller then parent
			if (key < node.getKey()) {
				node.left = add(node.left, key, value);
			} 
			//If key is larger than parent
			else {
				node.right = add(node.right, key, value);
				}
			}

		    return node;
	}
	
	
	
	public boolean remove(int key) {
		//Check if key actually exists in BST
		if (contains(key)) {
			//Call recursive method starting from root
			root = remove(root, key);
			count--;
			return true;
			}
		return false;
	}
	
	//Recursive method called by public remove method
	private Node remove(Node node, int key) {
		if (node == null) {
		    return null;
		}
		    
		//Check left subtree
		if (key < node.getKey()) {
			node.left = remove(node.left, key);
		} 
		    
		///Check right subtree
		else if (key > node.getKey()) {
			node.right = remove(node.right, key);
			
		} 
		//Node with key is found
		else {
			// Case 1 for right child or none, swap node with right child
			if (node.left == null) {
				return node.right;
			} 
			//Case 2 for left child or none, swap node with left child
			else if (node.right == null) {
				return node.left;
			}
			// Case 3 for when there are 2 children, replace with smallest key at right subtree
			else {
				//Find the leftmost node in the right subtree
		        Node new_child = smallest(node.right);

		        // Swap the data
		        node.student = new_child.student;
		        node.right = remove(node.right, new_child.getKey());
		      }
		    }

		    return node;
		  }
	
	//Find if BST contains a key
	public boolean contains(int key) {
		//Calls recursive method starting from root
	    return find(root, key) != null;
	  }

	public int nextKey(int key) {
		  Node n = find(root,key);
		  if (n != null && n.right != null) {
			  return n.right.getKey();
		  }
		  else {
			  return -1;
		  }
	}	
	
	public int prevKey(int key) {
		  Node n = find(root,key);
		  if (n != null && n.left != null) {
			  return n.left.getKey();
		  }
		  else {
			  return -1;
		  }
	}
	
	public String getValues(int key) {
		Node n = find(root, key);
		if (n != null) {
			return n.getValues();
		}
		return null;
		
	}
	
	private Node find(Node node, int key) {
		//if no node found then return false
		if (node == null) 
			return null;
		
		//if key is smaller than node, check left child
	    if (key  < node.getKey()) {
	    	return find(node.left, key);
	    }
	    //else if key is larger than node, check right child
	    else if (key > node.getKey()) 
	    	return find(node.right, key);
	    //else key is found, return true
	    else return node;
	  }
	
	  
	  private Node smallest(Node node) {
		    while (node.left != null) { 
		    	node = node.left;
		    }
		    return node;
	  }
	  
	  public int[] allKeys() {
		  //Initiate array of keys
		  int[] keys = new int[count];
		  index = 0;
		  //Get keys by doing inOrder starting from root
		  inOrder(root, keys);
		  return keys;
	  }
	  
	  private int index = 0;
	  
	  private void inOrder(Node node, int[] keys) {
		  if (node.left != null) {
			  inOrder(node.left, keys);
		  }
		  visit(node, keys);
		  if (node.right != null) {
			  inOrder(node.right, keys);
		  }
	  }
	  
	  private void visit(Node n, int[] keys) {
		  keys[index++] = n.getKey();
	  }

	  
	private int range_count = 0;
	public int rangeKey(int key1, int key2) {
		range_count = 0;
		inOrderRange(root, key1, key2);
		return range_count;
	}
	
	
	private void inOrderRange(Node node,int key1,int key2) {
		  if (node.left != null) {
			  inOrderRange(node.left, key1, key2);
		  }
		  visitRange(node, key1, key2);
		  if (node.right != null) {
			  inOrderRange(node.right, key1, key2);
		  }
	  }
	  
	  private void visitRange(Node n, int key1, int key2) {
		  if (n.getKey() >= key1 && n.getKey()  <= key2) {
				range_count++;
			}
	  }
	  
	  public Node pop() {
		  return remove(root, root.getKey());
	  }
}
