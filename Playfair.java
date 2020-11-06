
import java.io.*;
public class Playfair{
	static char matrix[][] = new char[5][5];
	static String key;
	static String p;
	static String ciphertext;
	static String removeDuplicates(String input) {
		char[] s = input.toCharArray();
		String out = "";
		for (int i=0; i <s.length ; i++) {
			boolean flag = false;
			for(int j=0;j<i;j++)
				if (s[j]==s[i])
					flag = true;
			if(flag)
				continue;
			out += String.valueOf(s[i]);
		}
		return out;
	}

	static boolean alreadyInMatrix(int val) {
		// need to check if val is already in matrix
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				if(matrix[i][j] == val)
					return true;
		return false;
	}
	static int[] search(char x) {
		int[] out = new int[2];
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				if(matrix[i][j]==x)
				{
					out[0]=i;
					out[1] =j;
					break;
				}
		return out;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("1. Read plaintext");
			System.out.println("2. Read key");
			System.out.println("3. Encrypt");
			System.out.println("4. Decrypt");
			System.out.println("5. Exit");
			int opt = Integer.parseInt(br.readLine());
			if(opt==1)
			{
				System.out.println("Enter the plaintext: ");
				p = br.readLine();
				if(p.length()%2 != 0)
				p+="Z";
			}
			else if(opt == 2)
			{
				System.out.println("Enter the key: ");
				key = removeDuplicates(br.readLine());
				int t=0, alphabetcount=65;// monache 
				for(int i=0;i<5;i++)
					for(int j=0;j<5;j++)
					{
						if(t<key.length())
						{
							matrix[i][j] = key.charAt(t);
							t++;
						}
						else 
						{
							while(alreadyInMatrix(alphabetcount) || alphabetcount == 'J')
								alphabetcount++;
							matrix[i][j] =(char) alphabetcount++;
						}
					}
				System.out.println("Matrix:");
				for(int i=0;i<5;i++)
				{
					for(int j=0;j<5;j++)
						System.out.print(matrix[i][j]+" ");
					System.out.println();
				}	
			}
			else if(opt == 3)
			{
				char enc[] = new char[p.length()];
				for(int i = 0;i<p.length();i+=2)
				{
					int x1,y1,x2,y2;
					int[] coords;

					coords = search(p.charAt(i));
					x1 = coords[0]; y1=coords[1];
					coords = search(p.charAt(i+1));
					x2 = coords[0]; y2 = coords[1];

					if(y1==y2) //same col
					{
						//wrap around rows
						if(x1==4) x1 = -1;
						if(x2==4) x2 = -1;

						enc[i] = matrix[x1+1][y1];
						enc[i+1] = matrix[x2+1][y2];
					}
					else if(x1==x2) //same row 
					{
						//wrap around cols
						if(y1==4) y1 = -1;
						if(y2==4) y2 = -1;
						enc[i] = matrix[x1][y1+1];
						enc[i+1] = matrix[x2][y2+1];
					}
					else {
						enc[i] = matrix[x1][y2];
						enc[i+1] = matrix[x2][y1];
					}
				}

				ciphertext = String.valueOf(enc);
				System.out.println("Ciphertext is "+ciphertext);
			}			
			else if(opt==4)
			{
				System.out.println("Decrypting "+ciphertext);
				char[] ptextarray = new char[ciphertext.length()];

				for(int i=0;i<ciphertext.length();i+=2)
				{
					int x1,y1,x2,y2;
					int[] coords;
					coords = search(ciphertext.charAt(i));
					x1 = coords[0]; y1=coords[1];
					coords = search(ciphertext.charAt(i+1));
					x2 = coords[0]; y2 = coords[1];

					if(y1==y2) //same column
					{
						//wrap around rows
						if(x1==0) x1=5;
						if(x2==0) x2=5;
						ptextarray[i] = matrix[x1-1][y1];
						ptextarray[i+1] = matrix[x2-1][y2];
					}
					else if(x1 == x2) //same row
					{
						//wrap around cols
						if(y1==0) y1=5;
						if(y2==0) y2=5;
						ptextarray[i] = matrix[x1][y1-1];
						ptextarray[i+1] = matrix[x2][y2-1];
					}
					else
					{
						ptextarray[i] = matrix[x1][y2];
						ptextarray[i+1] = matrix[x2][y1];
					}
				}
				System.out.println("Plaintext = "+String.valueOf(ptextarray));
			}
			else if(opt==5)
				break;
				
		}
	}
}