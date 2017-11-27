<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">

<div class="container">
    <div class="panel panel-default ps13-forum-block ps-13-box-shadow">
    <div class="panel-heading">  <h2 class="head ps13-text-shadow">User Profile</h2></div>
        <div class="panel-body">

            <div class="box box-info">
                <div class="box-body">
                <div class="flex-container">
                <div class="first">
                    <div>
                        <div  align="center"> <img alt="User Pic" src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg" id="profile-image1" class="img-circle img-responsive">

                                <input id="profile-image-upload" class="hidden" type="file">
                                <!--Upload Image Js And Css-->







                        </div>

                        <br>

                        <!-- /input-group -->
                    </div>
                    <div>
                        <h4 style="color:#00b1b1;"><c:out value="${requestScope.user.username}"/></h4></span>
                        <span><p>There will be forum tag</p></span>
                    </div>
                </div>
                <div class="second">
                    <div class="clearfix"></div>
                        <hr style="margin:5px 0 5px 0;">


                    <div class="col-sm-5 col-xs-6 tital " >First Name:</div>
                    <div class="col-sm-7 col-xs-6 "><c:out value="${requestScope.user.profile.firstName}"/></div>
                         <div class="clearfix"></div>
                    <div class="bot-border"></div>

                    <div class="col-sm-5 col-xs-6 tital " >Last Name:</div>
                    <div class="col-sm-7"><c:out value="${requestScope.user.profile.lastName}"/></div>
                      <div class="clearfix"></div>
                    <div class="bot-border"></div>

                    <div class="col-sm-5 col-xs-6 tital " >Date Of Joining:</div>
                    <div class="col-sm-7"><c:out value="${requestScope.user.profile.registrationDate}"/></div>
                      <div class="clearfix"></div>
                    <div class="bot-border"></div>


                    <!-- /.box-body -->
                </div>
                </div>
                </div>
                <!-- /.box -->

                    <h3 class="head ps13-text-shadow">User Rating</h3>
                    <div class="rating">
                        <div style="width: <c:out value="${requestScope.user.profile.rating}"/>%"></div>
                    </div>
            </div>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>

    <script>
              $(function() {
    $('#profile-image1').on('click', function() {
        $('#profile-image-upload').click();
    });
});
              </script>


</div>