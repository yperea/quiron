<%@include file="../shared/tag-libs.jsp"%>
<c:set var="root" value="/quiron" scope="session" />

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../shared/meta.jsp"%>
    <link rel="icon" href="${root}/style/img/QuironIcon75x75.png">

    <title>404</title>

    <%@include file="../shared/cdn-css.jsp"%>

    <!-- Bootstrap validator CSS -->
    <link href="${root}/style/css/form-validation.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap Signin CSS -->
    <link href="${root}/style/css/signin.css" rel="stylesheet" type="text/css" />
    <!-- Project Quiron Style Sheet -->
    <link href="${root}/style/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body class="text-center">

    <form class="form-signin" action="/quiron" method="GET">
        <img class="mb-4" src="${root}/style/img/QuironIcon75x75.png" alt="" width="72" height="72">
        <h1 class="h1 mb-3 display-1 font-weight-normal text-warning">404</h1>
        <p>The page you were looking for couldn't be found.</p>
        <a class="btn btn-lg btn-secondary btn-block" href="/quiron">Take me Home</a>
    </form>

<%@include file="../shared/cdn-jss.jsp"%>

<!-- Bootstrap Core JavaScript -->
<script src="${root}/style/js/holder.min.js"></script>
</body>
</html>

