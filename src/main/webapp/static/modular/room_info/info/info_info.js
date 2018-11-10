/**
 * 初始化住房管理详情对话框
 */
var InfoInfoDlg = {
    infoInfoData : {}
};

/**
 * 清除数据
 */
InfoInfoDlg.clearData = function() {
    this.infoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoInfoDlg.set = function(key, val) {
    this.infoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoInfoDlg.close = function() {
    parent.layer.close(window.parent.Info.layerIndex);
}

/**
 * 收集数据
 */
InfoInfoDlg.collectData = function() {
    this
    .set('roomId')
    .set('type')
    .set('name')
    .set('parentId')
    .set('address')
}

/**
 * 提交添加
 */
InfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/info/add", function(data){
        Feng.success("添加成功!");
        window.parent.Info.table.refresh();
        InfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/info/update", function(data){
        Feng.success("修改成功!");
        window.parent.Info.table.refresh();
        InfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoInfoData);
    ajax.start();
}
InfoInfoDlg.onClickTree = function (e, treeId, treeNode) {
    $("#deviceSel").attr("value", instance.getSelectedVal());
    $("#parentId").attr("value", treeNode.id);
};
InfoInfoDlg.showDeviceSelectTree = function () {
    var deviceObj = $("#deviceSel");
    var deviceOffset = $("#deviceSel").offset();
    $("#menuContent").css({
        left: deviceOffset.left + "px",
        top: deviceOffset.top + deviceObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};



/**
 * 隐藏部门选择的树
 */
InfoInfoDlg.hideDeviceSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        InfoInfoDlg.hideDeviceSelectTree();
    }
}

$(function() {
    $("#type").val($("#type_val").val());
    $("#parentId").val($("#paremtId_val").val());
    var ztree = new $ZTree("deviceTree", "/info/tree");
    ztree.bindOnClick(InfoInfoDlg.onClickTree);
    ztree.init();
    instance = ztree;
});
