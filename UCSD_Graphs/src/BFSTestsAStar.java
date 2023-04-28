import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runners.Parameterized;

public class BFSTestsAStar extends BFSTestsAbstract{
    public BFSTestsAStar(String in, GeographicPoint start, GeographicPoint end) {
        super(in, start, end);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "map1.txt", new GeographicPoint(0, 0), new GeographicPoint(6, 6) },
                { "map2.txt", new GeographicPoint(7, 3), new GeographicPoint(4, -1) },
                { "map3.txt", new GeographicPoint(0, 0), new GeographicPoint(0, 4) },
                { "ucsd.map", new GeographicPoint(32.8709815, -117.2434254), new GeographicPoint(32.8742087, -117.2381344) },
        });
    }

    @Before
    public void setUp() throws FileNotFoundException {
        super.setUp();
        
        correct = new CorrectAnswer(workingDir + input + ".answer", false);

        studentPath = studentImpl.aStarSearch(start, end);
    }
}
