package money;

public abstract class MoneyExpression implements Expression {
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    public abstract Expression times(int multiplier);

    public abstract Money reduce(Bank bank, String to);
}