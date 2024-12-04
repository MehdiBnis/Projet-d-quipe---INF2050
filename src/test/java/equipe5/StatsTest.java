package equipe5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class StatsTest {

    Stats one;

    @BeforeEach
    void setUp() throws IOException {
        one = new Stats("src/test/resources/stats-mock/stats-input1");
    }

    @AfterEach
    void tearDown() {
        one = null;
    }

    @Test
    public void constructorTest() throws IOException {

        int[] expectedNbReclam1 = {0, 0};
        int[] expectedNbSoins1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        assertEquals(Arrays.toString(expectedNbReclam1), Arrays.toString(Stats.nbReclam));
        assertEquals(Arrays.toString(expectedNbSoins1), Arrays.toString(Stats.nbSoins));

        Stats two = new Stats("src/test/resources/stats-mock/stats-input2");
        int[] expectedNbReclam2 = {2, 9};
        int[] expectedNbSoins2 = {3, 4, 5, 6, 0, 5, 6, 12, 3, 1};

        assertEquals(Arrays.toString(expectedNbReclam2), Arrays.toString(Stats.nbReclam));
        assertEquals(Arrays.toString(expectedNbSoins2), Arrays.toString(Stats.nbSoins));

        assertDoesNotThrow(() -> {
            Stats temp = new Stats("src/test/resources/stats-mock/stats-input3");
            temp.getStatsFromFile("src/test/resources/stats-mock/stats-input3");
        });
    }

    @Test
    void reinitialiserStats() throws IOException {

        one.reinitialiserStats();
        Stats two = new Stats("src/test/resources/stats-mock/stats-input2");
        two.reinitialiserStats();

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stats-mock/stats-output1")));
        expected = expected.replace("\r","");

        assertEquals(expected, one.toString());
        assertEquals(expected, two.toString());

    }

    @Test
    void getStatsFromFile() throws IOException {

        int[] actualArray1 = one.getStatsFromFile("src/test/resources/stats-mock/stats-input2");
        int[] expected = {2, 9, 3, 4, 5, 6, 0, 5, 6, 12, 3, 1};

        Stats temp = new Stats("src/test/resources/stats-mock/stats-input3");

        assertEquals(Arrays.toString(expected), Arrays.toString(actualArray1));
    }

    @Test
    void testToString() throws IOException {
        String actual = one.toString();
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stats-mock/stats-output1")));
        expected = expected.replace("\r","");
        assertEquals(expected,actual);
    }
}
