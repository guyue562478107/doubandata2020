layui.use(['jquery', 'form', 'table', 'layer'], function () {

    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;

    table.render ({
        elem: '#tblResult',
        page: true,
        limit: 15,
        cols: [[
            {field:'id', title:'id', align:'center', width: 50},
            {field: 'article', title: '标题', align: 'center', width: 300},
            {field: 'zuozhe', title: '作者', align: 'center', width: 200},
            {field: 'review1', title: '热评1', align: 'center',width: 400},
            {field: 'reviewFrom1', title: '热评来源', align: 'center',width: 200},
            {field: 'reviewTime1', title: '热评时间', align: 'center',width: 200},
            {field: 'review2', title: '热评2', align: 'center',width: 400},
            {field: 'reviewFrom2', title: '热评来源', align: 'center',width: 200},
            {field: 'reviewTime2', title: '热评时间', align: 'center',width: 200},
            {field: 'review3', title: '热评3', align: 'center',width: 400},
            {field: 'reviewFrom3', title: '热评来源', align: 'center',width: 200},
            {field: 'reviewTime3', title: '热评时间', align: 'center',width: 200},
            {field: 'review4', title: '热评4', align: 'center',width: 400},
            {field: 'reviewFrom4', title: '热评来源', align: 'center',width: 200},
            {field: 'reviewTime4', title: '热评时间', align: 'center',width: 200},
            {field: 'review5', title: '热评5', align: 'center',width: 400},
            {field: 'reviewFrom5', title: '热评来源', align: 'center',width: 200},
            {field: 'reviewTime5', title: '热评时间', align: 'center',width: 200}

        ]],
        data:[]
    });
    $('#btnSearch').click(function (event) {
        table.reload('tblResult', {
            url: '/logic/group/search',
            method: 'post',
            where:{
                'article' : $('#article').val(),
                'zuozhe' : $('#zuozhe').val()
            }
        })

    });

    $('#btnSearchAll').click(function (event) {

        table.reload('tblResult',{
            url:'/logic/group/searchAll',
            method:'post',
            where:{
                'article' : $('#article').val(),
                'zuozhe' : $('#zuozhe').val()
            }
        })
    })
});