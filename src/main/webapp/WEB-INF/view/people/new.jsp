<%@ page import="models.Person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NikitaKogtev
  Date: 10.12.2023
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    nihilism
</head>
<body>

<%
    List<Person> personList = (List<Person>) request.getAttribute("people");

    if (personList != null && !personList.isEmpty()) {
        out.println("<ui>");
        for (Person p : personList) {
            out.println("<a href=/people/" + p.getId() + "" + ">" + p.getName() + "," + p.getAge() + "</a>");
        }
        out.println("</ui>");
    } else out.println("<p>Читателей нет в библиотеке</p>");

    String action = request.getServletPath();

    System.out.println(action);

%>

</body>
</html>
