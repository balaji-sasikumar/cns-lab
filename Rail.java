import java.util.Scanner;
public class Rail {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the depth: ");
		int depth = scanner.nextInt();
		System.out.println("Enter the plaintext");
		scanner.nextLine();
		String plaintext = scanner.nextLine();
		char[][] matrix = new char[depth][plaintext.length()];
		int i=0,j=0;
		for(i=0;i<depth;i++)
			for(j=0;j<plaintext.length();j++)
			{
				matrix[i][j]=' ';
			}
		i=0;j=0;
		for(int k=0;k<plaintext.length();k++) {
			matrix[i][j] = plaintext.charAt(k);
			i++;
			j++;
			if(i==depth) i = 0;
		}
		System.out.println("The generated matrix for encryption is:");
		for(i=0;i<depth;i++) {
			for(j=0;j<matrix[0].length;j++){
					System.out.print(matrix[i][j]);
				}
			System.out.println();
		}
		String ciphertext="";
		for(i=0;i<depth;i++) {
			for(j=0;j<matrix[0].length;j++)
				if(matrix[i][j]!=' ')
					ciphertext+=(matrix[i][j]);
		}
		System.out.println("Ciphertext: "+ciphertext);		
		int cols = ciphertext.length() / depth + ((ciphertext.length() % depth == 0) ? 0 : 1); 
		matrix = new char[depth][cols];
		int k=0;
		for(i=0;i<depth;i++)
			for(j=0;j<cols&&k<ciphertext.length();j++)
				matrix[i][j]=ciphertext.charAt(k++);
		System.out.println("The generated matrix for decryption is: ");
		for(i=0;i<depth;i++) {
			for(j=0;j<cols;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
		
		plaintext="";
		for(j=0;j<cols;j++)
			for(i=0;i<depth;i++)
				plaintext+=matrix[i][j];
		System.out.println("Plaintext: "+plaintext);
	}
}