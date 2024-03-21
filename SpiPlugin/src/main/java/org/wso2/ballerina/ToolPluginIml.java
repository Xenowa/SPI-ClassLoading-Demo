package org.wso2.ballerina;

import org.wso2.ballerina.spi.IssueReporter;
import org.wso2.ballerina.spi.ToolPlugin;

public class ToolPluginIml implements ToolPlugin {

    @Override
    public void init(IssueReporter reporter) {
        reporter.reportIssue("ToolPluginIml", "external issue", 10);
    }
}
