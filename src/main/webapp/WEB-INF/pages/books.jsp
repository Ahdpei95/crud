<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles.css">
</head>
<body>
<h1>Books:</h1>

<div class="tableOfBooks">
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
                <th>SetIsAlready</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.description}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.isbn}"/></td>
                    <td><c:out value="${book.printYear}"/></td>
                    <td><c:out value="${book.readAlready}"/></td>
                    <form:form action="/${page}/edit/${book.id}" method="POST">
                        <td><input type="submit" class="actionOnTable" value="Update" /></td>
                        <input type="hidden" name="search_title" value="${searchTitle}"/>
                    </form:form>
                    <form:form action="/${page}/remove/${book.id}" method="POST"> <%--/${page}/remove/${book.id}--%>
                        <td><input type="submit" class="actionOnTable" value="Delete" /></td>
                        <input type="hidden" name="search_title" value="${searchTitle}"/>
                    </form:form>
                    <form:form action="/${page}/readAlready/${book.id}" method="POST">
                        <td><input type="submit" class="actionOnTable" value="ReadAlready" /></td>
                        <input type="hidden" name="search_title" value="${searchTitle}"/>
                    </form:form>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div class="findAndPaging">
    <table class="pagination">
        <tr>
            <c:forEach begin="0" end="${count}" step="1" varStatus="i">
                <c:choose>
                    <c:when test="${empty search_title}">
                        <form:form action="/books/${i.index}" method="GET">
                            <td class="white"><input type="submit" class="page" value="${i.index}"/></td>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <form:form action="/books/find/${i.index}" method="POST">
                            <td class="white"><input type="submit" class="page" value="${i.index}"/></td>
                            <input type="hidden" name="search_title" value="${searchTitle}"/>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <div class="find">
        <form action="/books/find/0" method="POST">
            <table>
                <tr>
                    <td class="white">
                        <input type="submit" class="actionButton" name="action" value="Find" />
                    </td>
                    <td class="white">
                        <input type="text" name="search_title" value="${searchTitle}"/>
                    </td>
                    <td class="white"><a href="<c:url value="/books/0"/>">BackToAllBook</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="addOrEdit">
    <c:url var="addAction" value="/books/${page}/add"/>
    <form:form action="${addAction}" method="POST" commandName="book">
        <c:choose>
            <c:when test="${!empty book.title}">
                <h1>Edit a Book</h1>
            </c:when>
            <c:otherwise>
                <h1>Add a Book</h1>
            </c:otherwise>
        </c:choose>
        <table>
            <tr>
                <td>
                    ID
                </td>
                <td>
                    <form:input path="id" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    Title
                </td>
                <td>
                    <form:input path="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    Description
                </td>
                <td>
                    <form:input path="description"/>
                </td>
            </tr>
            <tr>
                <td>
                    Author
                </td>
                <td>
                    <form:input path="author" readonly="${isEdit}"/>
                </td>
            </tr>

            <tr>
                <td>
                    ISBN
                </td>
                <td>
                    <form:input path="isbn"/>
                </td>
            </tr>
            <tr>
                <td>
                    PrintYear
                </td>
                <td>
                    <form:input path="printYear"/>
                </td>
            </tr>
            <tr>
                <td>
                    ReadAlready
                </td>
                <td>
                    <form:input path="readAlready" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty book.title}">
                        <input type="submit" class="actionButton"
                               value="<spring:message text="Edit Book"/>"/>
                        <input type="hidden" name="search_title" value="${searchTitle}"/>
                    </c:if>
                    <c:if test="${empty book.title}">
                        <input type="submit" class="actionButton"
                               value="<spring:message text="Add Book"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
