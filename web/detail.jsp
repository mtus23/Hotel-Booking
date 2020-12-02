<%-- 
    Document   : detail
    Created on : Oct 31, 2020, 1:37:44 PM
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
        <title>Hotel detail Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            .custom-container {
                margin-left: 50px;
                margin-right: 50px;
            }
            .image{
                height:400px;
            }
            img{
                height:100%;
                width:100%;
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
        <c:if test="${not empty requestScope.AddFail}">
            <div class="alert alert-danger" role="alert">              
                ${requestScope.AddFail} 
            </div>
        </c:if>
        <c:if test="${not empty requestScope.AddSuccess}">
            <div class="alert alert-success" role="alert">              
                ${requestScope.AddSuccess} 
            </div>
        </c:if>
        <c:set var="hotelDto" value="${requestScope.hotelDto}"></c:set>
            <div class="custom-container container-fluid">

                <div class="row">
                    <div class="col-6 p-3 m-2">
                        <div class="image">
                            <img src="${hotelDto.image}">
                    </div>
                </div>
                <div class="col-4 p-3 m-2 sideCol">
                    <div>
                        <h5>${hotelDto.hotelName}</h5>
                        <p> <i class="fa fa-map-marker"></i> ${MapArea[hotelDto.areaId]}</p>
                        <br>
                        <p class="content">${hotelDto.description}</p>
                    </div>
                    <div>
                        <c:if test="${not empty requestScope.listHotelRoomtype}">
                            <form action="addToCart" method="POST">

                                <c:forEach var="roomType" items="${requestScope.listHotelRoomtype}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="txtHotelRoom" value="${roomType.hotelRoomId}"> 
                                        <label class="form-check-label">${MapRoomType[roomType.roomTypeId]}</label>
                                        <label class="form-check-label">${roomType.price}</label>
                                    </div>
                                </c:forEach>
                                <input type="hidden" name="txtHotelId" value="${requestScope.hotelDto.hotelId}">
                                <c:if test="${sessionScope.User.roleId == 2}">
                                    <input type="submit" class="btn btn-primary" value="Add to cart">
                                </c:if>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
