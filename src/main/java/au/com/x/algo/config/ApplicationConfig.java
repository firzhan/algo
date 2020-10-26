package au.com.x.algo.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class ApplicationConfig {

    private static ch.qos.logback.classic.Logger logger =
            (Logger) LoggerFactory.getLogger(ApplicationConfig.class);

    @PreDestroy
    public void onShutDown() {
        logger.warn("No House Keeping Tasks to be performed now");
    }
}
