/**
 * 用户信息管理初始化
 */
var InfoUser = {
    id: "InfoUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InfoUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '身份证号码', field: 'idNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'gender', visible: true, align: 'center', valign: 'middle',width:'50px',
                formatter: function(value,row,index){
                    if(row.gender==true)
                    return '男';
                    else if(row.gender==false)
                        return "女"
                }
            },
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle',width:'50px',},
            {title: '民族', field: 'race', visible: false, align: 'center', valign: 'middle'},
            {title: '职业', field: 'job', visible: false, align: 'center', valign: 'middle'},
            {title: '房间名', field: 'roomAdress', visible: true, align: 'center', valign: 'middle'},
            {title: '区域标识号', field: 'roomId', visible: false, align: 'center', valign: 'middle'},
            {title: '手机登录密码', field: 'passwd', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
InfoUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InfoUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户信息
 */
InfoUser.openAddInfoUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/infoUser/infoUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户信息详情
 */
InfoUser.openInfoUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/infoUser/infoUser_update/' + InfoUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户信息
 */
InfoUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/infoUser/delete", function (data) {
            Feng.success("删除成功!");
            InfoUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("infoUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户信息列表
 */
InfoUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    InfoUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InfoUser.initColumn();
    var table = new BSTable(InfoUser.id, "/infoUser/list", defaultColunms);
    table.setPaginationType("client");
    InfoUser.table = table.init();
});
