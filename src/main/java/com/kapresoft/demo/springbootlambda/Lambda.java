package com.kapresoft.demo.springbootlambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.kapresoft.demo.springbootlambda.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

import static org.springframework.util.StringUtils.arrayToCommaDelimitedString;

@Slf4j
public class Lambda {

    private final ConfigurableApplicationContext appContext;
    private final ConfigurableEnvironment environment;
    private final JsonUtils jsonUtils;

    public Lambda() {
        appContext = new AnnotationConfigApplicationContext(AppInitializer.class);
        environment = appContext.getEnvironment();
        jsonUtils = appContext.getBean(JsonUtils.class);
    }

    public LambdaResponse handler(Map<String, Object> request, Context context) {
        logContext(appContext);
        log.debug("Input: {}", jsonUtils.toJson(request));
        final LambdaResponse response = LambdaResponse.builder().status("success").build();
        log.debug("Response: {}", jsonUtils.toJson(response));
        return response;
    }

    public void logContext(ConfigurableApplicationContext context) {
        log.info("spring.application.name: {}", environment.getProperty("spring.application.name"));
        log.info("Active Profile(s): {}", arrayToCommaDelimitedString(environment.getActiveProfiles()));
        log.info("  spring.profiles.active: {}", environment.getProperty("spring.profiles.active"));
        log.info("House: {}", environment.getProperty("motto.house"));
        log.info("Motto: {}", environment.getProperty("motto.message"));
    }

    ApplicationContext getContext() {
        return appContext;
    }


}
