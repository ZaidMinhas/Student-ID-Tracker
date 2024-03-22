import java.util.Random;

public class LinkedList {

	
	class Node{	
			
			private Student student;
			private Node prev;
			private Node next;
			
			public Node(Student student) {
				this.student = student;
			}
			
			public Node(int key, String value) {
				student = new Student(key, value);
			}
			
			public Student getStudent() {
				return student;
			}
			
			public int getKey() {
				return student.getKey();
			}
			
			public String getValues() {
				return student.getValues();
			}
		}
	Node head;
	int count = 0;
	
	public int nextKey(int key) {
		Node n = find(key);
		if (n != null && n.next != null) {
			return n.next.getKey();
		}
		else {
			return -1;
		}
	}
	
	public int prevKey(int key) {
		Node n = find(key);
		if (n != null && n.prev != null) {
			return n.prev.getKey();
		}
		else {
			return -1;
		}
	}
	
	public void add(int key, String value) {
		Node n = new Node(key, value);
		count++;
		if (head == null) {
			head = n;
		}
		else {
			n.next = head;
			head.prev = n;
			head = n;
		}
	}
	
	//checks if bin contains a key
	public boolean contains(int key) {
		//if a node is found return true else false
		return find(key) != null;
	}
	
	public Node find(int key) {
		//Cycle throught all nodes starting from head

		Node n = head;
		while (n != null) {
			if (n.getKey() == key) {
				return n;
			}
			n = n.next;
		}
		return null;
	}
	
	public String getValues(int key) {
		Node n = find(key);
		if (n != null) {
			return n.getValues();
		}
		return null;
	}
		
	public Node remove(int  key) {
		Node n = find(key);
		if (n != null) {
			if (head == n) {
				head = head.next;
				if (head != null)
					head.prev = null;
			}
			
			else {
				Node previous = n.prev;
				Node after = n.next; 
				
				previous.next = after;
				if (after != null)
				after.prev = previous;
				
				n.prev = null;
				n.next = null;
			}
			count--;
		}
		return n;
	}
	
	public Node pop() {
		Node top = head;
		head = head.next;
		return top;
	}
	
	public int[] allKeys() {
		//Initiate bin keys
		int[] keys = new int[count];
		int index = 0;
		
		Node n = head;
		while (n != null) {
			//get key from each node
			keys[index++] = n.getKey();
			n = n.next;
		}

		//return keys in bin
		return keys;
	}
	
	public int[] allKeysSorted() {
		int[] keys = allKeys();
		//All keys sorted
		quicksort(keys, 0, count-1);
		return keys;
		
	}
	
	public Student[] allStudents() {
		Student[] students = new Student[count];
		int index = 0; 
		Node n = head;
		while (n != null) {
			students[index++] = n.student;
			n = n.next;
		}
		return students;
	}
	
	
	private void quicksort(int[] keys, int lower, int higher) {
		if (lower >= higher) {
			return;
		}
		
		int index = new Random().nextInt(higher-lower) + lower;
		int pivot = keys[index];
		swap(keys, index, higher);
		
		int left = partition(keys, lower, higher, pivot);
		
		quicksort(keys, lower, left-1);
		quicksort(keys, left+1, higher);
	}
	
	
	private int partition(int[] keys, int lower, int higher, int pivot) {
		int left = lower;
		int right = higher;
		
		while (left < right) {
			while (keys[left] <= pivot && left < right ) {
				left++;
			}
			
			while (keys[right] >= pivot && left < right) {
				right--;
			}
			
			swap(keys, left, right);
		}
		
		swap(keys, left, higher);
		return left;
	}
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
