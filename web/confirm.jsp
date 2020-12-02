<%-- 
    Document   : confirm
    Created on : Nov 6, 2020, 3:00:15 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <title>Confirm Page</title>
        <style>
            .container{
                margin-top: 25px;
            }
        </style>
    </head>
    <body>
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

        <c:if test="${empty sessionScope.Cart.items || empty requestScope.CustomerName}">
            <c:redirect url="cart.jsp"></c:redirect>
        </c:if>
        <div class="container mt-5">
            <c:if test="${empty requestScope.AddSuccess}">
                <div class="d-flex justify-content-center m-1">
                    <h3>Please confirm before progressing</h3>
                    <hr>
                </div>
                <div class="d-flex justify-content-center m-1 col">
                    Name: ${requestScope.CustomerName}<br>
                    Address: ${requestScope.CustomerAddress}<br>
                    Phone: ${requestScope.CustomerPhone}<br>
                    Check in day: ${requestScope.CheckInDate}<br>
                    Check out day: ${requestScope.CheckOutDate}<br>
                    Number of days: ${requestScope.NumOfDate}<br>
                    Discount code: 
                    <c:if test=" ${not empty requestScope.codeId}">
                        ${requestScope.codeId}
                    </c:if>
                    <c:if test=" ${empty requestScope.codeId}">
                        None 
                    </c:if>
                    <hr>
                </div>
                <div class="d-flex justify-content-center">
                    <c:if test="${not empty sessionScope.Cart.items}">
                        <div class="table col m-3">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Hotel name</th>
                                        <th>Room type</th>
                                        <th>Quantity</th>
                                        <th>Price</th>                                   
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <form action="cart" method="POST">
                                    <c:forEach var="item" items="${sessionScope.Cart.items}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${sessionScope.MapHotel[item.key.hotelId]}</td>
                                            <td>${sessionScope.MapRoomType[item.key.roomTypeId]}</td>
                                            <td>
                                                <input type="number" min="1" value="${item.value}" name="txtQuantity${item.key.hotelRoomId}" readonly>
                                            </td>
                                            <td>${item.key.price} VND</td>
                                            <td>${item.key.price * item.value} VND</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3">
                                            <p>Total Bill</p>
                                        </td>
                                        <td colspan="3">
                                            ${sessionScope.TotalBill} VND
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
                <div class="d-flex justify-content-center">
                    <form action="processCheckOut" method="POST">
                        <input type="hidden" name="txtCustomer" value="${requestScope.CustomerName}" />
                        <input type="hidden" name="txtAddress" value="${requestScope.CustomerAddress}" />
                        <input type="hidden" name="txtPhone" value="${requestScope.CustomerPhone}" />
                        <input type="hidden" name="txtCheckInDate" value="${requestScope.CheckInDate}" />
                        <input type="hidden" name="txtCheckOutDate" value="${requestScope.CheckOutDate}" />
                        <input type="hidden" name="txtCode" value="${requestScope.CodeId}" />
                        <input type="submit" value="Checkout" class="btn btn-primary"
                               onclick="return confirm('Are you sure you want to continue?');"/>
                    </form>
                </div>
            </c:if>
            <div class="d-flex justify-content-center row">
                <c:if test="${not empty requestScope.AddSuccess}">
                    <p class="m-3">${requestScope.AddSuccess}</p><br>
                    <p class="m-3">Your Order Id is : ${requestScope.OrderId}</p>
                    <br>
                </c:if>
            </div>
            <div class="d-flex justify-content-center col">
                <a href="search.jsp" class="m-3">Continue shopping</a><br>
                <a href="cart.jsp" class="m-3">Return cart page</a>
            </div>
        </div>
    </body>

</html>
