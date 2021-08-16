package programmingexample1;

import java.util.Date;

public class Booking {
    private int id;
    private String customer;
    private Date startingDate;
    private Date checkoutDate;

    public Booking(int id, String customer, Date startingDate, Date checkoutDate) {
        this.id = id;
        this.customer = customer;
        this.startingDate = startingDate;
        this.checkoutDate = checkoutDate;
    }

    
}
