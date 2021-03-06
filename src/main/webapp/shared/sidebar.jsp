<!-- Sidebar -->
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <%--Type : [${personType}]--%>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="${root}">
                            <span data-feather="home"></span>
                            Home <!--<span class="sr-only">(current)</span>-->
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${title == 'My Account'}">active</c:if>" href="${root}/account">
                            <span data-feather="user"></span>
                            My Account
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${title == 'My Visits'}">active</c:if>" href="${root}/care/visits">
                            <span data-feather="clipboard"></span>
                            My Visits
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${title == 'My Treatments'}">active</c:if>" href="${root}/care/treatments">
                            <span data-feather="activity"></span>
                            My Treatments
                        </a>
                    </li>
                </ul>

                <!--
                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Saved reports</span>
                    <a class="d-flex align-items-center text-muted" href="#">
                        <span data-feather="plus-circle"></span>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Current month
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Last quarter
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Social engagement
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Year-end sale
                        </a>
                    </li>
                </ul>
                -->
            </div>
        </nav>
    </div>
</div>

