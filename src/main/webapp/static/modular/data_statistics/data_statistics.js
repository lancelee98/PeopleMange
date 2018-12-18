var Data = {
    findSexChart:document.getElementById('sexcharts'),
    findAgeChart:document.getElementById('agecharts'),
    findPeopleChart:document.getElementById('peoplecharts'),
    sexchart: null,
    peoplechart:null,
    agechart:null,
    request_val:"today",
    selectDeviceId:11,
    starttime:'',
    endtime:'',
    selectByHour:'',
    startDate:'' ,
    endDate:'',
    NotShowStaff:'true',
    AutoNotShow:'true',
    selectType:"num",
}
var resizeWorldMapContainer1=function(){
    Data.findSexChart.style.width=window.innerWidth*0.3+'px';
    Data.findSexChart.style.height=(window.innerHeight)*0.7+'px';
}
resizeWorldMapContainer1();
var resizeWorldMapContainer2=function(){
    Data.findPeopleChart.style.width=window.innerWidth*1+'px';
    Data.findPeopleChart.style.height=(window.innerHeight)*0.7+'px';
}
resizeWorldMapContainer2();
var resizeWorldMapContainer3=function(){
    Data.findAgeChart.style.width=window.innerWidth*0.7+'px';
    Data.findAgeChart.style.height=(window.innerHeight)*0.7+'px';
}
 resizeWorldMapContainer3();
// var resizeWorldMapContainer4=function(){
//     Data.findTypeChart.style.width=window.innerWidth*0.3+'px';
//     Data.findTypeChart.style.height=(window.innerHeight)*0.7+'px';
// }
// resizeWorldMapContainer4();
Data.initSexCharts = function() {
    Data.sexchart=echarts.init(Data.findSexChart);
    Data.sexchart.setOption(InitChart.initSexChartOrigin());
}
Data.getSexData = function() {
    $.get('/data_statistics/getAgeAndSEX/?type=sex').done(function (sexinfo) {
        // 填入数据
        Data.sexchart.hideLoading();
        Data.sexchart.setOption({
            series: [
                {
                    data:[
                        {value:sexinfo["男"]==0?null:sexinfo["男"], name:'男'},
                        {value:sexinfo["女"]==0?null:sexinfo["女"], name:'女'},
                    ]

                }
            ]
        });
    });
};

Data.getAgeData = function() {
    $.get('/data_statistics/getAgeAndSEX/?type=age').done(function (ageinfo) {
        // 填入数据
        Data.agechart.hideLoading();
        Data.agechart.setOption({
            series: [
                {
                    data:[ageinfo["age1"],
                        ageinfo["age2"],
                        ageinfo["age3"],
                        ageinfo["age4"],
                        ageinfo["age5"],
                        ageinfo["age6"],
                        ageinfo["age7"],
                        ageinfo["age8"],
                    ]
                }
            ]
        });
    });
}


Data.initPeoplecharts = function() {
    Data.peoplechart=echarts.init(Data.findPeopleChart);
    Data.peoplechart.setOption(InitChart.initPeopleChartOrigin());
}

Data.getPeopleData = function() {
    $.get('/data_statistics/people/?condition='+Data.request_val+
        '&device='+Data.selectDeviceId+'&starttime='+Data.starttime+'&endtime='+Data.endtime+'&selectByHour='+Data.selectByHour
        +'&type='+Data.selectType).done(function (peopleinfo) {
        // 填入数据
        var option;
        var data_arry;
        var title;
        var name;
        if(Data.request_val=="thisweek"||Data.request_val=="lastweek")
        {
            if(Data.selectType=="num"){title="车位租借次数统计";name="次数"}
            if(Data.selectType=="money"){title="车位租借金额统计";name="金额"}
            data_arry=DataFormat.ParaseWeek(peopleinfo);
            option={
                title : {
                    text: title,
                    x:'center'
                },
                xAxis: {
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                },
                series: [
                    {
                        name:name,
                        data:data_arry,
                    }]
            }
        }
        if(Data.request_val=="today"||Data.request_val=="yesterday")
        {
            if(Data.selectType=="num"){title="车位租借次数统计";name="次数"}
            if(Data.selectType=="money"){title="车位租借金额统计";name="金额"}
            data_arry=DataFormat.ParaseHour(peopleinfo);
            option={
                title : {
                    text: title,
                    x:'center'
                },
                xAxis: {
                    data : ['00:00-01:00','01:00-02:00','02:00-03:00','03:00-04:00',
                        '04:00-05:00','05:00-06:00','06:00-07:00','07:00-08:00','08:00-09:00',
                        '09:00-10:00','10:00-11:00','11:00-12:00','12:00-13:00','13:00-14:00',
                        '14:00-15:00','15:00-16:00','16:00-17:00','17:00-18:00','18:00-19:00',
                        '19:00-20:00','20:00-21:00','21:00-22:00','22:00-23:00','23:00-24:00']
                },
                series: [
                    {
                        name:name,
                        data:data_arry,
                    }]

            }
        }
        if(Data.request_val=="thismonth"||Data.request_val=="lastmonth")
        {
            if(Data.selectType=="num"){title="车位租借次数统计";name="次数"}
            if(Data.selectType=="money"){title="车位租借金额统计";name="金额"}
            data_arry=DataFormat.ParaseMonth(peopleinfo);
            option={
                title : {
                    text: title,
                    x:'center'
                },
                xAxis: {
                    data : ['1号','2号','3号','4号','5号','6号','7号','8号','9号',
                        '10号','11号','12号','13号','14号','15号','16号','17号','18号',
                        '19号','20号','21号','22号','23号','24号','25号','26号','27号',
                        '28号','29号','30号','31号']
                },
                series: [
                    {
                        name:name,
                        data:data_arry,
                    }]
            }
        }
        if(Data.request_val==""&&Data.starttime!=''&&Data.endtime!='')
        {

            option={
                title : {
                    text:"车位租借次数统计",
                    x:'center'
                },
                xAxis: {
                    data : DataFormat.ParaseStartAndEndX(peopleinfo,Data.selectByHour)
                },
                series: [
                    {
                        name:"次数",
                        data:DataFormat.ParaseStartAndEndY(peopleinfo)
                    }]
            }
        }
        Data.peoplechart.hideLoading();
        Data.peoplechart.setOption(option);
    });
}
Data.initageChart = function(){
    Data.agechart=echarts.init(Data.findAgeChart);
    Data.agechart.setOption(InitChart.initAgeChartOrigin());
}
Data.hide = function(opt){
    jQuery.each(opt.series.data, function (i, item) {
        if (item.value == 0) {
            item.itemStyle.normal.labelLine.show = false;
            item.itemStyle.normal.label.show = false;
        }
    });
}

Data.doGetData=function()
{
    Data.peoplechart.showLoading();
    Data.sexchart.showLoading();
    Data.agechart.showLoading();
   // console.log("查询条件：request_val="+Data.request_val+"    device="+Data.selectDeviceId+"    endtime="+Data.endtime+"    starttime="+Data.starttime+
     //   "    selectByhour="+Data.selectByHour+'&AutoNotShow='+Data.AutoNotShow
     //   +'&NotShowStaff='+Data.NotShowStaff)
    Data.getPeopleData();
    Data.getAgeData();
    Data.getSexData();
}
Data.setLayDate=function () {
    Data.startDate= laydate.render({//渲染开始时间选择
        elem: '#start_time'//通过id绑定html中插入的start
        , type: 'date'
        ,min:"2018-8-8"//设置min默认最小值
        ,max:DataFormat.getNow()//设置一个默认最大值
        ,theme: '#78C4F1'
        ,trigger: 'click',
        done: function (value, dates) {
            Data.starttime=value;
            if(Data.starttime!=''&&Data.endtime!='')
            {
                Data.resetOption();
                Data.doGetData();
            }
            Data.endDate.config.min ={
                year:dates.year,
                month:dates.month-1, //关键
                date: dates.date,
                hours: 0,
                minutes: 0,
                seconds : 0
            };
        },
    });
    Data.endDate= laydate.render({//渲染结束时间选择
        elem: '#end_time',//通过id绑定html中插入的end
        type: 'date',
        min:"2018-8-8",//设置min默认最小值
        max:DataFormat.getNow(),
        theme: '#78C4F1',
        trigger: 'click',
        done: function (value, dates) {
            Data.endtime=value;
            if(Data.starttime!=''&&Data.endtime!='')
            {
                Data.resetOption();
                Data.doGetData();
            }
            Data.startDate.config.max={
                year:dates.year,
                month:dates.month-1,//关键
                date: dates.date,
                hours: 0,
                minutes: 0,
                seconds : 0
            }

        }
    });
}
Data.resetDate=function()
{
    Data.starttime='';
    Data.endtime='';
    $("#start_time").val("");
    $("#end_time").val("");
    Data.startDate.config.min="2018-8-8";
    Data.endDate.config.min='2018-8-8';
    Data.startDate.config.max=Data.endDate.config.max;
}
Data.resetOption=function()
{
    if(Data.request_val=="today")
    {
        $("#today").removeProp("checked");
        $("#today_lable").removeClass("active");
    }
    Data.request_val='';
    var id=$("input[name='options']:checked").prop("id")
    $("#"+id).removeProp("checked");
    $("#"+id+"_lable").removeClass("active");
    Data.selectByHour=DataFormat.GetDateDiff(Data.starttime,Data.endtime);
}
Data.Listener = function() {
    $("input[name='options']").change(
        function() {
            var request_val = $("input[name='options']:checked").prop("id")
            if(request_val!=Data.request_val)
            {
                Data.request_val=request_val;
                Data.request_val_name = $("input[name='options']:checked").val();
                Data.resetDate();
                Data.doGetData();
            }
        });
    $("input[name='type']").change(
        function() {
            var request_val = $("input[name='type']:checked").prop("id")
            if(request_val!=Data.selectType)
            {
                Data.selectType=request_val;
                Data.resetDate();
                Data.doGetData();
            }
        });
}

$(function () {
    Data.AutoNotShow='true';
    Data.NotShowStaff='true';
    Data.setLayDate();
    $("#deviceSel").attr("value","访客统计");
    Data.initageChart();
    Data.initPeoplecharts();
    Data.initSexCharts();
    Data.getSexData();
    Data.getPeopleData();
    Data.getAgeData();
    Data.Listener();
    window.onresize=function()
    {
        resizeWorldMapContainer1();
        resizeWorldMapContainer2();
        resizeWorldMapContainer3();
        Data.agechart.resize();
        Data.peoplechart.resize();
        Data.sexchart.resize();
    }
});
