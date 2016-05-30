package io.dweomer.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
@RestController
public class ExampleApplication {

    @Value("${io.dweomer.example.derp}")
    private String derp;

    @RequestMapping(path = "/", produces = "text/plain")
    public String home() {
        return "Hello Docker World";
    }

    @RequestMapping(path = "/derp", produces = "text/plain")
    public String derp() {
        return derp;
    }

    @RequestMapping(path = "/_/properties", produces = "text/plain")
    public String systemProperties() {
        final Map<String,String> sortedProperties = new TreeMap<>();

        for (final String property : System.getProperties().stringPropertyNames()) {
            sortedProperties.put(property, System.getProperty(property));
        }

        final StringBuilder buf = new StringBuilder();

        for (final Map.Entry<String,String> entry : sortedProperties.entrySet()) {
            buf.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\r\n");
        }

        return buf.toString();
    }

    @RequestMapping(path = "/_/environment", produces = "text/plain")
    public String environmentVariables() {
        final Map<String,String> sortedEnvironmentVariables = new TreeMap<>(System.getenv());

        final StringBuilder buf = new StringBuilder();

        for (final Map.Entry<String,String> entry : sortedEnvironmentVariables.entrySet()) {
            buf.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\r\n");
        }

        return buf.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}