import com.example.bank.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    public void pinSetTest() {
        Account account = new Account("Mu'men", Account.TYPE_SAVINGS);
        account.setPin("1224");
        String pin = account.getPin();

        assertEquals("1224", pin);
    }
}
