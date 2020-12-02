<%-- 
    Document   : search
    Created on : Oct 9, 2020, 2:23:02 PM
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
        <title>The Hotel</title>
        <style>
            .container{
                margin-top: 25px;
            }
            img{
                width: 200px;
                height: 250px;
            }
            .max-lines {
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 4; /* number of lines to show */
                -webkit-box-orient: vertical;
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
            <c:if test="${not empty sessionScope.User}">
                <h1>Hello ${sessionScope.User.name}</h1>
            </c:if>
            <c:if test="${not empty requestScope.SearchResultEmpty}">
                <div class="alert alert-danger" role="alert">              
                    ${requestScope.SearchResultEmpty} 
                </div>
            </c:if>
            <c:if test="${not empty requestScope.DateError}">
                <div class="alert alert-danger" role="alert">              
                    ${requestScope.DateError} 
                </div>
            </c:if>
            <c:if test="${not empty requestScope.CreateCode}">
                <div class="alert alert-success" role="alert">              
                    ${requestScope.CreateCode} 
                </div>
            </c:if>
            <c:if test="${not empty requestScope.CheckOutConfirmSuccess}">
                <div class="alert alert-success" role="alert">              
                    ${requestScope.CheckOutConfirmSuccess} 
                </div>
            </c:if>
            <c:if test="${not empty requestScope.CheckOutConfirmFail}">
                <div class="alert alert-danger" role="alert">              
                    ${requestScope.CheckOutConfirmFail} 
                </div>
            </c:if>
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
            <hr>

            <div class="col">
                <div class="col m-1">
                    <jsp:useBean id="now" class="java.util.Date" />
                    <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
                    <form action="searchHotel">
                        <div class="row"><label class="m-3">Search Hotel:</label></div>
                        <div class="form-row">
                            <div class="form-group col-sm-2">
                                <label class="m-1">Hotel name</label>
                                <input class="form-control m-1" type="text" name="txtHotelNameSearch" value="${param.txtHotelNameSearch}" placeholder="Hotel name">
                            </div>
                            <div class="form-group col-md-2">
                                <label class="m-1">Hotel area</label>
                                <select name="cboArea" class="form-control m-1">
                                    <option>------All------</option>
                                    <c:forEach var="area" items="${sessionScope.ListArea}">
                                        <option value="${area.areaId}" 
                                                <c:if test="${param.cboArea ne '------All------'}">
                                                    ${area.areaId eq param.cboArea ? 'selected' : ''}
                                                </c:if>
                                                >${area.areaName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <label class="m-1">Room amount: </label>
                                <input type="number" name="txtAmount" class="form-control m-1" min="1" 
                                       <c:if test="${not empty param.txtAmount}">value="${param.txtAmount}"</c:if>
                                           required >
                                </div>
                                <div class="form-group col-md-2">
                                    <label class="m-1">Check in day</label>
                                    <input type="date" name="txtCheckInDate" class="form-control m-1" min="${today}" 
                                       <c:if test="${not empty param.txtCheckInDate}">value="${param.txtCheckInDate}"</c:if>
                                       <c:if test="${empty param.txtCheckInDate}">value="${today}"</c:if>
                                           required >
                                </div>
                                <div class="form-group col-md-2">
                                    <label class="m-1">Check out day</label>
                                    <input type="date" name="txtCheckOutDate" class="form-control m-1" min="${today}" 
                                       <c:if test="${not empty param.txtCheckOutDate}">value="${param.txtCheckOutDate}"</c:if>
                                           required >
                                </div>
                                <div class="form-group col-md-2">
                                    <input type="submit" value="Search" class="btn btn-primary" style="margin-top: 35px; margin-bottom: 10px;">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row m-5">

                    <c:if test="${empty requestScope.SearchResultEmpty}">
                        <c:if test="${empty requestScope.DateError}">
                            <c:if test="${empty requestScope.SearchResult}">
                                <c:if test="${not empty sessionScope.ListHotel}">
                                    <c:forEach var="dto" items="${sessionScope.ListHotel}">
                                        <div class="card m-3" style="width: 18rem;">
                                            <c:url var="image" value="${dto.image}"></c:url>
                                            <img class="card-img-top" src="${image}" alt="image">
                                            <div class="card-body">
                                                <h5 class="card-title">${dto.hotelName}</h5>
                                                <p style="font-size: 75%;" class="card-text">${sessionScope.MapArea[dto.areaId]}</p>
                                                <p class="card-text max-lines">${dto.description}</p>
                                                <c:if test="${sessionScope.User.roleId != 1}">
                                                    <c:url var="detailHotel" value="loadHotelDetail">
                                                        <c:param name="txtHotelId" value="${dto.hotelId}"></c:param>
                                                    </c:url>
                                                    <a href="${detailHotel}"><button class="btn btn-primary">Detail</button></a>
                                                </c:if>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${not empty requestScope.SearchResult}">
                        <c:forEach var="dto" items="${requestScope.SearchResult}">
                            <div class="card m-3" style="width: 18rem;">
                                <c:url var="image" value="${dto.image}"></c:url>
                                <img class="card-img-top" src="${image}" alt="image">
                                <div class="card-body">
                                    <h5 class="card-title">${sessionScope.MapHotel[dto.hotelId]}</h5>
                                    <p style="font-size: 95%;" class="card-text">${sessionScope.MapRoomType[dto.roomTypeId]}</p>
                                    <p class="card-text max-lines">${dto.price} VND</p>
                                    <c:if test="${sessionScope.User.roleId == 2}">
                                        <c:url var="addToCart" value="addToCart">
                                            <c:param name="txtHotelId" value=""></c:param>
                                            <c:param name="txtHotelRoom" value="${dto.hotelRoomId}"></c:param>
                                        </c:url>
                                        <a href="${addToCart}"><button class="btn btn-primary">Add to cart</button></a>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
