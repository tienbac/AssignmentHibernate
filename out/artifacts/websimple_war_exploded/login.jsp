<%@ page import="entity.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: tienb
  Date: 4/16/2019
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loginFail = (String) request.getAttribute("loginFail");
    if (loginFail == null){
        loginFail = "";
    }
    HashMap<String, ArrayList<String>> errors = (HashMap<String, ArrayList<String>>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
    Account account = (Account) request.getAttribute("account");
    if (account==null){
        account = new Account();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <jsp:include page="head.jsp"/>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" action="login" method="post">
					<span class="login100-form-title">
						Login
					</span>

                <div class="wrap-input100 validate-input" data-validate = "Valid username is required">
                    <input class="input100" type="text" name="username" placeholder="Username" value="<%=account.getUsername()%>">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-user" aria-hidden="true"></i>
						</span>
                    <% if (errors.containsKey("username")) {
                        ArrayList<String> userNameError = errors.get("username");
                        for (String error :
                                userNameError) {
                    %>
                    <span class="text-danger" style="margin-left: 25px"><%=error%></span>
                    <br>
                    <%
                            }
                        }
                    %>

                </div>


                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                    <% if (errors.containsKey("password")) {
                        ArrayList<String> passwordError = errors.get("password");
                        for (String error :
                                passwordError) {
                    %>
                    <span class="text-danger" style="margin-left: 25px"><%=error%></span>
                    <br>
                    <%
                            }
                        }
                    %>
                </div>

                <div class="container-login100-form-btn">
                    <%if (loginFail != ""){%>
                    <h3 class="text-danger"><%= loginFail%></h3>
                    <%} %>
                    <button class="login100-form-btn">
                        Login
                    </button>
                </div>

                <div class="text-center p-t-136">
                    <a class="txt2" href="register">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="script.jsp"/>

</body>
</html>
