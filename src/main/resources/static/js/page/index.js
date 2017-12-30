
//开始计时按钮事件
$("#startTimingButton").click(function(){
    $("#startTimingButton").css("display","none");
    $.ajax({
        type : "POST",  //提交方式
        url : "/user/startTiming.json",//路径
        data : {},
        dataType : "json",
        success :  function(result){
            if(result.success){
                window.location.reload();
            }
        }
    });
});

$("#endTimingButton").click(function(){
    window.location.href = "/timeRecord";
});

var ingStartTimestamp = 0;

//执行计时
var executeTimingSecond = function(){
    var secondCount = parseInt((basic.getNowTimestamp() - ingStartTimestamp) / 1000);
    $("#timingSecond").html(basic.getTimeStr(secondCount));
    setTimeout(executeTimingSecond,1000);
};

//初始化用户公共部分
var iniUserPublic = function(){
    $.ajax({
        type : "POST",  //提交方式
        url : "/user/findCurrentUser.json",//路径
        data : {},
        dataType : "json",
        success :  function(result){
            if(result.success){
                var data = result.data;
                if(data.user.isIng == 0){
                    $("#startTimingButton").css("display","inline");
                } else {
                    $("#endTimingButton").css("display","inline");
                    ingStartTimestamp = data.user.ingStartTime;
                    executeTimingSecond();//执行计时
                }
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