package money;

public abstract class MoneyExpression implements Expression {
    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public abstract Expression times(int multiplier);

    @Override
    public abstract Money reduce(Bank bank, String to);
}