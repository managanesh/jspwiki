<%-- 
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
--%>
<%@ taglib uri="http://jakarta.apache.org/jspwiki.tld" prefix="wiki" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<wiki:TabbedSection defaultTab="${param.tab}">
  <wiki:Tab id="viewgroup" titleKey="group.tab">
  <h3><c:out value="${wikiActionBean.name}" /></h3>
  <s:messages />
  <wiki:Permission permission="createGroups">
    <fmt:message key="group.createsuggestion">
      <fmt:param>
        <s:link beanclass="org.apache.wiki.action.GroupActionBean" event="create">
          <s:param name="group" value="${wikiActionBean.name}" />
          <s:param name="group" value="${wikiActionBean.name}" />
          <fmt:message key="group.createit" />
        </s:link>
      </fmt:param>
    </fmt:message>
  </wiki:Permission>

  <table class="wikitable">
    <!-- Name -->
    <tr>
      <th><fmt:message key="group.name" /></th>
      <td>
        <fmt:message key="group.groupintro">
          <fmt:param><em><c:out value="${wikiActionBean.name}" /></em></fmt:param>
        </fmt:message>
      </td>
    </tr>
    <!-- Members -->
    <tr>
      <th><fmt:message key="group.members" />
      </th>
      <td>
         <c:forEach items="${wikiActionBean.members}" var="member" varStatus="loop">
           <c:out value="${member.name}" /><br/>
         </c:forEach>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <fmt:message key="group.modifier">
          <fmt:param><c:out value="${wikiActionBean.modifier}" /></fmt:param>
          <fmt:param><c:out value="${wikiActionBean.modified}" /></fmt:param>
        </fmt:message>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <fmt:message key="group.creator">
          <fmt:param><c:out value="${wikiActionBean.creator}" /></fmt:param>
          <fmt:param><c:out value="${wikiActionBean.created}" /></fmt:param>
        </fmt:message>
      </td>
    </tr>
  </table>

  <wiki:Permission permission="deleteGroup">
    <c:set var="confirm" value="<fmt:message key='grp.deletegroup.confirm'/>" scope="page"/>
    <s:form beanclass="org.apache.wiki.action.GroupActionBean" class="wikiform"
      id="deleteGroup"
      onsubmit="return( confirm('${confirm}') && Wiki.submitOnce(this) );"
      method="POST" acceptcharset="UTF-8">
      <s:submit name="delete"><fmt:message key="actions.deletegroup" /></s:submit>
    </s:form>
  </wiki:Permission>

</wiki:Tab>

<wiki:Permission permission="editGroup">
  <wiki:Tab id="editgroup" titleKey="actions.editgroup"
           url="<s:link beanclass='org.apache.wiki.action.GroupActionBean' event='edit'><s:param name='group' value='${wikiActionBean.name}' /></s:link>"
           accesskey="e" >
  </wiki:Tab>
</wiki:Permission>

</wiki:TabbedSection>
