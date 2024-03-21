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

package org.wso2.ballerina.classloading.demo5;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Scanner;

public class CustomClassLoading {

    public static void main(String[] args) {

        // load a JAR using a self-first custom class loader
        String jarPath =
                "C:\\Users\\Tharana Wanigaratne\\Desktop\\SPI-ClassLoading-Demo\\ClassLoadingModule\\build\\libs\\ClassLoadingModule.jar";
        URLClassLoader customUcl = loadRemoteJAR(jarPath);

        // Loading a resource in the remote JAR
        String resourceName = "sample2.json";
        InputStream resourceAsStream = customUcl.getResourceAsStream(resourceName);

        if (resourceAsStream != null) {
            Scanner scanner = new Scanner(resourceAsStream);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } else {
            System.out.println("resource: '" + resourceName + "' Unavailable!");
        }

        // Let's try to load a resource with the same name
        resourceName = "sample.json";
        resourceAsStream = customUcl.getResourceAsStream(resourceName);
        if (resourceAsStream != null) {
            Scanner scanner = new Scanner(resourceAsStream);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine()); // Guess the message =)
            }
        } else {
            System.out.println("resource: '" + resourceName + "' Unavailable!");
        }
    }

    public static URLClassLoader loadRemoteJAR(String jarPathString) {
        Path jarPath = Path.of(jarPathString);
        URI jarURI = jarPath.toUri();
        URL jarURL = null;
        try {
            jarURL = new URL(jarURI.toString());
            return new CustomClassLoader(new URL[]{jarURL});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
