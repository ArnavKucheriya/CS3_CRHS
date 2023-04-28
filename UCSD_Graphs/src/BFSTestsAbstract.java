import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class BFSTestsAbstract {
    // workingDir is the directory where the input files are located note that the path is relative to the package
    // so if you are running the tests from the package, the path will be correct
    final String workingDir = Graph.class.getResource("").getPath()  + ".." + File.separator;

    final String input;

    MapGraph studentImpl;
    List<GeographicPoint> studentPath;

    CorrectAnswer correct;
    GeographicPoint start;
    GeographicPoint end;

    public BFSTestsAbstract(String in, GeographicPoint start, GeographicPoint end) {
        this.input = in;
        this.start = start;
        this.end = end;
    }

    @Before
    public void setUp() throws FileNotFoundException {
        studentImpl = new MapGraph();

        GraphLoader.loadRoadMap(workingDir + input, studentImpl);
    }

    @Test
    public void testPath() {
        assertEquals(correct.path, studentPath);
    }
}
