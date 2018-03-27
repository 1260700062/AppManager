$(function(){
	
	$("#del").click(function(){
		$("#imageDiv").hide();
		$("#image").attr("src","");
		$("#pictureDiv").show();
	});
	$("#picture").change(function(){
		var path = $(this).val();
		alert(path);
		/* 
		        var $file = $(this);  
		        var file = $file[0];  
		  
		        var url = null;  
	            if (window.createObjcectURL != undefined) {  
	                url = window.createOjcectURL(file);  
	            } else if (window.URL != undefined) {  
	                url = window.URL.createObjectURL(file);  
	            } else if (window.webkitURL != undefined) {  
	                url = window.webkitURL.createObjectURL(file);  
	            }  
		   alert(url);*/
		
		$.ajax({
			url:"modifyPic",
			type:"get",
			data:{path:path},
			datatype:"json",
			success:function(data){
				alert(data);
				$("#imageDiv").html(data);
			},
			error:function(){
				alert("ERROR~~");
			}
		});
		$("#pictureDiv").hide();
		$("#image").attr("src");
		$("#imageDiv").show();
	});
})
