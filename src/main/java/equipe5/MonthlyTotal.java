package equipe5;

public class MonthlyTotal {
    private Money[] totalArray;

    public MonthlyTotal() throws RefundException {
       totalArray = new Money[10];
        for (int i = 0; i < totalArray.length; i++) {
            totalArray[i] = new Money();
        }
    }

    public void addToMonthlyTotalForCare(int categoryIndex, Money amount) {
        Money total = getMonthlyTotalForCare(categoryIndex);
        total.add(amount);
        totalArray[categoryIndex] = total;
    }

    private Money getMonthlyTotalForCare(int categoryIndex) {
        return new Money(totalArray[categoryIndex]);
    }

    public Money adjustAmount(int categoryIndex, Money amount) throws RefundException {
        Money overflow = getMonthlyTotalForCare(categoryIndex);
        overflow.substract(new Money(Claims.getMonthlyRefundMax(categoryIndex)));

        Money total = amount;

        if (overflow.getTotalMoney() > 0) {
            total.substract(overflow);
        }
        if (total.getTotalMoney() < 0) {
            total.setTotalMoney(0);
        }

        return total;
    }
}
