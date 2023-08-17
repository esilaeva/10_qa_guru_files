package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.StudentModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class JacksonJsonParsingTests {

    private final ClassLoader cl = JacksonJsonParsingTests.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void jacksonJsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("student.json")) {
            StudentModel student = mapper.readValue(stream, StudentModel.class);

            Assertions.assertEquals("01", student.getId());
            Assertions.assertEquals("Tom", student.getFirstName());
            Assertions.assertEquals("Tilman", student.getLastName());
            Assertions.assertEquals("english", student.getSubject().getNameSubject());
            Assertions.assertEquals("90", student.getSubject().getExamPoint());
        }
    }
}
