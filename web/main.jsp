<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="random" class="java.util.Random" scope="application" />
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
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Kalkulator wydatków! Witaj <c:out value="${sessionScope.user.username}"></c:out></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="product">Overview <span class="sr-only">(current)</span></a></li>
                <li><a href="report">Raport</a></li>
                <li><a href="#">Analytics</a></li>
                <li><a href="#">Export</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Twoje wydatki</h1>

            <div class="table-responsive">
                <canvas id="myChart"></canvas>
            </div>


            <a href="new.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-right"></span>Dodaj produkt<span class="glyphicon glyphicon-arrow-left"></span></a>
            <a href="delete.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-right"></span>Usuń produkt<span class="glyphicon glyphicon-arrow-left"></span></a>
            <h2 class="sub-header">Section title</h2>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Numer dodania</th>
                        <th>Nazwa produktu</th>
                        <th>Cena</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${requestScope.allProduct}">
                        <tr>
                            <td>
                                <c:out value="${product.id}"></c:out>
                            </td>
                            <td>
                                <c:out value="${product.name}"></c:out>
                            </td>
                            <td>
                                <c:out value="${product.price}"></c:out>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mdb.js"></script>

    <script type="text/javascript">
        var ctx = document.getElementById("myChart").getContext('2d');
        var counter = 0;
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
                labels: [
                  <c:forEach var="product"  items="${requestScope.allProduct}">

                    <c:out value="\"" escapeXml="false"/><c:out value="${product.name}"></c:out><c:out value="\"" escapeXml="false"/>
                    <c:out value=","></c:out>

                    </c:forEach>
                ],
                datasets: [{
                    label: 'cena produktu',
                    data: [<c:forEach var="product" items="${requestScope.allProduct}">

                        <c:out value="${product.price}"></c:out>
                        <c:out value=","></c:out>
                        </c:forEach>],
                    backgroundColor: [

                        <c:forEach begin="0" end="${requestScope.allProduct.size()}" varStatus="loop">
                            <c:out value="\'rgba(" escapeXml="false"/><c:out value="${random.nextInt(255)}"></c:out>,<c:out value="${random.nextInt(255)}"></c:out>,<c:out value="${random.nextInt(255)}"></c:out>,0.7<c:out value=")\'," escapeXml="false"/>
                        </c:forEach>
                    ],
                    borderWidth: 1
                }]
         },
         options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                 }]
                }
            }
        });
    </script>

</body>
</html>
