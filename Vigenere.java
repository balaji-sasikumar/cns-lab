import java.util.Scanner;
public class Vigenere {
	public static void main(String[] args) {
		char[][]table = new char[26][26];
		int[] tableKey=new int[1];
		for(int i = 0;i<26;i++) {
			for(int j = 0; j<26;j++) {
				table[i][j] = (char) (((i+j) % 26) + 65);
			}
		}
		String plaintext="",ciphertext="",key="";
		Scanner scanner = new Scanner(System.in);

		while(true) {
			System.out.println("1. Read plaintext");
			System.out.println("2. Read key");
			System.out.println("3. Encrypt");
			System.out.println("4. Decrypt");
			System.out.println("5. Print Vigenere Table");
			System.out.println("6. Exit");
			int ch = scanner.nextInt();
			if(ch==1) {
				System.out.println("Enter plaintext:");
				scanner.nextLine();
				plaintext = scanner.nextLine();
			}
			else if (ch==2) {
				System.out.println("Enter key:");
				scanner.nextLine();
				String tkey = scanner.nextLine();
				key=""+tkey;
				while(key.length() < plaintext.length())
					key += tkey;

				// convert key from character to integer
				tableKey = new int[key.length()];
				for (int i=0; i<key.length(); i++) {
					tableKey[i] = key.charAt(i) - 65;
				}
			}
			else if (ch==3) {
				for(int i=0;i<plaintext.length();i++) {
					ciphertext+= table[ tableKey[i] ][plaintext.charAt(i)-65];
				}
				System.out.println("The ciphertext is: "+ciphertext);		
			}
			else if (ch==4) {
				plaintext = "";
				for(int i=0;i<ciphertext.length();i++) {
					int row = tableKey[i];
					int j;
					//search through this row of the table to find ciphertext
					//it's column label is the plaintext
					for(j=0;j<26;j++)
						if(table[row][j] == ciphertext.charAt(i))
							break;
					plaintext+= (char)(j+65);
				}
				System.out.println("The plaintext is: "+plaintext);		
			}
			else if (ch==5) {
				for(int i=0;i<26;i++) {
					for(int j=0;j<26;j++)
						System.out.print(table[i][j] + " ");
					System.out.println();
				}
			}
			else if (ch==6) {
				break;
			}
		}
	}
}