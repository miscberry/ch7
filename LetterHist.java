import java.util.Scanner;
import java.util.Arrays;

public class LetterHist {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.print("str: ");
	String str = in.nextLine();
	
	System.out.print("result: " + Arrays.toString(letterHist(str)));
	}
    
    public static int[] letterHist(String str) {
	str = str.toLowerCase();
	int[] histogram = new int[26];
	for(int i = 0; i < str.length(); i++) {
	    for(char c = 'a'; c <= 'z'; c++) {
		if(str.charAt(i) == c) histogram[c - 'a']++;
	    }
	}
	return histogram;
    }
}
