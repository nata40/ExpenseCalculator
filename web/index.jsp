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
< <div class="container">
  <div class="header clearfix">
    <nav>
      <ul class="nav nav-pills pull-right">
        <li role="presentation" class="active"><a href="#">Home</a></li>
        <li role="presentation"><a href="login">Masz Konto? Zaloguj się</a></li>
        <li role="presentation"><a href="#">Contact</a></li>
      </ul>
    </nav>
    <h3 class="text-muted">Kalkulator wydatków</h3>
  </div>

  <div class="jumbotron">
    <h1>Dołącz do nas!</h1>
    <p class="lead">Kalkulator umożliwa kontrolowanie wydatków i wynagrodzeń. Zarejestruj się już dziś!</p>
    <p><a class="btn btn-lg btn-success" href="register" role="button">Zarejestruj się</a></p>
  </div>

  <div class="row marketing">
    <div class="col-lg-6">

    </div>

    <div class="col-lg-6">

    </div>
  </div>

  <footer class="footer">
    <p>Developed by Daniel Siwulec</p>
  </footer>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>
