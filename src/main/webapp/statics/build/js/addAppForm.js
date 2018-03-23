$(function(){
	var message = {
		empty : "输入内容不能为空！",
		aa : "格式不对~"
	};
	
	//表单元素的验证是否通过的状态
	var sub = false;
	var nameSta = false;
	var apkSta = false;
	var romSta = false;
	var languageSta = false;
	var appSizeSta = false;
	var countSta = false;
	var nameSta = false;
	
	$("#name").blur(function(){
		var  name = $("#name");
		if(name.val() == null || name.val() == "") {
			$(this).next().html(message.empty);
			nameSta = false;
		}else {
			$(this).next().html("");
			nameSta = true;
		}
	});
	
	$("#apk").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			apkSta = false;
		}else {
			$(this).next().html("");
			//ajax 验证唯一性
			
			apkSta = true;
		}
	});
	
	$("#rom").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			romSta = false;
		}else {
			$(this).next().html("");
			romSta = true;
		}
	});
	
	$("#language").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			languageSta = false;
		}else {
			$(this).next().html("");
			languageSta = true;
		}
	});
	
	$("#appSize").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			appSizeSta = false;
		}else {
			$(this).next().html("");
			appSizeSta = true;
		}
	});
	
	$("#count").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			countSta = false;
		}else {
			$(this).next().html("");
			countSta = true;
		}
	});
	
	$("form").submit(function(){
		if(nameSta && apkSta &&romSta &&languageSta &&appSizeSta &&countSta &&nameSta) {
			return true;
		}else {
			$("#send").parent().prev().html("请填写完整信息");
			return false;
		}
		
	});
})
