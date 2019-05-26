
//定义一个员工列表数组
var empIdList = [];
//定义一个原先的员工列表数组
var oldEmpIdList = [];

var length = $('#selSelectedEmployees').find('option').length;
$('#mNum').val(length);
$('#showmNum').html(length);

var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();                
    }
}



function clearSelectedEmployees(){
	 var selEmployees=document.getElementById("selEmployees");
	 var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
     for(var i=0;i<selSelectedEmployees.options.length;i++){
         selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
     }
     $('#selSelectedEmployees').empty();
     setSelected();
}

 function selectEmployees(){
 		var selEmployees=document.getElementById("selEmployees");
 		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     

 		 //每次点击添加的时候获取当前已经添加的员工id
 		 oldEmpIdList = $('#empIdList').val();
 		 var a = oldEmpIdList.replace("[", "");
 		 var b = a.replace("]", "");
 		 empIdList = b.split(",");
         $('#mNum').val(empIdList.length-1);
         $('#showmNum').html(empIdList.length-1);
 		 $('#empIdList').val(empIdList);
 		
 		for(var i=0;i<selEmployees.options.length;i++){
            if (selEmployees.options[i].selected){
            	
                var opt=new Option(selEmployees.options[i].text,selEmployees.options[i].value);
                
                //这里是选中的员工的id
//                console.log(opt)
                //当选中员工的时候让员工数组中增加该员工的id
                empIdList.push(opt.value);
                $('#mNum').val(empIdList.length-1);
                $('#showmNum').html(empIdList.length-1);
                $('#empIdList').val(empIdList);
                selSelectedEmployees.options.add(opt);
            }
        }
 		setTimeout(function() {
 			for(var i=0;i<selEmployees.options.length;i++){
 		        if (selEmployees.options[i].selected){
	 	        	selEmployees.options.remove(i);
	 	        	i--;
	 	        }
	 		}
 		}, 30);
    }

function deSelectEmployees(){
		var selEmployees=document.getElementById("selEmployees");
 		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
        for(var i=0;i<selSelectedEmployees.options.length;i++){
            if (selSelectedEmployees.options[i].selected){
                var value = selSelectedEmployees.options[i].value;
                var option = document.createElement('option');
                option.value = value;
                option.text = selSelectedEmployees.options[i].text;
                selEmployees.appendChild(option);
            	//从已选中员工中移除的员工id
            	//循环已选中的员工数组 把该员工删除
            	for (var j = 0; j < empIdList.length; j ++) {
            		if (empIdList[j] === value) {
            			empIdList.splice(empIdList.indexOf(value), 1);
            		}
            	}
                $('#mNum').val(empIdList.length-1);
                $('#showmNum').html(empIdList.length-1);
                $('#empIdList').val(empIdList);
            }
        }
        setTimeout(function() {
 			for(var i=0;i<selSelectedEmployees.options.length;i++){
 		        if (selSelectedEmployees.options[i].selected){
 		        	selSelectedEmployees.options.remove(i);
	 	        	i--;
	 	        }
	 		}
 		}, 30);
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


//设置表单中的开始时间，比当前时间多一小时
var now = new Date();
now.setHours(now.getHours() + 1);
var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
$("#startTime").val(str);


//设置表单中的结束时间，比当前时间多24小时
var now = new Date();
now.setHours(now.getHours() + 24);
var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
$("#endTime").val(str);


//将日期格式化为两位，不足补零
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

function ifCanUse() {
	
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
	} else {
		$('#endTimeMsg').html('');
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
    
	
	
	//发送到servlet检查该会议室是否被占用
	if (flag) {
		$.post('IfTheMRUsedServlet', {
			'startTime':startTime,
			'endTime':endTime,
			'mrId': mrId
		}, function (data) {
			if (data.trim() === 'true') {
				//被占用
				flag = false;
				$('#mrIdMsg').html('该会议室已被占用');
			} else {
				//未被占用
				$('#mrIdMsg').html('');
			}
		})
	}
	
}


function confirmBook() {
	ifCanUse();
	
	if (flag) {
		$('#form1').submit();
	}
}


