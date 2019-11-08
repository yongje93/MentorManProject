//$(document).ready(function(){
//	$.ajax({
//		type : 'post',
//		url : '/memtorman/board/assayList',
//		data : '',
//		dataType : 'json',
//		success : function(data){
//			$.each(data.list, function(index, items){
//				$('<div/>',{
//					class : 'row no-gap'
//				}).append($('<div/>',{
//					class : 'card mentor-post-card mentor_post_6589'
//				}).append($('<div/>',{
//					class : 'card-header'
//				}).append($('<a/>',{
//					class : 'color-black',
//					type : 'external',
//					href : '/memtros/11087'
//				}).append($('<div>')).append($('<div/>',{
//					class : 'mentor-info'
//				}).append($('<div/>')).append($('<strong/>',{
//					class : 'mentor-name'
//				}))))
//				
//				
//				
//				
//				$('<div/>',{
//					class : 'row no-gap'
//				}).append($('<div/>',{
//					class : 'mentor-image img-circle'
//				}).append($('<img/>',{
//					src : '../storage/'
//				}))).append()
//			});
//		},
//		error : function(){
//			alert('응 안돼~~');
//		}
//	});
//});

$('#essayWriteBtn').on('click', function(){
	location.href="/mentor/essayboard/essayboardWriteForm";
});