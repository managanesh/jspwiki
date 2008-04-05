<%@ taglib uri="/WEB-INF/jspwiki.tld" prefix="wiki" %>
<%@ page import="java.security.Principal" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.ecyrd.jspwiki.*" %>
<%@ page import="com.ecyrd.jspwiki.auth.PrincipalComparator" %>
<%@ page import="com.ecyrd.jspwiki.auth.authorize.Group" %>
<%@ page import="com.ecyrd.jspwiki.auth.authorize.GroupManager" %>
<%@ page errorPage="/Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="javax.servlet.jsp.jstl.fmt.*" %>
<fmt:setLocale value="${prefs['Language']}" />
<fmt:setBundle basename="templates.default"/>
<%
  // Extract the group name and members
  String name = request.getParameter( "group" );

  Group group = (Group)pageContext.getAttribute( "Group",PageContext.REQUEST_SCOPE );
  Principal[] members = null;

  if ( group != null )
  {
    name = group.getName();
    members = group.members();
    Arrays.sort( members, new PrincipalComparator() );
  }

  // FIXME : find better way to i18nize default group name
  if ( "MyGroup".equals(name) )
  {
	  name = LocaleSupport.getLocalizedMessage(pageContext, "newgroup.defaultgroupname");
  }

  name = TextUtil.replaceEntities(name);
%>

<wiki:TabbedSection defaultTab="${param.tab}">
  <wiki:Tab id="logincontent" title='<%=LocaleSupport.getLocalizedMessage(pageContext, "newgroup.heading.create")%>'>

<h3><fmt:message key="newgroup.heading.create"/></h3>

<wiki:Messages div='error' topic='<%=GroupManager.MESSAGES_KEY%>' prefix='<%=LocaleSupport.getLocalizedMessage(pageContext,"newgroup.errorprefix")%>' />

  <form id="createGroup" action="<wiki:Link format="url" jsp="NewGroup.jsp"/>"
    method="POST" accept-charset="UTF-8">

  <div class="formhelp">
     <fmt:message key="newgroup.instructions.start"/>
  </div>

  <table class="wikitable">
    <!-- Name -->
    <tr>
      <th><label><fmt:message key="newgroup.name"/></label></th>
      <td><input type="text" name="group" size="30" value="<%=name%>" />
      <div class="formhelp">
        <fmt:message key="newgroup.name.description"/>
      </div>
      </td>
    </tr>

    <!-- Members -->
    <%
      StringBuffer s = new StringBuffer();
      for ( int i = 0; i < members.length; i++ )
      {
        s.append( members[i].getName().trim() );
        s.append( '\n' );
      }
    %>
    <tr>
      <th><label><fmt:message key="group.members"/></label></th>
      <td><textarea id="members" name="members" rows="20" cols="40"><%=TextUtil.replaceEntities(s.toString())%></textarea>
      <div class="formhelp">
        <fmt:message key="newgroup.members.description"/>
      </div>
      </td>
    </tr>
    </table>
    <input type="submit" name="ok" value="<fmt:message key="newgroup.creategroup"/>" />
    <input type="hidden" name="action" value="save" />
    <div class="formhelp">
         <fmt:message key="newgroup.instructions.end"/>
    </div>
  </form>


</wiki:Tab>
</wiki:TabbedSection>