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
                <p style="color:#ffffff" id="isupdated"></p>

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

                <form action="" method="post">

                    <div class="col-sm-5 col-xs-6 tital " >First Name:</div>

                    <c:if test="${ sessionScope.user == requestScope.user }">
                        <input class="form-control col-sm-7 col-xs-6" type="text" id="firstname" name="firstname" value="<c:out value="${requestScope.user.profile.firstName}"/>"/>
                    </c:if>
                    <c:if test="${ sessionScope.user != requestScope.user }">
                        <div class="col-sm-7 col-xs-6 "><c:out value="${requestScope.user.profile.firstName}"/></div>
                    </c:if>

                       <div class="clearfix"></div>
                    <div class="bot-border"></div>

                    <div class="col-sm-5 col-xs-6 tital " >Last Name:</div>

                    <c:if test="${ sessionScope.user == requestScope.user }">
                        <input class="form-control col-sm-7 col-xs-6" type="text" id="lastname" name="lastname" value="<c:out value="${requestScope.user.profile.lastName}"/>"/>
                    </c:if>
                    <c:if test="${ sessionScope.user != requestScope.user }">
                        <div class="col-sm-7 col-xs-6 "><c:out value="${requestScope.user.profile.lastName}"/></div>
                    </c:if>

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
            <c:if test="${ sessionScope.user == requestScope.user }">
                  <input style="margin-left:auto; display: inherit;" class="btn btn-primary ps13-text-shadow ps13-button navbar-btn my-2 my-sm-0" type="button" value="Update Profile" onclick="updProfileAjax()"/>
            </c:if>
            </form>
        </div>
    </div>


        <script
                 src="https://code.jquery.com/jquery-3.2.1.min.js"
                 integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                 crossorigin="anonymous">
         </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>x


    <script>
          $(function() {
              $('#profile-image1').on('click', function() {
                  $('#profile-image-upload').click();
              });
          });
    </script>


</div>



<script  type="application/javascript">
    function updProfileAjax() {
        var data_json = {
            "firstname": document.getElementById("firstname").value,
            "lastname": document.getElementById("lastname").value,
        };
        $.ajax({
            type: "POST",
            url: "upd_profile",
            data: data_json,
            dataType: 'json',
            success: function(response) {
                document.getElementById("firstname").value = response.firstname;
                document.getElementById("lastname").value = response.lastname;
                document.getElementById("isupdated").innerText = "Profile updated";
            },
            error: function(data) {
                console.log(data);
            }
        });
    };
</script>