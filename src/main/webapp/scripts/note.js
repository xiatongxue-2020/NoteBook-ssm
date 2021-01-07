/**
 * 加载笔记本对应的笔记
 */
function loadNotes() {
    //获取请求参数
    //清空所有选中状态
    $("#book_ul").find("a").removeClass("checked")
    var bookid =  $(this).data("bookid");
    //清空笔记信息
    $("#input_note_title").val("");
    um.setContent("");
    //显示笔记
    $("#pc_part_2").css("display","block");
    $("#pc_part_4").css("display","none");
    $("#pc_part_6").css("display","none");
    $("#pc_part_3").show();
    $("#pc_part_5").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    //添加类属性
    $(this).find("a").addClass("checked");

    //发送ajax
    $.ajax({
        url:base_path+"/note/loadNotes.do",
        type:"post",
        data:{"bookid":bookid},
        dataType:"json",
        success:function (result) {
            /**
             * empty删除结构内容，保留结构
             * remove是直接删除结构与内容
             */
            $("#note_ul").empty();
            if (result.status == 0){
               var notes =  result.data;
               for (var i=0;i<notes.length;i++){
                   var title = notes[i].cn_note_title;
                   var noteid = notes[i].cn_note_id;
                   createNoteLi(noteid,title);
                   if (notes[i].cn_note_type_id == '2'){
                       var img = '<i class="fa fa-sitemap">';
                       $("#note_ul li:last").find(".btn_slide_down").before(img);
                   }
               }
            }
        },
        error:function () {
            alert("显示异常");
        }
    });
}
/**
 * 创建笔记列表
 * @param noteid
 * @param title
 */
function createNoteLi(noteid,title) {

    var sli ='';
    sli += '<li class="online">';
    sli += '<a>';
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += '</a>';
    sli += '<div class="note_menu" tabindex=\'-1\'>';
    sli += '<dl>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title=\'移动至...\'><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title=\'分享\'><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title=\'删除\'><i class="fa fa-times"></i></button></dt>';
    sli += '</dl>';
    sli += '</div>';
    sli += '</li>';

    var  $sli = $(sli);
    //将noteID绑到li
    $sli.data("noteid",noteid);
    $("#note_ul").append($sli);
}
/**
 * 点击笔记列表显示笔记详细信息
 */
function loadNoteInfo() {
    /**
     * 获取笔记的ID
     * @type {jQuery}
     */
    $("#note_ul a").removeClass("checked");
   var noteid =  $(this).data("noteid");
   $(this).find("a").addClass("checked");
   // alert(noteid);
    //参数格式校验
    /**
     * 发送ajax
     */
    $.ajax({
        url: base_path+"/note/noteInfo.do",
        type: "post",
        data: {"noteid":noteid},
        dataType: "json",
        success:function (result) {
            if (result.status == 0){
                var noteinfo = result.data;
                var title = noteinfo.cn_note_title;
                var body = noteinfo.cn_note_body;
                $("#input_note_title").val(title);
                $("#input_note_title").data("noteid",noteid);
                //使用副文本编辑器设置body
               // ue.setContent(title);
               um.setContent(body);
            }
        },
        error:function () {
        alert("查询失败");
        }
    });
}
/**
 * 保存笔记
 */
function savenote() {
    //获取参数
    var title = $("#input_note_title").val().trim();
    var body = um.getContent();
    //获取笔记ID
    var $li = $("#note_ul a.checked").parent();
    var noteid = $("#input_note_title").data("noteid");


    if ($li.length == 0) {
        alert("请选择要保存的笔记")
    } else if (title == "") {
        $("#note_title_span").html("<font color='red'>标题不能为空</font>");
    } else {
        //发送ajax
        $.ajax({
            url: base_path + "/note/savenote.do",
            type: "post",
            data: {"noteid": noteid, "title": title, "body": body},
            dataType: "json",
            success: function (result) {
                if (result.status == 0) {

                    //刷新笔记列表
                    //找到直接
                    var sli = '';
                    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + title + '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                    $li.find("a").html(sli);
                    alert("保存日记成功");
                }
            },
            error: function () {
                alert("保存日记异常")
            }
        });
    }
}
/**
 * 添加笔记
 */
function addnote() {
    //1.获取参数
    var userid = getCookie("uid");
    var bookid = $("#book_ul a.checked").parent().data("bookid");
    var noteTitle = $("#input_note").val().trim();

    var ok = true;
    if (userid == null){
        ok = false;
        window.location.href="log_in.html";
    }
    if (noteTitle == ""){
        ok=false;
        $("#note_span").html="笔记名不能为空";
    }
    $.ajax({
        url:base_path+"/note/add.do",
        type:"post",
        data:{"userid":userid,"bookid":bookid,"noteTitle":noteTitle},
        dataType:"json",
        success:function (result) {
            var noteid = result.data.cn_note_id;
            var title = result.data.cn_note_title;
            createNoteLi(noteid,title);
            closeAlertWindow();
        },
        error:function () {
            alert("创建笔记异常")
        }
    });
}

/**
 * 显示笔记操作菜单
 */
function showMenu() {
    hideMenu();
    var $div = $(this).parent().next();
    $div.slideDown(666);
    $("#note_ul").find("a").removeClass("checked");
    $(this).parent().addClass("checked");
    return false;
}

/**
 *隐藏笔记操作菜单
 */
function hideMenu() {
    $("#note_ul div").hide();
}

/**
 * 删除笔记操作
 */
function deleteNote() {
    //1.获取参数
    var $li = $("#note_ul a.checked").parent();
    var noteid = $li.data("noteid");
    console.log()
    //2.参数格式校验
    //3.发送爱ajax
    $.ajax({
        url:base_path+"/note/delete.do",
        type:"post",
        data:{"noteid":noteid},
        dataType:"json",
        success:function (result) {
            closeAlertWindow();
            if (result.status == 0){
                //清除笔记详情
                $("#input_note_title").val("");
                um.setContent("");
                $li.remove();
                alert(result.msg);
            }
        },
        error:function () {
            alert("删除笔记异常");
        }
    });
}

/**
 * 显示回收站里面的日记
 */
function showDnote() {
    //进来之后先清除里面的信息
    $("#four_side_right ul").empty();
    $("#pc_part_2").css("display","none");
    $("#pc_part_4").css("display","block");
    $("#pc_part_6").css("display","none");
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    $("#pc_part_5").hide();
    $("#pc_part_3").show();
    //获取参数
    var userid =  getCookie("uid");
    if (userid == null){
        window.location.href="log_in.html";
    }
    $.ajax({
        url:base_path+"/note/recycleNote.do",
        type:"post",
        data:{"userid":userid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                var oldNotes = result.data;
                for (var i = 0;i<oldNotes.length;i++){
                   var title = oldNotes[i].cn_note_title;
                   var noteid = oldNotes[i].cn_note_id;
                   createRecycle(noteid,title);
                }
            }

        },
        error:function () {
        alert("显示回收站异常");
        }
    });

//<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 虚假回收站笔记<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>

}

/**
 * 创建回收站日记
 */
function createRecycle(noteid,title) {
    var lis ='';
    lis += '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
    var $li = $(lis);
    $li.data("noteid",noteid);
    $("#four_side_right ul").append($li);
}

/**
 * 点击li显示回收站里面的信息详情
 */
function showNoteInfo() {
    $("#four_side_right a").removeClass("checked");
    //获取笔记ID
   var noteid = $(this).data("noteid");
    $(this).find("a").addClass("checked");
   $.ajax({
       url:base_path+"/note/noteInfo.do",
       type:"post",
       data:{"noteid":noteid},
       dataType:"json",
       success:function (result) {
           if (result.status == 0){
               var title = result.data.cn_note_title;
               var body = result.data.cn_note_body;
               um.setContent(body);
               $("#input_note_title").val(title);
           }
       },
       error:function () {
        alert("显示笔记信息异常");
       }
   });
}

/**
 *移动笔记
 */
function moveNote() {
    //1.获取参数
    var $li = $("#note_ul a.checked").parent();
    var noteid = $li.data("noteid");
    //获取移动的笔记ID
    var bookid = $("#moveSelect").val();

    $.ajax({
        url:base_path+"/note/move.do",
        type:"post",
        data:{"noteid":noteid,"bookid":bookid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                $li.remove();
                alert("移动笔记成功");
            }
        },
        error:function () {
        alert("移动笔记失败")
        }
    });
}

/**
 * 分享笔记
 */
function shareNote() {
    //获取笔记id
    var $li = $("#note_ul a.checked").parent();
    var noteid = $li.data("noteid");

    $.ajax({
        url:base_path+"/note/shareNote.do",
        type:"post",
        data:{"noteid":noteid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                var img = '<i class="fa fa-sitemap">';
                $li.find(".btn_slide_down").before(img);

            }
            alert(result.msg);
        },
        error:function () {
        alert("分享笔记失败");
        }
    });

}

/**
 * 恢复笔记
 */
function replayNote() {
    var $li = $("#four_side_right a.checked").parent();
    var noteid = $li.data("noteid");
    //移动笔记本的ID
    var bookid = $("#replaySelect").val();

    $.ajax({
        url:base_path+"/note/replayNote.do",
        type:"post",
        data:{"noteid":noteid,"bookid":bookid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                $li.remove();
                alert("恢复笔记成功")
            }
        },
        error:function () {
            alert("恢复笔记失败");
        }
    });
}

/**
 * 彻底删除笔记
 */
function deleteRollbackNote() {
    //获取笔记ID
    var $li = $("#four_side_right a.checked").parent();
    var noteid = $li.data("noteid");

    $.ajax({
        url:base_path+"/note/deleteRollbackNote.do",
        type:"post",
        data:{"noteid":noteid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                $li.remove();
                alert(result.msg);
            }
        },
        error:function () {
        alert("删除笔记失败")
        }
    });

}

/**
 * 搜索分享笔记模糊查询带分页
 * @param keyword
 * @param page
 */
function  searchSharePage(keyword,page) {
    $("#pc_part_3").hide();
    $("#pc_part_5").show();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    $.ajax({
        url:base_path+"/note/search_note.do",
        type:"post",
        data:{"keyword":keyword,"page":page},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                $("#pc_part_2").hide();
                $("#pc_part_4").hide();
                $("#pc_part_6").show();
                var $ul = $("#sixth_side_right ul");
                //先清除li元素
                var obj = result.data;
                for (var i = 0;i<obj.length;i++){
                    var shareid = obj[i].cn_share_id;
                    var lis ='';
                    lis += '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+obj[i].cn_share_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
                    var $li = $(lis);
                    $li.data("shareid",shareid);
                    $ul.append($li);
                }
            }
        },
        error:function () {
            alert("搜索笔记异常");
        }
    });
}

/**
 * 查看分享的笔记详情
 */
function showShareNote() {
    $("#pc_part_3").hide();
    $("#pc_part_5").show();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    $("#sixth_side_right li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //笔记标题
    var title = $(this).text();
    //笔记id
   var shareid =  $(this).data("shareid");

   $.ajax({
       url:base_path+"/note/shareInfo.do",
       type:"post",
       data:{"shareid":shareid},
       dataType:"json",
       success:function (result) {
        if (result.status == 0){
            var body = result.data.cn_share_body;
            //进来之前先清空
            $("#fifth_side_right p").html("")
            $("#noput_note_title").text(title);
            $("#fifth_side_right p").html(body);
        }
       },
       error:function () {
        alert("查看笔记详情异常");
       }
   });
}