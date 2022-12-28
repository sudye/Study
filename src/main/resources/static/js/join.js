/**
 * 
 */
 
 function joinCheck() {
	
	if(document.join_frm.userId.value.length == 0) {
		alert("아이디는 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userId.value.length < 2) {
		alert("아이디가 너무 짧습니다. 아이디는 2자 이상이어야 합니다.");
		return;
	}
	
	if(document.join_frm.userPw.value.length == 0) {
		alert("비밀번호는 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userPw_check.value.length == 0) {
		alert("비밀번호체크란은 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userPw.value != document.join_frm.userPw_check.value) {
		alert("비밀번호가 일치하지 않습니다. 비밀번호를 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userName.value.length == 0) {
		alert("이름은 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userPhone.value.length == 0) {
		alert("전화번호는 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	if(document.join_frm.userEmail.value.length == 0) {
		alert("이메일은 필수 입력사항입니다. 다시 확인해주세요!");
		return;
	}
	
	document.join_frm.submit();
	
}