

//点击返回首页按钮事件
$("#userPublic_returnIndexButton").click(function(){
    window.location.href = "/index";
});

//点击退出登录按钮事件
$("#userPublic_logoutButton").click(function(){
    $.ajax({
        type : "POST",  //提交方式
        url : "/user/logoutUser.json",//路径
        data : {},
        dataType : "json",
        success :  function(result){
            if(result.success){
                window.location.reload();
            }
        }
    });
});

//初始化用户公共部分
var iniUserPublic = function(){
    $.ajax({
        type : "POST",  //提交方式
        url : "/user/findCurrentUser.json",//路径
        data : {},
        dataType : "json",
        success :  function(result){
            if(result.success){
                $("#userPublic_userName").html(result.data.user.name);
            } else{
                window.location.href = "/login";
            }
        }
    });
};

//初始化
$(function () {
    iniUserPublic();
});