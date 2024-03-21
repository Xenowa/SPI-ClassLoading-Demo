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

package org.wso2.ballerina.classloading.demo1;

public class ClassLoaderTypes {

    public static void main(String[] args) {
        // Application class loader
        System.out.println("ClassLoader Name: " + ClassLoaderTypes.class.getClassLoader().getName());

        // Platform class loader
        System.out.println("ClassLoader Name: " + ClassLoaderTypes.class.getClassLoader().getParent().getName());

        // Bootstrap class loader (since null following will give exception)
        // System.out.println("ClassLoader Name: " + ClassLoaderTypes.class.getClassLoader().getParent().getParent().getName());
        // So we directly get class loader of the String class
        System.out.println(String.class.getClassLoader());
    }
}