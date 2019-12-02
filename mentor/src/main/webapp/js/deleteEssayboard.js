function essayDeleteCheck(seq) {
	var toastWithCallback = app.toast.create({
		text: '정말로 삭제하시겠습니까?',
		position: 'center',
		closeButton: true,
		on: {
			close: function() {
				$.ajax({
					type : 'get',
					url : '/mentor/essayboard/essayboardDelete',
					data : {'seq' : seq},
					success : function(data){
						location.href='/mentor/essayboard/essayboardList';
					},
					error : function(){
						
					}
				});
			}
		}
	});	
	toastWithCallback.open();
}