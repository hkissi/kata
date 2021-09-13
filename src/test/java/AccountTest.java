import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Person person;
    private Account account;

    @BeforeEach
    public void init()
    {
        person = new Person();
        person.setName("testName");
        person.setAccount(new Account());
    }


    @Test
    void depositException() {
        account = person.getAccount();
        BigDecimal amountNull = null;
        BigDecimal amountZero= BigDecimal.ZERO ;
        BigDecimal amountNegative= new BigDecimal(-1) ;

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> account.deposit(amountNull) ,
                "Amount error"
        );

        RuntimeException thrown2 = assertThrows(
                RuntimeException.class,
                () -> account.deposit(amountZero),
                "Amount error"
        );

        RuntimeException thrown3 = assertThrows(
                RuntimeException.class,
                () -> account.deposit(amountNegative) ,
                "Amount error"
        );

        Assertions.assertEquals("Amount error",thrown.getMessage());
        Assertions.assertEquals("Amount error",thrown2.getMessage());
        Assertions.assertEquals("Amount error",thrown3.getMessage());
    }

    @Test
    void deposit() {
        account = person.getAccount();
        BigDecimal amount = new BigDecimal(500) ;
        account.deposit(amount) ;
        Assertions.assertEquals(account.getBalance(), amount);
        Assertions.assertEquals(account.getOperations().size(), 1);
        Assertions.assertEquals(account.getOperations().get(0).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(0).getType(), Operation.Type.DEPOSIT);
        Assertions.assertEquals(account.getOperations().get(0).getBalance(), amount);
    }


    @Test
    void withdrawalException() {
        account = person.getAccount();
        BigDecimal amountNull = null;
        BigDecimal amountZero= BigDecimal.ZERO ;
        BigDecimal amountNegative= new BigDecimal(-1) ;

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> account.withdrawal(amountNull) ,
                "Amount error"
        );

        RuntimeException thrown2 = assertThrows(
                RuntimeException.class,
                () -> account.withdrawal(amountZero),
                "Amount error"
        );

        RuntimeException thrown3 = assertThrows(
                RuntimeException.class,
                () -> account.withdrawal(amountNegative) ,
                "Amount error"
        );

        Assertions.assertEquals("Amount error",thrown.getMessage());
        Assertions.assertEquals("Amount error",thrown2.getMessage());
        Assertions.assertEquals("Amount error",thrown3.getMessage());
    }

    @Test
    void withdrawal() {
        account = person.getAccount();
        account.setBalance(new BigDecimal(500));
        BigDecimal amount = new BigDecimal(200) ;
        BigDecimal expectedResult = new BigDecimal(300);
        account.withdrawal(amount) ;
        Assertions.assertEquals(account.getBalance(), expectedResult);
        Assertions.assertEquals(account.getOperations().size(), 1);
        Assertions.assertEquals(account.getOperations().get(0).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(0).getType(), Operation.Type.WITHDRAWAL);
        Assertions.assertEquals(account.getOperations().get(0).getBalance(), expectedResult);
    }

    @Test
    void withdrawalWithInsufficientBalance() {
        account = person.getAccount();
        BigDecimal amount= new BigDecimal(200) ;
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> account.withdrawal(amount) ,
                "Insufficient balance"
        );
        Assertions.assertEquals("Insufficient balance",thrown.getMessage());
    }

    @Test
    void showOperations() {
        account = person.getAccount();
        BigDecimal amount = new BigDecimal(200) ;
        account.deposit(amount);
        account.deposit(amount);
        account.withdrawal(amount);
        account.withdrawal(amount);

        Assertions.assertEquals(account.getOperations().size(), 4);
        Assertions.assertEquals(account.getOperations().get(0).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(0).getType(), Operation.Type.DEPOSIT);
        Assertions.assertEquals(account.getOperations().get(0).getBalance(), amount);

        Assertions.assertEquals(account.getOperations().get(1).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(1).getType(), Operation.Type.DEPOSIT);
        Assertions.assertEquals(account.getOperations().get(1).getBalance(), new BigDecimal(400));

        Assertions.assertEquals(account.getOperations().get(2).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(2).getType(), Operation.Type.WITHDRAWAL);
        Assertions.assertEquals(account.getOperations().get(2).getBalance(), amount);

        Assertions.assertEquals(account.getOperations().get(3).getAmount(), amount);
        Assertions.assertEquals(account.getOperations().get(3).getType(), Operation.Type.WITHDRAWAL);
        Assertions.assertEquals(account.getOperations().get(3).getBalance(), BigDecimal.ZERO);

    }




}