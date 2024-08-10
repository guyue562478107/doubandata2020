layui.use(['layer','form','jquery'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;


    form.on('submit(Login)', function (data) {
        $.post(
            '/logic/user/login',
            {
                'uid' : data.field.uid,
                'pwd' : data.field.pwd,
            }
        )
    });

});


// function toregis()
// {
//     window.location.href="/html/addUser.html";
// }
