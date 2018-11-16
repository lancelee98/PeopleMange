/**
 * 初始化租借信息详情对话框
 */
var InfoRentInfoDlg = {
    infoRentInfoData : {}
};

/**
 * 清除数据
 */
InfoRentInfoDlg.clearData = function() {
    this.infoRentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoRentInfoDlg.set = function(key, val) {
    this.infoRentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoRentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoRentInfoDlg.close = function() {
    parent.layer.close(window.parent.InfoRent.layerIndex);
}

/**
 * 收集数据
 */
InfoRentInfoDlg.collectData = function() {
    this
    .set('carportId')
    .set('idNumber')
    .set('startTime')
    .set('endTime');
}

/**
 * 提交添加
 */
InfoRentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoRent/add", function(data){
        Feng.success("添加成功!");
        window.parent.InfoRent.table.refresh();
        InfoRentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoRentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoRentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoRent/update", function(data){
        Feng.success("修改成功!");
        window.parent.InfoRent.table.refresh();
        InfoRentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoRentInfoData);
    ajax.start();
}

$(function() {

});
