package au.com.x.algo.console;

import au.com.x.algo.exception.InvalidInputException;
import au.com.x.algo.service.Result;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandLineAppRunner implements CommandLineRunner{

    private static ch.qos.logback.classic.Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(CommandLineAppRunner.class);


    private final Result result;

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    public CommandLineAppRunner(Result result){
        this.result = result;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            logger.info("args[{}]: {}", i, args[i]);
        }

        try {

            List<Integer> inputList =
                    Stream.of(args).filter(x -> x != null && !x.isEmpty()).map(Integer::parseInt).
                            collect(Collectors.toList());

            //List<Integer> inputList = commandLineInputProcessor.readInput();
            int value = result.processRequest(inputList);
            if ( value != -1 ) {
                logger.info("The returned min input value:" + value);
            }
        } catch (InvalidInputException | RuntimeException e) {
            logger.error("Operation resuming due to an unexpected error", e);
        } finally {
            context.close();
        }
    }

}
