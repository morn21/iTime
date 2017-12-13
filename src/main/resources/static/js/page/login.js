
//点击登录按钮事件
$("#loginButton").click(function(){
    $.ajax({
        type : "POST",  //提交方式
        url : "/user/loginUser.json",//路径
        data : {
            name : $("#userName").val(),
            password : $("#userPassword").val()
        },
        dataType : "json",
        success :  function(result){
            if(result.success){
                window.location.href = "/index";
            } else{
                $("#errorMsgDiv").html(result.msg);
            }
        }
    });
});