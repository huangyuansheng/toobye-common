/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.toobye.common.exec;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public final class SshStream extends PipedOutputStream {

    private OutputStream tee;

    /**
     * <pre> 构造器. </pre>
     *
     * @param tee 输出流
     */
    public SshStream(final OutputStream tee) {
        this.tee = tee;
    }

    @Override
    public void write(final int b) throws IOException {
        super.write(b);
        tee.write(b);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        super.write(b, off, len);
        tee.write(b, off, len);
    }
    
}