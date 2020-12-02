<%-- 
    Document   : signup
    Created on : Oct 28, 2020, 10:49:08 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign up Page</title>
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
    <body class="text-center">
        <div class="container">
            <div class="row justify-content-center mt-5">
                <aside class="col-sm-4">
                    <div class="card align-center">
                        <article class="card-body">
                            <h4 class="card-title text-center mb-4 mt-1">Sign up</h4>
                            <hr>
                            <form action="signUp" method="POST" required>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-user"></i></span>
                                        </div>
                                        <input class="form-control" id="txtEmail" placeholder="Email" type="text" name="txtEmail" value="${param.txtEmail}" required onblur="checkEmail()">
                                        <c:if test="${not empty requestScope.ErrorEmail}">
                                            <div class="form-group">
                                                <p class="justify-content-center" style="color: red;">${requestScope.ErrorEmail}</p>
                                            </div>
                                        </c:if>
                                        
                                    </div> 
                                        <div class="form-group">
                                            <p class="justify-content-center" style="display:none; color: red;" id="emailErrorFormat">Email wrong format</p>
                                        </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-user-circle"></i></span>
                                        </div>
                                        <input class="form-control" placeholder="Name" type="text" name="txtName" value="${param.txtName}" required>
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-phone"></i></span>
                                        </div>
                                        <input class="form-control" placeholder="Phone number" type="text" name="txtPhone" id="phoneNum" value="${param.txtPhone}" required onblur="checkPhoneNumber()">
                                    </div> 
                                    <div class="form-group">
                                        <p class="justify-content-center" style="display:none; color: red;" id="phoneErrorFormat">Phone number wrong format</p>
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-address-book "></i></span>
                                        </div>
                                        <input class="form-control" placeholder="Address" type="text" name="txtAddress" value="${param.txtAddress}" required>
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-lock"></i></span>
                                        </div>
                                        <input class="form-control" placeholder="Password" type="password" name="txtPassword" value="${param.txtPassword}" required>
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-lock"></i></span>
                                        </div>
                                        <input class="form-control" placeholder="RePassword" type="password" name="txtRePassword" value="${param.txtRePassword}" required>
                                    </div>
                                    <c:if test="${not empty requestScope.ErrorPassword}">
                                        <div class="form-group">
                                            <p class="justify-content-center" style="color: red;">${requestScope.ErrorPassword}</p>
                                        </div>
                                    </c:if>
                                </div> 
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary btn-block" name="btnAction" value="Signup">
                                </div> 
                            </form>
                            <a class="text-center">Return search page</a>
                        </article>
                    </div>
                </aside>
            </div>
        </div> 
    </body>
    <script>
        function checkEmail(){
            var email = document.getElementById("txtEmail");
            if (!email.value.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/)) {
                email.value = '';
                document.getElementById("emailErrorFormat").style.display = "block";
            } else {
                document.getElementById("emailErrorFormat").style.display = "none";
            }
        }
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