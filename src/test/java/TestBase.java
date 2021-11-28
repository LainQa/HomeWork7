import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;

public class TestBase {
    @BeforeAll
    static void beforeall(){
        Configuration.browserSize ="1920x1080";
    }

}
