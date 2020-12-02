<%-- 
    Document   : cart
    Created on : Oct 31, 2020, 3:41:32 PM
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
        <title>Cart Page</title>
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
            <c:if test="${empty sessionScope.Cart.items}">
                <div class="alert alert-danger" role="alert">              
                    Cart empty
                </div>
            </c:if>
            <div class="d-flex justify-content-center m-1">
                <h3>Your cart</h3>
                <hr>
            </div>
            <div class="d-flex justify-content-center m-1">

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
                                    <th>Action</th>
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
                                            <input type="number" min="1" value="${item.value}" name="txtQuantity${item.key.hotelRoomId}">
                                        </td>
                                        <td>${item.key.price} VND</td>
                                        <td>${item.key.price * item.value} VND</td>
                                        <td><input type="checkbox" name="cbItem" value="${item.key.hotelRoomId}"></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <p>Total Bill</p>
                                    </td>
                                    <td>
                                        ${sessionScope.TotalBill} VND
                                    </td>
                                    <td colspan="2">
                                        <input type="submit" name="btnAction" value="Update selected hotel room" class="btn btn-primary" /><br>
                                    </td>
                                    <td colspan="2">
                                        <input type="submit" name="btnAction" value="Remove selected hotel room" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete these hotel room?');"/>
                                    </td>
                                </tr>
                            </form>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <div class="d-flex justify-content-center">

                <form action="checkOut" method="POST">
                    <div class="form-group row">

                        <label>Name: </label>
                        <input name="txtCustomer" type="text" min="1" max="60" class="form-control"
                               <c:if test="${not empty sessionScope.User}">
                                   value="${sessionScope.User.name}"
                               </c:if>
                               <c:if test="${not empty param.txtCustomer}">
                                   value="${param.txtCustomer}"
                               </c:if>
                               required />
                    </div>
                    <div class="form-group row">
                        <label>Address: </label>
                        <input name="txtAddress" type="text" min="1" max="50" class="form-control"
                               <c:if test="${not empty sessionScope.User}">
                                   value="${sessionScope.User.address}"
                               </c:if>
                               <c:if test="${not empty param.txtAddress}">
                                   value="${param.txtAddress}"
                               </c:if>
                               required />
                    </div>
                    <div class="form-group row">
                        <label>Phone: </label>
                        <input name="txtPhone" type="text" class="form-control"
                               <c:if test="${not empty sessionScope.User}">
                                   value="${sessionScope.User.phone}"
                               </c:if>
                               <c:if test="${not empty param.txtPhone}">
                                   value="${param.txtPhone}"
                               </c:if>
                               required />
                    </div>
                    <div class="form-group">
                        <p class="justify-content-center" style="display:none; color: red;" id="phoneErrorFormat">Phone number wrong format</p>
                    </div>
                    <div class="form-group row">
                        <label>Discount code: </label>
                        <input name="txtCode" type="text" class="form-control"
                               <c:if test="${not empty param.txtCode}">
                                   value="${param.txtCode}"
                               </c:if>
                               />
                    </div>
                    <jsp:useBean id="now" class="java.util.Date" />
                    <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
                    <div class="form-group row">
                        <label class="m-1">Check in day</label>
                        <input type="date" name="txtCheckInDate" class="form-control m-1" min="${today}" 
                               <c:if test="${not empty param.txtCheckInDate}">value="${param.txtCheckInDate}"</c:if>
                               <c:if test="${empty param.txtCheckInDate}">value="${today}"</c:if>
                                   required >
                        </div>
                        <div class="form-group row">
                            <label class="m-1">Check out day</label>
                            <input type="date" name="txtCheckOutDate" class="form-control m-1" min="${today}" 
                               <c:if test="${not empty param.txtCheckOutDate}">value="${param.txtCheckOutDate}"</c:if>
                                   required >
                        </div>
                        <div class="form-group d-flex justify-content-center">
                            <input class="btn btn-primary" type="submit" value="Checkout" onclick="checkPhoneNumber()">
                        </div>
                    </form>
                </div>
            <c:if test="${not empty requestScope.DateError}">
                <div class="alert alert-danger" role="alert">              
                    ${requestScope.DateError}
                </div>
            </c:if>
            <c:if test="${not empty requestScope.QuantityError}">
                <div class="alert alert-danger" role="alert">              
                    <c:forEach var="error" items="${requestScope.QuantityError}">
                        ${error}
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.CodeError}">
                <div class="alert alert-danger" role="alert">              
                    ${requestScope.CodeError}
                </div>
            </c:if>
        </div>
    </body>
    <script>
        function checkPhoneNumber() {
            var phone = document.getElementById("phoneNum");
            if (!phone.value.match(/^\d{10}$/g)) {
                phone.value = '';
            } else {
                document.getElementById("phoneErrorFormat").style.display = "none";
            }
        }
    </script>
</html>
