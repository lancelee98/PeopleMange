/**
 * 初始化事物管理详情对话框
 */
var AffairInfoDlg = {
    affairInfoData : {}
};

/**
 * 清除数据
 */
AffairInfoDlg.clearData = function() {
    this.affairInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AffairInfoDlg.set = function(key, val) {
    this.affairInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AffairInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AffairInfoDlg.close = function() {
    parent.layer.close(window.parent.Affair.layerIndex);
}

/**
 * 收集数据
 */
AffairInfoDlg.collectData = function() {
    this
    .set('affairId')
    .set('idNumber')
    .set('adminId')
    .set('creatTime')
    .set('content')
    .set('imageLink')
    .set('receipt')
    .set('solved');
}

/**
 * 提交添加
 */
AffairInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/affair/add", function(data){
        Feng.success("添加成功!");
        window.parent.Affair.table.refresh();
        AffairInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.affairInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AffairInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/affair/update", function(data){
        Feng.success("修改成功!");
        window.parent.Affair.table.refresh();
        AffairInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.affairInfoData);
    ajax.start();
}

$(function() {

});
