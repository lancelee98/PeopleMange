/**
 * 初始化用户信息详情对话框
 */
var InfoUserInfoDlg = {
    infoUserInfoData : {}
};

/**
 * 清除数据
 */
InfoUserInfoDlg.clearData = function() {
    this.infoUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoUserInfoDlg.set = function(key, val) {
    this.infoUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoUserInfoDlg.close = function() {
    parent.layer.close(window.parent.InfoUser.layerIndex);
}

/**
 * 收集数据
 */
InfoUserInfoDlg.collectData = function() {
    this
    .set('idNumber')
    .set('name')
    .set('gender')
    .set('phone')
    .set('age')
    .set('race')
    .set('job')
    .set('roomId')
    .set('passwd');
}

/**
 * 提交添加
 */
InfoUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.InfoUser.table.refresh();
        InfoUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.InfoUser.table.refresh();
        InfoUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoUserInfoData);
    ajax.start();
}

$(function() {

});
