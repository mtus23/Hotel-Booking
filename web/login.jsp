<%-- 
    Document   : login
    Created on : Sep 18, 2020, 2:19:12 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </head>
    <body class="text-center">
        <div class="container">
            <div class="row justify-content-center mt-5">
                <aside class="col-sm-4">
                    <div class="card align-center">
                        <article class="card-body">
                            <h4 class="card-title text-center mb-4 mt-1">Login</h4>
                            <hr>
                            <c:if test="${not empty requestScope.loginMessage}">
                                <div class="alert alert-danger" role="alert">
                                    ${requestScope.loginMessage}
                                </div>
                                <hr>
                            </c:if>
                                <c:if test="${not empty requestScope.SignUpSuccess}">
                                <div class="alert alert-success" role="alert">
                                    ${requestScope.SignUpSuccess}
                                </div>
                                <hr>
                            </c:if>
                            <form action="login" method="POST">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        </div>
                                        <input class="form-control" placeholder="Email" type="text" name="txtEmail" value="">
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                        </div>
                                        <input class="form-control" placeholder="Password" type="password" name="txtPassword" value="">
                                    </div> 
                                </div> 
                                <!-- reCAPTCHA -->
                                <div class="g-recaptcha"
                                     data-sitekey="6Lduyt4ZAAAAADxDsSvjmcxxldqjget37QUSv2O1"></div>
                                
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary btn-block" name="btnAction" value="Login">
                                </div> 
                            </form>
                            <hr>
                            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#ForgotPassword">
                                Forgot password
                            </button>
                            <hr>
                            <p class="text-center"><a href="signup.jsp"><button class="btn btn-light btn-block">Sign up</button></a></p>
                            <hr>
                            <a class="text-center">Return search page</a>
                        </article>
                    </div>
                </aside>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="ForgotPassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Forgot password</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="sendMailReset">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Enter your email: </label>
                                <input class="form-control" type="text" placeholder="Email" min="1" name="txtEmail" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
</html>
