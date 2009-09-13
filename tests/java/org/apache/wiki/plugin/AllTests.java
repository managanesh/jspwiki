/*
    JSPWiki - a JSP-based WikiWiki clone.

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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class AllTests extends TestCase
{
    public AllTests( String s )
    {
        super( s );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite("JSPWiki plugins");

        suite.addTest( CounterPluginTest.suite() );
        suite.addTest( GroupsTest.suite() );
        suite.addTest( InsertPageTest.suite() );
        suite.addTest( PluginIndexPluginTest.suite() );
        suite.addTest( PluginManagerTest.suite() );
        suite.addTest( ReferringPagesPluginTest.suite() );
        suite.addTest( TableOfContentsTest.suite() );
        suite.addTest( UndefinedPagesPluginTest.suite() );
        suite.addTest( DenouncePluginTest.suite() );
        suite.addTest( ReferredPagesPluginTest.suite() );
        suite.addTest( RecentChangesPluginTest.suite() );
        suite.addTest( IndexPluginTest.suite() );
        suite.addTest( InterWikiLinksPluginTest.suite() );
        suite.addTest( PageViewPluginTest.suite() );

        return suite;
    }
}