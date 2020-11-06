import java.util.*;

public class Row {
    public static void printMenu(){
        System.out.println("\n");
        System.out.println("Menu:");
        System.out.println("1. Decode");
        System.out.println("2. Encode");
        System.out.println("3. Exit");
        System.out.println("\n");
        System.out.println("Enter your choice:");
      }
      
      public static String  encrypt(String s,String k){
        int keylen = k.length();
        int extra = keylen-(s.length()%keylen);
        while(extra-->0){
            s+="X";
        }
        int strlen = s.length();
        int r = strlen/keylen, c = keylen;
        char matrix[][] = new char[r][c];
        String res = new String();
        for(int i=0;i<strlen;i++){
            matrix[i/c][i%c] = s.charAt(i);
        }
        for(int j=0;j<c;j++){
            int col = (int)k.charAt(j)-(int)'0'-1;
            for(int i=0;i<r;i++){
                res+=matrix[i][col];
            }
        }
        System.out.println("The matrix is: ");
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
        return res;
      }

      public static String  decrypt(String s,String k){
        int keylen = k.length();
        int strlen = s.length();
        System.out.println(keylen);
        System.out.println(strlen);
        
        int r = strlen/keylen, c = keylen;
        char matrix[][] = new char[r][c];
        String res = new String();
        int p = 0;
        for(int j=0;j<c;j++){
            int col = (int)k.charAt(j)-(int)'0'-1;
            for(int i=0;i<r;i++){
                matrix[i][col] = s.charAt(p);
                p++;
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                res+=matrix[i][j];
            }
        }        
        System.out.println("The matrix is: ");
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
        return res;
      }
      public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res = new String();
        String key = "";
        String str = new String();
        while(true){
          printMenu();
          int option = in.nextInt();
          if(option==1){
            System.out.println("Enter the Ciphertext: ");
            str = in.next();
            System.out.println("Enter the key: ");
            key = in.next();
            res = decrypt(str.toUpperCase(),key);
            System.out.println("Plaintext: "+res);
           }
          else if(option==2){
            System.out.println("Enter the Plaintext: ");
            str = in.next();
            System.out.println("Enter the key: ");
            key = in.next();
            res = encrypt(str.toUpperCase(),key);
            System.out.println("Ciphertext: "+res); 
          }
          else if(option==3){
            break;
          }
          else{
            System.out.println("Error. Wrong option");
          }  
        }
        in.close();
      }
    
}