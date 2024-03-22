import java.util.Currency;
import java.util.Random;

public class CleverSIDC {
	Random rand = new Random();
	
	private HashTable HT;
	private BinarySearchTree BST;
	 
	
	private final int DEFAULT_THRESHOLD = 100;
	int count = 0;
	private int threshold;
	boolean thresholdPassed;
	
	//Constructor will initialize Hashtable and its bins
	public CleverSIDC() {
		HT = new HashTable();
		threshold = DEFAULT_THRESHOLD;
	}
	
	
	//threshold getter
    public int getThreshold() {
        return this.threshold;
    }

	public void SetSIDCThreshold(int Size) {
		if (Size <= 100) {
			threshold = 100;
		}
		else if(Size >= 500000) {
			
			threshold = 500000;
		}
		else {
			threshold = Size;
		}
		CheckThreshold();
	}
	
	public int generate() {
		int random_key;
		
		while (true) {
			//generate key
			random_key = rand.nextInt(100000000);
			
			//check if using BST
			if (thresholdPassed) {
				//stop if key is unique
				if (!BST.contains(random_key))
					break;
			}
			//else using HT
			else {
				//stop if key is unique 
				if (!HT.contains(random_key))
					break;
			}
		}
		//return unique key
		return random_key;
	}
	
	//Add a student to SIDC (DOES NOT CHECK FOR DUPLICATES)
	public void add(CleverSIDC SIDC, int key, String value) {
		//Check if using BinarySearchTree or HashTable
		CheckThreshold();
		count++;
		if (SIDC.thresholdPassed) {
			SIDC.BST.add(key, value);
		}
		else {
			SIDC.HT.add(key, value);
		}
		
	}
	
	public void remove(CleverSIDC SIDC, int key) {
		
		//Check if using BinarySearchTree or HashTable
		if (SIDC.thresholdPassed) {
			if(SIDC.BST.remove(key)) {
				count--;
			}
		}
		else {
			if(SIDC.HT.remove(key)) {
				count--;
			}
		}
		CheckThreshold();
	}

	public int[] allKeys(CleverSIDC SIDC) {
		if (SIDC.thresholdPassed) {
			
			return SIDC.BST.allKeys();
		}
		else {
			return SIDC.HT.allKeys();
		}
		
	}
	
	public int nextKey(CleverSIDC SIDC, int key) {
		if (SIDC.thresholdPassed) {
			return SIDC.BST.nextKey(key);
		}
		else {
			return SIDC.HT.nextKey(key);
		}
	}
	
	public int prevKey(CleverSIDC SIDC, int key) {
		if (SIDC.thresholdPassed) {
			return SIDC.BST.prevKey(key);
		}
		else {
			return SIDC.HT.prevKey(key);
		}
	}
	
	
	public String getValues(CleverSIDC SIDC, int key) {
		if (SIDC.thresholdPassed) {
			return SIDC.BST.getValues(key);
		}
		else {
			return SIDC.HT.getValues(key);
		}
	}
	
	public int rangeKey(int key1, int key2) {
		if (thresholdPassed) {
			return BST.rangeKey(key1, key2);
		}
		else {
			return HT.rangeKey(key1, key2);
		}
	}
	
	private void HTtoBST() {
		BST = new BinarySearchTree();
		Student[] allStudents = HT.allStudents();
		for (Student s: allStudents) {
			BST.add(s.getKey(), s.getValues());
		}
		
		HT = null;
	}
	
	private void BSTtoHT() {
		HT = new HashTable();
		while (BST.root != null) {
			Student s = BST.pop().getStudent();
			HT.add(s.getKey(), s.getValues());
		}
		BST = null;
	}

	public void CheckThreshold() {
		if (count >= threshold && BST == null) {
			thresholdPassed = true;

			HTtoBST();
			
		}
		else if (count < threshold && HT == null) {
			thresholdPassed = false;

			BSTtoHT();
		}
		
		
	}

	
	
		
	
	
	
	
	
	
}