<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 09.09.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <form class="form-signin" method="post" action="register">
            <h2 class="form-signin-heading">Zarejestruj się!</h2>
            <label for="inputEmail" class="sr-only">adres email</label>
            <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Adres email" required autofocus>
            <label for="inputUsername" class="sr-only">nazwa użytkownika</label>
            <input type="text" name="inputUsername" id="inputUsername" class="form-control" placeholder="Nazwa użytkownika" required autofocus>
            <label for="inputPassword" class="sr-only">hasło</label>
            <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Hasło" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Wyślij</button>
        </form>
    </div>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
