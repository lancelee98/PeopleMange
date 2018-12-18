var DataFormat = {

}
DataFormat.getTotal=function (data) {
    var sum=0;
    for(var i=0;i<data.length;i++)
    {
        sum+=data[i];
    }
    return sum;
}
DataFormat.ParaseStartAndEndX=function(data,type)
{
    xarray=new Array(data.length)
    for(var i=0;i<data.length;i++)
    {
        if(type=='true')
            xarray[i]=(data[i].datas).substring(8,14);
        else
            xarray[i]=(data[i].datas).substring(5,10);
    }
    return xarray;
}
DataFormat.ParaseStartAndEndY=function(data)
{
    yarray=new Array(data.length)
    for(var i=0;i<data.length;i++)
    {
        yarray[i]=data[i].counts;
    }
    return yarray;
}
DataFormat.getPercent=function (data) {
    var total=this.getTotal(data.map(Number));
    var data_array=new Array(data.length);
    for(var i=0;i<data.length;i++)
    {
        if(data[i]==0||data[i]=='0') data_array[i]=0;
        else if(!isNaN(data[i]))
        {data_array[i]=Math.round(data[i] / total * 100);}
        else
            data_array[i]=0;
    }
    return data_array;
}
DataFormat.ParaseWeek = function(info){
    var data_arry=[0,0,0,0,0,0,0];
    info.forEach(function (f) {
        switch (f.datas) {
            case "Monday":
                data_arry[0]=f.counts;
                break;
            case "Tuesday":
                data_arry[1]=f.counts;
                break;
            case "Wednesday":
                data_arry[2]=f.counts;
                break;
            case "Thursday":
                data_arry[3]=f.counts;
                break;
            case "Friday":
                data_arry[4]=f.counts;
                break;
            case "Saturday":
                data_arry[5]=f.counts;
                break;
            case "Sunday":
                data_arry[6]=f.counts;
                break;
            default:break;

        }
    });
    return data_arry;
};
DataFormat.ParaseHour = function(info){
    var data_arry=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
    info.forEach(function (f) {
        switch (f.datas) {
            case "00":
                data_arry[0]=f.counts;
                break;
            case "01":
                data_arry[1]=f.counts;
                break;
            case "02":
                data_arry[2]=f.counts;
                break;
            case "03":
                data_arry[3]=f.counts;
                break;
            case "04":
                data_arry[4]=f.counts;
                break;
            case "05":
                data_arry[5]=f.counts;
                break;
            case "06":
                data_arry[6]=f.counts;
                break;
            case "07":
                data_arry[7]=f.counts;
                break;
            case "08":
                data_arry[8]=f.counts;
                break;
            case "09":
                data_arry[9]=f.counts;
                break;
            case "10":
                data_arry[10]=f.counts;
                break;
            case "11":
                data_arry[11]=f.counts;
                break;
            case "12":
                data_arry[12]=f.counts;
                break;
            case "13":
                data_arry[13]=f.counts;
                break;
            case "14":
                data_arry[14]=f.counts;
                break;
            case "15":
                data_arry[15]=f.counts;
                break;
            case "16":
                data_arry[16]=f.counts;
                break;
            case "17":
                data_arry[17]=f.counts;
                break;
            case "18":
                data_arry[18]=f.counts;
                break;
            case "19":
                data_arry[19]=f.counts;
                break;
            case "20":
                data_arry[20]=f.counts;
                break;
            case "21":
                data_arry[21]=f.counts;
                break;
            case "22":
                data_arry[22]=f.counts;
                break;
            case "23":
                data_arry[23]=f.counts;
                break;
            default:break;
        }
    });
    return data_arry;
};
DataFormat.ParaseMonth = function(info){
    var data_arry=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
    info.forEach(function (f) {
        switch (f.datas) {
            case "01":
                data_arry[0]=f.counts;
                break;
            case "02":
                data_arry[1]=f.counts;
                break;
            case "03":
                data_arry[2]=f.counts;
                break;
            case "04":
                data_arry[3]=f.counts;
                break;
            case "05":
                data_arry[4]=f.counts;
                break;
            case "06":
                data_arry[5]=f.counts;
                break;
            case "07":
                data_arry[6]=f.counts;
                break;
            case "08":
                data_arry[7]=f.counts;
                break;
            case "09":
                data_arry[8]=f.counts;
                break;
            case "10":
                data_arry[9]=f.counts;
                break;
            case "11":
                data_arry[10]=f.counts;
                break;
            case "12":
                data_arry[11]=f.counts;
                break;
            case "13":
                data_arry[12]=f.counts;
                break;
            case "14":
                data_arry[13]=f.counts;
                break;
            case "15":
                data_arry[14]=f.counts;
                break;
            case "16":
                data_arry[15]=f.counts;
                break;
            case "17":
                data_arry[16]=f.counts;
                break;
            case "18":
                data_arry[17]=f.counts;
                break;
            case "19":
                data_arry[18]=f.counts;
                break;
            case "20":
                data_arry[19]=f.counts;
                break;
            case "21":
                data_arry[20]=f.counts;
                break;
            case "22":
                data_arry[21]=f.counts;
                break;
            case "23":
                data_arry[22]=f.counts;
                break;
            case "24":
                data_arry[23]=f.counts;
                break;
            case "25":
                data_arry[24]=f.counts;
                break;
            case "26":
                data_arry[25]=f.counts;
                break;
            case "27":
                data_arry[26]=f.counts;
                break;
            case "28":
                data_arry[27]=f.counts;
                break;
            case "29":
                data_arry[28]=f.counts;
                break;
            case "30":
                data_arry[29]=f.counts;
                break;
            case "31":
                data_arry[30]=f.counts;
                break;
            default:break;
        }
    });
    return data_arry;
};
DataFormat.getNow=function () {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
DataFormat.GetDateDiff=function(startDate,endDate)
{
    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);
    if(dates>3)
        return ''
    else
        return 'true'
}

