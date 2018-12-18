/**
 * 初始化用户信息详情对话框
 */
var InfoUserInfoDlg = {
    infoUserInfoData : {},
    validateFields: {
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
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                },
                stringLength: {
                    max:20,
                    message: '格式错误'
                }
            }
        },
        gender: {
            validators: {
                notEmpty: {
                    message: '性别不能为空'
                }
            }
        },
        deviceSel: {
            validators: {
                notEmpty: {
                    message: '房间不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
                },
                stringLength: {
                    min:7,
                    max:11,
                    message: '格式错误'
                }
            }
        },
        age: {
            validators: {
                notEmpty: {
                    message: '年龄不能为空'
                },
                stringLength: {
                    max:3,
                    message: '格式错误'
                }
            }
        },
        passwd: {
            validators: {
                notEmpty: {
                    message: '手机登录密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
                stringLength: {
                    min:6,
                    max:25,
                    message: '格式错误'
                }
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '重复密码不能为空'
                },
                identical: {
                    field: 'passwd',
                    message: '两次密码不一致'
                },
            }
        }
    }
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
    .set('passwd')
    .set('id');
}

/**
 * 验证数据是否为空
 */
InfoUserInfoDlg.validate = function () {
    $('#InfoUserInfoDlgForm').data("bootstrapValidator").resetForm();
    $('#InfoUserInfoDlgForm').bootstrapValidator('validate');
    return $("#InfoUserInfoDlgForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
InfoUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
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

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/infoUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.InfoUser.table.refresh();
        InfoUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoUserInfoData);
    console.log($("#roomId").val())
    ajax.start();
}
InfoUserInfoDlg.onClickTree = function (e, treeId, treeNode) {
    $("#deviceSel").attr("value", instance.getSelectedVal());
    $("#roomId").attr("value", treeNode.id);
    console.log($("#roomId").val())
};
InfoUserInfoDlg.showDeviceSelectTree = function () {
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
InfoUserInfoDlg.hideDeviceSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        InfoUserInfoDlg.hideDeviceSelectTree();
    }
}

$(function() {
    Feng.initValidator("InfoUserInfoDlgForm", InfoUserInfoDlg.validateFields);
    $("#gender").val($("#genderValue").val());
    var ztree = new $ZTree("deviceTree", "/info/tree");
    ztree.bindOnClick(InfoUserInfoDlg.onClickTree);
    ztree.init();
    instance = ztree;
});
