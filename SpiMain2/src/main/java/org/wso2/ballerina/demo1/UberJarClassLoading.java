package org.wso2.ballerina.demo1;

import org.nexus.v0.MessagePrinter;

public class UberJarClassLoading {

    // Since all dependent classes come packed, we do not give dependent JARs to class path
    // java -cp SpiMain2-all.jar org.wso2.ballerina.demo1.UberJarClassLoading
    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        messagePrinter.printMessage("From uber JAR main program");
    }
}
