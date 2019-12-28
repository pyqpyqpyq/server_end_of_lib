$('#register').click(function () {
    var password = $('input[name=password]').val();
    var password2 = $('input[name=password2]').val();
    if (password !== password2)
        $('.note').text('two passwords are inconsistent');
    else
        $('.note').text('');
    $.ajax("/user/register", {
        method: 'post',
        data: {
            username: $('input[name=username]').val(),
            password: password,
            email:$('input[name=email]').val()
        },
        success: function (res) {
            if (res.status === 0) {
                alert(res.message);
                window.location.href = "./index.html";
            } else $('.note').text(res.message);
        }
    })
});