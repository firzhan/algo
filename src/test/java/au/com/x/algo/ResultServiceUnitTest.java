package au.com.x.algo;

import au.com.x.algo.exception.InvalidInputException;
import au.com.x.algo.service.Result;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)

class ResultServiceUnitTest {

    private static Logger logger = (Logger) LoggerFactory.getLogger(ResultServiceUnitTest.class.getPackage().getName());
    private static Result resultObj;
    private static MemoryAppender memoryAppender;
    private static StringBuilder stringBuilder15;
    private static StringBuilder stringBuilder18;


    @BeforeAll
    public static void init() {
        resultObj = new Result();
        buildStringBuilderFor15();
        buildStringBuilderFor18();
    }

    @BeforeEach
    public void setup() {
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @AfterEach
    public void cleanUp() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    void fizz_buzz_prints_invalid_input_number() {

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            resultObj.processRequest((Stream.of(1, 21).collect(Collectors.toList())));
        });

        String expectedMessage = "Number range is not allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(InvalidInputException.class, () -> {
            resultObj.processRequest((Stream.of(1, 0).collect(Collectors.toList())));
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void fizz_buzz_prints_valid_input_number_15() throws Exception {
        resultObj.processRequest(Stream.of(1, 15).collect(Collectors.toList()));
        List<ILoggingEvent> loggingEventList = memoryAppender.search(stringBuilder15.toString());
        assertEquals(loggingEventList.get(0).getMessage(), stringBuilder15.toString());
    }

    @Test
    void fizz_buzz_prints_valid_input_number_18() throws Exception {
        resultObj.processRequest(Stream.of(1, 18).collect(Collectors.toList()));
        List<ILoggingEvent> loggingEventList = memoryAppender.search(stringBuilder18.toString());
        assertEquals(loggingEventList.get(0).getMessage(), stringBuilder18.toString());
    }

    @Test
    void min_x_valid_input() throws Exception {
        int minValue = resultObj.processRequest(Stream.of(2,-5,4,-2,3,1,-1,-6,-1,0,5).collect(Collectors.toList()));
        assertEquals( minValue, 8);

        minValue = resultObj.processRequest(Stream.of(2,-5, 4, -2, 3, 1).collect(Collectors.toList()));
        assertEquals( minValue, 6);

        minValue = resultObj.processRequest(Stream.of(2,-5, 4, -2, 3, 1, -1, -6, -1, 0, -5).collect(Collectors.toList()));
        assertEquals( minValue, 13);

    }

    @Test
    void min_x_invalid_input() throws Exception {

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            resultObj.processRequest((Stream.of(2,-105,4,-2,3,1,-1,-6,-1,0,5).collect(Collectors.toList())));
        });

        String expectedMessage = "Input array consists of invalid values";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(InvalidInputException.class, () -> {
            resultObj.processRequest((Stream.of(2,105,4,-2,3,1,-1,-6,-1,0,5).collect(Collectors.toList())));
        });

        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(InvalidInputException.class, () -> {
            resultObj.processRequest((Stream.of(2,15,4,-2,3,1,-1,-6,-1,0,5,11).collect(Collectors.toList())));
        });

        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static void buildStringBuilderFor15() {
        stringBuilder15 = new StringBuilder();
        stringBuilder15.append(1).append("\n").
                append(2).append("\n").
                append("Fizz").append("\n").
                append(4).append("\n").
                append("Buzz").append("\n").
                append("Fizz").append("\n").
                append(7).append("\n").
                append(8).append("\n").
                append("Fizz").append("\n").
                append("Buzz").append("\n").
                append(11).append("\n").
                append("Fizz").append("\n").
                append(13).append("\n").
                append(14).append("\n").
                append("FizzBuzz");
    }

    private static void buildStringBuilderFor18() {
        stringBuilder18 = new StringBuilder();
        stringBuilder18.append(1).append("\n").
                append(2).append("\n").
                append("Fizz").append("\n").
                append(4).append("\n").
                append("Buzz").append("\n").
                append("Fizz").append("\n").
                append(7).append("\n").
                append(8).append("\n").
                append("Fizz").append("\n").
                append("Buzz").append("\n").
                append(11).append("\n").
                append("Fizz").append("\n").
                append(13).append("\n").
                append(14).append("\n").
                append("FizzBuzz").append("\n").
                append(16).append("\n").
                append(17).append("\n").
                append("Fizz");
    }


}
