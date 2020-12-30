/**
 * 根据用户ID查询用户笔记信息
 */
function loadUserBook() {
    //获取请求参数
    var uid = getCookie("uid");
    alert(uid);

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
//     <!-- 动态生成笔记li元素 -->
// <!-- 	<li class="online">
//         <a  class='checked'>
//         <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 使用Java操作符<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>
//         </a>
//         <div class="note_menu" tabindex='-1'>
//         <dl>
//         <dt><button type="button" class="btn btn-default btn-xs btn_move" title='移动至...'><i class="fa fa-random"></i></button></dt>
//     <dt><button type="button" class="btn btn-default btn-xs btn_share" title='分享'><i class="fa fa-sitemap"></i></button></dt>
//     <dt><button type="button" class="btn btn-default btn-xs btn_delete" title='删除'><i class="fa fa-times"></i></button></dt>
//     </dl>
//     </div>
//     </li> -->
    var lis='';
    lis +='<li class="online">';
    lis +='<a>';
    lis +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
    lis +='</i>'+bookname+'</a></i>';
    var $lis = $(lis);

    $lis.data("bookid",bookid);
    $("#book_ul").append($lis);
}