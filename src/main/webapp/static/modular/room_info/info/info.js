/**
 * 住房管理初始化
 */
var Info = {
    id: "InfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Info.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '区域标识号', field: 'room_id', visible: true, align: 'center', valign: 'middle',width:"100px"},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle',
                formatter: function(value,row,index){
                    if(row.type==true)
                    {
                        value='<div style="text-align: center;" ><i class="fa fa-bed"></i>&nbsp;房间</div>';
                    }
                    else value='<div style="text-align: center;" >区域</div>';
                    return value ;
                }},
            {title: '父节点', field: 'parent_id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Info.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Info.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加住房
 */
Info.openAddInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加住房',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/info/info_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看住房详情
 */
Info.openInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '住房详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/info/info_update/' + Info.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除住房
 */
Info.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/info/delete", function (data) {
            Feng.success("删除成功!");
            Info.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("infoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询住房列表
 */
Info.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Info.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Info.initColumn();
    var table = new BSTreeTable(Info.id, "/info/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("room_id");
    table.setCodeField("room_id");
    table.setParentCodeField("parent_id");
    table.setExpandAll(true);
    table.init();
    Info.table = table;
});
