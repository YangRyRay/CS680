package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrimeGeneratorTest {
    @Test
    public void primeGen1to10() {
        PrimeGenerator gen = new PrimeGenerator(1,10);
        gen.generatePrimes();
        Object[] expected = {2L,3L, 5L,7L};
        assertArrayEquals(expected,gen.getPrimes().toArray());
    }
    @Test
    public void primeGenNeg10to10() {
        try{
            PrimeGenerator gen = new PrimeGenerator(-10,10);
            fail("Negative index Allowed");
        }
        catch(RuntimeException e){
            assertEquals("Wrong input values: from=-10 to=10",e.getMessage());
        }
    }
    @Test
    public void primeGen100to1() {
        try{
            PrimeGenerator gen = new PrimeGenerator(100,1);
            fail("First index larger allowed");
        }
        catch(RuntimeException e){
            assertEquals("Wrong input values: from=100 to=1",e.getMessage());
        }
    }

}
