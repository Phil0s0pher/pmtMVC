<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="container">
    <%--@elvariable id="registerDTO" type="com.epam.passwordManagerMVC.dto"--%>

    <form:form  cssClass="box card" method="POST" class="form-signin" action="${pageContext.request.contextPath}/registerUser" modelAttribute="registerDTO">
        <h4>Welcome to Password Management Tool</h4>
        <h2 class="form-heading text-center">Create your account</h2>
        <div class="form-group">
            <form:input type="text" path="accountName" class="form-control" placeholder="AccountName"
                        autofocus="true"/>
            <form:errors path="accountName" cssClass="has-error"></form:errors>
        </div>
        <div class="form-group">
            <form:input type="text" path="userName" class="form-control" placeholder="UserName"
                        autofocus="true"/>
            <form:errors path="userName" cssClass="has-error"></form:errors>
        </div>
        <div class="form-group">
            <form:input type="password" path="password" class="form-control" placeholder="Password"/>
            <form:errors path="password" cssClass="has-error"></form:errors>
        </div>
        <span class="has-error">${error}</span>
        <button  type="submit">Submit</button>
    </form:form>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>


