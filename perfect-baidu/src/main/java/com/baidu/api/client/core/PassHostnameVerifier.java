/**
 * PassHostnameVerifier.java
 * <p>
 * Copyright 2010 @company@, Inc.
 *
 * @company@ licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.baidu.api.client.core;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Internal use only, please ignore.
 *
 * @author @author@ (@author-email@)
 * @version @version@, $Date: 2010-12-23$
 */
public class PassHostnameVerifier implements HostnameVerifier {

    /**
     * Override super method.
     *
     * @see HostnameVerifier#verify(String, SSLSession)
     */
    public boolean verify(String arg0, SSLSession arg1) {
        return true;
    }

}
