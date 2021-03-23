package au.edu.jcu.cp3406.billsplit;

import org.junit.Test;

import static org.junit.Assert.*;

public class BillTests {

    @Test
    public void testStringAndCalculation() {
        Bill bill = new Bill();
        bill.setAmount(100);
        bill.setNumberOfPeople(3);
        bill.calculateTotalPerPerson();
        assertEquals("$33.33", bill.toString());
    }

    @Test
    public void testReset() {
        Bill bill = new Bill();
        bill.setAmount(50);
        bill.setNumberOfPeople(2);
        bill.resetBill();
        assertEquals("$.00", bill.toString());
    }
}