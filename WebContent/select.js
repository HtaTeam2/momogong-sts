let data2;


// 검색 화면
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
	
	list.forEach(function(item){
		let td = document.createElement("td"); // <td></td>
		td.classList.add('w3-third');
		td.classList.add('w3-center');
		td.classList.add('w3-container'); 
		let img = document.createElement('img'); // <img></img>
		img.src = '../../team_studyroom/images/profile.png';
		// location.href="/StdGroup/insert/" + item['roomNo']
		// "\'location.href=\"/StdGroup/insert/\"" + item['roomNo'] + "\'"
		img.setAttribute('onclick', 'location.href=\"../../team_studyroom/StdGroup/insert/' + item['roomNo'] + '\"');
		img.classList.add('w3-hover-opacity');
		img.setAttribute('width','60%');
		console.log('출력: ' + item['roomNo']); // Object 타입
		let br = document.createElement("br"); // <a></a>
		let a = document.createElement("a"); // <a></a>
		a.setAttribute('href', 'StdGroup/insert/' + item['roomNo']);
		a.innerText = item['roomTitle'];
		
		let form = document.createElement("form");
		form.action = 'StdList/oneRoom/' + item['roomNo'];
		let button = document.createElement("button");
		button.type = 'submit';
		button.classList.add('w3-button');
		button.classList.add('w3-white');
		button.classList.add('w3-hide-small');
		button.innerText = '정보보기';
		
		td.appendChild(img);	// <td><img></img></td>
		td.appendChild(br);	// <td><img></img><br></td>
		td.appendChild(a);	// <td><img></img><a></a></td>
		
		br = document.createElement("br");
		
		td.appendChild(br);	// <td><img></img><br><a></a><br></td>
		form.appendChild(button);	// <td><img></img><br><a></a><br></td>
		td.appendChild(form);	// <td><img></img><br><a></a><br></td>
		tr.appendChild(td);		// <tr><td><img></img></td><td><img></img></td></tr>
	})
	
	table.appendChild(tr);
	return document.getElementById("searchList").appendChild(table);
}


// 스터디 검색
function dataReceive(title){
	axios({
		method : "POST",
		url : "../../team_studyroom/StdList/search/" + title
	}).then(function(resData){
		data2 = resData.data;
		console.log('넘어온 데이터' + data2);
		// data타입이 object가 아니면 json이 아닌 예외 메세지가 왔다는 뜻
		if(typeof(data2) == "string"){
			alert("실행중 문제 발생 : " + data2);
		}else if(typeof(data2) == "object") {
			drawTable(data2);
		}
	}).catch(function(){
		alert("실행중 문제 발생 : " + "값을 입력해주세요.");
	});
}

document.querySelector('#btn1').addEventListener('click', function () {
	// title: study input 태그의 값
	let title = document.querySelector('#study').value;
	console.log(title);
	dataReceive(title);
	title.innerText = '';
});

