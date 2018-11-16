/**
 * 租借信息管理初始化
 */
var InfoRent = {
    id: "InfoRentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InfoRent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '车位标识号', field: 'carportId', visible: true, align: 'center', valign: 'middle'},
            {title: '发起人身份证号', field: 'idNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '租用时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '到期时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
InfoRent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InfoRent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加租借信息
 */
InfoRent.openAddInfoRent = function () {
    var index = layer.open({
        type: 2,
        title: '添加租借信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/infoRent/infoRent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看租借信息详情
 */
InfoRent.openInfoRentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '租借信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/infoRent/infoRent_update/' + InfoRent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除租借信息
 */
InfoRent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/infoRent/delete", function (data) {
            Feng.success("删除成功!");
            InfoRent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("infoRentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询租借信息列表
 */
InfoRent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    InfoRent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InfoRent.initColumn();
    var table = new BSTable(InfoRent.id, "/infoRent/list", defaultColunms);
    table.setPaginationType("client");
    InfoRent.table = table.init();
});
