import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;
import java.util.NoSuchElementException;


public class Main {

	public static void main(String[] args) throws IOException {
		
		FileWriter bench = new FileWriter("BenchTest.txt");
		double t1;
		double t2;
		
		
		//Random rand = new Random();
		CleverSIDC s = new CleverSIDC();
		
		//switch cases for testing different methods 
		boolean close_program = false; 
		Scanner keyboard = new Scanner(System.in);
		
		//reading test file
		try {
			System.out.print("Enter txt file name: ");
			String file_input = keyboard.next();
			Scanner test_file = new Scanner(new FileInputStream(file_input)); 

			t1= System.nanoTime();
			while (test_file.hasNextLine()) {
				int key = test_file.nextInt();
				s.add(s,key, "");
			}
			t2= System.nanoTime();
			bench.write("Time taken to add " + s.count + " student: " + (t2-t1) + " ns\n");
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found, restart I guess or something");
		}
		
		
		
		String options = "1. SetSIDCThreshold \n2. generate \n3. allKeys \n4. add"
				+  "\n5. remove \n6. getValues \n7. nextKey \n8. prevKey \n9. rangeKey \n0. exit";
		
		
		while(close_program == false) {
			System.out.println("CleverSIDC Methods \n------------------");
			System.out.println(options); 
			int choice = keyboard.nextInt();
			
			switch (choice) {
				//SetSIDCThreshold
				case 1: 
					System.out.println("Set a threshold"); 
					int threshold = keyboard.nextInt();
					s.SetSIDCThreshold(threshold);
					System.out.println("Threshold = " + s.getThreshold() + "\n");
					break;
					
				//generate	
				case 2:
					t1 = System.nanoTime();
					int newKey = s.generate();
					t2 = System.nanoTime();
					bench.write("Time taken to generate a key : " + (t2-t1) + " ns\n");
					System.out.println("New Key added = " + newKey + "\n");
					break;	
					
				//allKeys
				case 3:
					
					t1 = System.nanoTime();
					int[] allKeys = s.allKeys(s);
					t2 = System.nanoTime();
					bench.write("Time taken to get all keys : " + (t2-t1) + " ns\n");
					for (int key: allKeys) {
						System.out.println("\t" + key);
					}
					System.out.println("All Keys returned \n");
					System.out.println("");
					break;
					
				//add
				case 4:
					System.out.println("Add a new key: "); 
					int adding = keyboard.nextInt();
					
					t1 = System.nanoTime();
					s.add(s, adding, "");
					t2 = System.nanoTime();
					bench.write("Time taken to get add a key: " + (t2-t1) + " ns\n");
					
					
					System.out.println("");
					break;
					
				//remove
				case 5:
					System.out.println("Remove a key: "); 
					int removing = keyboard.nextInt();
					
					
					t1 = System.nanoTime();
					s.remove(s, removing);
					t2 = System.nanoTime();
					bench.write("Time taken to get remove a key: " + (t2-t1) + " ns\n");
					
					System.out.println("");
					break;
					
				//getValues
				case 6:
					System.out.println("To get a value, enter key: "); 
					int keyget = keyboard.nextInt();
					
					
					t1 = System.nanoTime();
					String value = s.getValues(s, keyget);
					t2 = System.nanoTime();
					bench.write("Time taken to get a value: " + (t2-t1) + " ns\n");
					
					System.out.println(value);
					System.out.println("");
					break;
					
				//nextKey
				case 7:
					System.out.println("Get next key: "); 
					int nextkey = keyboard.nextInt();
					
					
					t1 = System.nanoTime();
					int nextKey = s.nextKey(s, nextkey);
					t2 = System.nanoTime();
					bench.write("Time taken to get next Key: " + (t2-t1) + " ns\n");
					
					System.out.println(nextkey);
					System.out.println("");
					break;
					
				//prevKey
				case 8:
					System.out.println("Get previous ext key: "); 
					int prevkey = keyboard.nextInt();
					
					t1 = System.nanoTime();
					int prevKey = s.prevKey(s, prevkey);
					t2 = System.nanoTime();
					bench.write("Time taken to get prev Key: " + (t2-t1) + " ns\n");
					System.out.println(prevkey);
					
					System.out.println("");
					break;
					
				//rangeKey
				case 9:
					
					System.out.println("Enter 1st key: "); 
					int firstkey = keyboard.nextInt();
					System.out.println("Enter 2nd key: "); 
					int secondkey = keyboard.nextInt();
					
					
					t1 = System.nanoTime();
					int keys = s.rangeKey(firstkey, secondkey);
					t2 = System.nanoTime();
					bench.write("Time taken to get range of keys from " + firstkey + " to " + secondkey + ": " + (t2-t1) + " ns\n");
					
					System.out.println(keys);
					System.out.println("");
					break;
					
				//exit
				case 0: 
					System.out.println("Bye-Bye!");
					close_program = true;
					keyboard.close();
					bench.close();
			}
		}
	}

}
