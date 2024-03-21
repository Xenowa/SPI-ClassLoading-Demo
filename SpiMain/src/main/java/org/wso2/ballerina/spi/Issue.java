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

public class Issue {

    private final String source;
    private final String message;
    private final int issueCount;

    Issue(String source, String message, int issueCount) {
        this.source = source;
        this.message = message;
        this.issueCount = issueCount;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public int getIssueCount() {
        return issueCount;
    }
}
