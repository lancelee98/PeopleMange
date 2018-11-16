/**
 * 初始化车位信息详情对话框
 */
var InfoCarportInfoDlg = {
    infoCarportInfoData : {}
};

/**
 * 清除数据
 */
InfoCarportInfoDlg.clearData = function() {
    this.infoCarportInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoCarportInfoDlg.set = function(key, val) {
    this.infoCarportInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoCarportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoCarportInfoDlg.close = function() {
    parent.layer.close(window.parent.InfoCarport.layerIndex);
}

/**
 * 收集数据
 */
InfoCarportInfoDlg.collectData = function() {
    this
    .set('carportId')
    .set('carportLoc');
}

/**
 * 提交添加
 */
InfoCarportInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoCarport/add", function(data){
        Feng.success("添加成功!");
        window.parent.InfoCarport.table.refresh();
        InfoCarportInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoCarportInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoCarportInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoCarport/update", function(data){
        Feng.success("修改成功!");
        window.parent.InfoCarport.table.refresh();
        InfoCarportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoCarportInfoData);
    ajax.start();
}

$(function() {

});
