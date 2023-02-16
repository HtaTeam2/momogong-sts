
if(recommcheck>0){
	
    console.log(recommcheck);
    document.getElementById("recommBtn").innerText = "★추천취소★";

    document.querySelector("#recommBtn").addEventListener('click', function(){
        axios({
                url : '../../Recommend/thdown',
                method : 'post',
                data:{
                    commid : commid,
                    memid : memid,
                    recommcheck : recommcheck
                }
        }).then(function(resData){
            alert("비추천")
        }).catch(function(errorMsg){
            alert(errorMsg);
        });
    })



}else{
    console.log(recommcheck);
    console.log(commid);
    console.log(memid);
    
    document.getElementById("recommBtn").innerText = "★추천★";

    document.querySelector("#recommBtn").addEventListener('click', function(){
        axios({
                url : '../../Recommend/thup',
                method : 'post',
                params:{
                    commid : commid,
                    memid : memid,
                    recommcheck : recommcheck
                }
        }).then(function(resData){
            alert("추천")
        }).catch(function(errorMsg){
            alert(errorMsg);
        });
    })

}