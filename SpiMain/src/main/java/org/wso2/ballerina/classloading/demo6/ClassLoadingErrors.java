package org.wso2.ballerina.classloading.demo6;

import org.nexus.v0.MessagePrinter;

public class ClassLoadingErrors {

    public static void main(String[] args) {
        // 1. ClassNotFoundException: Try to load a class that does not exist
        try {
            ClassLoadingErrors.class.getClassLoader().loadClass("org.wso2.ballerina.demo111.NotExists");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. NoClassDefFoundError: Load a class from a JAR without adding the JAR to the classpath
        // Triggered when JAR not added to class path: java -cp  SpiMain-1.0-SNAPSHOT.jar  org.wso2.ballerina.demo6.ClassLoadingErrors
        try {
            MessagePrinter messagePrinter = new MessagePrinter();
            messagePrinter.printMessage("From main program");
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }

        /** To mitigate NoClassDefFoundError, we could:
         * 1. Add the JAR that contains the class to same class path: java -cp  SpiMain-1.0-SNAPSHOT.jar;"C:\Users\Tharana Wanigaratne\Desktop\SPI-ClassLoading-Demo\ClassLoadingModule\build\libs\ClassLoadingModule.jar" org.wso2.ballerina.demo6.ClassLoadingErrors
         * 2. package the JAR as an Uber JAR {@link  org.wso2.ballerina.demo1.UberJarClassLoading}
         * */

        /** ClassCastException: Created when there are 2 classes with same definitions but different class loaders
         * {@link  org.wso2.ballerina.demo2.ClassCastExceptionDemo}
         * */

    }
}
