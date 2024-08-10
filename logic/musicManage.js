layui.use(['jquery', 'form', 'table', 'layer'], function () {

    var $ = layui.jquery,
        form = layui.form,
        table = layui.table
        layer = layui.layer;

    table.render ({
        elem: '#tblResult',
        page: true,
        limit: 10,
        cols: [[
            {field:'musicName', title:'名称', align:'center'},
            {field:'musicSinger', title: '作者', align: 'center'},
            {field:'musicTime', title: '发行年份', align: 'center'},
            {field: 'musicType', title:'流派', align: "center"},
            {field: 'musicScore', title:'评分', align: "center"},
            // {field: 'id',title: '操作', align: 'center',
            //     templet:function (rowData) {
            //         var btnPop = '<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="pop">'+
            //             '<i class="layui-icon layui-icon-password"></i>流行</button>'
            //         var btnBallad = '<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="ballad">'+
            //             '<i class="layui-icon layui-icon-about"></i>民谣</button>'
            //         var btnHiphop = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="hiphop">'+
            //             '<i class="layui-icon layui-icon-about"></i>说唱</button>'
            //         var btnRock = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="rock">'+
            //             '<i class="layui-icon layui-icon-about"></i>摇滚</button>'
            //         var btnSound = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="sound">'+
            //             '<i class="layui-icon layui-icon-about"></i>原声</button>'
            //         var btnClas = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="clas">'+
            //             '<i class="layui-icon layui-icon-about"></i>古典</button>'
            //         var btnElec= '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="elec">'+
            //             '<i class="layui-icon layui-icon-about"></i>电子</button>'
            //         var btnJazz= '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="jazz">'+
            //             '<i class="layui-icon layui-icon-about"></i>爵士</button>'
            //         return btnPop + btnBallad + btnHiphop + btnRock + btnSound + btnClas + btnElec + btnJazz;
            //
            //     }}

        ]],
        data:[]
    });



    /**
     * 页面上的检索按钮的绑定，单击激活检索事件
     * 1）向指定的服务接口（url属性中的链接）以指定方法（POST/GET）发送页面收集的数据（where属性中的JSON数据）
     * 2）并将返回的查询结果填充至指定的LayUI表格控件
     */
    $('#btnSearch').click(function(event) {
        table.reload('tblResult', {
            url:'/logic/music/search',
            method:'post',
            where: {
                // 'musicName' : $('#musicName').val(),
                'musicSinger' : $('#musicSinger').val(),
                'musicType':$('#musicType').val(),
                'musicTime' : $('#musicTime').val()
            },
            page:{
                curr: 1
            }
        })

    });

    $('#btnSearchAll').click(function (event) {
        // alert('successful');
        table.reload('tblResult',{
            url:'/logic/music/search all',
            method:'post',
            page:{
                curr: 1
            }
        })

    })

});