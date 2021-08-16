package programmingexample6;

public class TestProduct {

    static boolean errFlag = false;

    /** Run the main method to run three sets of tests.
     * 
     *  Incremental testing: select only one test method, 
     *  comment out other test methods, and run it.
     * 
     *  Batch testing: you can also run all the test methods 
     *  together, if you wish.
     */

    public static void main(String[] args) {
        try{
            TestDiscount();   
            TestFreeShipping();
            TestCombinedDecorator();    
        }
        catch(Exception e){
            System.err.println(">> Error: Unexpected exception!");
            e.printStackTrace();
        }
    }

    
    public static void TestDiscount() {
        // Shoes cost $90 and weigh 100 grams
        Product p1 = new Shoes();

        myCheckValues(90, p1.getPrice(), getLineNumber() );    

        // Give a discount of 20%
        p1 = new DiscountDecorator(p1, 20);

        myCheckValues(72, p1.getPrice(), getLineNumber() );    

        // Give a further discount of 25%
        p1 = new DiscountDecorator(p1, 25);
        myCheckValues(54, p1.getPrice(), getLineNumber() );    

        myPrintErrMsg("TestDiscount");
    }

    
    public static void TestFreeShipping() {
        // A lamp costs $50 and weighs 900 grams
        Product p1 = new Lamp();

        // Give free shipping for the lamp product if it costs more than $45 and
        // weighs less than 1kg
        Product p2 = new FreeShippingDecorator(p1, 45, 1000);

        // The lamp is eligible for the discount.
        myCheckValues(0, p2.getShippingCost(), getLineNumber() );    

        // ... and its weight shouldn't change
        myCheckValues(900, p2.getWeight(), getLineNumber() );    

        // Give free shipping for the lamp product if it costs more than $60 and
        // weighs less than 1kg
        Product p3 = new FreeShippingDecorator(p1, 60, 1000);

        // Now the lamp is no longer eligible
        myCheckValues(2, p3.getShippingCost(), getLineNumber() );    

        // Give free shipping for the lamp product if it costs more than $45 and
        // weighs less than 500g
        Product p4 = new FreeShippingDecorator(p1, 45, 500);

        // Now the lamp is no longer eligible
        myCheckValues(2, p4.getShippingCost(), getLineNumber() );    

        myPrintErrMsg("TestFreeShipping");

    }

    
    public static void TestCombinedDecorator() {
        Product p1 = new Lamp();
        // After a 5% discount a lamp is still eligible for free shipping
        Product p2 = new FreeShippingDecorator(new DiscountDecorator(p1, 5), 45, 1000);

        myCheckValues(0, p2.getShippingCost(), getLineNumber() );    

        // But after a 12% discount has been applied, it is no longer eligible
        // for free shipping.
        Product p3 = new FreeShippingDecorator(new DiscountDecorator(p1, 12), 45, 1000);

        myCheckValues(2, p3.getShippingCost(), getLineNumber() );    

        // If the discount is applied after the free shipping, however, it is
        // still eligible
        Product p4 = new DiscountDecorator(new FreeShippingDecorator(p1, 45, 1000), 10);

        myCheckValues(0, p4.getShippingCost(), getLineNumber() );    

        myPrintErrMsg("TestCombinedDecorator");
    }


   /**
     * Utility methods below ... 
     */

    public static void myCheckValues(double expectedValue, double stuValue, String msg){
        if(Math.abs(expectedValue - stuValue) > 0.01 ){
            TestProduct.errFlag = true;
            System.err.print("  >> Error! line: " + msg);
            System.err.print(";  [Expected value: " + expectedValue + "]") ;
            System.err.println(";  [Student's value: " + stuValue + "]");
        }
    }


    public static void myCheckValues(boolean expectedValue, boolean stuValue, String msg){
        if(expectedValue != stuValue){
            TestProduct.errFlag = true;
            System.err.print("  >> Error! line: " + msg);
            System.err.print(";  [Expected value: " + expectedValue + "]") ;
            System.err.println(";  [Student's value: " + stuValue + "]");
        }
    }

    public static void myPrintErrMsg(String testName){
        if(TestProduct.errFlag == true){
            System.err.printf(">> Failed: %s Failed!\n", testName);
            TestProduct.errFlag = false;
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
