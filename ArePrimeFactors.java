public class ArePrimeFactors {
    public static void main(String[] args) {
	int[] a = {64,2,48};
	System.out.println(arePrimeFactors(512, a));
	
	int[] a2 = {64,2};
	System.out.println(arePrimeFactors(512, a2));
	
	int[] a3 = {7,3};
	System.out.println(arePrimeFactors(21, a3));
    }
    
    public static boolean arePrimeFactors(int n, int[] a) {
	int product = 1;
	for(int i = 0; i < a.length; i++) {
	    if(a[i] == 1) return false;
	    for(int p = 2; p < a[i]; p++) {
		if(a[i] % p == 0) return false;
	    }
	    product *= a[i];
	    if(i == a.length-1 && product != n || a[i] == 1) return false;
	}
	return true;
    }
}
