package org.wso2.ballerina.spi.demo1;

import org.wso2.ballerina.spi.Issue;
import org.wso2.ballerina.spi.IssueReporter;
import org.wso2.ballerina.spi.ToolPlugin;

import java.util.ArrayList;
import java.util.ServiceLoader;

public class DefaultServiceLoading {

    public static void main(String[] args) {
        ArrayList<Issue> allIssues = new ArrayList<>();
        IssueReporter reporter = new IssueReporter(allIssues);

        // Perform core issue reporting
        reporter.reportIssue("MainTool", "core issue", 10);

        // By default, services on default class path will work with service loader
        // java -cp SpiMain-1.0-SNAPSHOT.jar;"C:\Users\Tharana Wanigaratne\Desktop\SPI-ClassLoading-Demo\SpiPlugin\build\libs\SpiPlugin.jar" org.wso2.ballerina.spi.demo1.DefaultServiceLoading
        ServiceLoader<ToolPlugin> pluginServices = ServiceLoader.load(ToolPlugin.class);
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

}
