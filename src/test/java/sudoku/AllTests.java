package sudoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellValidatorTests.class,
        SolutionCounterTests.class,
        ValidateGuessTests.class,
        BoardValidatorTests.class,
        RemovalTests.class
})


public class AllTests {

}