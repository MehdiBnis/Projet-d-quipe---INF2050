package equipe5;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.test.JSONAssert;
import org.junit.jupiter.api.*;
import org.apache.commons.io.IOUtils;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

class ClientReportTest {

    ClientReport report;

    @BeforeEach
    void setUp() throws URISyntaxException {
        report = new ClientReport(new File(getClass().getResource("/json-mock/mock-input1.json").toURI()).toString());
    }

    @AfterEach
    void tearDown() {
        report = null;
    }

    @Test
    public void constructorTest() throws IOException, URISyntaxException {
        String expected1 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output1.json"), StandardCharsets.UTF_8);
        String expected2 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output2.json"), StandardCharsets.UTF_8);
        String expected3 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output3.json"), StandardCharsets.UTF_8);
        String expected4 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output4.json"), StandardCharsets.UTF_8);
        String expected5 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output5.json"), StandardCharsets.UTF_8);
        String expected6 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output6.json"), StandardCharsets.UTF_8);
        String expected7 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output7.json"), StandardCharsets.UTF_8);
        String expected8 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output8.json"), StandardCharsets.UTF_8);
        String expected9 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output9.json"), StandardCharsets.UTF_8);
        String expected10 = IOUtils.toString(this.getClass().getResourceAsStream("/json-mock/mock-output10.json"), StandardCharsets.UTF_8);
        String actual1 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input1.json").toURI()).toString()).toString();
        String actual2 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input2.json").toURI()).toString()).toString();
        String actual3 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input3.json").toURI()).toString()).toString();
        String actual4 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input4.json").toURI()).toString()).toString();
        String actual5 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input5.json").toURI()).toString()).toString();
        String actual6 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input6.json").toURI()).toString()).toString();
        String actual7 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input7.json").toURI()).toString()).toString();
        String actual8 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input8.json").toURI()).toString()).toString();
        String actual9 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input9.json").toURI()).toString()).toString();
        String actual10 = new ClientReport(new File (getClass().getResource("/json-mock/mock-input10.json").toURI()).toString()).toString();

        JSONObject expectedJSON1 = (JSONObject) JSONSerializer.toJSON(expected1);
        JSONObject expectedJSON2 = (JSONObject) JSONSerializer.toJSON(expected2);
        JSONObject expectedJSON3 = (JSONObject) JSONSerializer.toJSON(expected3);
        JSONObject expectedJSON4 = (JSONObject) JSONSerializer.toJSON(expected4);
        JSONObject expectedJSON5 = (JSONObject) JSONSerializer.toJSON(expected5);
        JSONObject expectedJSON6 = (JSONObject) JSONSerializer.toJSON(expected6);
        JSONObject expectedJSON7 = (JSONObject) JSONSerializer.toJSON(expected7);
        JSONObject expectedJSON8 = (JSONObject) JSONSerializer.toJSON(expected8);
        JSONObject expectedJSON9 = (JSONObject) JSONSerializer.toJSON(expected9);
        JSONObject expectedJSON10 = (JSONObject) JSONSerializer.toJSON(expected10);

        JSONObject actualJSON1 = (JSONObject) JSONSerializer.toJSON(actual1);
        JSONObject actualJSON2 = (JSONObject) JSONSerializer.toJSON(actual2);
        JSONObject actualJSON3 = (JSONObject) JSONSerializer.toJSON(actual3);
        JSONObject actualJSON4 = (JSONObject) JSONSerializer.toJSON(actual4);
        JSONObject actualJSON5 = (JSONObject) JSONSerializer.toJSON(actual5);
        JSONObject actualJSON6 = (JSONObject) JSONSerializer.toJSON(actual6);
        JSONObject actualJSON7 = (JSONObject) JSONSerializer.toJSON(actual7);
        JSONObject actualJSON8 = (JSONObject) JSONSerializer.toJSON(actual8);
        JSONObject actualJSON9 = (JSONObject) JSONSerializer.toJSON(actual9);
        JSONObject actualJSON10 = (JSONObject) JSONSerializer.toJSON(actual10);

        assertEquals(expectedJSON1, actualJSON1);
        assertEquals(expectedJSON2, actualJSON2);
        assertEquals(expectedJSON3, actualJSON3);
        assertEquals(expectedJSON4, actualJSON4);
        assertEquals(expectedJSON5, actualJSON5);
        assertEquals(expectedJSON6, actualJSON6);
        assertEquals(expectedJSON7, actualJSON7);
        assertEquals(expectedJSON8, actualJSON8);
        assertEquals(expectedJSON9, actualJSON9);
        assertEquals(expectedJSON10, actualJSON10);
    }

    //Only tests the validity of the JSON in the string because formatting is impossible with the lib
    @Test
    void testToString() throws IOException {
        InputStream file = this.getClass().getResourceAsStream("/json-mock/mock-output1.json");
        String expectedJSONString = IOUtils.toString(file, StandardCharsets.UTF_8);
        String actualJSONString = report.toString();

        JSONObject expected = (JSONObject) JSONSerializer.toJSON(expectedJSONString);
        JSONObject actual = (JSONObject) JSONSerializer.toJSON(actualJSONString);

        JSONAssert.assertEquals(expected, actual);
    }
}