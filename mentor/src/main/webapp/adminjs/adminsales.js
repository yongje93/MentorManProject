/** 
 * @Title : 매출 게시판 js파일입니다
 * @author : 안상구
 * @date : 2019. 11. 29.
 * 이해 안가시는 부분이있으면 연락주세요
 */
$(function(){
	chartHandler();
});

function chartHandler(){
	getSalesList().then(getData).catch();
	getMonthSalesList().then(getMonthData).catch();
	getMentorSalesList().then(getMentorData).catch();
}

function getSalesList(){
	return $.ajax({
		type : 'post',
		url : '/mentor/adminsales/daySalesChart',
		datType : 'json'
	});
}
function getData(data){
	var chartData = [];
	var chartDate = [];
	var result =[];
	$.each(data,function(index,item){
		chartData.push([item.ORDER_DATE,item.SALES]);
	});
	result.push(chartData)
	makeChart(chartData);
}
function makeChart(chartData){
	Highcharts.setOptions({
        lang: {
            thousandsSep: ','
        }
    });
	Highcharts.stockChart('dayChart',{
		rangeSelector:{
			selected:2
		},

		title:{
			text:'일별 매출'
		},
		yAxis: {
		        labels : {
					format: '{value:,.0f}',
				}
	      },
		series:[{
			 /*name:'아아아',*/ 
			data: chartData,
			tooltip:{
				pointFormat:'<b>{point.y:,0f}원</b><br/>',
				valueDecimals:2
			}
		}],
	});
}
/*월별 차트---------------------------------------------------------------------------------------------------*/
function getMonthSalesList(){
	return $.ajax({
		type : 'post',
		url : '/mentor/adminsales/monthSalesChart',
		datType : 'json'
	});
}
function getMonthData(data){
	var monthData = [];
	var monthDate = [];
	$.each(data,function(index,item){
		monthDate.push(item.ORDER_DATE);
		monthData.push(item.SALES);
	});
	makeMonthChart(monthDate,monthData);
}
function makeMonthChart(monthDate,monthData){
	Highcharts.setOptions({
        lang: {
            thousandsSep: ','
        }
    });
	Highcharts.chart('monthChart',{
		 chart: {
		        renderTo: 'chart',
		        type: 'column',
		        inverted: false
		      },
		      width: '100',
		      colors: ['#90EE90'],
		      title: {
		        text: '월별 매출'
		      },
		      xAxis: {
		        categories: monthDate,
		        /*scrollbar: { enabled: true },*/
		        tickLength: 0
		      },
		      yAxis: {
		    	  title: {
			            text: '단위 : 원',
			            align: 'high'
			        },
			        labels : {
						format: '{value:,.0f}',
					},
		      },
		      series: [{
		        name: '월별 매출',
		        data: monthData
		      }]
	});
}

/*멘토별 차트-------------------------------------------------------------------------------*/
function getMentorSalesList(){
	return $.ajax({
		type : 'post',
		url : '/mentor/adminsales/mentorSalesChart',
		datType : 'json'
	});
}
function getMentorData(data){
	var mentorData = [];
	var salesData = [];
	$.each(data,function(index,item){
		mentorData.push(item.MEMBER_NAME);
		salesData.push(item.SALES);
	});
	makeMonthChart(mentorData,salesData);
}
function makeMonthChart(mentorData,salesData){
	Highcharts.chart('mentorChart', {
	    chart: {
	        type: 'pie'  
	    },
	    title: {
	        text: '멘토별 판매율순위'
	    }, 
	    xAxis: {
	    	categories: mentorData,
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	            	formatter: function() {
	            		var sliceIndex = this.point.index;
	                    var sliceName = this.series.chart.axes[0].categories[sliceIndex];
	                    return sliceName
	                  }
	            },
	            showInLegend:true
	        }
	    },
	    legend: { enabled: false },
	    series: [{
	    	name : '판매율',
	        data: salesData
	    }] 
	});
}