package au.edu.jcu.cp3406.billsplit;

import org.junit.Test;

import static org.junit.Assert.*;

public class BillTests {

    @Test
    public void testStringAndCalculation() {
        Bill bill = new Bill(100, 3);
        bill.calculateTotalPerPerson();
        assertEquals("$33.33", bill.toString());
    }
}