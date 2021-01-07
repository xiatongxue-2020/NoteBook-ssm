/**
 * 根据用户ID查询用户笔记信息
 */
function loadUserBook() {
    //获取请求参数
    var uid = getCookie("uid");
    var uname = getCookie("uname");
    $(".profile-username").html(uname);
    //参数格式校验
    if (uid == null){
        window.location.href="log_in.html";
    }
    //发送ajax
    $.ajax({
        url:base_path+"/book/loadbooks.do",
        type:"post",
        data:{"userId":uid},
        dataType:"json",
        success:function (result) {
           if(result.status == 0){
               var books = result.data;
               for (var i=0;i<books.length;i++){
                  var bookname = books[i].cn_notebook_name;
                  var bookid = books[i].cn_notebook_id;
                   createBookLi(bookid,bookname);
               }
           }
        },
        error:function () {
            alert("查询失败")
        }
    })
}

function createBookLi(bookid,bookname) {
    <!-- 动态生成笔记本列表li -->
// <!-- 	<li class="online">
//         <a  class='checked'>
//         <i class="fa fa-book" title="online" rel="tooltip-bottom">
//         </i> 默认笔记本</a></li> -->
    var lis='';
    lis +='<li class="online" >';
    lis +='<a>';
    lis +='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
    lis +='</i>'+bookname+'</a></i>';
    var $lis = $(lis);


    $lis.data("bookid",bookid);
    $("#book_ul").append($lis);
}
function addbook() {
    //获取请求参数
    var bookName = $("#input_notebook").val();
    //用户id
    var userid = getCookie("uid");
    //参数格式检验
    var ok = true;
    if (bookName == ""){
        ok=false;
        $("#notebook_span").html("笔记本名字不能为空")
    }
    if (userid == null){
        ok =false;
        window.location.href="log_in.html";
    }
    //发送ajax
    if (ok){
        $.ajax({
            url:base_path+"/book/add.do",
            type: "post",
            data:{"bookName":bookName,"userid":userid},
            dataType: "json",
            success:function (result) {
                closeAlertWindow();
                if (result.status  == 0){
                    var bookid = result.data.cn_notebook_id;
                    createBookLi(bookid,bookName);
                }
                        alert(result.msg);
            },
            error:function () {
            alert("创建笔记异常")
            }
        });
    }
}
//重命名
function  updateName() {
   var oldhtml =  this.innerText;
   var newobj = document.createElement('input');
   newobj.value=oldhtml;
   this.childNodes.innerText='';
   this.appendChild(newobj);
   newobj.focus();
    newobj.onblur = function(){
        //下面应该判断是否做了修改并使用ajax代码请求服务端将id与修改后的数据提交
        // alert(element.id);
        //当触发时判断newobj的值是否为空，为空则不修改，并返回oldhtml
        var lis='';
        lis +='<a>';
        lis +='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
        lis +='</i>'+newobj.value+'</a>';
        $(this).find("input").remove();
        $(this).html(lis);


    }
}

/**
 * 绑定重命名的确定按钮
 */
function renameBook() {
    var $li = $("#book_ul a.checked").parent();
    var bookid = $li.data("bookid");
    var bookname = $("#input_notebook_rename").val();

    if (bookname == ""){
        alert("名字不能为空");
    }
    $.ajax({
        url:base_path+"/book/rename.do",
        type:"post",
        data:{"bookid":bookid,"bookname":bookname},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                var lis='';
                lis +='<li class="online" >';
                lis +='<a>';
                lis +='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
                lis +='</i>'+bookname+'</a></i>';
                $li.html(lis);
                alert(result.msg);
            }
        },
        error:function () {
            alert("重命名异常")
        }
    });
}

/**
 * 点击显示收藏笔记本
 */
function showLike() {
    $("#pc_part_2").hide();
    $("#pc_part_4").hide();
    $("#pc_part_3").show();
    $("#pc_part_5").hide();
    $("#pc_part_6").hide();
    $("#pc_part_7").show();
    $("#pc_part_8").hide();
}
/**
 * 点击显示参加活动日记
 */
function showAction() {
    $("#pc_part_2").hide();
    $("#pc_part_4").hide();
    $("#pc_part_6").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").show();

}