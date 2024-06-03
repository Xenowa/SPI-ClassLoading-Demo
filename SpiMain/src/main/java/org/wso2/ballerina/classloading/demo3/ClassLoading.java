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

package org.wso2.ballerina.classloading.demo3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoading {

    // Uses Application Class Loader (Therefore the JAR needs to be in same class path for class loading)
    // Method will execute during run time when class path is given as follows
    // java -cp SpiMain-1.0-SNAPSHOT.jar;"C:\Users\Tharana Wanigaratne\Desktop\SPI-ClassLoading-Demo\ClassLoadingModule\build\libs\ClassLoadingModule.jar" org.wso2.ballerina.classloading.demo3.ClassLoading
    public static void main(String[] args) {
        try {
            Class<?> MessagePrinter = ClassLoading.class.getClassLoader().loadClass("org.nexus.v0.MessagePrinter");
            Object messagePrinterInstance = MessagePrinter.getConstructor().newInstance();
            Method printMessage = MessagePrinter.getDeclaredMethod("printMessage", String.class);
            printMessage.invoke(messagePrinterInstance, "from Ballerina");
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
