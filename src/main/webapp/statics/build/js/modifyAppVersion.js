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
			url:"downloadApk",
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
})