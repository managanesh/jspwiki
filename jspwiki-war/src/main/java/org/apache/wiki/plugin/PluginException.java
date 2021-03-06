/*
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.  
 */
package org.apache.wiki.plugin;


/**
 *  Provides a generic PluginException.  This is the kind of
 *  an exception that the plugins should throw. 
 *  @deprecated will be removed in 2.10 scope. Consider using 
 *  {@link org.apache.wiki.api.exceptions.PluginException} instead
 */
@Deprecated
public class PluginException
    extends org.apache.wiki.api.exceptions.PluginException
{
    private static final long serialVersionUID = 0L;

    private final Throwable m_throwable;

    /**
     *  Create a PluginException.
     *  
     *  @param message {@inheritDoc}
     */
    public PluginException( String message )
    {
        super( message );
        m_throwable = null;
    }

    /**
     *  Create a PluginException with the given original exception wrapped.
     *  
     *  @param message {@inheritDoc}
     *  @param original The original exception.
     */
    public PluginException( String message, Throwable original )
    {
        super( message, original );
        m_throwable = original;
    }
}
