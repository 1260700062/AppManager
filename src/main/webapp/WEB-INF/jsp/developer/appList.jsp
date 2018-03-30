<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>APP BMS! | </title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath }/statics/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="${pageContext.request.contextPath }/statics/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath }/statics/build/css/custom.min.css" rel="stylesheet">
</head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>APP信息管理</span></a>
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
        
        <div id="top-app-form" style="position: absolute;top: 150px;right:700px;">
					<form id="select_app" method="post" action="${pageContext.request.contextPath }/appSearch" >
						软件名称:<input type="text" name="appName" /> 
						所属平台:
						<select id="flatform" name="flatform">
							<option selected value="0">--请选择--</option>
						   	<option value="1">手机</option>
						   	<option value="2">平板</option>
						   	<option value="3">通用</option>
						</select>
						一级分类: 
						<select id="appLevel1" name="category1" onchange="getListLevel2()">
							<option selected>--请选择--</option>
							<c:forEach var="appLevel1" items="${appLevel1}">
						   		<option value="${appLevel1.id}">${appLevel1.categoryname}</option>
						   </c:forEach>
						</select>
						二级分类: 
						<select id="appLevel2" name="category2" onchange="getListLevel3()">
							<option selected="selected">--请选择--</option>
						</select> 
						三级分类: 
						<select id="appLevel3" name="category3">
							<option selected="selected" value="0">--请选择--</option>
						</select>
						<input class="btn btn-xs" type="submit" value="查询" />
					</form>
				</div>
        <div class="col-md-12 col-sm-12 col-xs-12"
				style="width: 1200px;position: absolute;top: 230px;right:400px;">
					<button class="btn btn-sm btn-success" style="width:100px"><a href="${pageContext.request.contextPath }/addAppPage">添加app</a></button>
				<div class="x_panel">
					<div class="x_content">

						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th><input type="checkbox" id="check-all" class="flat">
										</th>
										<th class="column-title">软件名称</th>
										<th class="column-title">ADK名称</th>
										<th class="column-title">软件大小(单位:M)</th>
										<th class="column-title">所属分类(一级,二级,三级)</th>
										<th class="column-title">状态</th>
										<th class="column-title no-link last"><span class="nobr">下载次数</span>
										<th class="column-title no-link last"><span class="nobr">最新版本号</span>
										<th class="column-title no-link last"><span class="nobr">操作</span>
										</th>
									</tr>
								</thead>
								<c:forEach items="${appList }" var="appinfo">
								<c:set var="version" value="${appinfo.versionid }"></c:set>
									<tbody>
										<tr class="even pointer">
											<td class="a-center "><input type="checkbox"
												class="flat" name="table_records"></td>
											<td class=" ">${appinfo.softwarename }</td>
											<td class=" ">${appinfo.apkname }</td>
											<td class=" ">${appinfo.softwaresize }</td>
											<td class=" ">${level1 }-${level2 }-${level3 }</td>
											
											<c:if test="${appinfo.status==1 }">
												<td class=" ">待审核</td>
											</c:if>
											<c:if test="${appinfo.status==2 }">
												<td class=" ">审核通过</td>
											</c:if>
											<c:if test="${appinfo.status==3 }">
												<td class=" ">审核不通过</td>
											</c:if>
											<c:if test="${appinfo.status==4 }">
												<td class=" ">已上架</td>
											</c:if>
											<c:if test="${appinfo.status==5 }">
												<td class=" ">已下架</td>
											</c:if>
											<td class=" ">${appinfo.downloads }</td>
											<c:if test="${empty appinfo.versionid}">
												<td class="">暂无版本信息</td>
											</c:if>
											<c:if test="${!empty appinfo.versionid}">
												<td class="">${map[version] }</td>
											</c:if>
											<td class=" ">
												<div class="btn-group">
												    <button type="button" class="btn btn-success dropdown-toggle btn-xs" data-toggle="dropdown">点击操作
												        <span class="caret"></span></button>
												    <ul class="dropdown-menu" role="menu">
												    <c:if test="${appinfo.status==2 }">
												    	<li>
												            <a href="${pageContext.request.contextPath }/changeStatus/${appinfo.id}">上架</a>
												        </li>
												    </c:if>
												    <c:if test="${appinfo.status==4 }">
												    	<li>
												            <a href="${pageContext.request.contextPath }/changeStatus/${appinfo.id}">下架</a>
												        </li>
												    </c:if>
												        <li>
												            <a href="${pageContext.request.contextPath }/modifyAppPage/${appinfo.id}">修改信息</a>
												        </li>
												        <li>
												            <a href="${pageContext.request.contextPath }/showAppInfo/${appinfo.id}">查看</a>
												        </li>
												        <li>
												            <a href="${pageContext.request.contextPath }/addVersionPage/${appinfo.id}">增加版本</a>
												        </li>
												        <li>
												            <a href="#">修改版本</a>
												        </li>
												        <li>
												            <a href="${pageContext.request.contextPath }/deleteApp/${appinfo.id}">删除</a>
												        </li>
												    </ul>
												</div>
											</td>
											<div class="btn-group">
											</div>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>

     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="appId" name="appId" value="${appinfo.id}"/>

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
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
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath }/statics/vendors/iCheck/icheck.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath }/statics/build/js/custom.min.js"></script>
    
    <script type="text/javascript">
    	function getListLevel2(){ 
    		var path = $("#path").val();
    		var appLevel1 = $("#appLevel1").val();
    		$("#appLevel2").empty();
    		$("#appLevel3").empty();
    		$("#appLevel2").append("<option value = \"--请选择--\">--请选择--</option>");
    		$("#appLevel3").append("<option value = \"--请选择--\">--请选择--</option>");
    		if(appLevel1 != "--请选择--"){
    				$.post({
        				type:"post",
        				url:path+"/getAppList",
        				data:{"parentId":appLevel1},
        				dataType:"json",
        				success:function(data){
        					for(i=0;i<data.length;i++){
        						$("#appLevel2").append("<option value = \"" + data[i].id + "\">"+ data[i].categoryname +"</option>");
        					}
        				},
        				error:function(data){
        					alert($("#appLevel1").val());
        				}
        			});	
    		}
    	} 
    	 
    	function getListLevel3(){
     		var appLevel2 = $("#appLevel2").val();
     		var path = $("#path").val();
    		$("#appLevel3").empty();
    		$("#appLevel3").append("<option value = \"--请选择--\">--请选择--</option>");
     		
     		if(appLevel2 != "--请选择--"){
     			$.post({
     				type:"post",
     				url:path+"/getAppList",
     				data:{"parentId":appLevel2},
     				dataType:"json",
     				success:function(data){
     					for(i=0;i<data.length;i++){
     						$("#appLevel3").append("<option value = \"" + data[i].id + "\">"+ data[i].categoryname +"</option>");
     					}
     				},
     				error:function(data){
     					alert("bbbbb");
     				}
     			});	
     		}
     	} 
    	
    	/* $(".del").click(function(){
    		var appId = $("#appId");
    		var con = confirm("是否确认删除？");
    		if(con == true){
    			alert("a");
    			$.post({
    				type:"post",
     				url:path+"/deleteApp",
     				data:{"appId":appId},
     				dataType:"text",
     				success:function(data){
     					alert("aaa");
     				},
     				error:function(data){
     					alert("bbbbb");
     				}
    			})
    		}
    	}) */
    	
    </script>
  </body>
</html>