import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import aventurian.AventurianManagerTest;
import aventurian.AventurianTest;
import aventurian.LevelCostCalculatorTest;
import aventurian.PrimaryAttributesTest;
import aventurian.SecondaryAttributeHelperTest;
import aventurian.SecondaryAttributesTest;
import skills.BadPropertyTest;
import skills.LanguageTest;
import skills.PropertyTest;
import ui.AttributePaneTest;
import ui.LanguagePaneTest;
import ui.NavigatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PrimaryAttributesTest.class, SecondaryAttributeHelperTest.class, SecondaryAttributesTest.class,
		AventurianTest.class, LevelCostCalculatorTest.class, LanguageTest.class, BadPropertyTest.class,
		PropertyTest.class, AventurianManagerTest.class, AttributePaneTest.class, NavigatorTest.class,
		LanguagePaneTest.class })
public class TestSuite {

}
