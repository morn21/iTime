
var basic = {
    /**获得当前时间戳*/
    getNowTimestamp : function(){
        var nowTime = new Date().getTime();
        if(basic["timeDifference"] == undefined){
            $.ajax({
                type : "POST",  //提交方式
                url : "/util/findCurrentTime.json",//路径
                async: false,//异步false
                data : {},
                dataType : "json",
                success :  function(result){
                    if(result.success){
                        var data = result.data;
                        basic["timeDifference"] = nowTime - data.nowDate;
                    }
                }
            });
        }
        return nowTime + basic["timeDifference"];
    },
    /**获得时间字符串*/
    getTimeStr : function(secondCount){
        var fillNum = function(num){//填充补全两位数字
            if(num < 10){
                num = "0" + num;
            }
            return num + "";
        };
        var daySecondCount = 60 * 60 * 24;
        var hourSecondCount = 60 * 60;
        var minuteSecondCount = 60;

        var timeStr = "";//用于返回的时间字符串
        var dayCount = parseInt(secondCount  / daySecondCount);
        secondCount -= dayCount * daySecondCount;//减去天的秒数
        timeStr += fillNum(dayCount) + ":";//天
        var hourCount = parseInt(secondCount / hourSecondCount);
        secondCount -= hourCount * hourSecondCount;//减去时的秒数
        timeStr += fillNum(hourCount) + ":";//时
        var minuteCount = parseInt(secondCount / minuteSecondCount);
        secondCount -= minuteCount * minuteSecondCount;//减去分的秒数
        timeStr += fillNum(minuteCount) + ":";//分
        timeStr += fillNum(secondCount);//秒
        return timeStr;
    },
    /**获得get参数*/
    getGetQueryString : function (name){
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
};