import java.util.Scanner;
import java.util.Arrays;

public class Anagrams {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.print("str: ");
	String str1 = in.nextLine();
	
	System.out.print("str: ");
	String str2 = in.nextLine();
	
	System.out.print("result: " + anagram(str1, str2));
	}
    
    public static int[] histogram(String str) {
	str = str.toLowerCase();
	int[] histogram = new int[26];
	for(int i = 0; i < str.length(); i++) {
	    for(char c = 'a'; c <= 'z'; c++) {
		if(str.charAt(i) == c) histogram[c - 'a']++;
	    }
	}
	return histogram;
    }
    
    public static boolean anagram(String str1, String str2) {
	int[] hist1 = histogram(str1);
	int[] hist2 = histogram(str2);
	for(int i = 0; i < 26; i++) {
	    if(hist1[i] != hist2[i]) return false;
	}
	return true;
    }
}
