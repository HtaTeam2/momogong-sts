//요청값에 따라 data value 달라짐
let data;

function drawTable(mems) {
  document.getElementById("members").innerHTML = ``;
//리스트 작성
  let ul = document.createElement("ul");  
//  table.classList.add('w3-table-all');

  let li = document.createElement("li");  
  th.style.textAlign = 'center';
//컬럼 테이블 그리기
//JSON배열에서 key값만 뽑아오기: Object.keys(JSONObject)
	let key = Object.keys(mems[0]); 
	for(v in key){
		th = document.createElement("th");
		th.style.textAlign = 'center';
		th.innerHTML = key[v];
	  //console.log(key[v]);
		tr.appendChild(th);  
		table.appendChild(tr); 
	}
//데이터 테이블 그리기
	list.forEach(function (item) {
	//console.log(item);
    	let tr = document.createElement("tr");
    	tr.classList.add('w3-hover-green');
    	for (let key in item) { 
	      let td = document.createElement("td");
	      td.style.textAlign = 'center';
	      td.innerHTML = item[key]; //item['empno'], item['ename']
	      tr.appendChild(td);
	    };
    table.appendChild(tr);
  });
  return document.getElementById("view").appendChild(table);
}

//전체리스트
function dataReceive(command){
	axios({
		method : "GET",
		url : "company?command="+command //요청 받아서 해당 데이터 가져옴
	}).then(function(resData){
		data = resData.data; //key로 value 구분을 위한 변환 JSON 자동형변환
		//console.log(typeof(data));
		//예외 발생시 JSONObject로 안넘어가서 errorMsg는 string으로 반환
		if(typeof(data) == "string"){
			alert("실행중 문제 발생 : " + data);
		}else if(typeof(data) == "object") {
			drawTable(data);
		}
	}).catch(function(errorMsg){
		alert("실행중 문제 발생 : " + errorMsg);
	});
}


//검색
function dataReceive2(){
	axios({
		method : "GET",
		url : 'http://localhost:3000', 
	}).catch(function(errorMsg){
		alert("실행중 문제 발생 : " + errorMsg);
	});
}

document.querySelector('#btn1').addEventListener('click', function () {
		dataReceive('emps');
});

document.querySelector('#chat').addEventListener(dataReceive2);

document.querySelector('#btn3').addEventListener('click', function () {
		let empno = document.querySelector('#empone').value;
		let check = document.querySelector('#like').checked;
		console.log(check);
		//부분검색 여부
		if(check == false){
			dataReceive2('empOne', empno);
		}else{
			dataReceive2('empLike', empno);
		}
});
