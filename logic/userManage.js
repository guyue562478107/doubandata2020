layui.use(['jquery','form','table'],function () {

    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;

    table.render({    //渲染
        elem: '#tblResult',  //#以id为标准在页面上查找元素
        page: true,
        limit: 6,    //limit语法两个参数  参数1：数据开始的位置（查询结果中的第几条的下一个位置）  参数2：连续获取多少条目
                    // 应用： 参数1的获取方法（当前页码-1）*条目数
        cols: [[    //列 二维数组[[]]
            {field:'uid', title:'用户名', align: 'center'},  //每个列的定义都是一个json对象  :要敲半角的
            {field: 'nickName', title: '昵称', align: 'center'}, //密码永远不能显示出来
            {field: 'state', title: '用户状态', align: 'center',   //理解为具体JavaScript的实体
                templet: function (rowData) {
                    switch (rowData.state) {
                        case 0:
                            return '<font color="yellow">' +
                                '<i class="layui-icon layui-icon-about"></i> ' +
                                '<b>未激活</b></font>';
                        case 1:
                            return '<font color="green"><b>正常</b></font>';
                        case 2:
                            return '<font color="red"><b>已屏蔽</b></font>';
                    }
                }},
            {field: 'id', title: '操作', align: 'center',
                templet:function (rowData) {
                    var btnReset = '<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="reset" >' +
                        '<i class="layui-icon layui-icon-password"></i>密码重置</button>'
                    var btnInfo = '<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="info">' +
                        '<i class="layui-icon layui-icon-about"></i>编辑详情</button>'
                    var btnDisable = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="disable">' +
                        '<i class="layui-icon layui-icon-close"></i>屏蔽用户</button>'

                    return btnReset + btnInfo + btnDisable;
                }
            }
        ]],
        data:[]

    });
    /**
    * 为表格内的按钮提供事件相应
    * */
    //table.on响应table自身的事件
    table.on('tool(tblResult)',function (obj) {

        if ('reset' === obj.event){
            //alert('密码重置 - uid: ' + obj.data.uid + ",ID:" + obj.data.id);

            let index = layer.open({
                title:'警告',
                content: '您将重置当前用户：' + obj.data.uid + '的密码，请确认!</br> 注意，该操作不可逆',
                btn:['确认','取消'],
                btnAlign:'c',
                yes: function () {
                    $.post(
                        '/logic/user/pwdreset',
                        {
                            'id':obj.data.id
                        },
                        function (data) {
                            let msg = '服务器错误，请联系管理员';
                            if (!data.error){
                                msg = '您已经成功重置了该用户的密码';
                            }

                            layer.alert(msg);
                            layer.close(index);

                        }
                    )

                },
                btn2: function () {
                    layer.closeAll();
                }
            });
        }
        if ('info' === obj.event){
            alert('用户详情  -  uid: ' + obj.data.uid + "，ID：" + obj.data.id);
        }
        if ('disable' === obj.event){
            alert('屏蔽用户 - uid' + obj.data.uid + "，ID:" + obj.data.id);
        }

    });


    /**
     * 页面上的检索按钮的绑定，单机激活检索事件
     * 1）向指定的服务接口（url属性中的链接）以制定方法（POST/GET）发送页面收集的数据（where属性中的JSON数据）
     * 2）并将返回的查询结果填充至指定的LayUI表格控件
     * */

    $('#btnSearch').click(function (event) {    //jquer可以用id找页面上的元素  当我们单击这个按钮绑定事件
             //重新加载页面上的哪个表（第一个参数）
        table.reload('tblResult', {
            url:'/logic/user/search',
            method:'post',  //post形式进行发起  方法
            where: {  //传递条件有哪些
                'uid' : $('#uid').val(),   //获取里面的值
                'nickName' : $('#nickName').val(),
                'state' : $('#selState').val()
            },
            page: {
                curr: 1
            }
        })

    });

});