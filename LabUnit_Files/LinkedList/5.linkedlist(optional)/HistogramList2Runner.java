//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class - 
//Lab  -

import static java.lang.System.*;

public class HistogramList2Runner
{
	public static void main(String[] args)
	{
		HistoList2 test = new HistoList2();
        String testString = "ABCDEFABCDEFFEDCBAAAAABBBBBCCCDAAAAAAAEEFFF";
        for (char c : testString.toCharArray()) {
            test.add(c);
        }
        System.out.println(test);

        Integer[] testInts = {11, 22, 33, 44, 55, 66, 33, 44, 22, 11, 11, 11, 11, 22, 11, 11, 11};
        test = new HistoList2();
        for (int num : testInts) {
            test.add(num);
        }
        System.out.println(test);

        Double[] testDbls = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 3.3, 4.4, 2.2, 1.1, 1.1, 1.1, 1.1, 2.2, 1.1};
        test = new HistoList2();
        for (double num : testDbls) {
            test.add(num);
        }
        System.out.println(test);

        //demonstrate bad data check
        test = new HistoList2();
        test.add("dog");
        test.add(33);
        test.add(3.4);
        System.out.println(test);
	}
}