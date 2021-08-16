package programmingexample4;


public class TestBag {

    static boolean errFlag = false;

    /** Run the main method to run five sets of tests.
     * 
     *  Incremental testing: select only one test method, 
     *  comment out other test methods, and run it.
     * 
     *  Batch testing: you can also run all the test methods 
     *  together, if you wish.
     */

    public static void main(String[] args) {
        try{
            simpleTest();   
            removeTest();
            sizeTest();    
            sumTest();
            simpleEqualityTest();
        }
        catch(Exception e){
            System.err.println(">> Error: Unexpected exception!");
            e.printStackTrace();
        }
    }

    public static void simpleTest() {
        TestBag.errFlag = false;

        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"));
        fruitBag.add(new Orange("Navel"));

        // Check values        
        myCheckValues(1, fruitBag.count(new Apple("Gala")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Apple("Fuji")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Orange("Navel")), getLineNumber() );

        // The same element again
        fruitBag.add(new Apple("Gala"));

        // Check values 
        myCheckValues(2, fruitBag.count(new Apple("Gala")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Apple("Fuji")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Orange("Navel")), getLineNumber() );
        
        myPrintErrMsg("simpleTest");
    }

   
    public static void removeTest() {
        TestBag.errFlag = false;

        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"), 2);
        fruitBag.add(new Orange("Navel"), 3);

        fruitBag.remove(new Orange("Navel"), 2);

        // Check values
        myCheckValues(1, fruitBag.count(new Apple("Gala")), getLineNumber() );
        myCheckValues(2, fruitBag.count(new Apple("Fuji")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Orange("Navel")), getLineNumber() );

        fruitBag.remove(new Apple("Fuji"), 2);

        // Check values        
        myCheckValues(1, fruitBag.count(new Apple("Gala")), getLineNumber() );
        myCheckValues(0, fruitBag.count(new Apple("Fuji")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Orange("Navel")), getLineNumber() );


        fruitBag.remove(new Apple("Gala"), 3);

        // Check values
        myCheckValues(0, fruitBag.count(new Apple("Gala")), getLineNumber() );
        myCheckValues(0, fruitBag.count(new Apple("Fuji")), getLineNumber() );
        myCheckValues(1, fruitBag.count(new Orange("Navel")), getLineNumber() );

        // Check the invariant hasn't been broken
        for (Count<Fruit> c : fruitBag) {
            myCheckValues(true, c.getCount() > 0, getLineNumber() );
        }

        myPrintErrMsg("removeTest");
    }
  
    public static void sizeTest() {  
        TestBag.errFlag = false;

        Bag<Fruit> fruitBag = new ArrayListBag<Fruit>();
        fruitBag.add(new Apple("Gala"));
        fruitBag.add(new Apple("Fuji"), 2);
        fruitBag.add(new Orange("Navel"), 3);

        // Check value 
        myCheckValues(6, fruitBag.size(), getLineNumber() );    
        
        myPrintErrMsg("sizeTest");
    }


  
    public static void sumTest() {        
        TestBag.errFlag = false;

        Bag<Apple> b = new ArrayListBag<Apple>();
        Apple a1 = new Apple("Gala");
        Apple a2 = new Apple("Fuji");
        Apple a3 = new Apple("Granny Smith");
        b.add(a1, 2);
        b.add(a2, 3);
        b.add(a3);

        Bag<Apple> b2 = new ArrayListBag<Apple>();
        b2.add(a2);
        b2.add(a1);

        Bag<Apple> b3 = b.sum(b2);

        // Check values
        myCheckValues(3, b3.count(a1), getLineNumber() );    
        myCheckValues(4, b3.count(a2), getLineNumber() );    
        myCheckValues(1, b3.count(a3), getLineNumber() );    

        int counter = 0;
        for (Count<Apple> c : b3) {
            if (c.getElement().equals(a1)){  
                myCheckValues(3, c.getCount(), getLineNumber() );    
            }
            else if (c.getElement().equals(a2)){
                myCheckValues(4, c.getCount(), getLineNumber() );   
            }
            else if (c.getElement().equals(a3)){                
                myCheckValues(1, c.getCount(), getLineNumber() );                   
            }
            counter++;
        }
      
        myCheckValues(3, counter, getLineNumber() );      
        myPrintErrMsg("sumTest");
    }

    
    public static void simpleEqualityTest() {
        TestBag.errFlag = false;       

        Bag<Apple> b = new ArrayListBag<Apple>();
        Apple a1 = new Apple("Gala");
        Apple a2 = new Apple("Fuji");
        Apple a3 = new Apple("Granny Smith");
        b.add(a1, 2);
        b.add(a2, 3);
        b.add(a3);
        
        myCheckValues(true, b.equals(b), getLineNumber() );

        Bag<Apple> b2 = new ArrayListBag<Apple>();
        b2.add(a3);
        b2.add(a1, 2);
        b2.add(a2, 3);
        
        myCheckValues(true, b.equals(b2), getLineNumber() );        
        myCheckValues(true, b2.equals(b), getLineNumber() );        

        b2.add(a1);
        
        myCheckValues(false,  b.equals(b2),  getLineNumber() );        
        myCheckValues(false, b2.equals(b),   getLineNumber() );   

        Bag<Apple> b3 = new ArrayListBag<Apple>();
        b3.add(a3);
        b3.add(a1, 2);
        b3.add(a2, 3);
        b3.add(new Apple("Honey Crisp"));
        
        myCheckValues(false, b.equals(b3), getLineNumber() );        
        myCheckValues(false, b3.equals(b), getLineNumber() );   

        myPrintErrMsg("simpleEqualityTest");

    }

    /**
     * Utility methods below ... 
     */

    public static void myCheckValues(int expectedValue, int stuValue, String msg){
        if(expectedValue != stuValue){
            TestBag.errFlag = true;
            System.err.print("  >> Error! line: " + msg);
            System.err.print(";  [Expected value: " + expectedValue + "]") ;
            System.err.println(";  [Student's value: " + stuValue + "]");
        }
    }


    public static void myCheckValues(boolean expectedValue, boolean stuValue, String msg){
        if(expectedValue != stuValue){
            TestBag.errFlag = true;
            System.err.print("  >> Error! line: " + msg);
            System.err.print(";  [Expected value: " + expectedValue + "]") ;
            System.err.println(";  [Student's value: " + stuValue + "]");
        }
    }

    public static void myPrintErrMsg(String testName){
        if(TestBag.errFlag == true){
            System.err.printf(">> Failed: %s Failed!\n", testName);
            TestBag.errFlag = false;
        }
        else{
            System.err.printf(">> Passed: %s\n", testName);
        }
    }

    public static String getLineNumber() {
        int lineNo = Thread.currentThread().getStackTrace()[2].getLineNumber();
        return " " + lineNo;
    }
}
