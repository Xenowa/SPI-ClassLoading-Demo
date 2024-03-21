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

package org.wso2.ballerina.classloading.demo2;

import java.io.InputStream;
import java.util.Scanner;

public class LoadingResources {

    public static void main(String[] args) {
        // Load the JSON file in resources folder as input stream
        String resourceName = "sample.json";
        InputStream resourceAsStream = LoadingResources.class.getClassLoader().getResourceAsStream(resourceName);

        if (resourceAsStream != null) {
            // Create scanner to read file contents
            Scanner scanner = new Scanner(resourceAsStream);

            // Print file contents to console
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } else {
            System.out.println("resource: '" + resourceName + "' Unavailable!");
        }
    }
}
