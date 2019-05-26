function checkName() {
	var name = document.getElementById('username').value.trim();
	var userMsg = document.getElementById('userMsg');
	if (name === '' || name === null) {
		userMsg.innerHTML = '用户名不能为空';
	} else {
		//如果用户名不为空判断是否已经存在这个用户名
		$.post('IsAlreadyExistTheUsernameServlet', {
			'username': name
		}, function(data) {
			if (data === 'true') {
				userMsg.innerHTML = '该用户名已被注册';
			} else if (data === 'false') {
				userMsg.innerHTML = '用户名合法';
			} else {
				userMsg.innerHTML = '';
			}
		})
	}
}
	
function checkPwd() {
	var pwd = document.getElementById('pwd').value.trim();
	var pwdMsg = document.getElementById('pwdMsg');
	if (pwd === '' || pwd === null) {
		pwdMsg.innerHTML = '密码不能为空';
	} else if(!(pwd.length >= 6 && pwd.length <= 12)) {
		pwdMsg.innerHTML = '密码长度有误';
	} else {
		pwdMsg.innerHTML = '';
	}
}
	
function checkPwd2() {
	var pwd = document.getElementById('pwd').value.trim();
	var pwd2 = document.getElementById('pwd2').value.trim();
	var pwdMsg2 = document.getElementById('pwdMsg2');
	if (pwd2 === '' || pwd2 === null) {
		pwdMsg2.innerHTML = '确认密码不能为空';
	} else if (pwd !== pwd2) {
		pwdMsg2.innerHTML = '两次密码不一致';
	} else {
		pwdMsg2.innerHTML = '';
		}
}
	
	
function checkEmpName() {
	var empName = document.getElementById('empName').value.trim();
	var empNameMsg = document.getElementById('empNameMsg');
	if (empName === '' || empName === null) {
		empNameMsg.innerHTML = '真实姓名不能为空';
	} else {
		empNameMsg.innerHTML = '';
	}
}
	
function checkTel() {
	var tel = document.getElementById('tel').value.trim();
	var telMsg = document.getElementById('telMsg');
	if (tel === '' || tel === null) {
		telMsg.innerHTML = '电话号码不能为空';
	} else if(tel.length != 11){
		telMsg.innerHTML = '长度必需是11位';
	} else{
		telMsg.innerHTML = '';
	}
}
	

function checkEmail() {
	var email = document.getElementById('email').value.trim();
	var emailMsg = document.getElementById('emailMsg');
	var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+((\.[a-zA-Z0-9]{2,3}){1,2})$/;
	if (email === '' || email === null) {
		emailMsg.innerHTML = '邮箱不能为空';
	} else if(!regEmail.test(email)) {
		emailMsg.innerHTML = '邮箱格式不正确';
	} else {
		emailMsg.innerHTML = '';
	}
}
	
	
function check() {
	var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+((\.[a-zA-Z0-9]{2,3}){1,2})$/;
	var name = document.getElementById('username').value.trim();
	var pwd = document.getElementById('pwd').value.trim();
	var pwdMsg = document.getElementById('pwdMsg');
	var userMsg = document.getElementById('userMsg');
	var pwd2 = document.getElementById('pwd2').value.trim();
	var pwdMsg2 = document.getElementById('pwdMsg2');
	var empName = document.getElementById('empName').value.trim();
	var empNameMsg = document.getElementById('empNameMsg');
	var tel = document.getElementById('tel').value.trim();
	var telMsg = document.getElementById('telMsg');
	var email = document.getElementById('email').value.trim();
	var emailMsg = document.getElementById('emailMsg');
	var form1 = document.getElementById('form1');
	$.post('IsAlreadyExistTheUsernameServlet', {
		'username': name
	}, function(data) {
		if (data === 'true') {
			userMsg.innerHTML = '该用户名已被注册';
		} else if (name === '' || name === null) {
			userMsg.innerHTML = '用户名不能为空';
		} else if (pwd === '' || pwd === null) {
			pwdMsg.innerHTML = '密码不能为空';
		} else if (pwd2 === '' || pwd2 === null) {
			pwdMsg2.innerHTML = '确认密码不能为空';
		} else if (pwd !== pwd2) {
			pwdMsg2.innerHTML = '两次密码不一致';
		} else if (empName === '' || empName === null) {
			empNameMsg.innerHTML = '真实姓名不能为空';
		} else if (tel === '' || tel === null) {
			telMsg.innerHTML = '电话号码不能为空';
		} else if(tel.length != 11){
			telMsg.innerHTML = '长度必需是11位';
		} else if (email === '' || email === null) {
			emailMsg.innerHTML = '邮箱不能为空';
		} else if(!regEmail.test(email)) {
			emailMsg.innerHTML = '邮箱格式不正确';
		} else {
			form1.submit();
		}
	})
}