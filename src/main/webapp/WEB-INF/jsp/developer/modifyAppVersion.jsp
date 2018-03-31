<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>App信息管理平台 |</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath }/statics/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link
	href="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<!-- Animate.css -->
<link href="https://colorlib.com/polygon/gentelella/css/animate.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath }/statics/build/css/custom.min.css"
	rel="stylesheet">

<style type="text/css">
.fontColor {
	color: red;
}
</style>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<!--<div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentellela Alela!</span></a>
            </div>-->

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="images/img.jpg" alt="..."
								class="img-circle profile_img">
						</div>
						<!--登录用户显示-->
						<div class="profile_info">
							<span>欢迎,</span>
							<h2>John Doe</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>
								<div class="clearfix"></div>
							</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-edit"></i> App管理 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="addApp.html">添加APP</a></li>
										<li><a href="showAllApp.html">所有app</a></li>
									</ul></li>
							</ul>
						</div>

					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
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
						<li class=""><a href="javascript:;"
							class="user-profile dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <img src="images/img.jpg" alt="">John
								Doe <span class=" fa fa-angle-down"></span>
						</a>
							<ul class="dropdown-menu dropdown-usermenu pull-right">
								<li><a href="javascript:;"> Profile</a></li>
								<li><a href="javascript:;"> <span
										class="badge bg-red pull-right">50%</span> <span>Settings</span>
								</a></li>
								<li><a href="javascript:;">Help</a></li>
								<li><a href="login.html"><i
										class="fa fa-sign-out pull-right"></i> Log Out</a></li>
							</ul></li>

						<li role="presentation" class="dropdown"><a
							href="javascript:;" class="dropdown-toggle info-number"
							data-toggle="dropdown" aria-expanded="false"> <i
								class="fa fa-envelope-o"></i> <span class="badge bg-green">6</span>
						</a>
							<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
								role="menu">
								<li><a> <span class="image"><img
											src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
												Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be
											do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"><img
											src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
												Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be
											do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"><img
											src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
												Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be
											do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"><img
											src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
												Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be
											do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li>
									<div class="text-center">
										<a> <strong>See All Alerts</strong> <i
											class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>
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
							<h3>历代版本信息</h3>
						</div>
						
						<div class="clearfix"></div>

						<div class="row">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="x_panel">
									<div class="x_content">
									<table border="1px gray" bordercolor="lightgrey" style="text-align: center">
										<tr class="item ">
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">软件名称</th>
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">版本号</th>
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">版本大小(单位：M)</th>
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">发布状态</th>
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">apk文件下载</th>
											<th style="text-align: center" class="col-lg-2 col-md-2 col-xs-12">最新更新时间</th>
										</tr>
										<c:forEach items="${appVersions }" var="app" varStatus="varSta">
										<c:set var="key" value="${app.publishstatus }"></c:set>
											<tr>
											<td>${appInfo.softwarename }</td>
											<td>${app.versionno }</td>
											<td>${app.versionsize }</td>
											<td>${publishMap[key] }</td>
											<td><a title="点击下载" style="cursor: pointer;" class="download" value="${app.id }">${app.apkfilename }</a></td>
											<td>${app.modifydate }</td>
											</tr>
										</c:forEach>
									</table>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="">
						<div class="page-title">
							<div class="title_left">
								<h3>修改APP版本信息</h3>
							</div>
						</div>
						<div class="clearfix"></div>

						<div class="row">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="x_panel">
									<div class="x_content">
<!--form start  -->
										<form class="form-horizontal form-label-left" novalidate
											action="${pageContext.request.contextPath }/modifyAppVersion"
											method="post" enctype="multipart/form-data" id="myForm">
											<div class="item form-group" hidden>
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="a_id">id<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<input id="id"
														class="form-control col-md-7 col-xs-12" name="id"
														 required="required" type="text" value="${modifyAppVersion.id }"/> 
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="versionno">版本号 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<input id="versionno" disabled
														class="form-control col-md-7 col-xs-12" name="versionno"
														 required="required" type="text" value="${modifyAppVersion.versionno }"/> <span
														class="fontColor"></span>
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="">版本大小 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<input type="number" id="versionsize" name="versionsize"
														required="required"
														class="form-control col-md-7 col-xs-12" value="${modifyAppVersion.versionsize }">
													<span class="fontColor"></span>
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="publishstatus">发布状态 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<!-- <select id="status" class="form-control col-md-7 col-xs-12">
														<option style="text-align: center;" value="3">预发布</option>
													</select>  -->
													<input type="text" hidden value="${modifyAppVersion.publishstatus }" name="publishstatus">
													<span>${publishStatusName }</span>
													<span class="fontColor"></span>
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="versioninfo">版本简介 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<input type="text" id="versioninfo" name="versioninfo"
														value="${modifyAppVersion.versioninfo }" required="required"
														class="form-control col-md-7 col-xs-12"><span
														class="fontColor"></span>
												</div>
											</div>
											<div class="item form-group">
												<label class="control-label col-md-3 col-sm-3 col-xs-12"
													for="apk">apk文件 <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12"  id="apkFileNameDiv">
												<span id="apkFileName">${modifyAppVersion.apkfilename }</span>
												<a style="cursor: pointer;" value="${modifyAppVersion.id }" class="download">下载</a>&nbsp;&nbsp; &nbsp;&nbsp;<a style="cursor: pointer;" id="del">删除</a>
												<span class="fontColor">${modifyApkError }</span>
												</div >
												<div class="col-md-6 col-sm-6 col-xs-12" style="display:none" id="apkNameDiv">
													<input type="file" id="apk" name="apk" />
												</div>
												  
											</div>
											<div class="ln_solid"></div>
											<div class="form-group">
												<span class="fontColor"></span>
												<div class="col-md-6 col-md-offset-3">
													<button onclick="window.history.back(-1)" class="btn btn-primary">取消</button>
													<button id="send" type="submit" class="btn btn-success">提交</button>
												</div>
											</div>
											<input type="hidden" id="appId" name="appId"
												value="${appId }" />
<!--form start  -->
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
		<input type="hidden" id="appId" name="appId"
			value="${sessionScope.appId }" />

		<!-- jQuery -->
		<script
			src="${pageContext.request.contextPath }/statics/vendors/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap -->
		<script
			src="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script
			src="${pageContext.request.contextPath }/statics/vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script
			src="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.js"></script>
		<!-- validator -->
		<!--<script src="../vendors/validator/validator.js"></script>-->
		<!--自定义js表单验证-->
		<%-- <script type="text/javascript"
			src="${pageContext.request.contextPath }/statics/build/js/addVersion.js"></script> --%>
			<script type="text/javascript"
			src="${pageContext.request.contextPath }/statics/build/js/modifyAppVersion.js"></script>
		<!-- Custom Theme Scripts -->
		<script
			src="${pageContext.request.contextPath }/statics/build/js/custom.min.js"></script>
</body>
</html>