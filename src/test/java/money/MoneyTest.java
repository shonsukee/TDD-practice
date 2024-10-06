package money;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        // Dollar Equality
        assertTrue(Money.dollar(5).equals((Money.dollar(5))));
        assertFalse(Money.dollar(5).equals((Money.dollar(6))));

        // Franc Equality
        assertTrue(Money.franc(5, "CHF").equals(Money.franc(5, "CHF")));
        assertFalse(Money.franc(5, "CHF").equals(Money.franc(6, "CHF")));

        assertFalse(Money.dollar(5).equals(Money.franc(5, "CHF")));
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5, "CHF");
        assertEquals(Money.franc(10, "CHF"), five.times(2));
        assertEquals(Money.franc(15, "CHF"), five.times(3));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(5).currency());
        assertEquals("CHF", Money.franc(5, "CHF").currency());
    }

    @Test
    public void testDifferentClassEquality() {
        assertTrue(new Money(5, "CHF").equals(new Franc(5, "CHF")));
    }
}
