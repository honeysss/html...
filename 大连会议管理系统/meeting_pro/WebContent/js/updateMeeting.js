
//定义一个所有员工列表数组
var allEmpIdList = [];
//定义一个已参加会议的员工列表数组
var joinedEmpIdList = [];

//定义一个未参加的新的员工列表鼠标
var notJoinIdList = [];

//新的员工列表为所有员工列表减掉已经参加的员工的列表

//所有的
$('.allEmpId').each(function() {
	allEmpIdList.push($(this).val());
})

//已参加的
$('.joinedEmpId').each(function() {
	joinedEmpIdList.push($(this).html());
})

var length = joinedEmpIdList.length;
$('#mNum').val(length);
$('#showmNum').html(length);


$('#empIdList').val(joinedEmpIdList);

notJoinIdList = deleteSameValue(allEmpIdList, joinedEmpIdList);

//数组去重
function deleteSameValue(array1, array2){
    //临时数组存放
    var tempArray1 = [];//临时数组1
    var tempArray2 = [];//临时数组2
 
    for (var i = 0; i < array2.length; i++) {
      tempArray1[array2[i]] = true;//将数array2 中的元素值作为tempArray1 中的键，值为true；
    }
 
    for (var i = 0; i < array1.length; i++) {
      if (!tempArray1[array1[i]]) {
        tempArray2.push(array1[i]);//过滤array1 中与array2 相同的元素；
      }
    }
    return tempArray2;
  }


$('.notJoinIdList').each(function() {
	if (notJoinIdList.indexOf($(this).val()) === -1) {
		$(this).remove();
	}
})



function clearSelectedEmployees(){
	 var selEmployees=document.getElementById("selEmployees");
	 var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
     for(var i=0;i<selSelectedEmployees.options.length;i++){
         selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
     }
     $('#selSelectedEmployees').empty();
     setSelected();
}

 function selectEmployees(mId){
	 var addEmpIdList = [];
 		var selEmployees=document.getElementById("selEmployees");
 		for(var i=0;i<selEmployees.options.length;i++){
            if (selEmployees.options[i].selected){
                addEmpIdList.push(selEmployees.options[i].value)
            }
        }
 		var startTime = $('#startTime').val();
 		var endTime = $('#endTime').val();
 		var mrId = $('#mrId').val();
 		var mName = $('#mName').val().trim();
 		var mDescribe = $('#mDescribe').val().trim();
 		changeToPostMethod('AddEmpToMeetingServlet', {
 			'mName':mName, 
 			'mDescribe': mDescribe,
 			'startTime':startTime, 
 			'endTime': endTime,
 			'addEmpIdList': addEmpIdList,
 			'mrId': mrId,
 			'mId': mId
 		})
 		
    }
 
 
 
 
 

 //删除员工时
 function deleteEmp(empId, mId) {
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var mrId = $('#mrId').val();
	var mName = $('#mName').val().trim();
	var mDescribe = $('#mDescribe').val().trim();
	changeToPostMethod('DeleteEmpFromMeeting', {
		'mName':mName, 
		'mDescribe': mDescribe,
		'startTime':startTime, 
		'endTime': endTime,
		'mId': mId,
		'mrId': mrId,
		'empId': empId
	})
 }
 

 
 var xmlHttp;

 function createXMLHttpRequest() {
     if (window.ActiveXObject) {
         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
     } 
     else if (window.XMLHttpRequest) {
         xmlHttp = new XMLHttpRequest();                
     }
 }
 
 
 function callback() {
    clearEmployees();
    var selEmployees=document.getElementById("selEmployees");
     if (xmlHttp.readyState == 4) {
         if (xmlHttp.status == 200) {
             var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
             for (var i = 0; i < elements.length; i++) {
                 var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
                 var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
                 selEmployees.options.add(new Option(text,value));
             }       
         }
     }
       
 }

function clearEmployees(){
 	document.getElementById("selEmployees").options.length=0;
}
	
	
//当部门发生变化时 将部门id 员工idList传到后台 得到新的员工列表
function changeDeptId() {
	var deptId = $('#deptId').val();
	var empIdList = $('#empIdList').val();
	createXMLHttpRequest();	
	var url = "ChangeDeptIdServlet?deptId=" + escape(deptId) + "&empIdList=" + escape(empIdList);           
	xmlHttp.open("GET", url, true);     
	xmlHttp.onreadystatechange = callback;
	xmlHttp.send(null);
}

function convertDateFromString(dateString) { 
	if (dateString) { 
		var arr1 = dateString.split(" "); 
		var sdate = arr1[0].split('-'); 
		var sdate2 = arr1[1].split(':'); 
		var date = new Date(sdate[0], sdate[1]-1, sdate[2], sdate2[0], sdate2[1]); 
		return date;
	} 
}





//将日期格式化为两位，不足补零
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}


var startTime = convertDateFromString($('#time1').html().trim());
var startTime2 = startTime.getFullYear() + "-" + fix((startTime.getMonth() + 1), 2) + "-" + fix(startTime.getDate(), 2) + "T" + fix(startTime.getHours(), 2) + ":" + fix(startTime.getMinutes(), 2);
$('#startTime').val(startTime2);

var endTime = convertDateFromString($('#time2').html().trim());
var endTime2 = endTime.getFullYear() + "-" + fix((endTime.getMonth() + 1), 2) + "-" + fix(endTime.getDate(), 2) + "T" + fix(endTime.getHours(), 2) + ":" + fix(endTime.getMinutes(), 2);
$('#endTime').val(endTime2);




function ifCanUse(theMrId) {
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var mrId = $('#mrId').val();

	var flag = true;
	startTimeDate = Number(new Date(startTime).getTime());
	endTimeDate = Number(new Date(endTime).getTime());
	if (startTime === '' || startTime === null) {
		$('#startTimeMsg').html('请输入合法的开始时间');
		flag = false;
	} else {
		$('#startTimeMsg').html('');
	}
	
	if (endTime === '' || endTime === null) {
		$('#endTimeMsg').html('请输入合法的结束时间');
		flag = false;
	} 
	
	//如果开始时间小于当前系统时间 提示 开始时间不能在目前时间之前
	var nowTime = new Date().getTime();

	if (startTimeDate <= nowTime) {
		$('#startTimeMsg').html('开始时间不能在目前时间之前');
    	flag = false;
	} else {
		$('#startTimeMsg').html('');
	}
	//开始时间要在结束时间之前 所以开始时间要小于结束时间
	if (startTimeDate > endTimeDate) {
    	$('#endTimeMsg').html('结束时间要大于开始时间');
    	flag = false;
    } else {
    	$('#endTimeMsg').html('');
    	
    }
	
	if (mrId === '' || mrId === null) {
		$('#mrIdMsg').html('请选择一个会议室');
		flag = false;
	} else {
		$('#mrIdMsg').html('');
	}
	
	if (theMrId === mrId) {
		$('#mrIdMsg').html('');
		flag = false;
	}
	
	
	//发送到servlet检查该会议室是否被占用
	if (flag) {
		$.post('IfTheMRUsedServlet', {
			'startTime':startTime,
			'endTime':endTime,
			'mrId': mrId
		}, function (data) {
			if (data.trim() === 'true') {
				//被占用
				$('#mrIdMsg').html('该会议室已被占用');
			} else {
				//未被占用
				$('#mrIdMsg').html('');
			}
		})
	}
	
}

function confirmUpdate(theMrId) {

	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var mrId = $('#mrId').val();

	var flag2 = true;
	startTimeDate = Number(new Date(startTime).getTime());
	endTimeDate = Number(new Date(endTime).getTime());
	if (startTime === '' || startTime === null) {
		console.log(111)
		$('#startTimeMsg').html('请输入合法的开始时间');
		flag2 = false;
	} else {
		$('#startTimeMsg').html('');
	}
	
	if (endTime === '' || endTime === null) {
		console.log(222)
		$('#endTimeMsg').html('请输入合法的结束时间');
		flag2 = false;
	} 
	
	//如果开始时间小于当前系统时间 提示 开始时间不能在目前时间之前
	var nowTime = new Date().getTime();

	if (startTimeDate <= nowTime) {
		console.log(333)
		$('#startTimeMsg').html('开始时间不能在目前时间之前');
		flag2 = false;
	} else {
		$('#startTimeMsg').html('');
	}
	//开始时间要在结束时间之前 所以开始时间要小于结束时间
	if (startTimeDate > endTimeDate) {
		console.log(444)
    	$('#endTimeMsg').html('结束时间要大于开始时间');
    	flag2 = false;
    } else {
    	$('#endTimeMsg').html('');
    	
    }
	
	if (mrId === '' || mrId === null) {
		console.log(555)
		$('#mrIdMsg').html('请选择一个会议室');
		flag2 = false;
	} else {
		$('#mrIdMsg').html('');
	}
	
	if (theMrId === mrId) {
		if (flag2) {
			$('#form1').submit();
		}
		flag2 = false;
	}
	console.log(flag2)
	//发送到servlet检查该会议室是否被占用
	if (flag2) {
		$.post('IfTheMRUsedServlet', {
			'startTime':startTime,
			'endTime':endTime,
			'mrId': mrId
		}, function (data) {
			if (data.trim() === 'true') {
				//被占用
				$('#mrIdMsg').html('该会议室已被占用');
			} else {
				//未被占用
				$('#form1').submit();
			}
		})
	}
}
