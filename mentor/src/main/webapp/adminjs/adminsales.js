$(function(){
	chartHandler();
});

function chartHandler(){
	getSalesList().then(getData).catch();
}

function getSalesList(){
	return $.ajax({
		type : 'post',
		url : '/mentor/adminsales/adminsalesChart',
		datType : 'json'
	});
}

//function getData(data){
//	var chartData = [];
//	var chartDate = [];
//	$.each(data,function(index,item){
//		chartDate.push(item.ORDER_DATE);
//		chartData.push(item.SALES);
//	});
//	makeChart(chartDate,chartData);
//}
//function makeChart(chartDate,chartData){
//	Highcharts.stockChart('myChart',{
//		rangeSelector:{
//			selected:2
//		},
//
//		title:{
//			text:'모임 매출'
//		},
//		series:[{
//			 name:'아아아', 
//			data: chartData,
//			tooltip:{
//				pointFormat:'<b>{point.y:,0f} 원</b><br/>',
//				valueDecimals:2
//			}
//		}],
//		xAxis : {chartDate : chartDate}
//	});
//}
function getData(data){
	console.log(data);
	var chartData = [];
	var chartDate = [];
	var result =[];
	$.each(data,function(index,item){
		chartDate.push(item.ORDER_DATE);
		chartData.push([item.ORDER_DATE,item.SALES]);
	});
	result.push(chartData)
	makeChart(chartData);
}
function makeChart(chartData){
	Highcharts.stockChart('myChart',{
		rangeSelector:{
			selected:2
		},

		title:{
			text:'모임 매출'
		},
		series:[{
			 name:'아아아', 
			data: chartData,
			tooltip:{
				pointFormat:'<b>{point.y:,0f} 원</b><br/>',
				valueDecimals:2
			}
		}],
//		xAxis : {chartDate : chartDate}
	});
}
