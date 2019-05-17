import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddPieceTest.class, UpdateGameTest.class, ValidAndEmptyTest.class })
public class AllTests {

}
