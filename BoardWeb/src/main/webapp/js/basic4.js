/**
 * 
 */

console.log(json); // 문자열
let data = JSON.parse(json); //문자열 -> 객체변경
console.log(data); //객체
console.clear();


let addBtn = document.querySelector('#searchBtn');
addBtn.addEventListener('click', function(e){
	
})

let result = data.filter(function(item, idx, ary){
			if(item.Salary >= 8000 && item.gender == 'Female'){
			//console.log(item);
			return true;
	}		
});
console.log(result);  
  
  