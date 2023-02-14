/**
 * 
 * @author Steve Tanimoto
 *
 * The method IsPrime.isPrime(n) tests whether n is a prime number.
 * It handles several common cases quickly, and then uses a variation
 * of the Sieve of Eratosthenes.
 */

public class IsPrime {
	
	public static boolean isPrime(long n) {
		if (n < 2) return false;
		if(n==2 || n==3) return true;
		if (n%2==0 || n%3==0) return false;
		long sqrtN = (long) Math.sqrt(n)+1;
		for(long i = 6L; i <= sqrtN; i += 6) {
			if(n%(i-1) == 0 || n%(i+1) == 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		for(int i=0; i<200; i++) {
			if (isPrime(i)) System.out.print(i + "  ");
		}
	}
}
