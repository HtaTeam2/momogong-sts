

//채팅창 비동기
function chat(){
	axios({
		method : "GET",
		url : 'http://localhost:3000', 
	}).catch(function(errorMsg){
		alert("실행중 문제 발생 : " + errorMsg);
	});
}


document.querySelector('#chat').addEventListener(chat);

