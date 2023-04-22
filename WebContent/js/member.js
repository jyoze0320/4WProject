/*
* 로그인 및 회원가입, 회원정보수정 페이지에서 사용하는 js 파일
* 작성자 : 이수봉
*/

// ID 입력 변화 시 처리하는 function
function id_input_change(){
  $('#id_chk').text("");    // 중복 체크 확인 문구 비우기
  $('#overlapchk_btn').css("border-width", "thin"); // 테두리 두께변경
  $('#chk1').val("false");  // id가 chk1인 hidden의 값을 false로 변경(중복 체크 안한 상태)
}

// ID 중복 확인 function
function overlapchk() {
  // 빈 칸 입력한 채로 중복 확인 시도하는 경우 처리
  if($('#id_input').val() == ''){
    $('#id_chk').text("ID를 입력하세요"); // 알림 문구
    $('#id_chk').css("color", "red");     // 글자 색 red로 변경
    $('#overlapchk_btn').css("border-width", "thin"); // 테두리 두께변경
    $('#id_input').focus();  // ID 입력란으로 커서 이동
    return;
  }

  // ajax 이용하여 데이터 전달 >> ID 중복 여부 확인
  var inputID = $('#id_input').val();  // ID 입력값
  $.ajax({
    type:"post",      // post 방식
    dataType:"text",  // text 타입
    async:false,      // 동기식
    url:"4w.do?command=id_overlapchk",  // 처리 URL
    data:{input:inputID},               // 서블릿으로 전송할 데이터
    success:function(data){             // 데이터 송수신 성공 시 실행
      if(data == 'true'){
        $('#id_chk').text("사용 가능한 ID입니다."); // 알림 문구
        $('#id_chk').css("color", "green");         // 글자 색 green으로 변경
        $('#chk1').val("true");         // id가 chk1인 hidden의 값을 true로 변경(중복 체크 완료)
      }else{
        $('#id_chk').text("중복된 ID입니다.");  // 알림 문구
        $('#id_chk').css("color", "red");       // 글자 색 red로 변경
        $('#id_input').val("");                 // ID 입력란 빈칸으로 변경
        $('#id_input').focus();                 // ID 입력란으로 커서 이동
      }
      $('#overlapchk_btn').css("border-width", "thin"); // 테두리 두께 변경
    },
    error:function(){    // 데이터 송수신 실패 시 실행
      alert("에러 발생");
    }
  })
}

// 비밀번호 입력이 비밀번호 확인란과 일치하는지 검사하는 function
function pw_input_chk(){
  if($('#pwchk_input').val() != ''){  // 비밀번호 확인란이 빈 칸이 아닌 경우
    if($('#pw_input').val() == $('#pwchk_input').val()){ // 비밀번호가 일치하면
      $('#pw_eqchk').text("비밀번호가 일치합니다.");
      $('#pw_eqchk').css("color", "green");              // 글자 색 green으로 변경
      $('#chk2').val("true");   // id가 chk2인 hidden의 값을 true로 변경(비밀번호 일치 확인)
    }else{
      $('#pw_eqchk').text("비밀번호가 일치하지 않습니다.");
      $('#pw_eqchk').css("color", "red");                // 글자 색 red로 변경
      $('#chk2').val("false");  // id가 chk2인 hidden의 값을 false로 변경(비밀번호 불일치)
    }
  }else{                        // 비밀번호 확인란이 빈 칸인 경우
    $('#pw_eqchk').text("");    // 알림 문구 빈 칸으로 초기화
    $('#chk2').val("false");    // id가 chk2인 hidden의 값을 false로 변경(비밀번호 불일치)
  }
}

// 비밀번호 입력 변화 시 처리하는 function
function pw_input_change(){
  if($('#pwchk_input').val() != ''){   // 비밀번호 확인란을 입력한 상태(빈 칸이 아님)인데 입력값 변화하는 경우
    if($('#pw_input').val() != ''){    // 비밀번호 입력값이 빈 칸이 아닌 경우
      pw_input_chk();             // 비밀번호 확인란 검사
    }else{
      $('#pwchk_input').val("");  // 비밀번호 입력값이 빈 칸인 경우, 비밀번호 확인란도 빈 칸으로 초기화
      $('#pw_eqchk').text("");    // 알림 문구 빈 칸으로 초기화
      $('#chk2').val("false");    // id가 chk2인 hidden의 값을 false로 변경(비밀번호 불일치)
    }
  }
}

// 이메일 주소 뒷자리 선택 시 처리하는 function
function set_email_input(){
  var selected_val = $('#email_select').val();  // 선택한 항목의 value
  if(selected_val == '0'){
    $('#email2_input').val("");                 // 이메일 뒷자리 입력란을 빈칸으로 변경
    $('#email2_input').attr("disabled", false); // 활성화(작성 가능)
    $('#email2_input').focus();                 // 입력란으로 커서 이동
  }else{
    $('#email2_input').val(selected_val);       // 이메일 뒷자리 입력란을 해당 값으로 변경
    $('#email2_input').attr("disabled", true);  // 비활성화(작성 불가능)
  }
}

// 가입하기 버튼 동작 처리하는 function(ID 중복확인, 비밀번호 일치 유효성 검사)
function createmem_btn_action(){
  $('#form_createmem').off("submit").on("submit", function(){	// 현재의 submit 이벤트 핸들러를 제거하고 새로 생성하여 function 등록
    var done_id_overlapchk = $('#chk1').val();  // ID 중복확인 여부 관리하는 hidden 값
    var done_pwchk = $('#chk2').val();          // 비밀번호 확인란 일치여부 관리하는 hidden 값
    if(done_id_overlapchk == 'false'){          // ID 중복확인이 안 된 경우
      alert("아이디 중복확인이 필요합니다!");
      $('#overlapchk_btn').focus();             // 중복확인 버튼 선택
      $('#overlapchk_btn').css("border-width", "medium"); // 테두리 두께 변경
      return false;	// submit 차단
    }else if(done_pwchk == 'false'){            // 입력한 비밀번호가 확인란에 입력한 값과 일치하지 않는 경우
      alert("입력한 비밀번호를 다시 확인해주시기 바랍니다!");
      $('#pwchk_input').focus();                // 비밀번호 확인란 선택
      return false;	// submit 차단
    }else{
      $('#email2_input').attr("disabled", false); // Email 뒷자리 request 활성화
    }
  });
}

// 정보수정 버튼 동작 처리하는 function(정보수정 가능하게 form 변경)
function modmem_btn_action(){
  // form이 표시될 레이아웃 크기 및 위치 변경
  $('#modmem_card').css("left", "0%");
  $('#modmem_card').css("width", "550px");
  $('#modmem_card').css("height", "580px");
  // 제목 변경
  $('.Header_title').text("회원정보 수정");
  // 입력 불가한 input 태그를 모두 입력 가능하게 변경
  var input_list = document.querySelectorAll('.TxtInput, .TxtInput_num');
  for(var i=0; i<input_list.length; i++){
    input_list[i].disabled = false;
  }
  // ID 입력란은 수정 불가하게 변경
  $('#id_input').attr("disabled", true); // 비활성화
  // 원래 회원정보 hidden 타입에 저장
  $('input[name="origin_name"]').val($('input[name="name"]').val());
  $('input[name="origin_email1"]').val($('input[name="email1"]').val());
  $('input[name="origin_email2"]').val($('input[name="email2"]').val());
  $('input[name="origin_address"]').val($('input[name="address"]').val());
  $('input[name="origin_phone1"]').val($('input[name="phone1"]').val());
  $('input[name="origin_phone2"]').val($('input[name="phone2"]').val());
  $('input[name="origin_phone3"]').val($('input[name="phone3"]').val());
  // 숨겨진 입력란이 화면에 보이도록 변경
  var hide_list = document.querySelectorAll('select[hidden="true"], div[hidden="true"]');
  for(var i=0; i<hide_list.length; i++){
    hide_list[i].hidden = false;
  }
  // 기존의 버튼 레이아웃 숨기기
  $('#btn_layout').hide();
}

// 수정하기 버튼 동작 처리하는 function(비밀번호가 일치하지 않을 때만 알림-빈 칸인 경우는 무시)
function updatemem_action(){
  $('#form_modemem').off("submit").on("submit", function(){	// 현재의 submit 이벤트 핸들러를 제거하고 새로 생성하여 function 등록
    var done_pwchk = $('#chk2').val();     // 비밀번호 확인란 일치여부 관리하는 hidden 값
	if(($('#pw_input').val() == '') && ($('#pwchk_input').val() == '')){
	  done_pwchk = 'true';                 // 비밀번호 입력란과 확인란이 모두 빈 칸인 경우 유효성 검사 무시(비밀번호 변경X)
	}
	if(done_pwchk == 'true'){	// 유효성 검사 통과 시
	  $('#id_input').attr("disabled", false);     // ID request 활성화
	  $('#email2_input').attr("disabled", false); // Email 뒷자리 request 활성화
	}else{
	  alert("입력한 비밀번호를 다시 확인해주시기 바랍니다!"); // 통과 못할 시 알림
	  $('#pwchk_input').focus();	// 비밀번호 확인란 선택
	  return false;	// submit 차단
	}
  });
}

// 회원정보 수정 시 빈 칸인채로 focus가 해제되면 원래 정보로 채우는 function
function blankchk(field){
  if(field.value == ''){	// 현재 요소의 값이 빈 칸인 경우
    field.value = field.nextElementSibling.value;	// 다음 형제 요소(원래 회원정보를 담아놓은 hidden타입 input요소) 값으로 대체
  }
}


