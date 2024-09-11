/**
 *  replyBoard.js
 * replyService에 정의된 메소드를 통해서 기능 실행.
 */

/*
 * 댓글목록 그리기.
 */

let pagination;
let page = 1; // 페이지가 변경될때마다 사용해야함.
//DOM 요소를 다 읽은 후 코드를 실행
document.addEventListener("DOMContentLoaded", function(e) {
	/*----------------------------
		  이벤트(댓글등록)
	 -----------------------------*/
	document.querySelector('#addReply').addEventListener('click', addReplyFnc);
	// 댓글 등록

	//페이지링크 클릭
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFnc)
	})
	pagination = document.querySelector('ul.pagination');
	/*----------------------------
		  댓글목록출력
	 -----------------------------*/
	showReplyListAndPagingList();
});



/* ------------------------------
 * 댓글정보 -> li 생성하는 함수.
 * ------------------------------*/
function makeLi(reply = {}) {
	let cloned = document.querySelector("#template").cloneNode(true); // 복제
	cloned.style.display = 'block'; // display속성도 복사되기 때문에 block으로 변경.
	cloned.setAttribute('data-rno', reply.replyNo);
	cloned.querySelector('span').innerHTML = reply.replyNo;
	cloned.querySelector('span:nth-of-type(2)').innerHTML = reply.reply;
	cloned.querySelector('span:nth-of-type(3)').innerHTML = reply.replyer;
	cloned.querySelector('button').addEventListener('click', deleteLiFnc);
	console.log(cloned);
	return cloned;
}

/* ------------------------------
	함수: deleteLiFnc
	기능: 버튼이 포함된 row 삭제.
 * ------------------------------*/
function deleteLiFnc(e) {
	//SweetAlert code.
	Swal.fire({
		title: "d?",
		text: "f?",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!"
	}).then((result) => {
		if (result.isConfirmed) {
			//삭제처리
			let rno = e.target.parentElement.parentElement.dataset.rno;
			svc.removeReply(rno, //삭제글 번호 
				function(result) {
					if (result.retCode == 'OK') {
						Swal.fire({
							title: "Deleted!",
							text: "dd",
							icon: "success"
						});
						//e.target.parentElement.parentElement.remove();
						showReplyListAndPagingList();
					}
					else {
						Swal.fire({
							title: "삐용삐용:",
							text: "오류발생!",
							icon: "success"
						});
					}
				},
				function(err) {
					console.log(err);
				}
			);
		}
	});
}

/* --------------------------
	댓글등록 이벤트핸들러.
 ----------------------------*/
function addReplyFnc(e) {
	// bno, replyer, reply
	let reply = document.querySelector('#reply').value;
	//로그인 정보
	if (!writer || !reply) {
		alert('필수입력값 입력');
		return;
	}
	//입력값 정보
	let param = { "bno": bno, "replyer": writer, "reply": reply };

	//svc객체의 addReply메소드 호출.
	svc.addReply(param,
		function(result) {

			if (result.retCode == 'OK') {
				Swal.fire({
					title: "d",
					text: "d",
					icon: "success"
				});
				//alert('등록성공');
				//document.querySelector('div.content ul').appendChild(makeLi(result.retVal));
				page = 1;
				showReplyListAndPagingList();
			}
			else {
				alert('예외발생');
			}
		},
		function(err) {
			console.log(err);
		}
	);
}


/* ------------------------------
	링크 클릭시 댓글목록 새로출력.
 * ------------------------------*/
function showReplyListFnc(e) {
	// 기존에 출력 리스트 지워주고.
	page = e.target.dataset.page; //페이지번호
	// page = e.target.innerHTML; 
	showReplyListAndPagingList()
}

function showReplyListAndPagingList() {
	svc.replyList({ bno, page }, // 원본글번호. 
		function(result) {
			document.querySelectorAll('div.content li').forEach((li, idx) => {
				if (idx > 2) {
					li.remove();
				}
			})
			result.forEach(reply => {
				document.querySelector('div.content ul').appendChild(makeLi(reply));
			});
			showPagingListFnc();
		}, //성공처리 됐을때 실행함수.
		function(err) {
			console.log(err);
		})
}


/*----------------------------------
 댓글갯수를 활용해서 페이지 리스트 생성
 함수: showPagingListFnc
 -----------------------------------*/
pagination = document.querySelector('ul.pagination');

showPagingListFnc();
function showPagingListFnc() {
	svc.replyPagingCount(bno, // 글번호
		makePagingList, //성공했을때 실행할 콜백함수.
		function(err) {
			console.log(err);
		}
	)
}
//정상 처리 실행할 콜백함수 >>makePagingList 
function makePagingList(result) {
	pagination.innerHTML = '';

	let totalCnt = result.totalCount;
	// 페이지 목록 만들기
	let startPage, endPage, realEnd; // 첫페이지 ~ 마지막페이지
	let prev, next; // 이전페이지, 이후페이지

	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4; // 6 ~ 10
	realEnd = Math.ceil(totalCnt / 5);

	endPage = endPage > realEnd ? realEnd : endPage;
	prev = startPage > 1;
	next = endPage < realEnd;

	//이전 페이지 생성
	let li = document.createElement('li');
	li.className = 'page-item'
	let aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = 'Previous';
	aTag.setAttribute('data-page', startPage - 1);
	li.appendChild(aTag); // <li class="page-item"><a class="page-link">
	if (prev) {
		aTag.setAttribute('href', '#');
	}
	else {
		li.classList.add('disabled');
	}
	pagination.appendChild(li);

	//li 생성. 페이지 범위에 들어갈...
	for (let p = startPage; p <= endPage; p++) {
		let li = document.createElement('li');
		li.className = 'page-item'
		let aTag = document.createElement('a');
		aTag.className = 'page-link';
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', p);
		aTag.innerHTML = p;
		li.appendChild(aTag); // <li class="page-item"><a class="page-link">
		if (p == page) {
			li.classList.add('active');
			li.setAttribute('aria-current', 'page');
		}
		pagination.appendChild(li);
	}

	// 이후 페이지 생성.
	li = document.createElement('li');
	li.className = 'page-item'
	aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = 'Next';
	li.appendChild(aTag); // <li class="page-item"><a class="page-link">
	if (next) {
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage + 1);
	}
	else {
		li.classList.add('disabled');
	}
	pagination.appendChild(li);

	// 이벤트 등록..
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFnc)
	})
}


