<%-- 
    Document   : index
    Created on : Mar 4, 2017, 12:10:04 PM
    Author     : Mukolwe Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Post</title>
        <style type="text/css">
            body{background: #c7c7c7;}
            .blog-content{
                width: 960px;
                background: #fff;
                border: 1px solid #c4c4c4;
                padding: 20px;
                margin: 10px auto;
            }
            .success{
                background: green;
                color: black;
                border: 1px solid #eedc82;
                padding: 7px 10px;
            }
            .succ-out{
                background: #fef1b5;border: 1px solid #eedc82;
                padding: 7px 10px;
            }
            .incorrect{
                background: #e49a9a;
                border: 1px solid #c05555;
                padding: 7px 10px;
            }
        </style>
        <link rel="stylesheet" href="../assets/css/bootstrap.css"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
        <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
        <%@page import="com.classes.database"%>
        <%
            database db = new database();
        %>

        <sql:setDataSource var='bgGet' driver='<%= db.jstlDriver()%>' url='<%= db.jstlUrl()%>' user='<%= db.jstlUser()%>'  password='<%= db.jstlPassword()%>'/>

        <sql:query dataSource="${bgGet}" var="reqBlogs">
            <%= db.blogsPosted()%>
        </sql:query>

    </head>
    <body>
        <%
            String post_succ = (String) request.getAttribute("post_succ");
            if (post_succ == null) {
                post_succ = "";
            }
            String post_error = (String) request.getAttribute("post_error");
            if (post_error == null) {
                post_error = "";
            }
            String ErrorMessage = (String) request.getAttribute("ErrorMessage");
            if (ErrorMessage == null) {
                ErrorMessage = "";
            }
        %>
        <div class="blog-content">
            <form action="PostQuerry" method="get">
                <div class="row">
                    <div class="form-group">
                        <label for="" class="col-md-2 control-label">Question Post: </label>
                        <div class="col-md-10">
                            <textarea rows="3" name="myquestion" class="form-control"></textarea>
                        </div>
                        <input type="hidden" name="email" value="michaelolukaka@gmail.com"/>
                        <input type="hidden" name="date" value="<%= (new java.util.Date().toLocaleString())%>"/>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Post Question</button>
                            </div>
                        </div>
                    </div>                
                </div>
            </form>
            <span style="margin-bottom: 30px;"><%= ErrorMessage%></span>
            <span style="margin-bottom: 30px;"><%= post_succ%></span>
            <span style="margin-bottom: 30px;"><%= post_error%></span>

            <h3>Earlier Postings</h3>
            <table class="table table-bordered">
                <tbody>
                    <c:forEach var="blogs" items="${reqBlogs.rows}">
                        <tr>
                            <td><c:out value="${blogs.blog}"/></td>                                                           
                            <td><c:out value="${blogs.user_email}"/></td>
                            <td><c:out value="${blogs.date}"/></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>
