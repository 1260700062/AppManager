<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>App信息管理平台 | </title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath }/statics/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath }/statics/build/css/custom.min.css" rel="stylesheet">
    
    <style type="text/css">
    	
    	.fontColor{
    		color: red;
    	}
    </style>
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="${pageContext.request.contextPath }/appList" class="site_title"><i class="fa fa-paw"></i> <span>APP信息管理</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">
                <img src="${pageContext.request.contextPath }/statics/img/img1.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>${sessionScope.DevUser.devname }</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>开发者</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> App应用管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${pageContext.request.contextPath }/appList">App基础信息维护</a></li>
                    </ul>
                  </li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath }/statics/img/img1.jpg" alt="">${sessionScope.DevUser.devname }
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="${pageContext.request.contextPath }/logOut"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>

              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>添加APP信息</h3>
              </div>

            </div>
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_content">
<!-- enctype="multipart/form-data" -->
                    <form id="myForm" class="form-horizontal form-label-left" novalidate action="${pageContext.request.contextPath }/modifyApp/${appInfo.id }" method="post" enctype="multipart/form-data">
					<div hidden class="item form-group">
                     <label  class="control-label col-md-3 col-sm-3 col-xs-12" for="id">软件名称 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="id" value="${appInfo.id }" class="form-control col-md-7 col-xs-12"   name="id"  required="required" type="text" />
                          <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                     <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwarename">软件名称 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="softwarename" value="${appInfo.softwarename }" class="form-control col-md-7 col-xs-12"   name="softwarename"  required="required" type="text" />
                          <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="apkname">APK名称 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="apkname" name="apkname" value="${appInfo.apkname }" required="required" class="form-control col-md-7 col-xs-12"  disabled>
                          <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="supportrom">支持ROM <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="supportrom" name="supportrom"  required="required" value="${appInfo.supportrom }" class="form-control col-md-7 col-xs-12">
                          <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="interfacelanguage">界面语言 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="interfacelanguage" name="interfacelanguage" required="required" value="${appInfo.interfacelanguage }"  class="form-control col-md-7 col-xs-12"><span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwaresize">软件大小(单位：M) <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="number" id="softwaresize" name="softwaresize" required="required" value="${appInfo.softwaresize }"  class="form-control col-md-7 col-xs-12"><span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="downloads">下载次数 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="downloads" type="number" name="downloads"  value="${appInfo.downloads }" class="optional form-control col-md-7 col-xs-12"><span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label for="flatformid" class="control-label col-md-3">所属平台</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <!--<input id="platform" type="text" name="platform" data-validate-length="6,8" class="form-control col-md-7 col-xs-12" required="required">-->
                       		<select id="flatformid" name="flatformid" class="optional form-control col-md-7 col-xs-12">
                       		 <%-- <option value="${appInfo.flatformid }" selected="selected">通用平台</option> --%>
              				<c:forEach items="${allFolatform }" var="flatform" varStatus="varSta">
              					{flatform.valueid == appInfo.flatformid }<c:if test="${flatform.valueid == appInfo.flatformid }">
              					<option value="${flatform.valueid }" selected>${flatform.valueid } ${flatform.valuename } </option>
              					</c:if>
              					<c:if test="${flatform.valueid != appInfo.flatformid }">
                       		 	 <option value="${flatform.valueid }">${flatform.valuename }</option>
              					</c:if>
                       		 </c:forEach>
                       	</select>
                       <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label for="categorylevel1" class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <!--<input id="password2" type="password" name="password2" data-validate-linked="password" class="form-control col-md-7 col-xs-12" required="required">-->
                       		<span id="categorylevel1_id" hidden>${appInfo.categorylevel1 }</span>
                      	<select id="categorylevel1" class="form-control col-md-7 col-xs-12" name="categorylevel1">
                       		<%-- <option  value="${appInfo.categorylevel1 }" hidden>${level1.categoryname }</option> --%>
                       	</select>
                       <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group" >
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="categorylevel2">二级分类 <span class="required">*</span>
                        </label>
                        <span id="categorylevel2_id" hidden>${appInfo.categorylevel2 }</span>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <!--<input type="tel" id="telephone" name="phone" required="required" data-validate-length-range="8,20" class="form-control col-md-7 col-xs-12">-->
                        <select id="categorylevel2" class="form-control col-md-7 col-xs-12" name="categorylevel2">
                       		<%-- <option  value="${appInfo.categorylevel2 }">${level2.categoryname }</option> --%>
                       	</select>
                        <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="categorylevel3">三级分类 <span class="required">*</span>
                        </label>
                        <span id="categorylevel3_id" hidden>${appInfo.categorylevel3 }</span>
                        <div class="col-md-6 col-sm-6 col-xs-12">
						<select id="categorylevel3" class="form-control col-md-7 col-xs-12" name="categorylevel3">
						<%-- <option  value="${appInfo.categorylevel3 }">${level3.categoryname }</option> --%>
                       	</select>
						<span class="fontColor"></span>                        
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="status">APP状态 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                         <input type="text"  disabled class="form-control col-md-7 col-xs-12" value="${statusName }" />
                        <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="appinfo">应用简介 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea id="appinfo" required="required" name="appinfo" rows="5"
                          class="form-control col-md-7 col-xs-12" >${appInfo.appinfo }</textarea>
                        <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="picture">LOGO图片 <span class="required">*</span>
                        </label>
                      <div class="col-md-6 col-sm-6 col-xs-12" id="imageDiv"><img alt="qqq" src="${appInfo.logopicpath }" width="100px" height="100px" id="image">
                      <a  id="del">删除</a>
                      </div>
                      <div class="col-md-6 col-sm-6 col-xs-12" style="display:none" id="pictureDiv">
                        	<input type="file" id="picture"  name="picture" value=""/>
                        <span class="fontColor"></span>
                        </div>
                      </div>
                      <div class="ln_solid"></div>
                      <div class="form-group">
                      	<span  class="fontColor"></span>
                        <div class="col-md-6 col-md-offset-3">
                          <button type="submit" class="btn btn-primary">取消</button>
                          <button id="send" type="submit" class="btn btn-success">保存</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            <!--Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>-->
            APP官方网站
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath }/statics/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath }/statics/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.js"></script>
    <!-- validator -->
    <!--<script src="../vendors/validator/validator.js"></script>-->
<!--自定义js表单验证-->

    <!-- Custom Theme Scripts -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/statics/build/js/modifyApp.js"></script>
    <script src="${pageContext.request.contextPath }/statics/build/js/custom.min.js"></script>
  </body>
</html>