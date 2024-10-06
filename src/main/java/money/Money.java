package money;

abstract public class Money {
    protected int amount;
    protected String currency;

    abstract Money times(int multiplier);
    abstract String currency();

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && getClass().equals(money.getClass());
    }

    static public Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    static public Money franc(int amount) {
        return new Franc(amount, "CHF");
    }
}
