var InitChart = {
}
InitChart.initSexChartOrigin=function () {
    option = {
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px"
        },
        color: ['#49A9EE','#F3857B'],
        title : {
            text: '男女比例',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: ' horizontal',
            x:15,
            y:30,
            data:['男','女']
        },
        series: [
            {
                name:'男女比例',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[

                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    return option;
}
InitChart.initPeopleChartOrigin=function () {
    option = {
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px"
        },
        title : {
            text: "车位租借次数统计",
            x:'center'
        },
        color: ['#49A9EE'],
        xAxis: {
            type: 'category',
            boundaryGap: false
        },
        yAxis: {
            type: 'value'
        },
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        series: [{
            name:"单数",
            type: 'line',
            areaStyle: {},
            smooth: true
        }]
    };
    return option;
}
InitChart.initAgeChartOrigin=function () {
    option = {
        color: ['#2DB7F5'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },title : {
            text: '年龄分布',
            x:'center'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['0-18岁', '18-25岁', '25-35岁', '35-45岁', '45-55岁','55-65岁','65岁-75岁','75岁以上'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'人数',
                type:'bar',
                barWidth: '50%',
            }
        ]
    };
    return option;
}
