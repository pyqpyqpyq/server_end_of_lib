$('input[name=password3]').blur(function () {
   var password3=$(this).val();
   var password2=$('input[name=password2]').val();
   if (password3===password2)
       $('.note').text('');
   else
       $('.note').text('two new passwords are inconsistent');
});



$('#update').click(function () {
    $.ajax('/user/update_user',{
        method:'post',
        data:{
          oldPassword:$('input[name=password1]').val(),
          newPassword:$('input[name=password2]').val()
        },
        success:function (res) {
            if (res.status===10)
                window.location.href="/page/index.html";
            if(res.status===-1) {
                $('.note').text(res.message)
            }
            else {
                alert(res.message);
                window.location.href='/page/home.html';
            }
        }
    })
});