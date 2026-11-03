public class AreFactors {
    public static void main(String[] args) {
	int[] a = {64,2,48};
	System.out.println(areFactors(512, a));
	
	int[] a2 = {64,2};
	System.out.println(areFactors(512, a2));
    }
    
    public static boolean areFactors(int n, int[] a) {
	for(int i = 0; i < a.length; i++) {
	    if(n % a[i] != 0) return false;
	}
	return true;
    }
}
