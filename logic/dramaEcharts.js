layui.use(['jquery', 'layer'], function(){

    let $ = layui.$,
        layer = layui.layer;

    let myChart = echarts.init(document.getElementById('dramaCount'), 'dark');
    let myChart2 = echarts.init(document.getElementById('dramaCount2'), 'dark');
    let myChart3 = echarts.init(document.getElementById('dramaCount3'), 'dark');
    let myChart5 = echarts.init(document.getElementById('dramaCount5'), 'dark');
    let smoothed = echarts.init(document.getElementById('tblResult'), 'dark');



    $.post(
        "/logic/drama/count/scorecount",
        {},
        function (result) {
            console.log(result);

            let score = [];
            let count = [];

            let pieCount = [];

            //为柱状图组装数据
            for (i = 0;i<result.length;i++){
                score.push(result[i].tvScore);
                count.push(result[i].count);

                //  if(i <= 10)
                //     pieCount.push({'value':result[i].count, 'name':result[i].tvScore});
            }

            //柱状图：制定图表的配置项和数据
            let option = {
                title: {
                    text: '电视剧评分统计',
                    subtext: '数据来自豆瓣'
                },
                tooltip: {},
                legend: {
                    data:['评分']
                },
                xAxis: {
                    data: score
                },
                yAxis: {},
                series: [{
                    name: '评分：部数',
                    type: 'bar',
                    data: count
                }]
            };
            /*普通折线图
          let option2 = {
              xAxis: {
                  type: 'category',
                  data: score
              },
              yAxis: {
                  type: 'value'
              },
              series: [{
                  data: count,
                  type: 'line'
              }]
          };*/




            myChart.setOption(option);

        })

    $.post(
        "/logic/drama/count/typecount",
        {},
        function (result) {
            console.log(result);

            let type = [];
            let count = [];

            let pieCount = [];


            //为柱状图组装数据
            for (i = 0;i<result.length;i++){
                type.push(result[i].type);
                count.push(result[i].count);

                //  if(i <= 10)
                pieCount.push({'value':result[i].count, 'name':result[i].type});
            }
            /*
                        //柱状图：制定图表的配置项和数据
                        let option = {
                            title: {
                                text: '豆瓣电视剧类型统计',
                                subtext: '数据来自豆瓣'
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow'
                                }
                            },
                            legend: {
                                data: ['豆瓣电视剧']
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis: {
                                type: 'value',
                                boundaryGap: [0, 0.01]
                            },
                            yAxis: {
                                type: 'category',
                                data: type
                            },
                            series: [
                                {
                                    name: '部数',
                                    type: 'bar',
                                    data:count
                                }
                            ]
                        };*/
            let option = {
                title: {
                    text: '豆瓣电视剧类型统计',
                    subtext: '数据来自豆瓣',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: type
                },
                series: [
                    {
                        name: '类型',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: pieCount,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };/*
            let option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: type
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: pieCount
                    }
                ]
            };*/


            myChart2.setOption(option);

        })
    $.post(
        "/logic/drama/count/languagecount",
        {},
        function (result) {
            console.log(result);

            let language = [];
            //let count = [];

            let pieCount = [];

            //组装数据
            for (i = 0;i<result.length;i++){

                language.push(result[i].language);
                //count.push(result[i].count);

                //  if(i <= 10)
                pieCount.push({'value':result[i].count, 'name':result[i].language});
            }

            //柱状图：制定图表的配置项和数据
            let option = {
                title: {
                    text: '电视剧语言统计',
                    subtext: '数据来自豆瓣',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: language
                },
                series: [
                    {
                        name: '语言：',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: pieCount
                    }
                ]
            };




            myChart3.setOption(option);

        })

    $.post(
        "/logic/drama/count/timescore",
        {},
        function (result) {
            console.log(result);

            let score = [];
            let time = [];

            let pointCount = [];

            //为柱状图组装数据
            for (i = 0;i<result.length;i++){
                score.push(result[i].tvScore);
                time.push(result[i].tvTime);

                //  if(i <= 10)
                pointCount.push([result[i].tvTime, result[i].tvScore]);
                     //pointCount.push({'value':result[i].count, 'name':result[i].tvScore});
            }

            //散点图：制定图表的配置项和数据
            let option = {
                title: {
                    text: '电视剧上映时间-评分关系',
                    subtext: '数据来自豆瓣',
                    left: 'center'
                },
                xAxis: {
                    scale: true
                },
                yAxis: {
                    scale: true
                },
                series: [{
                    type: 'effectScatter',
                    symbolSize: 30,
                    data: [
                        [2016, 9.9],
                        [2020, 3.1]
                    ]
                }, {
                    type: 'scatter',
                    data: pointCount,
                }]
            };






            myChart5.setOption(option);

        })

    $('#btnEcharts').click(function (event) {
        $.post(
            "/logic/drama/actorTime",
            {
                'tvActors' : $('#tvActors').val()
            },
            function (result) {
                console.log(result);
                let score = [];
                let time = [];
               // let pieCount = [];

                //为线型图组装数据
                for (i = 0;i<result.length;i++){
                    score.push(result[i].tvScore);
                    time.push(result[i].tvTime);

                    //if(i <= 10)
                     //   pieCount.push({'time':result[i].tvTime, 'value':result[i].tvScore});
                }


                let option = {
                    title: {
                        text: '该演员电视剧上映时间及评分',
                        subtext: '数据来自豆瓣',
                        left: 'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: time
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: score,
                        type: 'line',
                        smooth: true
                    }]
                };


                smoothed.setOption(option);
            }
        )
    })

});