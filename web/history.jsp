<%-- 
    Document   : history
    Created on : Nov 4, 2020, 11:26:10 AM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>History Page</title>
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

        <div class="container mt-5">
            <c:if test="${not empty requestScope.CancelBookingError}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.CancelBookingError}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.CancelBookingSuccess}">
                <div class="alert alert-success" role="alert">
                    ${requestScope.CancelBookingSuccess}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.SearchError}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.SearchError}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.RatingSuccess}">
                <div class="alert alert-success" role="alert">
                    ${requestScope.RatingSuccess}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.RatingError}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.RatingError}
                </div>
            </c:if>
            <form action="searchHistory">
                <div class="form-inline row d-flex justify-content-center m-3">
                    <input type="text" name="txtOrderId" placeholder="Order Id" min="1" class="form-inline form-control m-3" value="${param.txtOrderId}">
                    <input type="date" class="form-control" name="txtSearchDate" 
                           <c:if test="${param.txtSearchDate}">
                               value="${param.txtSearchDate}"
                           </c:if>
                           >
                    <input type="submit" value="Search" class="btn btn-primary form-inline m-3">
                </div>
            </form>
            <c:if test="${not empty requestScope.ListOrderError}">
                <div class="d-flex justify-content-center">
                    <h3>${requestScope.ListOrderError}</h3>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.ListOrder}">
                <c:forEach var="order" items="${requestScope.ListOrder}">
                    <div class="border border-black mt-4">

                        <div class="d-flex justify-content-center m-3 row">
                            Name: ${order.name}<br>
                            Address: ${order.address}<br>
                            Phone: ${order.phone}<br>
                            Book date: ${order.createDate}<br>
                            Check in day: ${order.fromDate}<br>
                            Check out day: ${order.toDate}<br>
                            Discount code: ${order.codeId}<br>
                            <c:if test="${order.statusId eq 6}">
                                Status: Canceled
                            </c:if>
                            <c:if test="${order.statusId eq 10}">
                                Status: Done
                            </c:if>
                            <c:if test="${order.statusId eq 5}">
                                Status: Booked
                            </c:if>
                            <c:if test="${order.statusId eq 7}">
                                Status: Confirm
                            </c:if>
                            <hr>
                        </div>
                        <div class="d-flex justify-content-center row m-3">
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
                                                <c:if test="${order.statusId eq 10}">
                                                <th colspan="2">Rate</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="ordDetail" items="${requestScope.MapOrderDetail[order]}" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${sessionScope.MapHotel[ordDetail.hotelId]}</td>
                                                <td>${sessionScope.MapRoomType[ordDetail.roomTypeId]}</td>
                                                <td>
                                                    ${ordDetail.quantity}
                                                </td>
                                                <td>${ordDetail.price} VND</td>
                                                <td>${ordDetail.price * ordDetail.quantity} VND</td>

                                                <c:if test="${order.statusId eq 10}">
                                            <form action="rateRoom">
                                                <td>
                                                    <input type="hidden" name="txtHotelRoomId" value="${ordDetail.hotelRoomId}">
                                                    <input type="hidden" name="txtOrderId" value="${order.orderId}">
                                                    <input type="number" required min="0" max="10" name="txtRate" class="form-control"
                                                           <c:if test="${not empty requestScope.MapRate[ordDetail.hotelRoomId]}">
                                                               value="${requestScope.MapRate[ordDetail.hotelRoomId]}"
                                                           </c:if>
                                                           > 
                                                </td>
                                                <td>
                                                    <input type="submit" value="Rate" class="form-control btn btn-primary">
                                                </td>
                                            </form>
                                        </c:if>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="5">
                                            <p>Total Bill</p>
                                        </td>
                                        <td colspan="2">
                                            ${order.totalPrice} VND
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center m-3">
                            <jsp:useBean id="now" class="java.util.Date" />
                            <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
                            <c:if test="${order.statusId ne 6}">
                                <c:if test="${order.fromDate gt today}">
                                    <form action="cancelBooking" method="POST">
                                        <input type="hidden" name="txtOrderId" value="${order.orderId}">
                                        <input type="submit" class="btn btn-primary" value="Cancel booking" onclick="return confirm('Are you sure you want to cancel this order?');">
                                    </form>
                                </c:if>
                            </c:if>
                        </div>
                    </div>

                </c:forEach>

            </c:if>
        </div>
        <div class="d-flex justify-content-center col">
            <a href="search.jsp" class="m-3">Continue booking</a><br>
            <a href="cart.jsp" class="m-3">Return cart page</a>
        </div>
    </body>

</html>
