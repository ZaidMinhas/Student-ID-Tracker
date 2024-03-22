public class HashTable {

		//Total number of keys and values
		public int count;
		//Current capacity of hashtable
		private int capacity;
		//Upper limit of keys - 1
		private final double LIMIT = 99999999;
		//Starting capacity of hashtable
		private final int INITIAL_CAPACITY = 16;
		//Load factor = count/capacity 
		private final float LOAD_FACTOR = 0.9f;
		//The table that will contain all the data
		LinkedList[] table;
		
		public HashTable(){
			capacity = INITIAL_CAPACITY;
			
			table = new LinkedList[capacity];
			
			for (int i = 0; i < capacity; i++) {
				table[i] = new LinkedList();
			}
		}
		
		//Hashing algorithm
		public int hash(int key) {
			return (int)(key *(capacity/LIMIT));
		}
		
		//Adding keys and rehashing 
		public void add(int key, String value) {
			//Rehashing if student count exceeds or reaches load factor
			if (count >= LOAD_FACTOR*capacity) {
				rehash();
			}
			
			
			int index = hash(key);
			table[index].add(key, value);
			count++;
		}
		
		//Removing a key from hashtable
		public boolean remove(int key) {
			int index = hash(key);
			if (table[index].remove(key) != null) {
				count--;
				return true;
			}
			return false;
				
		}
		
		//Rehashing algorithm when key count reaches load factor
		public void rehash() {
			//Doubling the capacity
			capacity *= 2;
			
			//initializing new hashtable
			LinkedList[] new_table = new LinkedList[capacity];
			for (int i = 0; i < capacity; i++) {
				new_table[i] = new LinkedList();
			}
			
			//extracting data from previous hashtable
			for (LinkedList bins : table) {
				Student[] students = bins.allStudents();
				for (Student student: students) {
					int index = hash(student.getKey());
					
					new_table[index].add(student.getKey(), student.getValues());
				}
			}
			table = new_table;
			
		}
		
		public boolean contains(int key) {
			int index = hash(key);
			return table[index].contains(key);	
		}
		
		public int nextKey(int key) {
			int index = hash(key);
			return table[index].nextKey(key);
	
		}	
		
		public int prevKey(int key) {
			int index = hash(key);
			return table[index].prevKey(key);
		}
		
		public String getValues(int key) {
			int index = hash(key);
			return table[index].getValues(key);
		}
		
		//Get all keys sorted in a list
		public int[] allKeys() {
			//initiate list
			int[] keys = new int[count];
			int index = 0;
			
			//Check all bins
			for (LinkedList bin : table) {
				//Collect all keys from each bin
				int[] bin_keys = bin.allKeysSorted();
				
				//Add keys to list
				for (int key : bin_keys) {
					keys[index++] = key;
				}
			}
			//return keys
			return keys;
		}
		
		public Student[] allStudents() {
			//initiate list
			Student[] students = new Student[count];
			int index = 0;
			
			//Check all bins
			for (LinkedList bin : table) {
				//Collect all keys from each bin
				Student[] bin_student = bin.allStudents();
				
				//Add keys to list
				for (Student student : bin_student) {
					students[index++] = student;
				}
			}
			//return keys
			return students;
		}

		public int rangeKey(int key1, int key2) {
			int count_range = 0;
			
			int startRange = hash(key1);
			int endRange = hash(key2);
			
			for (int i = startRange; i <= endRange; i++) {
				int[] bin_keys = table[i].allKeys();
				
				for (int key: bin_keys) {
					if (key >= key1 && key <= key2) {
						count_range++;
					}
				}
			}
			
			return count_range;
			
		}
}
