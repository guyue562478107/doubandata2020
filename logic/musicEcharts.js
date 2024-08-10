layui.use(['jquery', 'layer'], function(){

    let $ = layui.$,
        layer = layui.layer;

    let myChart = echarts.init(document.getElementById('musicCount')); //统计分数和人数的条形图
    let myChart2 = echarts.init(document.getElementById('musicCount2'));//统计分数和人数的饼图
    let myChart3 = echarts.init(document.getElementById('musicCount3'));//统计分数和人数的漏斗图
    let smoothed = echarts.init(document.getElementById('artist')); //通过检索实现的评分和专辑发行时间图
    let Chart = echarts.init(document.getElementById('type'));  //统计专辑类型数目的饼图
    let Chart2 = echarts.init(document.getElementById('type2'));//统计专辑类型数目的条形图
    let Chart3 = echarts.init(document.getElementById('type3'));////统计专辑类型数目的漏斗图

    //统计分数和人数的条形图
    $.post(
        "/logic/music/scorecount",
        {},
        function (result) {
            console.log(result);

            let score = [];
            let count = [];
            let pieCount = [];

            //为柱状图组装数据
            for (i = 0;i<result.length;i++){
                score.push(result[i].musicScore);
                count.push(result[i].count);

                if(i <= 10)
                    pieCount.push({'value':result[i].count, 'name':result[i].musicScore});
            }

            let option = {
                title: {
                    text: '专辑/EP分数统计'
                },
                tooltip: {},
                legend: {
                    data:['分数']
                },
                xAxis: {
                    data: score
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


    //统计分数和人数的饼图
    $.post(
        "/logic/music/scorecount",
        {},
        function (result) {
            console.log(result);

            let score = [];
            let count = [];
            let pieCount = [];

            for (i = 0;i<result.length;i++){
                score.push(result[i].musicScore);
                count.push(result[i].count);

                if(i <= 10)
                    pieCount.push({'value':result[i].count, 'name':result[i].musicScore});
            }


            // var data = genData(50);

            let option = {
                title: {
                    text: '专辑/EP分数统计',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right: 50,
                    top: 20,
                    bottom: 20,
                    data: count,

                    // selected: data.selected
                },
                series: [
                    {
                        name: '评分',
                        type: 'pie',
                        radius: '70%',
                        center: ['40%', '50%'],
                        data: score,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            myChart2.setOption(option);

        }
    )


    //统计分数和人数的漏斗图
    $.post(
        "/logic/music/scorecount",
        {},
        function (result) {
            console.log(result);

            let score = [];
            let count = [];
            let pieCount = [];

            for (i = 0;i<result.length;i++){
                score.push(result[i].musicScore);
                count.push(result[i].count);

                if(i <= 10)
                    pieCount.push({'value':result[i].count, 'name':result[i].musicScore});
            }

             let option = {
                title: {
                    text: '专辑/EP分数统计',
                    subtext: '纯属虚构'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c}%"
                },
                toolbox: {
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                legend: {
                    data: score
                },

                series: [
                    {
                        name:'漏斗图',
                        type:'funnel',
                        left: '10%',
                        top: 60,
                        //x2: 80,
                        bottom: 60,
                        width: '80%',
                        // height: {totalHeight} - y - y2,
                        min: 0,
                        max: 100,
                        minSize: '0%',
                        maxSize: '100%',
                        sort: 'descending',
                        gap: 2,
                        label: {
                            show: true,
                            position: 'inside'
                        },
                        labelLine: {
                            length: 10,
                            lineStyle: {
                                width: 1,
                                type: 'solid'
                            }
                        },
                        itemStyle: {
                            borderColor: '#fff',
                            borderWidth: 1
                        },
                        emphasis: {
                            label: {
                                fontSize: 20
                            }
                        },
                        data: count
                    }
                ]
            };

            myChart3.setOption(option);

        }
    )

    //专辑类型数目饼图展示
    $.post(
        "/logic/music/typecount",
        {},
        function (result) {
            console.log(result);

            let type = [];
            let count = [];
            let pieCount = [];

            for (i = 0;i<result.length;i++){
                type.push(result[i].musicType);
                //count.push(result[i].count);

                // if(i <= 10)
                     pieCount.push({'value':result[i].count, 'name':result[i].musicType});
            }

            let option = {
                title: {
                    text: '音乐类型',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    // data: ['Electronic電子','Funk/Soul/R&amp;B',
                    //     'Funk/Soul/R&amp;B放克/灵歌/R&amp;B',
                    //     'Soundtrack原聲','世界音乐','原声','古典','布鲁斯',
                    //     '拉丁','摇滚','放克/灵歌/R&amp;B','民谣',
                    //     '流行','爵士','电子','说唱','轻音乐']
                },
                series: [
                    {
                        name: '类型',
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
            Chart.setOption(option);

        }
    )





    //专辑类型数目条形图展示
    $.post(
        "/logic/music/typecount",
        {},
        function (result) {
            console.log(result);

            let type = [];
            let count = [];
            let pieCount = [];

            for (i = 0;i<result.length;i++){
                type.push(result[i].musicType);
                count.push(result[i].count);

                // if(i <= 10)
                pieCount.push({'value':result[i].count, 'name':result[i].musicType});
            }

            var dataAxis = type;
            var data = count;
            var yMax = 500;
            var dataShadow = [];

            for (var i = 0; i < data.length; i++) {
                dataShadow.push(yMax);
            }

            let option = {
                title: {
                    text: '音乐类型数量',
                    subtext: '点击或滑动放大缩小'
                },
                xAxis: {
                    data: dataAxis,
                    axisLabel: {
                        inside: true,
                        textStyle: {
                            color: 'black'
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    z: 10
                },
                yAxis: {
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#999'
                        }
                    }
                },
                dataZoom: [
                    {
                        type: 'inside'
                    }
                ],
                series: [
                    { // For shadow
                        type: 'bar',
                        itemStyle: {
                            color: 'rgba(0,0,0,0.05)'
                        },
                        barGap: '-100%',
                        barCategoryGap: '40%',
                        data: dataShadow,
                        animation: false
                    },
                    {
                        type: 'bar',
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#2378f7'},
                                        {offset: 0.7, color: '#2378f7'},
                                        {offset: 1, color: '#83bff6'}
                                    ]
                                )
                            }
                        },
                        data: data
                    }
                ]
            };

// Enable data zoom when user click bar.
            var zoomSize = 6;
            myChart.on('click', function (params) {
                console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                myChart.dispatchAction({
                    type: 'dataZoom',
                    startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                    endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                });
            });

            Chart2.setOption(option);
        }


    )


    //统计专辑类型数目的漏斗图展示
    $.post(
        "/logic/music/typecount",
        {},
        function (result) {
            console.log(result);

            let type = [];
            let count = [];
            let pieCount = [];
            for (i = 0;i<result.length;i++){
                type.push(result[i].musicType);
                count.push(result[i].count);

                // if(i <= 10)
                pieCount.push({'value':result[i].count, 'name':result[i].musicType});
            }

            let option = {
                title: {
                    text: '漏斗图',
                    subtext: '纯属虚构',
                    left: 'left',
                    top: 'bottom'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c}%'
                },
                toolbox: {
                    orient: 'vertical',
                    top: 'center',
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: type
                },

                series: [
                    {
                        name: '漏斗图',
                        type: 'funnel',
                        width: '40%',
                        height: '45%',
                        left: '5%',
                        top: '50%',
                        data: pieCount
                    },
                    {
                        name: '金字塔',
                        type: 'funnel',
                        width: '40%',
                        height: '45%',
                        left: '5%',
                        top: '5%',
                        sort: 'ascending',
                        data: pieCount
                    },
                    {
                        name: '漏斗图',
                        type: 'funnel',
                        width: '40%',
                        height: '45%',
                        left: '55%',
                        top: '5%',
                        label: {
                            position: 'left'
                        },
                        data: pieCount
                    },
                    {
                        name: '金字塔',
                        type: 'funnel',
                        width: '40%',
                        height: '45%',
                        left: '55%',
                        top: '50%',
                        sort: 'ascending',
                        label: {
                            position: 'left'
                        },
                        data: pieCount
                    }
                ]
            };

            Chart3.setOption(option);

        }
    )


    //歌手的专辑发行时间及评分
    $('#btnEcharts').click(function (event) {
        $.post(
            "/logic/music/singerTime",
            {
                'musicSinger' : $('#musicSinger').val()
            },
            function (result) {
                console.log(result);
                let score = [];
                let time = [];
                let pieCount = [];

                //为线型图组装数据
                for (i = 0;i<result.length;i++){
                    score.push(result[i].musicScore);
                    time.push(result[i].musicTime);

                    if(i <= 10)
                        pieCount.push({'time':result[i].musicTime, 'value':result[i].musicScore});
                }


                let option = {
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