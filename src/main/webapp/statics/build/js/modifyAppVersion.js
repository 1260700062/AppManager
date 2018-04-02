$(document).ready(function(){
	$("#del").click(function(){
		$("#apkFileNameDiv").hide();
		$("#apkNameDiv").show();
	});
	
	$(".download").click(function(){
		alert("aaaa");
		alert($(this).attr("value"));
		var id = $(this).attr("value");
		
		$.ajax({
			url:"../downloadApk",
			type:"get",
			data:{id:id},
			datatype:"json",
			success:function(data){
				alert(data);
				if(data == 0) {
					alert("下载成功");
				}else if(data == 1){
					alert("你选择下载的路径不存在");
				}else if(data == 2) {
					alert("文件没有正常关闭");
				}else if(data == -1) {
					alert("文件不存在或已被删除");
				}else if(data == -2) {
					alert("文件读取失败");
				}
			},
			error:function(){
				alert("error!!!~~~");
			}
		});
	});
	
	var versionSizeStatus = true;
	var versionInfoStatus = true;
	
	$("#versioninfo").blur(function(){
		var $versioninfo = $("#versioninfo").val();
		if($versioninfo == null || $versioninfo == "") {
			versionSizeStatus = false;
			$(this).next().html("内容不能为空");
		}else {
			versionInfoStatus = true;
			$(this).next().html("");
		}
	});
	
	
	$("#versionsize").blur(function(){
		var $versionsize = $("#versionsize").val();
		if($versionsize == null || $versionsize == "") {
			versionSizeStatus = false;
			$(this).next().html("内容不能为空");
		}else {
			versionSizeStatus = true;
			$(this).next().html("");
		}
	});
	
	$("#myForm").submit(function(){
		var flag = false;
	
		var dis = $("#apkNameDiv").css("display");
		var apk = $("#apk").val();
		if(dis == "block" && versionSizeStatus && versionSizeStatus) {
			if(apk == null || apk == "") {
				alert(apk == "");
				return false;
			}else {
				return true;
			}
		}else if(dis == "none") {
			return true;
		}
		return false;
	});
})