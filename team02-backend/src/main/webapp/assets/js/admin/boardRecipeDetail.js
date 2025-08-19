const deletebtn = document.getElementById('admin_deletebtn');
const commentdeletebtn = document.getElementById('admin_comment_deletebtn');

deletebtn.addEventListener('click', () =>{
  if(!confirm('정말 삭제하시겠습니까?'))
    return;
    location.replace('./../../app/admin/boardRecipeList.html');
})

commentdeletebtn.addEventListener('click', ()=>{
  if(!confirm('댓글을 삭제하시겠습니까?'))
    return;
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});