function memberPaging(page){
	if($('#value').val() == '')
		location.href = "/member/list?page=" + (page-1);
	else {
		//let columnName = $('#columnName').val();
		//let value = $('#value').val();
		//location.href = "/member/getSearchList?columnName="+columnName+"&value="+value+"&page=" + (page-1);
		$('#page').val(page-1);
		$('#searchListBtn').trigger('click');
	}
}


$(function(){
	$('#searchListBtn').click(function(){
	//searchListBtn	
			alert($('#columnName').val() + ", " + $('#value').val() + ", " + $('#page').val());
		if($('#value').val() == '') {
			alert('검색어를 입력하세요.')
		} else {
			$.ajax({
				type: 'get',
				url : '/member/getSearchList',
				data : {'columnName' : $('#columnName').val(),
					'value' : $('#value').val(),
					'page' : $('#page').val() },
				dataType : 'json',
				success : function(data) {
					console.log(data);
					
					$('#listTable tr:gt(0)').remove();
					
					$.each(data.list.content, function(index, item){
						let result = `<tr>`
						+ `<td align="center">`	+ item.seq +  `</td>`	
						+ `<td align="center">`	+ item.id +  `</td>`	
						+ `<td align="center">`	+ item.pwd +  `</td>`	
						+ `<td align="center">`	+ item.name +  `</td>`	
						+ `</tr>` ;
						$('#listTable').append(result);
					});//$.each()
					
				},
				error : function(e) {
					console.log(e)
				}
			});
		}
		
	}) //2nd if  
	
});//function