<html>
<%@include file="../shared/head.jsp"%>

<body>

<div class="container" id="main">
    <div class="text-center">
        <h2>${title}</h2>
    </div>

    <div class="row">

        <h2>Search Results</h2>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Created</th>
                    <th>Modified</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="country" items="${countries}">
                    <tr>
                        <td>${country.id}</td>
                        <td>${country.code}</td>
                        <td>${country.name}</td>
                        <td>${country.createdDate}</td>
                        <td>${country.modifiedDate}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="../shared/footer.jsp"%>
</body>
</html>
