//给按钮绑定单击事件
$(function () {
    $("#login").click(login);
    $("#regist_button").click(regist)
})
function login() {
     //1.获取请求参数
    var username = $("#count").val().trim();
    var password = $("#password").val().trim();
    //添加样式先去除
    $("#count_span").html("");
    $("#password_span").html("");
    //2.参数格式校验
    var ok=true;
    if (username == ""){
        ok=false;
        $("#count_span").html("用户名不能为空");
    }
    if (password == ""){
        ok=false;
        $("#password_span").html("密码不能为空");
    }
    //3.发送ajax
    if (ok){
        $.ajax({
            url:base_path+"/user/login.do",
            type:"post",
            data:{"username":username,"password":password},
            dataType:"json",
            success:function (result) {
                    //TODO
                if(result.status == 2){
                    alert(result.msg);
                }else if (result.status == 1){
                    alert(result.msg);
                }else if(result.status == 0){
                    addCookie("uid",result.data.cn_user_id,2);
                    addCookie("uname",result.data.cn_user_name,2);
                    window.location.href='edit.html';
                    alert(result.msg);
                }
            },
            error:function () {
                alert("登录异常");
            }
        });
    }
}
function regist() {
    var username = $("#regist_username").val().trim();
    var nick = $("#nickname").val().trim();
    var password = $("#regist_password").val().trim();
    var fpassword = $("#final_password").val().trim();
    $("#warning_1 span").html("");
    $("warning_2 span").html("");
    var ok=true;
    if (username == ""){
        ok=false;
        $("#warning_1").show("warning");
        $("#warning_1 span").html("用户名不能为空");
    }else if(password == ""){
        ok=false;
        $("#warning_2").show("warning");
        $("warning_2 span").html("密码不能为空");
    }else if (password != fpassword){
        ok=false;
        $("#warning_3").show("warning");
        $("warning_3 span").html("两次密码不一致");
    }
    if (ok){
        $.ajax({
            url: base_path+"/user/regist.do",
            type: "post",
            data: {"username":username,"nick":nick,"password":password},
            dataType: "json",
            success:function (result) {
                if (result.status == 0){
                    alert(result.msg);
                    $("#back").click();
                }else if(result.status == 1){
                    $("#warning_1").show("warning");
                    $("#warning_1 span").html(result.msg);
                }
            },
            error:function () {
                alert("注册异常")
            }
        })
    }
}