package javainterviewpreparation;

class AppendNumber {
	
/*	Explanation

	Multiply the number by 100 to make space for two digits (25).

	Then add 25.

	14916 * 100 = 1491600

	1491600 + 25 = 1491625*/


    public static void main(String[] args) {

        int num = 14916;
        int result = num * 100 + 25;

        System.out.println(result);
    }
}


