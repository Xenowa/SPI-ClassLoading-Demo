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

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public URL getResource(String name) {
        Objects.requireNonNull(name);

        // Give priority to find and load resources from remote JARs
        URL url = findResource(name);
        if (url != null) {
            return url;
        }

        // If it's not there in the remote JAR then delegate finding resources to the parent class loaders
        return super.getResource(name);
    }
}
