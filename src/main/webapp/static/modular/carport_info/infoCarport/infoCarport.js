/**
 * 车位信息管理初始化
 */
var InfoCarport = {
    id: "InfoCarportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InfoCarport.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'carportId', visible: false, align: 'center', valign: 'middle'},
        {title: '车位地址', field: 'carportLoc', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'isUsed', visible: true, align: 'center', valign: 'middle',
            formatter: function(value,row,index){
                if(row.isUsed==true)
                {
                    value='<div style="text-align: center;color: #dc0000" >使用中</div>';
                }
                else if(row.isUsed==false)
                {
                    value='<div style="text-align: center;color: #23a523" >空闲中</div>';
                }
                else {
                    value='';
                }
                return value ;
            }
        },
        {title: '使用人', field: 'username', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
InfoCarport.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InfoCarport.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车位信息
 */
InfoCarport.openAddInfoCarport = function () {
    var index = layer.open({
        type: 2,
        title: '添加车位信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/infoCarport/infoCarport_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车位信息详情
 */
InfoCarport.openInfoCarportDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车位信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/infoCarport/infoCarport_update/' + InfoCarport.seItem.carportId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除车位信息
 */
InfoCarport.delete = function () {
    if (this.check()) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/infoCarport/delete", function (data) {
                Feng.success("删除成功!");
                InfoCarport.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("infoCarportId", InfoCarport.seItem.carportId);
            ajax.start();
        };
        Feng.confirm("是否删除该车位包括其所有租用信息?", operation);
    }

};

/**
 * 查询车位信息列表
 */
InfoCarport.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    InfoCarport.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InfoCarport.initColumn();
    var table = new BSTable(InfoCarport.id, "/infoCarport/list", defaultColunms);
    table.setPaginationType("client");
    InfoCarport.table = table.init();
});
