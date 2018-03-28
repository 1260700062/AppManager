$(function(){
	var message = {
		empty : "输入内容不能为空！",
		aa : "格式不对~"
	};
	var appId = $("#appId").val();
	//表单元素的验证是否通过的状态
	var vno = false;
	var vsize = false;
	var vinfo = false;
	
	$("#versionno").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			vno = false;
		}else {
			$(this).next().html("");
			//ajax 验证唯一性
			var verNo = $(this).val();
			var $span = $(this).next();
			$.ajax({
				url:"/AppManager/virafyVersionNo",
				data:{id:appId,versionNo:verNo},
				datatype:"json",
				success:function(data){
					if(data.length == 0){
						vno = true;alert(vno);
					}else {
						vno = false;alert(vno);alert(data.length);
						$span.html("版本号已存在！");
					}
				},
				error: function(){
					alert("error~~~~~~");
				}
			});
			
		}
	});
	
	$("#versionsize").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			vsize = false;
		}else {
			$(this).next().html("");
			vsize = true;
		}
	});
	
	$("#versioninfo").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			vinfo = false;
		}else {
			$(this).next().html("");
			vinfo = true;
		}
	});
	
	$("form").submit(function(){
		if(vno &&vsize &&vinfo) {
			return true;
		}else {
			$("#send").parent().prev().html("请填写完整信息");
			return false;
		}
		
	});
})
