package money;

class Money extends MoneyExpression {
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public Expression times(int multiplier){
        return new Money(amount*multiplier, currency);
    }

    @Override
    public Money reduce(Bank bank, String to){
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    public String currency() {
        return currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Money money = (Money) object;
        return amount == money.amount && currency().equals(money.currency());
    }

    static public Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static public Money franc(int amount) {
        return new Money(amount, "CHF");
    }
}
