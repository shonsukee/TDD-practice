package money;

public class Money {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    Money times(int multiplier){
        return new Money(amount*multiplier, currency);
    }

    String currency() {
        return this.currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && currency().equals(money.currency());
    }

    static public Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static public Money franc(int amount, String currency) {
        return new Money(amount, currency);
    }
}
