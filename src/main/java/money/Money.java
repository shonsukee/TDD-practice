package money;

abstract public class Money {
    protected int amount;
    abstract Money times(int multiplier);

    public Money(int amount) {
        this.amount = amount;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && getClass().equals(money.getClass());
    }

    static public Money dollar(int amount) {
        return new Dollar(amount);
    }

    static public Money franc(int amount) {
        return new Franc(amount);
    }
}
