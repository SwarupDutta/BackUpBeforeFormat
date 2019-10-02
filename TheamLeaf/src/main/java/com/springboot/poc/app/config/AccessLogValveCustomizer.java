package com.springboot.poc.app.config;

import org.apache.catalina.valves.AccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import groovy.util.logging.Slf4j;

@Component
@Slf4j
public class AccessLogValveCustomizer implements EmbeddedServletContainerCustomizer {

    @Autowired
    ServerProperties serverProperties;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        if (container instanceof TomcatEmbeddedServletContainerFactory) {

            final TomcatEmbeddedServletContainerFactory tcContainer = (TomcatEmbeddedServletContainerFactory) container;
            ServerProperties.Tomcat.Accesslog accessLog = serverProperties.getTomcat().getAccesslog();

            AccessLogValve accessLogValve = new AccessLogValve();
            accessLogValve.setPattern(accessLog.getPattern());
            accessLogValve.setDirectory(accessLog.getDirectory());
            accessLogValve.setPrefix(accessLog.getPrefix());
            accessLogValve.setSuffix(accessLog.getSuffix());
            accessLogValve.setRenameOnRotate(true);

            tcContainer.addContextValves(accessLogValve);

           // log.info("Configured access log in {}", accessLogValve.getDirectory());

        }

    }

}