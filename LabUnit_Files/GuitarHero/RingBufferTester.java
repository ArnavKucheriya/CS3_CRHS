public class RingBufferTester {

    public static void main(String[] args) {
        // test RingBuffer!!
        RingBuffer rb = new RingBuffer(4);
        // test 1
        System.out.print(rb.size() + " expect 0: ");
        if(rb.size() == 0)
            System.out.println("PASSED TEST 1");
        else
            System.out.println("FAILED TEST 1");
        
        // test 2
        System.out.print(rb.isEmpty() + " expect true: ");
        if(rb.isEmpty())
            System.out.println("PASSED TEST 2");
        else
            System.out.println("FAILED TEST 2"); 
        
        // test 3
        System.out.print(rb.isFull() + " expect false: ");
        if(!rb.isFull())
            System.out.println("PASSED TEST 3");
        else
            System.out.println("FAILED TEST 3");  
        
        // test 4
        rb.enqueue(0.0);
        rb.enqueue(1.0);
        System.out.print(rb + " expect [0.0, 1.0]: ");
        if(rb.toString().equals("[0.0, 1.0]"))
            System.out.println("PASSED TEST 4");
        else
            System.out.println("FAILED TEST 4");
        
        
        // test 5
        System.out.print(rb.isEmpty() + " expect false: ");
        if(!rb.isEmpty())
            System.out.println("PASSED TEST 5");
        else
            System.out.println("FAILED TEST 5"); 
        
        // test 6
        System.out.print(rb.size() + " expect 2: ");
        if(rb.size() == 2)
            System.out.println("PASSED TEST 6");
        else
            System.out.println("FAILED TEST 6");
        
        // test 7
        rb.dequeue();
        rb.dequeue();
        System.out.print(rb + " expect []: ");
        if(rb.toString().equals("[]"))
            System.out.println("PASSED TEST 7");
        else
            System.out.println("FAILED TEST 7"); 
        
        // test 8
        System.out.print(rb.size() + " expect 0: ");
        if(rb.size() == 0)
            System.out.println("PASSED TEST 8");
        else
            System.out.println("FAILED TEST 8");
        
        // test 9
        System.out.print(rb.isEmpty() + " expect true: ");
        if(rb.isEmpty())
            System.out.println("PASSED TEST 9");
        else
            System.out.println("FAILED TEST 9"); 
        
        // test 10
        System.out.print(rb.isFull() + " expect false: ");
        if(!rb.isFull())
            System.out.println("PASSED TEST 10");
        else
            System.out.println("FAILED TEST 10");
        
        double[] data = {0.0, 1.0, 2.0, 3.0};
        for(double d : data)
            rb.enqueue(d);
        
        // test 11
        System.out.print(rb + " expect [0.0, 1.0, 2.0, 3.0]: ");
        if(rb.toString().equals("[0.0, 1.0, 2.0, 3.0]"))
            System.out.println("PASSED TEST 11");
        else
            System.out.println("FAILED TEST 11"); 
        
        // test 12
        System.out.print(rb.size() + " expect 4: ");
        if(rb.size() == 4)
            System.out.println("PASSED TEST 12");
        else
            System.out.println("FAILED TEST 12");
        
        // test 13
        System.out.print(rb.isEmpty() + " expect false: ");
        if(!rb.isEmpty())
            System.out.println("PASSED TEST 13");
        else
            System.out.println("FAILED TEST 13"); 
        
        // test 14
        System.out.print(rb.isFull() + " expect true: ");
        if(rb.isFull())
            System.out.println("PASSED TEST 14");
        else
            System.out.println("FAILED TEST 14");    
        
        rb.dequeue();
        rb.dequeue();
        rb.enqueue(1.0);
        rb.enqueue(0.0);
        
        // test 15
        System.out.print(rb + " expect [2.0, 3.0, 1.0, 0.0]: ");
        if(rb.toString().equals("[2.0, 3.0, 1.0, 0.0]"))
            System.out.println("PASSED TEST 15");
        else
            System.out.println("FAILED TEST 15"); 
        
        // test 16
        System.out.print(rb.size() + " expect 4: ");
        if(rb.size() == 4)
            System.out.println("PASSED TEST 16");
        else
            System.out.println("FAILED TEST 16");
        
        // test 17
        System.out.print(rb.isEmpty() + " expect false: ");
        if(!rb.isEmpty())
            System.out.println("PASSED TEST 17");
        else
            System.out.println("FAILED TEST 17"); 
        
        // test 18
        System.out.print(rb.isFull() + " expect true: ");
        if(rb.isFull())
            System.out.println("PASSED TEST 18");
        else
            System.out.println("FAILED TEST 18");
        
        while(!rb.isEmpty())
            rb.dequeue();
        
        for(int i = 0; i < data.length; i++)
            rb.enqueue(data[data.length - i - 1]);
        
        // test 19
        System.out.print(rb + " expect [3.0, 2.0, 1.0, 0.0]: ");
        if(rb.toString().equals("[3.0, 2.0, 1.0, 0.0]"))
            System.out.println("PASSED TEST 19");
        else
            System.out.println("FAILED TEST 19"); 
        
        rb.dequeue();
        // test 20
        System.out.print(rb + " expect [2.0, 1.0, 0.0]: ");
        if(rb.toString().equals("[2.0, 1.0, 0.0]"))
            System.out.println("PASSED TEST 20");
        else
            System.out.println("FAILED TEST 20");
        
    }
}
