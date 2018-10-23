<nav class="navbar navbar-default fixed-top navbar-expand-lg navbar-light bg-light fixed-top">

    <div class="container">

        <a class="navbar-brand nav-link" href="${root}">
            <img src="${root}/style/img/QuironLogo140.png" class="my-logo"/>
        </a>

        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <!-- Left menu navigation bar -->
            <!--
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="./">Home <span class="sr-only">(current)</span></a>
                </li>


                <li class="nav-item">
                    <a class="nav-link" href="dashboard.php">Dashboard</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="gallery.php">Gallery</a>
                </li>
            </ul>
            -->
            <!-- Right menu navigation bar -->
            <ul class="navbar-nav ml-auto">
                <!--
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Blog
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                        <a class="dropdown-item" href="#">Blog Home 1</a>
                        <a class="dropdown-item" href="#">Blog Home 2</a>
                        <a class="dropdown-item" href="#">Blog Post</a>
                    </div>
                </li>
                -->

                <c:choose>
                    <c:when test="${account.signed != null}" >
                        <li class="dropdown user-menu">
                            <a class="nav-link" href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img alt="Profile Picture" class="user-image" src="${root}/style/img/male.jpg" />
                                &nbsp;<span class="hidden-xs"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="my-user-header">
                                    <img alt="Profile Picture"
                                         class="img-circle"
                                         src="${root}/style/img/male.jpg" />
                                    <p>
                                        ${account.person.firstName} ${account.person.lastName}
                                        <br/><small>(${account.user.username})</small>
                                    </p>
                                </li>

                                <li class="my-user-footer">

                                    <div class="pull-left">
                                        <a href="${root}/patient/profile"
                                           class="btn btn-default"><i class="fa fa-user"></i>&nbsp;Profile</a>
                                    </div>

                                    <div class="pull-right">
                                        <a href="${root}/account/logout"
                                           class="btn btn-default"><i class="fa fa-power-off"></i>&nbsp;Log Out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="${root}/account/signup">Sign&nbsp;Up</a></li>
                        <li class="nav-item"><a class="nav-link" href="${root}/patient/profile">Log&nbsp;In</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>


        </div>
    </div>

</nav>