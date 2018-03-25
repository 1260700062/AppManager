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
	
	$("#softwarename").blur(function(){
		var  name = $("#softwarename");
		if(name.val() == null || name.val() == "") {
			$(this).next().html(message.empty);
			nameSta = false;
		}else {
			$(this).next().html("");
			nameSta = true;
		}
	});
	
	$("#apkname").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			apkSta = false;
		}else {
			$(this).next().html("");
			//ajax 验证唯一性
			var name = $(this).val();
			var $span = $(this).next();
			alert(name.trim());
			$.ajax({
				url:"virafyApkName",
				type:"get",
				data:{apkname:name},
				datatype:"text",
				success:function(data){
					if(data == "true"){
						apkSta = true;
					}else {
						apkSta = false;
						$span.html("用户名已存在！");
						alert("Aaa");
					}
				},
				error: function(){
					alert("error~~~~~~");
				}
			});
			
		}
	});
	
	$("#supportrom").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			romSta = false;
		}else {
			$(this).next().html("");
			romSta = true;
		}
	});
	
	$("#interfacelanguage").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			languageSta = false;
		}else {
			$(this).next().html("");
			languageSta = true;
		}
	});
	
	$("#softwaresize").blur(function(){
		if($(this).val() == null || $(this).val() == "") {
			$(this).next().html(message.empty);
			appSizeSta = false;
		}else {
			$(this).next().html("");
			appSizeSta = true;
		}
	});
	
	$("#downloads").blur(function(){
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
//	获取一级分类
	$.ajax({
		url:"categoryLevel",
		type:"get",
		data:{},
		datatype:"json",
		success:function(data){
			for(var i=0; i<data.length; i++) {
				var $option1 = "<option value="+data[i].id+">"+data[i].categoryname+"</option>";
				$("#categorylevel1").append($option1);
			}
		},
		error:function(){
			alert("error");
		}
	});
	
	$("#categorylevel1").change(function(){
		var parentId = $("#categorylevel1").val();
		$.ajax({
			url:"categoryLevel",
			type:"get",
			data:{id:parentId},
			datatype:"json",
			success:function(data){
				$("#categorylevel2").find("option").remove();
				$("#categorylevel3").find("option").remove();
				$("#categorylevel2").append("<option value='0'></option>");
				for(var i=0; i<data.length; i++) {
					var $option2 = "<option value="+data[i].id+">"+data[i].categoryname+"</option>";
					$("#categorylevel2").append($option2);
				}
			},
			error:function(){
				alert("error!!~~~~");
			}
		});
	});
	
	$("#categorylevel2").change(function(){
		var parentId = $("#categorylevel2").val();
		$.ajax({
			url:"categoryLevel",
			type:"get",
			data:{id:parentId},
			datatype:"json",
			success:function(data){
				$("#categorylevel3").find("option").remove();
				$("#categorylevel2 option[value='0']").remove();
				for(var i=0; i<data.length; i++) {
					var $option3 = "<option value="+data[i].id+">"+data[i].categoryname+"</option>";
					$("#categorylevel3").append($option3);
				}
			},
			error:function(){
				alert("error!!~~~~");
			}
		});
	});
})
