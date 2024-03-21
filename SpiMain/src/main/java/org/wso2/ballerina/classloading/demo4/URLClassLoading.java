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

package org.wso2.ballerina.classloading.demo4;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Scanner;

public class URLClassLoading {

    // UCL uses App Class Loader as parent
    // Since App Class Loader does not have the class it will delegate loading the class from UCL it self
    // Method will execute during run time when class path is given as follows
    // java -cp SpiMain-1.0-SNAPSHOT.jar;"C:\Users\Tharana Wanigaratne\Desktop\SPI-ClassLoading-Demo\ClassLoadingModule\build\libs\ClassLoadingModule.jar" org.wso2.ballerina.demo3.ClassLoading
    public static void main(String[] args) {
        Path jarPath =
                Path.of("C:\\Users\\Tharana Wanigaratne\\Desktop\\SPI-ClassLoading-Demo\\ClassLoadingModule\\build\\libs\\ClassLoadingModule.jar");
        URI jarURI = jarPath.toUri();
        URL jarURL = null;
        try {
            jarURL = new URL(jarURI.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Loading a class and running a method of it through reflection
        URLClassLoader ucl = new URLClassLoader(new URL[]{jarURL});
        try {
            Class<?> MessagePrinter = ucl.loadClass("org.nexus.v0.MessagePrinter");
            Object messagePrinterInstance = MessagePrinter.getConstructor().newInstance();
            Method printMessage = MessagePrinter.getDeclaredMethod("printMessage", String.class);
            printMessage.invoke(messagePrinterInstance, "from Ballerina");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // Loading a resource in the remote JAR
        String resourceName = "sample2.json";
        InputStream resourceAsStream = ucl.getResourceAsStream(resourceName);

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
        resourceAsStream = ucl.getResourceAsStream(resourceName);
        if (resourceAsStream != null) {
            Scanner scanner = new Scanner(resourceAsStream);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine()); // Guess the message =)
            }
        } else {
            System.out.println("resource: '" + resourceName + "' Unavailable!");
        }
    }
}
