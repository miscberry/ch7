import java.util.Scanner;
import java.util.Arrays;

public class Sieve {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.print("int: ");
	int n = in.nextInt();
	
	System.out.print("result: " + Arrays.toString(sieve(n)));
    }
    
    public static boolean[] sieve(int n) {
	boolean[] result = new boolean[n];
	for(int i = 0; i < n; i++) {
	    result[i] = (i >= 2);
	    for(int p = 2; p < i; p++) {
		if(i % p == 0) result[i] = false;
	    }
	}
	return result;
    }
}
