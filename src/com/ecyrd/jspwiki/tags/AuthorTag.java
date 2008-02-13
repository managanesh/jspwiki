/* 
    JSPWiki - a JSP-based WikiWiki clone.

    Copyright (C) 2001-2002 Janne Jalkanen (Janne.Jalkanen@iki.fi)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.ecyrd.jspwiki.tags;

import java.io.IOException;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.WikiPage;
import com.ecyrd.jspwiki.TextUtil;
import com.ecyrd.jspwiki.parser.MarkupParser;
import com.ecyrd.jspwiki.parser.WikiDocument;
import com.ecyrd.jspwiki.render.RenderingManager;

/**
 *  Writes the author name of the current page, including a link to that page,
 *  if that page exists.
 *
 *  @author Janne Jalkanen
 *  @since 2.0
 */
public class AuthorTag
    extends WikiTagBase
{
    private static final long serialVersionUID = 0L;
    
    public final int doWikiStartTag()
        throws IOException
    {
        if ( !( m_actionBean instanceof WikiContext ) )
        {
            return SKIP_BODY;
        }
        
        WikiContext context = (WikiContext)m_actionBean;
        WikiEngine engine = context.getEngine();
        WikiPage   page   = context.getPage();

        String author = page.getAuthor();

        if( author != null && author.length() > 0 )
        {
            author = TextUtil.replaceEntities(author);
            if( engine.pageExists(author) )
            {
                // FIXME: It's very boring to have to do this.
                //        Slow, too.

                RenderingManager mgr = engine.getRenderingManager();
                
                MarkupParser p = mgr.getParser( context, "["+author+"|"+author+"]" );

                WikiDocument d = p.parse();
                
                author = mgr.getHTML( context, d );
            }

            pageContext.getOut().print( author );
        }
        else
        {
            pageContext.getOut().print( "unknown" );
        }

        return SKIP_BODY;
    }
}