let data;


//<div><a href>
function drawTable(list){
	console.log(list[0]);
	document.getElementById("searchList").innerHTML = ``;
	let title = document.querySelector('#study').value;
	let h2 = document.createElement("h2");
	h2.innerText = '\'' + title + '\' 검색 결과';
	h2.setAttribute('style','font-size: 25px;');
	document.getElementById("searchList").appendChild(h2);
	
	let table = document.createElement("table");  
	table.classList.add('w3-table');

	let tr = document.createElement("tr");
	let td = document.createElement("td");
	//	InvalidCharacterError: Failed to execute 'add' on 'DOMTokenList': The token provided ('w3-center w3-third') contains HTML space characters, which are not valid in tokens.
	list.forEach(function(item){
		td = document.createElement("td"); //<td></td>
		td.classList.add('w3-third');
		td.classList.add('w3-center');
		td.classList.add('w3-container'); 
		let img = document.createElement('img'); //<img></img>
		img.src = './images/profile.png';
		//location.href="/StdGroup/insert/" + item['roomNo'] 
		//"\'location.href=\"/StdGroup/insert/\"" + item['roomNo'] + "\'"
		img.setAttribute('onclick', 'location.href=\"StdGroup/insert/' + item['roomNo'] + '\"');
		img.classList.add('w3-hover-opacity');
		img.setAttribute('width','60%');
		console.log('출력: ' + item['roomNo']); //Object 타입
		td.appendChild(img);	//<td><img></img></td>
		tr.appendChild(td);		//<tr><td><img></img></td><td><img></img></td></tr>
	})
	table.appendChild(tr);
	tr = document.createElement("tr");
	list.forEach(function(item){
		td = document.createElement("td");
		td.classList.add('w3-third');
		td.classList.add('w3-center');
		td.classList.add('w3-container');
		let a = document.createElement("a");
		a.setAttribute('href', 'StdGroup/insert/' + item['roomNo']);
		a.innerText = item['roomTitle'];
		console.log('출력: ' + item['roomTitle']);
		td.appendChild(a);
		tr.appendChild(td);
	})
	
	table.appendChild(tr);
	return document.getElementById("searchList").appendChild(table);
}

function dataReceive(title){
	axios({
		method : "POST",
		url : "StdList/search/" + title
	}).then(function(resData){
		data = resData.data;
		console.log('넘어온 데이터' + data);
		//data타입이 object가 아니면 json이 아닌 예외 메세지가 왔다는 뜻
		if(typeof(data) == "string"){
			alert("실행중 문제 발생 : " + data);
		}else if(typeof(data) == "object") {
			drawTable(data);
		}
	}).catch(function(errorMsg){
		alert("실행중 문제 발생 : " + errorMsg);
	});
}

document.querySelector('#btn1').addEventListener('click', function () {
	//title: study input 태그의 값
	let title = document.querySelector('#study').value;
	console.log(title);
	dataReceive(title);
	title.innerText = '';
});

document.querySelector('#cate').addEventListener('click', function () {
	//title: study input 태그의 값
//	let title = document.querySelector('#study').value;
//	console.log(title);
//	dataReceive(title);
//	title.innerText = '';
});