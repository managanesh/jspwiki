/* 
    JSPWiki - a JSP-based WikiWiki clone.

    Copyright (C) 2001 Janne Jalkanen (Janne.Jalkanen@iki.fi)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.ecyrd.jspwiki;

import java.util.Properties;
import java.util.Iterator;
import org.apache.log4j.Category;

/**
 *  Manages variables.  Variables are case-insensitive.  A list of all
 *  available variables is on a Wiki page called "WikiVariables".
 *
 *  @author Janne Jalkanen
 *  @since 1.9.20.
 */
public class VariableManager
{
    private static Category log = Category.getInstance( VariableManager.class );

    public VariableManager( Properties props )
    {
    }

    /**
     *  Returns true if the link is really command to insert
     *  a variable.
     *  <P>
     *  Currently we just check if the link starts with "{$".
     */
    public static boolean isVariableLink( String link )
    {
        return link.startsWith("{$");
    }

    /**
     *  Parses the link and finds a value.
     *  <P>
     *  A variable is inserted using the notation [{$variablename}].
     *
     *  @throws IllegalArgumentException If the format is not valid (does not 
     *          start with {$, is zero length, etc.)
     *  @throws NoSuchVariableException If a variable is not known.
     */
    public String parseAndGetValue( WikiContext context,
                                    String link )
        throws IllegalArgumentException,
               NoSuchVariableException
    {
        if( !link.startsWith("{$") )
            throw new IllegalArgumentException( "Link does not start with {$" );

        if( !link.endsWith("}") )
            throw new IllegalArgumentException( "Link does not end with }" );

        String varName = link.substring(2,link.length()-1);

        return getValue( context, varName.trim() );
    }

    /**
     *  Returns a value of the named variable.
     *
     *  @param name Name of the variable.
     *
     *  @throws IllegalArgumentException If the name is somehow broken.
     *  @throws NoSuchVariableException If a variable is not known.
     */
    // FIXME: Currently a bit complicated.  Perhaps should use reflection
    //        or something to make an easy way of doing stuff.
    public String getValue( WikiContext context,
                            String      name )
        throws IllegalArgumentException,
               NoSuchVariableException
    {
        if( name == null )
            throw new IllegalArgumentException( "Null variable name." );

        if( name.length() == 0 )
            throw new IllegalArgumentException( "Zero length variable name." );

        name = name.toLowerCase();
        String res = "";

        if( name.equals("pagename") )
        {
            res = context.getPage().getName();
        }
        else if( name.equals("applicationname") )
        {
            res = context.getEngine().getApplicationName();
        }
        else if( name.equals("jspwikiversion") )
        {
            res = Release.VERSTR;
        }
        else if( name.equals("encoding") )
        {
            res = context.getEngine().getContentEncoding();
        }
        else if( name.equals("totalpages") )
        {
            res = Integer.toString(context.getEngine().getPageCount());
        }
        else if( name.equals("pageprovider") )
        {
            res = context.getEngine().getCurrentProvider();
        }
        else if( name.equals("pageproviderdescription") )
        {
            res = context.getEngine().getCurrentProviderInfo();
        }
        else if( name.equals("interwikilinks") )
        {
            // FIXME: Use StringBuffer
            for( Iterator i = context.getEngine().getAllInterWikiLinks().iterator(); i.hasNext(); )
            {
                String link = (String) i.next();
                res += link+" --&gt; "+context.getEngine().getInterWikiURL(link)+"<BR>\n";
            }
        }
        else if( name.equals("inlinedimages") )
        {
            // FIXME: Use StringBuffer
            for( Iterator i = context.getEngine().getAllInlinedImagePatterns().iterator(); i.hasNext(); )
            {
                String ptrn = (String) i.next();
                res += ptrn+"<BR>\n";
            }
        }
        else if( name.equals("pluginpath") )
        {
            res = context.getEngine().getPluginSearchPath();

            res = (res == null) ? "-" : res;
        }
        else
        {
            throw new NoSuchVariableException( "No variable "+name+" defined." );
        }

        return res;
    }
}
