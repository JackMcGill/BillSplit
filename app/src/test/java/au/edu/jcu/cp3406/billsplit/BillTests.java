package au.edu.jcu.cp3406.billsplit;

import org.junit.Test;

import static org.junit.Assert.*;

public class BillTests {

    @Test
    public void testStringAndCalculation() {
        Bill billWithNoTip = new Bill();
        int tip = 0;
        billWithNoTip.setAmount(100);
        billWithNoTip.setNumberOfPeople(3);
        billWithNoTip.calculateTotalPerPerson(tip);
        assertEquals("$33.33", billWithNoTip.toString());

        Bill billWithTip = new Bill();
        tip = 20;
        billWithTip.setAmount(100);
        billWithTip.setNumberOfPeople(2);
        billWithTip.calculateTotalPerPerson(tip);
        assertEquals("$60.00", billWithTip.toString());

    }

    @Test
    public void testReset() {
        Bill bill = new Bill();
        bill.setAmount(50);
        bill.setNumberOfPeople(2);
        bill.resetBill();
        assertEquals("$0.00", bill.toString());
    }
}