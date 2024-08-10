layui.use(['jquery', 'form', 'table', 'layer'], function () {

    var $ = layui.jquery,
        form = layui.form,
        table = layui.table
        layer = layui.layer;

    table.render ({
        elem: '#tblResult',
        page: true,
        limit: 6,
        cols: [[
            {field:'movieName', title:'名称', align:'center'},
            {field:'movieDirector', title: '导演', align: 'center'},
            {field:'movieDate', title: '发行年份', align: 'center'},
            {field:'movieCategory', title:'类型', align: "center"},
            {field:'movieStar', title:'评分', align: "center"},
            {field:'movieVoteNum', title:'评分人数', align: "center"},
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

            $.post('/logic/movie/' + obj.data.id,
                {},
                function (result) {
                    //填充必要的值
                    $('#updatemovieDate').val(result.movieDate);
                    $('#updatemovieVoteNum').val(result.movieVoteNum);

                    layer.open({
                        type: 1
                        ,offset: 'auto'
                        ,content: $('#divMovieInfo')
                        ,btn:['保存','取消']
                        ,btnAlign:'c'
                        ,yes: function (index, lo) {
                            $.post('/logic/movie/update',
                                {
                                    'movieName': result.movieName,
                                    'movieDate':$('#updatemovieDate').val(),
                                    'movieVoteNum':$('#updatemovieVoteNum').val()
                                },
                                function (result) {
                                    if (!result.error){
                                        layer.msg(result.msg);
                                        layer.close(index);
                                    }else {
                                        layer.msg('您输入的内容有误，请检查后重新提交');
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
     * 页面上的检索按钮的绑定，单击激活检索事件
     * 1）向指定的服务接口（url属性中的链接）以指定方法（POST/GET）发送页面收集的数据（where属性中的JSON数据）
     * 2）并将返回的查询结果填充至指定的LayUI表格控件
     */
    $('#btnSearch').click(function(event) {
        table.reload('tblResult', {
            url:'/logic/movie/search',
            method:'post',
            where: {
                'movieName' : $('#movieName').val(),
                'movieDirector' : $('#movieDirector').val(),
                'movieDate' : $('#movieDate').val(),
            },
            page:{
                curr: 1
            }
        })

    });

    $('#btnSearchAll').click(function (event) {
        // alert('successful');
        table.reload('tblResult',{
            url:'/logic/movie/search all',
            method:'post',
        })

    })

});