layui.use(['jquery','form','table','layer'],function () {

    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;

    table.render({
        elem: '#tblResult',
        page: true,
        limit: 6,
        cols: [[
            {field:'bookName', title:'图书名称', align: 'center'},
            {field: 'bookAuthor', title: '作者', align: 'center'},
            {field: 'bookDate', title: '出版年份', align: 'center'},
            {field: 'bookPublish', title: '出版社', align: 'center'},
            {field: 'bookPrice', title: '图书价格', align: 'center'},
            {field: 'bookStar', title: '图书评分', align: 'center'},
            {field: 'id', title: '操作', align: 'center',
                templet:function (rowData) {
                    var btnInfo = '<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="info">' +
                        '<i class="layui-icon layui-icon-about"></i>编辑详情</button>'
                    return btnInfo;

                }}

        ]],
        data:[]

    });

    /**
     * 为表格内的按钮提供事件相应
     * */
    table.on('tool(tblResult)',function (obj){
        if ('info' === obj.event){
            // alert('用户详情  -  uid: ' + obj.data.uid + "，ID：" + obj.data.id);

            $.post('/logic/book/' + obj.data.id,
                {},
                function (result) {

                    $('#spanBookUpdate').html(result.bookName);
                    $('#updateBookAuthor').val(result.bookAuthor);


                    layer.open({
                        type: 1
                        ,offset: 'auto'
                        ,content: $('#divBookInfo')
                        ,btn:['保存','取消']
                        ,btnAlign:'c'
                        ,yes: function (index, lo) {
                            $.post('/logic/book/update',
                                {
                                    'id': result.id,
                                    'bookAuthor':$('#updateBookAuthor').val()
                                },
                                function (result) {
                                    if (!result.error){
                                        layer.msg(result.msg);
                                        layer.close(index);
                                    }else {
                                        layer.msg('您输入的内容有误!');
                                    }

                                }

                            );

                        }
                        ,btn2:function () {
                            layer.closeAll();
                        }
                    });

                }
            );
        }

    });


    /**
     * 页面上的检索按钮的绑定，单机激活检索事件
     * 1）向指定的服务接口（url属性中的链接）以制定方法（POST/GET）发送页面收集的数据（where属性中的JSON数据）
     * 2）并将返回的查询结果填充至指定的LayUI表格控件
     * */

    $('#btnSearch').click(function (event) {
        table.reload('tblResult', {
            url:'/logic/book/search',
            method:'post',
            where: {
                'bookName' : $('#bookName').val(),
                'bookAuthor' : $('#bookAuthor').val(),
            },
            page: {
                curr: 1
            }
        })

    });

    $('#btnSearchAll').click(function (event) {
        table.reload('tblResult',{
            url:'/logic/book/search all',
            method:'post',
            page:{
                curr: 1
            }
        })

    })

});