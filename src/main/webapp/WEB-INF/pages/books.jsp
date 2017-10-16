<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="utf-8" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<jsp:useBean id="someModel" scope="request" type="com.Crud.CrudController.controller.BooksController"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Books</title>
    <style type="text/css">
        .table {
            border-collapse: collapse;
            border-color: #ccc;
        }

        .table td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 10px;
            border: 1px solid gray;
            overflow: hidden;
            word-break: normal;
            color: #333;
            background-color: #fff;
        }

        .table th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border: 1px solid #ccc;
            overflow: hidden;
            word-break: normal;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h1>Books:</h1>
<jsp:useBean id="listBooks" scope="request" type="com.Crud.CrudController.controller.BooksController"/>
<c:if test="${!empty listBooks}">
    <table class="table">
    <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Description</th>
    <th>Author</th>
    <th>Isbn</th>
    <th>PrintYear</th>
    <th>ReadAlready</th>
    <th>Edit</th>
    <th>Delete</th>
    </tr>

    <%--<jsp:useBean id="listBooks" scope="request" type="java.util.List"/>--%>
    <c:forEach items="${listBooks}" var="b">
    <tr>
        <td><c:out value="${b.id}"/></td>
        <td><c:out value="${b.title}"/></td>
        <td><c:out value="${b.description}"/></td>
        <td><c:out value="${b.author}"/></td>
        <td><c:out value="${b.isbn}"/></td>
        <td><c:out value="${b.printYear}"/></td>
        <td><c:out value="${b.readAlready}"/></td>
        <td><a href="<c:url value="/edit/${b.id}"/>">Edit</a></td>
        <td><a href="<c:url value="/remove/${b.id}"/>">Delete</a></td>
    </tr>
    </c:forEach>
    </table>
</c:if>

<h1>Add a Book</h1>

<c:url var="addAction" value=""/>



<jsp:useBean id="book" scope="session" type="com.Crud.CrudController.controller.BooksController"/>
<form:form action="${addAction}" method="post" commandName="book">
    <table>
            <%--<tr>
                <td>
                    <form:label path="id">
                        <spring:message text="id"/>
                    </form:label>
                    <form:input path="id"/>
                </td>
                <c:if test="${!empty book}">--%>
                <%--<td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>--%>
            </tr>
      <%--  </c:if>--%>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="title"/>
                </form:label>
            </td>
            <td>
                <%--<form:input path="title"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author">
                    <spring:message text="author"/>
                </form:label>
            </td>
            <td>
                <form:input path="author"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="isbn"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="readAlready">
                    <spring:message text="readAlready"/>
                </form:label>
            </td>
            <td>
                <form:input path="readAlready"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="printYear">
                    <spring:message text="printYear"/>
                </form:label>
            </td>
            <td>
                <form:input path="printYear"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="action" value="Add" />
            </td>
        </tr>
    </table>-->-->
</form:form>
</body>
</html>
