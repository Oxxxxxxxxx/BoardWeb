/**
 * 
 */

let replyer = 'user01';
let bno = 148;

const xhtp = new XMLHttpRequest(); //객체생성
xhtp.open('get', 'replyList.do?bno=148'); // 서버 페이지 지정
xhtp.send(); // 서버요청.
xhtp.onload = function() {
	console.log(xhtp.responseText);
	let result = JSON.parse(xhtp.responseText); // json -> 객체
	console.log(result);
	result.forEach(reply => {
		document.querySelector('.list').appendChild(makeRow(reply));
	});
}

let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];
//댓글정보 -> tr>td*4 생성 반환.
function makeRow(reply = {}) {
	let tr = document.createElement('tr');

	//체크박스 생성.
	let btn = document.createElement('input'); // <button>삭제</button>
	btn.setAttribute('type', 'checkbox');

	let td = document.createElement('td'); // <td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td); //<tr>td.....<td><button>삭제</button></td></tr>

	// td생성
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = reply[field];
		tr.appendChild(td);
	})
	//삭제버튼
	btn = document.createElement('button'); // <button>삭제</button>
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRowFnc);

	td = document.createElement('td'); // <td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td); //<tr>td.....<td><button>삭제</button></td></tr>

	return tr;
}

// 삭제처리할 함수
function deleteRowFnc(e) {
	console.log(e.target.parentElement.parentElement.firstChild.innerHTML);
	let rno = e.target.parentElement.parentElement.children[1].innerHTML;
	console.log(e.target.parentElement.parentElement.children[1].innerHTML);
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', 'removeReply.do?rno=' + rno);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);
		if (result.retCode == 'OK') {
			e.target.parentElement.parentElement.remove(); // tr을 삭제
		}
		else if (result.retCode == 'NG') {
			alert('알수 없는 예외 발생');
		}
		else {
			alert('잘못된 반환 코드...');
		}
	}
}

document.querySelector('thead input[type="checkbox"]').addEventListener('change', function(e) {
	document.querySelectorAll('.list input[type="checkbox"]').forEach(item => {
		item.checked = this.checked;
	})
});

//선택삭제 이벤트
document.querySelector('#delChecked').addEventListener('click', delCheckedFuc)

//선택삭제함수 실행.
function delCheckedFnc1(e) {
	// ?rno=21&rno=22&rno=23
	let params = ""
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
		let rno = item.parentElement.nextElementSibling.innerHTML;
		params += rno + "&rno=";
	})

	const delHtp = new XMLHttpRequest();
	delHtp.open('get', "removeReplys.do?" + params);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);

		if (result.retCode == 'OK') {
			//화면상에 체크된 input을 삭제
			document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
				item.parentElement.parentElement.remove();
			})
		}
		else {
			alert('처리중 예외');
		}

	}

}

// 선택삭제함수 반복실행
function delCheckedFuc(e) {
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {

		let rno = item.parentElement.nextElementSibling.innerHTML;
		//input     td              td

		const delHtp = new XMLHttpRequest();
		delHtp.open('get', 'removeReply.do?rno=' + rno);
		delHtp.send();
		delHtp.onload = function() {
			let result = JSON.parse(delHtp.responseText);
			if (result.retCode == 'OK') {
				item.parentElement.parentElement.remove(); // tr을 삭제
			}
			else if (result.retCode == 'NG') {
				alert('알수 없는 예외 발생');
			}
			else {
				alert('잘못된 반환 코드...');
			}
		}
	})
}

// 추가
document.querySelector('#addReply').addEventListener('click', function(e) {
	let reply = document.querySelector('#reply').value;
	const addHtp = new XMLHttpRequest();
	addHtp.open('get', 'addReply.do?bno=' + bno+ '&reply='+reply+'&replyer='+replyer);
	addHtp.send();
	addHtp.onload = function(){
		let result = JSON.parse(addHtp.responseText); // 컨트롤 반환해주는 정보. json문자열
		console.log(result); //retCode, retVal=>{}
		if(result.retCod =='OK'){
			let tr = makeRow(result.reVal);
			document.querySelector('.list').appendChild(tr);
		}
		else{
			alert('처리중 예외.');	
		}
	}
	
})


