package sudoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellValidatorTests.class,
        RemovalTests.class,
        SolutionCounterTests.class
})


public class AllTests {

}