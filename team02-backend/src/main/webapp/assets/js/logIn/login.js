window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('header').innerHTML = data;
    });

  // 푸터 불러오기
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    });
});

const login = document.getElementById('login_form');
const userid = document.getElementById('login_input_id');
const userpw = document.getElementById('login_input_pw'); 
const warning = document.getElementById('login_warning_message');


const db = {
  id : 'user',
  pw : 'user123123',
  
}
const blackdb = {
  blackid : 'black',
  blackpw : 'black123123',
}

login.addEventListener('submit', function(e) {
  e.preventDefault();

  if(userid.value === db.id && userpw.value === db.pw){
    alert('로그인 되었습니다 메인페이지로 이동합니다.')
    window.location.href = './../../main.html';
  }else if(userid.value === blackdb.blackid && userpw.value === blackdb.blackpw){
    alert('정지된 아이디 입니다');
  } else{
    alert('아이디 혹은 비밀번호를 다시 확인해주세요');
    warning.style.display = 'block';
    userpw.focus();
    userpw.select();
  }
});


// btn[0].addEventListener('click', () => {
//   if(!id || !pw){
//   }
//   location.replace('./../../app/findPW/editPw.html');
//   alert('문자로 임시비밀번호가 발송되었습니다.')
// });