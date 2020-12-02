<%-- 
    Document   : createDiscount
    Created on : Nov 4, 2020, 11:04:17 AM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create discount Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </head>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="search.jsp">The Hotel</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="search.jsp">Home <span class="sr-only">Home</span></a>
                    </li>
                    <c:if test="${sessionScope.User.roleId == 2}">
                        <li class="nav-item">
                            <a class="nav-link" href="cart.jsp">Cart</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="loadHistory">My history</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.User.roleId == 1}">
                        <li>
                            <a class="nav-link" href="createDiscount.jsp">Create Discount code</a>
                        </li>
                    </c:if>
                </ul>

                <span class="navbar-text">
                    <c:if test="${not empty sessionScope.User}">
                        <a href="logout"><button class="btn btn-primary">Logout</button></a>
                    </c:if>
                    <c:if test="${empty sessionScope.User}">
                        <a href="login.jsp"><button class="btn btn-primary">Login</button></a>
                    </c:if>
                </span>

            </div>
        </nav>
    <body class="text-center">
        <div class="container">
            <div class="col mt-5 justify-center">
                <form action="createDiscount" class="need-validate" method="POST">
                    <div class="form-group">
                        <div class="form-group row d-flex justify-content-center">
                            <h3>Create new discount code</h3>

                        </div>
                        <hr>
                        <div class="form-group row">
                            <label>Discount code: </label>
                            <input type="text" name="txtCodeId" required class="form-control" value="${param.txtCodeId}">
                        </div>
                        <c:if test="${not empty requestScope.CodeError}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.CodeError}
                            </div>
                        </c:if>
                        

                        <div class="form-group row">
                            <label>Discount name:</label>
                            <input type="text" name="txtCodeName" required class="form-control" value="${param.txtCodeName}">
                        </div>
                        <div class="form-group row">
                            <label>Discount Percent: </label>
                            <input type="number" name="txtPercent" required class="form-control" min="1" max="100" value="${param.txtPercent}">
                        </div>
                        <div class="form-group row">
                            <jsp:useBean id="now" class="java.util.Date" />
                            <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
                            <label>Expiration Date:</label>
                            <input type="date" name="txtExpirationDate" min="${today}" 
                                   <c:if test="${not empty param.txtExpirationDate}">value="${paramtxtExpirationDate}"</c:if>
                                       class="form-control">
                                   <br>
                            </div>
                        <c:if test="${not empty requestScope.DateError}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.DateError}
                            </div>
                        </c:if>
                        <div class="d-flex justify-content-center form-group row">
                            <input type="submit" class="btn btn-primary" value="Create">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>