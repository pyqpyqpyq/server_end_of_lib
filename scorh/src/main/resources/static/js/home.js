$.ajax("/user/user_info",{
   success:function (res) {
       if (res.status===10)
           window.location.href="/page/index.html";
       else{
           var user=res.data;
           $('input[name=username]').val(user.username);
           $('input[name=email]').val(user.email);
       }
   }
});

$.ajax("/user/reservation",{
    success:function (res) {
        if (res.status===10)
            window.location.href="/page/index.html";
        else{
            var table=$('#reservation');
            var list=res.data;
            for(var i=0;i<list.length;i++){
                var notice=list[i];
                var tr=$('<tr><td class="notice-id">'+notice.id+'</td><td>'+notice.name+
                    '</td><td><button class="cancel">归还</button></td></tr>');
                tr.appendTo(table);
            }
        }
    }
});

$('#reservation').on('click','.cancel',{},function () {
    var noticeId=$(this).parent().parent().find('.notice-id').text();
    console.log(noticeId);
    $.ajax("/user/cancel",{
        method:'post',
        data:{
            noticeId:noticeId
        },
        success:function (res) {
            if (res.status===10)
                window.location.href="/page/index.html";
            else{
                alert(res.message);
            }
            location.reload();
        }
    });
});

$.ajax("/user/notice",{
    success:function (res) {
        if (res.status===10)
            window.location.href="/page/index.html";
        else{
            var table=$('#noticeboard');
            var list=res.data;
            for(var i=0;i<list.length;i++){
                var notice=list[i];
                var tr=$('<tr><td class="notice-id">'+notice.id+'</td><td>'+notice.name+
                    '</td><td><button class="reserve">借阅</button></td></tr>');
                tr.appendTo(table);
            }
        }
    }
});

$('#noticeboard').on('click','.reserve',{},function () {
    var noticeId=$(this).parent().parent().find('.notice-id').text();
    $.ajax("/user/reserve",{
        method:'post',
        data:{
          noticeId:noticeId
        },
        success:function (res) {
            if (res.status===10)
                window.location.href="/page/index.html";
            else{
                alert(res.message);
            }
            location.reload();
        }
    });
});



$('#logout').click(function () {
   var isLogout=confirm('确认注销账户？');
   if (isLogout){
       $.ajax('/user/logout',{
           success:function (res) {
               if (res.status===10)
                   window.location.href="/page/index.html";
               else{
                   alert(res.message);
               }
               location.reload();
           }
       })
   }
});