import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Account {
    private BigDecimal balance = BigDecimal.ZERO;
    private List<Operation> operations = new ArrayList<>();

    public Account deposit(BigDecimal amount){
        checkIfAmountNotNullAndNotNegative(amount);
        this.balance = this.balance.add(amount);
        this.operations.add(new Operation(Operation.Type.DEPOSIT, amount, LocalDate.now(), this.balance));
        return this ;
    }

    public Account withdrawal(BigDecimal amount)
    {
        checkIfAmountNotNullAndNotNegative(amount);
        if(this.balance.compareTo(amount) >= 0)
        {
            this.balance = this.balance.subtract(amount);
            this.operations.add(new Operation(Operation.Type.WITHDRAWAL, amount, LocalDate.now(),this.balance));
        }
        else
        {
            throw new RuntimeException("Insufficient balance");
        }
        return this;
    }

    private void checkIfAmountNotNullAndNotNegative(BigDecimal amount)
    {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new RuntimeException("Amount error");
    }
}
