layui.use(['jquery', 'layer'], function(){

    let $ = layui.$,
        layer = layui.layer;

    let myChart = echarts.init(document.getElementById('movieCount'));
    let smoothed = echarts.init(document.getElementById('director'));

    $.post(
        "/logic/movie/starcount",
        {},
        function (result) {
            console.log(result);

            let star = [];
            let count = [];
            let pieCount = [];

            //为柱状图组装数据
            for (i = 0;i<result.length;i++){
                star.push(result[i].movieStar);
                count.push(result[i].count);

                if(i <= 10)
                    pieCount.push({'value':result[i].count, 'name':result[i].movieStar});
            }

            let option = {
                title: {
                    text: '电影评分统计'
                },
                tooltip: {},
                legend: {
                    data:['星数']
                },
                xAxis: {
                    data: star
                },
                yAxis: {},
                series: [{
                    name: '评分',
                    type: 'bar',
                    data: count
                }]
            };


            myChart.setOption(option);

        })

    $('#btnEcharts').click(function (event) {
        $.post(
            "/logic/movie/directorDate",
            {
                'movieDirector' : $('#movieDirector').val()
            },
            function (result) {
                console.log(result);
                let star = [];
                let date = [];
                let pieCount = [];

                //为线型图组装数据
                for (i = 0;i<result.length;i++){
                    star.push(result[i].movieStar);
                    date.push(result[i].movieDate);

                    if(i <= 10)
                        pieCount.push({'time':result[i].movieDate, 'value':result[i].movieStar});
                }


                let option = {
                    xAxis: {
                        type: 'category',
                        data: date
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: star,
                        type: 'line',
                        smooth: true
                    }]
                };


                smoothed.setOption(option);
            }
        )
    })




});