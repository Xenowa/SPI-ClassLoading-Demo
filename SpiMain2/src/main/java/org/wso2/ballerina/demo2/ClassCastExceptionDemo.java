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

package org.wso2.ballerina.demo2;

import org.nexus.v0.MessagePrinter;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class ClassCastExceptionDemo {

    // Since this is an uber JAR, the MessagePrinter class is loaded from 2 different class loaders
    public static void main(String[] args) {
        // load a JAR using a self-first custom class loader
        String jarPath =
                "C:\\Users\\Tharana Wanigaratne\\Desktop\\SPI-ClassLoading-Demo\\ClassLoadingModule\\build\\libs\\ClassLoadingModule.jar";
        URLClassLoader customUcl = loadRemoteJAR(jarPath);

        // Set instance from loaded class
        MessagePrinter messagePrinter = null;
        try {
            Class<?> MessagePrinter = customUcl.loadClass("org.nexus.v0.MessagePrinter");

            messagePrinter = (MessagePrinter) MessagePrinter.getConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            System.out.println("Unable to load class: " + e.getMessage());
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        // Attempt to print a message
        if (messagePrinter != null) {
            messagePrinter.printMessage("Message 1 from uber JAR ");
        }

        // Solution (Using the same class loader)
        MessagePrinter messagePrinter2 = new MessagePrinter();
        messagePrinter2.printMessage("Message 2 from uber JAR");
    }

    public static URLClassLoader loadRemoteJAR(String jarPathString) {
        Path jarPath = Path.of(jarPathString);
        URI jarURI = jarPath.toUri();
        URL jarURL = null;
        try {
            jarURL = new URL(jarURI.toString());
            return new CustomJARClassLoader(new URL[]{jarURL});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
