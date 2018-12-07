/**
 * 初始化事物管理详情对话框
 */
var AffairInfoDlg = {
    affairInfoData: {},
    editor:null,
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        },
        idNumber: {
            validators: {
                notEmpty: {
                    message: '身份证号码不能为空'
                },
                stringLength: {
                    min:18,
                    max:18,
                    message: '格式错误'
                }
            }

        },
    }
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
    this.affairInfoData['content'] = AffairInfoDlg.editor.txt.html();
    this.set('title');
    this.affairInfoData['receipt'] = $("#idNumber").val();
}
AffairInfoDlg.collectData2 = function() {
    this.affairInfoData['receipt'] = AffairInfoDlg.editor.txt.html();
}
AffairInfoDlg.validate = function () {
    $('#AffairInfoDlgForm').data("bootstrapValidator").resetForm();
    $('#AffairInfoDlgForm').bootstrapValidator('validate');
    return $("#AffairInfoDlgForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
AffairInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/affair/add", function(data){
        Feng.success("添加成功!");
        window.parent.Affair.table.refresh();
        AffairInfoDlg.close();
    },function(data){
        Feng.error("添加失败!请检查身份证号是否输入正确！");
    });
    ajax.set(this.affairInfoData);
    ajax.start();
}
AffairInfoDlg.addSubmit2 = function() {

    this.clearData();
    this.collectData2();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/affair/update/"+$("#id").val(), function(data){
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

    if (!this.validate()) {
        return;
    }
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
    Feng.initValidator("AffairInfoDlgForm", AffairInfoDlg.validateFields);
    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    AffairInfoDlg.editor = editor;
});
