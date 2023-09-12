package Lab01;
import java.lang.Math;
public class Recursion {
 
	static int subsum(int n) {
        if (n == 0) {
            return 0;  // The sum for 0 is 0
        } else {
            return n * (int) Math.pow(-1, n+1) + subsum(n-1);
        }
    }
    
    
	static int sumDigit(int n) {
		
        if(n == 0 ){
            return 0;
        }
        return n%10 + sumDigit((int) Math.floor(n/10));
        
        
    }
    
	public static void main(String[] args) {
          

            // Test Uncomment these lines below to test your subsum code
           
                System.out.println("Calculating subsum(10):");
		        System.out.println("Your answer is "+subsum(10));
                System.out.println("The correct answer is -5");
                System.out.println("-----------------------"); 
           

            // Uncomment these lines below to test your sumDigit code
            // /* 
                System.out.println("sumDigit(123456789)");
                System.out.println("Your answer is "+ sumDigit(123456789));
                System.out.println("The correct answer is 45");
            // */
	}
    
}