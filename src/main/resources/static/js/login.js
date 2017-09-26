/**
 * Created by WangShiXiang on 2017/5/2.
 * 登录页面的JS
 */
$("form > button").click(function () {
    login();
});
$("input").focus(function () {
    window.clearInterval(interval_username);
    interval_username=undefined;
    window.clearInterval(interval_password);
    interval_password=undefined;
    $("input").css("border","1px solid #312E3D");
    $(".msg").html("");
});
var interval_username;
var interval_password;
function login() {
    var array=$("input");
    var username=array[0].value;
    var password=array[1].value;
    if (username===null||username===""){
        prompt(array[0]);
        if(interval_username!==undefined) return;
        interval_username=self.setInterval(function () {
            prompt(array[0]);
        },800);
    }else if (password===null||password===""){
        prompt(array[1]);
        if(interval_password!==undefined) return;
        interval_password=self.setInterval(function () {
            prompt(array[1]);
        },800);
    }else {
        postUserNameAndPasseWord(username,password);
    }
}
var content=1;
function prompt(obj) {
    content++;
    if (content%3===0){
        $(obj).css("border","1px solid #FFF");
    }else if (content%3===1){
        $(obj).css("border","1px solid #990000");
    }else {
        $(obj).css("border","1px solid #00FF66");
    }
}
function postUserNameAndPasseWord(username,password) {
    $.ajax({
        type:"post",
        url:"/user/login",
        data:{username:username,password:password},
        dataType:"text",
        success:function (result) {
            if (result==="success"){
               document.cookie="name="+username;
               document.cookie="state=success";
                location.href="index.html";
            }else {
                $(".msg").html("用户名或密码错误");
            }
         }
        }
    );
}
