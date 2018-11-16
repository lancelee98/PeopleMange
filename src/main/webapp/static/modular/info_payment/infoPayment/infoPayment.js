/**
 * 缴费记录管理初始化
 */
var InfoPayment = {
    id: "InfoPaymentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InfoPayment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '缴费标识号', field: 'paymentId', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费人身份证号码', field: 'idNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费金额', field: 'payment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
InfoPayment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InfoPayment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加缴费记录
 */
InfoPayment.openAddInfoPayment = function () {
    var index = layer.open({
        type: 2,
        title: '添加缴费记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/infoPayment/infoPayment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看缴费记录详情
 */
InfoPayment.openInfoPaymentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '缴费记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/infoPayment/infoPayment_update/' + InfoPayment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除缴费记录
 */
InfoPayment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/infoPayment/delete", function (data) {
            Feng.success("删除成功!");
            InfoPayment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("infoPaymentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询缴费记录列表
 */
InfoPayment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    InfoPayment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InfoPayment.initColumn();
    var table = new BSTable(InfoPayment.id, "/infoPayment/list", defaultColunms);
    table.setPaginationType("client");
    InfoPayment.table = table.init();
});
