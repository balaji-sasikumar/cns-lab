import java.util.*;

public class Caesar{
    private static String offset(String txt,int key){
        // StringBuilder cipher =new StringBuilder();
        String s = "";
        for(int i=0;i<txt.length();i++){ 
            char ch =txt.charAt(i);
            if(Character.isUpperCase(ch)){
                int s=ch-'A';///ascii conversion->int 66-65=1
                s=((s+key)%26+26)%26; // int
                cipher.append((char)(s+'A'));
            }
            else if(Character.isLowerCase(ch)){
                int s=ch-'a';
                s=((s+key)%26+26)%26;
                cipher.append((char)(s+'a'));

            }
            else{
                cipher.append(ch);
            }
        }
        return cipher.toString();

    }

    private static String encrypt(String cipher,int key){
        return offset(cipher,key);
    }

    private static String decrypt(String cipher,int key){
        return offset(cipher,-key);
    }


    public static void main(String[] args){
        String txt="Balaji";
        int key=3;
        String enc=encrypt(txt,key);
        System.out.println(enc);
        String dec =decrypt(enc,key);
        System.out.println(dec);
    }

}