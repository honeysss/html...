function checkName() {
	var mrName = $('#mrName').val().trim();
	if (mrName === null || mrName === '') {
		$('#mrNameMsg').html('不能为空');
	} else {
		$.post('IfExistMrNameServlet', {
			'mrName': mrName
		}, function (data) {
			if (data.trim() === 'true') {
				$('#mrNameMsg').html('该会议室名称已存在');
			} else {
				$('#mrNameMsg').html('');
			}
		})
	}
}

function check2() {
	var mrName = $('#mrName').val().trim();
	if (mrName === null || mrName === '') {
		$('#mrNameMsg').html('不能为空');
	} else {
		$.post('IfExistMrNameServlet', {
			'mrName': mrName
		}, function (data) {
			if (data.trim() === 'true') {
				$('#mrNameMsg').html('该会议室名称已存在');
			} else {
				$('#form1').submit();
			}
		})
	}
	
}