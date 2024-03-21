/*
 *
 *  * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 *  *
 *  * WSO2 LLC. licenses this file to you under the Apache License,
 *  * Version 2.0 (the "License"); you may not use this file except
 *  * in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied. See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

package org.wso2.ballerina.spi;

import java.util.ArrayList;

public class IssueReporter {

    private final ArrayList<Issue> issues;

    public IssueReporter(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    public void reportIssue(String source, String message, int issueCount) {
        Issue issue = new Issue(source, message, issueCount);
        issues.add(issue);
    }
}
