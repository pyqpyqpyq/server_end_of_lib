$('#login').click(function () {
    $.ajax("/user/login",{
        method:'get',
        data:{
            username:$('input[name=username]').val(),
            password:$('input[name=password]').val()
        },
        success:function (res) {
            if (res.status===0){
                window.location.href="/page/home.html";
            }
            else $('.note').text(res.message);
        }
    })
});