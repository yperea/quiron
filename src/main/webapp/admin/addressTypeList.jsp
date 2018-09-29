<html>
<%@include file="../shared/head.jsp"%>

<body>

<div class="container" id="main">
    <div class="text-center">
        <h2>${title}</h2>
    </div>

    <div class="row">
        <div class="container">

            <form class="needs-validation"
                  method="GET"
                  action="searchUser"
                  novalidate>


                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Search term" id="searchTerm" name="searchTerm" required />
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="submit" id="btnGetUsers" name="btnGetUsers">Get Users</button>
                    </div>
                    <div class="invalid-feedback" style="width: 100%;">
                        Please enter a search term
                    </div>

                </div>

            </form>
        </div>
    </div>

    <div class="row">

        <h2>Search Results</h2>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Created</th>
                    <th>Modified</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="addressType" items="${addressTypes}">
                    <tr>
                        <td>${addressType.id}</td>
                        <td>${addressType.name}</td>
                        <td>${addressType.createdDate}</td>
                        <td>${addressType.modifiedDate}</td>
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
