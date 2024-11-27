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