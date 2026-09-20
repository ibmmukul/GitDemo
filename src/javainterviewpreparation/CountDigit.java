package javainterviewpreparation;

public class CountDigit {
	
	/*
	 * // 1) count digit num % 10 Extracts the last digit. //Check if the digit is
	 * 6. If yes, increment count. // num = num / 10 → Removes the last digit.
	 * //Repeat until num becomes 0.
	 */


	
		    public static void main(String[] args) {

		        int num = 34566678;
		        int count = 0;

		        while (num > 0) {
		            int digit = num % 10;

		            if (digit == 6) {
		                count++;
		            }

		            num = num / 10;
		        }

		        System.out.println("Number of times 6 appears: " + count);
		    }
		}


