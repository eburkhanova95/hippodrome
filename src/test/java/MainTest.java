import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 21)
    public void timout() throws Exception{
        Main.main(null);
    }

}
