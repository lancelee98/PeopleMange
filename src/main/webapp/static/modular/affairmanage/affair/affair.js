/**
 * 事物管理管理初始化
 */
var Affair = {
    id: "AffairTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Affair.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '事务id', field: 'affairId', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'idNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '管理员编号', field: 'adminId', visible: true, align: 'center', valign: 'middle'},
            {title: '请求时间', field: 'creatTime', visible: true, align: 'center', valign: 'middle'},
            {title: '具体内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '图片描述', field: 'imageLink', visible: true, align: 'center', valign: 'middle'},
            {title: '回执内容', field: 'receipt', visible: true, align: 'center', valign: 'middle'},
            {title: '是否解决', field: 'solved', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Affair.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Affair.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加事物管理
 */
Affair.openAddAffair = function () {
    var index = layer.open({
        type: 2,
        title: '添加事物管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/affair/affair_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看事物管理详情
 */
Affair.openAffairDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '事物管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/affair/affair_update/' + Affair.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除事物管理
 */
Affair.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/affair/delete", function (data) {
            Feng.success("删除成功!");
            Affair.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("affairId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询事物管理列表
 */
Affair.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Affair.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Affair.initColumn();
    var table = new BSTable(Affair.id, "/affair/list", defaultColunms);
    table.setPaginationType("client");
    Affair.table = table.init();
});
