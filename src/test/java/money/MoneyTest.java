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

        assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(5).currency());
        assertEquals("CHF", Money.franc(5).currency());
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(5));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(8), result);
    }

    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    public void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        // (fiveBucks + tenFrancs) + fiveBucks == 15
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);

        // tenFrancs + ((fiveBucks + tenFrancs) + fiveBucks) + fiveBucks == 25
        sum = new Sum(tenFrancs, sum).plus(fiveBucks);
        result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(25), result);
    }

    @Test
    public void testPairEquality() {
        Pair pair = new Pair("CHF", "USD");
        Pair pair1 = new Pair("CHF", "USD");
        Pair pair2 = new Pair("CHF", "JPY");
        assertTrue(pair.equals(pair1));
        assertFalse(pair.equals(pair2));
    }

    @Test
    public void testPairHashCode() {
        Pair pair = new Pair("CHF", "USD");
        Pair pair1 = new Pair("CHF", "USD");
        System.out.println(pair.hashCode());
        System.out.println(pair1.hashCode());
        assertEquals(pair.hashCode(), pair1.hashCode());
    }
}
