/**
 * 初始化缴费记录详情对话框
 */
var InfoPaymentInfoDlg = {
    infoPaymentInfoData : {}
};

/**
 * 清除数据
 */
InfoPaymentInfoDlg.clearData = function() {
    this.infoPaymentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoPaymentInfoDlg.set = function(key, val) {
    this.infoPaymentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoPaymentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoPaymentInfoDlg.close = function() {
    parent.layer.close(window.parent.InfoPayment.layerIndex);
}

/**
 * 收集数据
 */
InfoPaymentInfoDlg.collectData = function() {
    this
    .set('paymentId')
    .set('idNumber')
    .set('type')
    .set('time')
    .set('payment');
}

/**
 * 提交添加
 */
InfoPaymentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoPayment/add", function(data){
        Feng.success("添加成功!");
        window.parent.InfoPayment.table.refresh();
        InfoPaymentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoPaymentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoPaymentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoPayment/update", function(data){
        Feng.success("修改成功!");
        window.parent.InfoPayment.table.refresh();
        InfoPaymentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoPaymentInfoData);
    ajax.start();
}

$(function() {

});
