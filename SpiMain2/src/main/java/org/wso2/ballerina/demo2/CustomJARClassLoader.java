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

import java.net.URL;
import java.net.URLClassLoader;

public class CustomJARClassLoader extends URLClassLoader {

    public CustomJARClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // Check if the class is already loaded in the custom class loader
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                // Try to load the class from the URLs
                loadedClass = findClass(name);
            } catch (ClassNotFoundException e) {
                // If not found then delegate to the parent
                loadedClass = super.loadClass(name);
            }
        }

        // Return loaded class
        return loadedClass;
    }
}
