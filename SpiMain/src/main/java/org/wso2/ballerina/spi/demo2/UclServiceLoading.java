package org.wso2.ballerina.spi.demo2;

import org.wso2.ballerina.spi.Issue;
import org.wso2.ballerina.spi.IssueReporter;
import org.wso2.ballerina.spi.ToolPlugin;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ServiceLoader;

public class UclServiceLoading {

    public static void main(String[] args) {
        ArrayList<Issue> allIssues = new ArrayList<>();
        IssueReporter reporter = new IssueReporter(allIssues);

        // Perform core issue reporting
        reporter.reportIssue("MainTool", "core issue", 10);

        String jarPath =
                "C:\\Users\\Tharana Wanigaratne\\Desktop\\SPI-ClassLoading-Demo\\SpiPlugin\\build\\libs\\SpiPlugin.jar";

        // Load Service using ucl
        URLClassLoader ucl = loadRemoteJAR(jarPath);

        // We can load services on remote JARs using a ucl with a service loader
        ServiceLoader<ToolPlugin> pluginServices = ServiceLoader.load(ToolPlugin.class, ucl);
        pluginServices.forEach(pluginService -> {
            pluginService.init(reporter);
        });

        // Print out all issues
        allIssues.forEach(issue -> {
            System.out.println(issue);
            System.out.println("Issue source: " + issue.getSource());
            System.out.println("Issue message: " + issue.getMessage());
            System.out.println("---------------------------------------\n");
        });
    }

    public static URLClassLoader loadRemoteJAR(String jarPathString) {
        Path jarPath = Path.of(jarPathString);
        URI uri = jarPath.toUri();
        URL jarURL = null;

        try {
            jarURL = uri.toURL();
            return new URLClassLoader(new URL[]{jarURL});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
