layui.use(['layer','form','jquery'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;


    form.on('submit(saveAndCommit)', function (data) {
        $.post(
            '/logic/user/add',
            {
                'uid' : data.field.uid,
                'pwd' : data.field.pwd,
                'nickName' : data.field.nickName
            },
            function (returnData) {
                if (returnData != null){
                    layer.alert("用户建立成功");
                }
            }
        )
    });

});