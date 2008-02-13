/* 
    JSPWiki - a JSP-based WikiWiki clone.

    Copyright (C) 2001-2006 Janne Jalkanen (Janne.Jalkanen@iki.fi)

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

import javax.servlet.jsp.JspWriter;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.action.CommentActionBean;

/**
 *  Writes a comment link.  Body of the link becomes the link text.
 *  <P><B>Attributes</B></P>
 *  <UL>
 *    <LI>page - Page name to refer to.  Default is the current page.
 *    <LI>format - Format, either "anchor" or "url".
 *  </UL>
 *
 *  @author Janne Jalkanen
 *  @since 2.0
 */
public class CommentLinkTag
    extends WikiLinkTag
{
    private static final long serialVersionUID = 0L;
    
    public final int doWikiStartTag()
        throws IOException
    {
        String pageName = null;
        
        //
        //  Determine the page and the link.
        //
        if( m_pageName == null )
        {
            if( m_page == null )
            {
                // You can't call this on the page itself anyways.
                return SKIP_BODY;
            }

            pageName = m_page.getName();
        }
        else
        {
            pageName = m_pageName;
        }

        //
        //  Finally, print out the correct link, according to what
        //  user commanded.
        //
        JspWriter out = pageContext.getOut();

        switch( m_format )
        {
          case ANCHOR:
            out.print("<a href=\""+getCommentURL(pageName)+"\">");
            break;

          case URL:
            out.print( getCommentURL(pageName) );
            break;
        }

        return EVAL_BODY_INCLUDE;
    }

    private String getCommentURL( String pageName )
    {
        return ((WikiContext)m_actionBean).getContext().getURL(CommentActionBean.class, pageName);
    }

}