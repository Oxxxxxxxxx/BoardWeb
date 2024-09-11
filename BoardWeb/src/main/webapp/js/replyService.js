/**
 * 
 */


/* ---------------------------------------------------------
	날짜포멧 : yyyy-MM-dd HH:mm-ss 반환하는메소드를 Date객체 추가
--------------------------------------------------------- */
Date.prototype.dateFormat = function() {
	let yyyy = this.getFullYear();
	let MM = ('0' + (this.getMonth() + 1)).slice(-2); // 09, 10
	let dd = ('0' + this.getDay()).slice(-2);

	let HH = ('0' + this.getHours()).slice(-2);
	let mm = ('0' + this.getMinutes()).slice(-2);
	let ss = ('0' + this.getSeconds()).slice(-2);

	return yyyy + '-' + MM + '-' + dd + ' ' + HH + ':' + mm + ':' + ss; // 2024-09-12 12:23:33
}



/* ---------------------------------------------------------
	ReplyVO 값을 tr생성해주는 함수
--------------------------------------------------------- */

let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];
//댓글정보 -> tr>td*4 생성 반환.
function makeRow(reply = {}) {
	console.log('reply=> ', reply);
	let tr = document.createElement('tr');
	tr.setAttribute('data-rno', reply.replyNo);
	//체크박스 생성.
	let btn = document.createElement('input'); // <button>삭제</button>
	btn.setAttribute('type', 'checkbox');

	let td = document.createElement('td'); // <td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td); //<tr>td.....<td><button>삭제</button></td></tr>

	// td생성
	fields.forEach(field => {
		let td = document.createElement('td');
		if (field == 'replyDate') {
			let today = new Date(reply[field]); //날짜문자 -> 날짜객체.dataFormat() 출력.
			td.innerHTML = today.dateFormat();
		}
		else {
			td.innerHTML = reply[field];
		}
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

// 서비스 메소드를 통해서 ajax 기능을 실행
// 1.목록, 2.삭제, 3.추가, 4.....
const svc = {
	replyList: function(param = { bno: 1, page: 1 }, successCallback, errorCallback) {
		fetch('replyList.do?bno=' + param.bno + '&page=' + param.page)
			.then(resolve => resolve.json())
			.then(successCallback)
			.catch(errorCallback)
	},
	removeReply(rno, successCallback, errorCallback) {
		fetch('removeReply.do?rno=' + rno)
			.then(resolve => resolve.json())
			.then(successCallback)
			.catch(errorCallback)
	},
	/* 댓글추가 메소드 */
	addReply(param = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do', {
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer
		})
			.then(resolve => resolve.json())
			.then(successCallback)
			.catch(errorCallback)
	},
	/* 댓글건수를 가져와서 페이징 정보를 생성 replyPagingCount */
	replyPagingCount(bno = 1, successCallback, errorCallback){
		fetch('replyCount.do?bno=' + bno)
			.then(resolve => resolve.json())
			.then(successCallback)
			.catch(errorCallback)
	}
	
}
	

	
	
	


result = {}
