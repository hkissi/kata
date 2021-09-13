import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter @AllArgsConstructor
public class Operation {
    private Type type;
    private BigDecimal amount;
    private LocalDate localDate;
    private BigDecimal balance ;

    enum Type {
        DEPOSIT,
        WITHDRAWAL
    }
}
