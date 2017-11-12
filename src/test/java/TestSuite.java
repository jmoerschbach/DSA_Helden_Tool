
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import skills.LanguageTest;
import aventurian.AventurianTest;
import aventurian.LevelCostCalculatorTest;
import aventurian.PrimaryAttributesTest;
import aventurian.SecondaryAttributeHelperTest;
import aventurian.SecondaryAttributesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PrimaryAttributesTest.class,
		SecondaryAttributeHelperTest.class, SecondaryAttributesTest.class,
		AventurianTest.class, LevelCostCalculatorTest.class, LanguageTest.class })
public class TestSuite {

}
