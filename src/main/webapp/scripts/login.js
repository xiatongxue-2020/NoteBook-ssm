//给按钮绑定单击事件
$(function () {
    $("#login").click(login);
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
                    alert(result.mag)
                }else if(result.status == 0){
                    alert(result.msg)
                    window.location.href='edit.html';
                }
            },
            error:function () {
                alert("登录异常");
            }
        });
    }
}