/*加载列表数据start*/
/******************pagination*********************/
var items_per_page = 20;    //默认每页显示20条数据
var pageIndex = 0;  //当前页码
var records = 0;//数据的总条数
var skip = 0;
var limit = 20;//每一页显示的条数

var pageType = 1;

var pageSelectCallback = function (page_index, jq) {
    //值为1的时候代表是关键词的分页,2代表是推广计划的分页
    if (pageType == 1) {
        $("#pagination_keywordPage").append("<span style='margin-right:10px;'>跳转到 <input id='keywordPageNum' type='text' class='price'/></span>&nbsp;&nbsp;<a href='javascript:skipKeywordPage();' class='page_go'> GO</a>");
    } else if (pageType == 2) {
        $("#pagination_campaignPage").append("<span style='margin-right:10px;'>跳转到 <input id='campaignPageNum' type='text' class='price'/></span>&nbsp;&nbsp;<a href='javascript:skipCampaignPage();' class='page_go'> GO</a>");
    } else if (pageType == 3) {//创意分页
        $("#creativePager").append("<span style='margin-right:10px;'>跳转到 <input id='creativePageNum' type='text' class='price'/></span>&nbsp;&nbsp;<a href='javascript:skipCreativePage();' class='page_go'> GO</a>");
    } else if (pageType == 4) {//单元分页
        $("#adgroupPager").append("<span style='margin-right:10px;'>跳转到 <input id='adgroupPageNum' type='text' class='price'/></span>&nbsp;&nbsp;<a href='javascript:skipAdgroupPage();' class='page_go'> GO</a>");
    }

    if (pageIndex == page_index) {
        return false;
    }
    pageIndex = page_index;
    if (pageType == 1) {
        getKwdList(page_index);
    } else if (pageType == 2) {
        getCampaignList(page_index);
    } else if (pageType == 3) {
        loadCreativeData(page_index);
    }else if(pageType==4){
        loadAdgroupData(page_index);
    }
    return false;
};

var getOptionsFromForm = function (current_page) {
    var opt = {callback: pageSelectCallback};

    opt["items_per_page"] = items_per_page;
    opt["current_page"] = current_page;
    opt["prev_text"] = "上一页";
    opt["next_text"] = "下一页";
    opt["num_display_entries"]=4;

    //avoid html injections
    var htmlspecialchars = { "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;"};
    $.each(htmlspecialchars, function (k, v) {
        opt.prev_text = opt.prev_text.replace(k, v);
        opt.next_text = opt.next_text.replace(k, v);
    });
    return opt;
};
//var optInit = getOptionsFromForm(0);
/*************************************************/


//Go按钮单击事件
function skipKeywordPage() {
    var pageNo = $("#keywordPageNum").val();
    getKwdList(/^\d+$/.test(pageNo) == false ? 0 : parseInt(pageNo) - 1);
}


//得到当前账户的所有关键词
function getKwdList(nowPage) {
    pageType = 1;

    $("#tbodyClick").empty();
    $("#tbodyClick").html("加载中...");

    if (/^\d+$/.test(nowPage) == false) {
        nowPage = 0;
    }

    var param = getNowChooseCidAndAid();
    if (param == null) {
        param = {};
    }

    param["nowPage"] = nowPage;
    param["pageSize"] = items_per_page;

    $.ajax({
        url: "/assistantKeyword/list",
        type: "post",
        data: param,
        dataType: "json",
        success: function (data) {
            $("#tbodyClick").empty();
            records = data.totalCount;
            pageIndex = data.pageNo;
            $("#pagination_keywordPage").pagination(records, getOptionsFromForm(pageIndex));

            if (data.list.length == 0) {
                $("#tbodyClick").html("暂无数据!");
                return;
            }

            for (var i = 0; i < data.list.length; i++) {
                var html = keywordDataToHtml(data.list[i], i);
                $("#tbodyClick").append(html);
                if (i == 0) {
                    setKwdValue($(".firstKeyword"), data.list[i].object.keywordId);
                    if (data.list[i].object.localStatus != null) {
                        $("#reduction").find("span").removeClass("z_function_hover");
                        $("#reduction").find("span").addClass("zs_top");
                    } else {
                        $("#reduction").find("span").removeClass("zs_top");
                        $("#reduction").find("span").addClass("z_function_hover");
                    }
                }
            }
        }
    });
}


/**
 * 单击某一行时将该行的值放入相应的文本框内
 */
$("#tbodyClick").delegate("tr", "click", function () {
    var span = $(this).find("td:last");
    if (span.html() != "&nbsp;") {
        $("#reduction").find("span").removeClass("z_function_hover");
        $("#reduction").find("span").addClass("zs_top");
    } else {
        $("#reduction").find("span").removeClass("zs_top");
        $("#reduction").find("span").addClass("z_function_hover");
    }
    var obj = $(this);
    var keywordId = $(this).find("input[type=hidden]").val();
    setKwdValue(obj, keywordId);
});


/**
 *将一条数据加到html中
 */
function keywordDataToHtml(obj, index) {
    if (obj.object.keywordId == null) {
        obj.object.keywordId = obj.object.id;
    }

    var html = "";
    if (index == 0) {

        html = html + "<tr class='list2_box3 firstKeyword'>";
    } else if (index % 2 != 0) {
        html = html + "<tr class='list2_box2'>";
    } else {
        html = html + "<tr class='list2_box1'>";
    }

    //kwid
    html = html + "<input type='hidden' camp='"+obj.campaignId+"' adg='"+obj.object.adgroupId+"' value = " + obj.object.keywordId + " />";
    html = html + "<td>" + obj.object.keyword + "</td>";

    switch (obj.object.status) {
        case 41:
            html = html + "<td>有效</td>";
            break;
        case 42:
            html = html + "<td>暂停推广</td>";
            break;
        case 43:
            html = html + "<td>不宜推广</td>";
            break;
        case 44:
            html = html + "<td>搜索无效</td>";
            break;
        case 45:
            html = html + "<td>待激活</td>";
            break;
        case 46:
            html = html + "<td>审核中</td>";
            break;
        case 47:
            html = html + "<td>搜索量过低</td>";
            break;
        case 48:
            html = html + "<td>部分无效</td>";
            break;
        case 49:
            html = html + "<td>计算机搜索无效</td>";
            break;
        case 50:
            html = html + "<td>移动搜索无效</td>";
            break;
        default:
            html = html + "<td>本地新增</td>";
    }

    html = html + "<td>" + until.convert(obj.object.pause, "暂停:启用") + "</td>";

    html = html + until.convert(obj.object.price == null, "<td><0.10></td>:<td>" + obj.object.price + "</td>");


    //计算机质量度
    var quanlityHtml = "<span>";
    var quanlityText = "";
    if(obj.quality>0){
        switch(parseInt(obj.quality)){
            case 11:
            case 12:
            case 13:
                quanlityHtml+="<img src='/public/img/star.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                break;
            case 21:
            case 22:
                quanlityHtml+="<img src='/public/img/star.png'>";
                quanlityHtml+="<img src='/public/img/star.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                break;
            case 3:
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
                quanlityHtml+="<img src='/public/img/star3.png'>";
        }

        switch (parseInt(obj.quality)){
            case 11:quanlityText = "一星较难优化";break;
            case 12:quanlityText = "一星难度中等";break;
            case 13:quanlityText = "一星较易优化";break;
            case 21:quanlityText = "二星较难优化";break;
            case 22:quanlityText = "二星较易优化";break;
            case 3:quanlityText = "三星";break;
        }
    }
    quanlityHtml+="&nbsp;&nbsp;&nbsp;"+quanlityText+"</span>";
    html = html + "<td cname="+obj.quality+">"+quanlityHtml+"</td>";


    //移动质量度
    var mobileQuanlityHtml = "<span>";
    if(parseInt(obj.mobileQuality)>0){
        for(var i=1;i<=5;i++){
            if(parseInt(obj.mobileQuality)>=i){
                mobileQuanlityHtml+="<img src='/public/img/star.png'>";
            }else{
                mobileQuanlityHtml+="<img src='/public/img/star3.png'>";
            }
        }
        mobileQuanlityHtml+="&nbsp;&nbsp;&nbsp;"+obj.mobileQuality;
    }
    mobileQuanlityHtml+="</span>";
    html = html + "<td cname="+obj.mobileQuality+">"+mobileQuanlityHtml+"</td>";


    //匹配模式
    var matchType;
    switch (obj.object.matchType) {
        case 1:
            matchType = "精确";
            break;
        case 2:
            matchType = "短语";
            if (obj.object.phraseType == 1) {
                matchType = matchType + "-同义包含";
            } else if (obj.object.phraseType == 2) {
                matchType = matchType + "-精确包含";
            } else if (obj.object.phraseType == 3) {
                matchType = matchType + "-核心包含";
            }
            ;
            break;
        case 3:
            matchType = "广泛";
            break;
        default :
            matchType = "&nbsp;";
    }
    html = html + "<td>" + matchType + "</td>";


    html = html + "<td>" + (obj.object.pcDestinationUrl != null?"<a target='_blank' href='" + obj.object.pcDestinationUrl + "'>" + obj.object.pcDestinationUrl.substr(0, 20) + "</a>":"") + "</td>";
    html = html + "<td>" + (obj.object.mobileDestinationUrl != null?"<a target='_blank' href='" + obj.object.mobileDestinationUrl + "'>" + obj.object.mobileDestinationUrl.substr(0, 20) + "</a>":"") + "</td>";
    html = html + "<td>"+obj.campaignName+"</td>";

    if (obj.object.localStatus != null) {
        if (obj.object.localStatus == 3||obj.object.localStatus == 4) {
            html = html + "<td><span class='error' step='" + obj.object.localStatus + "'></span></td>";
        } else {
            html = html + "<td><span class='pen' step='" + obj.object.localStatus + "'></span></td>";
        }
    } else {
        html = html + "<td>&nbsp;</td>";
    }

    html = html + "</tr>";

    return html;
}

/*加载列表数据end*/


function setKwdValue(obj, kwid) {
    $("#hiddenkwid_1").val(kwid);
    $(".keyword_1").val($(obj).find("td:eq(0)").html());


    if (($(obj).find("td:eq(3)").html()) == "&lt;0.10&gt;") {
        $(".price_1").val("<0.10>");
    } else {
        $(".price_1").val($(obj).find("td:eq(3)").html());
    }


    if ($(obj).find("td:eq(7) a").attr("href") != undefined) {
        $(".pcurl_1").val($(obj).find("td:eq(7) a").attr("href"));
        $(".pcurlSize_1").html($(obj).find("td:eq(7) a").attr("href").length + "/1024");
    } else {
        $(".pcurl_1").val("");
        $(".pcurlSize_1").html("0/1024");
    }

    if ($(obj).find("td:eq(8) a").attr("href") != undefined) {
        $(".mourl_1").val($(obj).find("td:eq(8) a").attr("href"));
        $(".mourlSize_1").html($(obj).find("td:eq(8) a").attr("href").length + "/1017");
    } else {
        $(".mourl_1").val("");
        $(".mourlSize_1").html("0/1017");
    }

    $(".matchModel_1").html($(obj).find("td:eq(6)").html());
    $(".status_1").html($(obj).find("td:eq(1)").html());

    if ($(obj).find("td:eq(2)").html() == "启用") {
        $(".pause_1").html("<option value='true'>暂停</option><option value='false' selected='selected'>启用</option>");
    } else {
        $(".pause_1").html("<option value='true' selected='selected'>暂停</option><option value='false' >启用</option>");
    }
}


/**
 * 编辑关键词信息
 * @param value
 */
function editKwdInfo(jsonData) {
    jsonData["kwid"] = $("#hiddenkwid_1").val();
    $.ajax({
        url: "/assistantKeyword/edit",
        type: "post",
        data: jsonData,
        dataType: "json",
        success: function (data) {
            var jsonData = {};
            jsonData["object"] = data;
            jsonData["campaignName"] = $("#tbodyClick").find(".list2_box3 td:eq(9)").html();
            jsonData["quality"] = $("#tbodyClick").find(".list2_box3 td:eq(4)").attr("cname");
            jsonData["mobileQuality"] = $("#tbodyClick").find(".list2_box3 td:eq(5)").attr("cname");

            var html = keywordDataToHtml(jsonData, 0);
            var tr = $("#tbodyClick").find(".list2_box3");
            tr.replaceWith(html);
        }
    });
}


/**
 * 控件失去焦点时候触发
 * @param num
 * @param value
 */
function whenBlurEditKeyword(num, value) {
    if ($("#tbodyClick").find("tr").length == 0) {
        return;
    }
    var jsonData = {};
    switch (num) {
        case 2:
            if (/^\d+$/.test(value) == false) {
                value = 0;
            }
            jsonData["price"] = value;
            break;
        case 3:
            jsonData["pcDestinationUrl"] = value;
            break;
        case 4:
            jsonData["mobileDestinationUrl"] = value;
            break;
        case 5:
            jsonData["matchType"] = value;
            break;
        case 6:
            jsonData["phraseType"] = value;
            break;
        case 7:
            jsonData["pause"] = value;
            break;
    }
    editKwdInfo(jsonData);
}


/**
 * 删除关键词
 */
function deleteKwd() {
    var ids = "";
    $("#tbodyClick").find(".list2_box3").each(function () {
        ids = ids + $(this).find("input[type=hidden]").val() + ",";
    });


    if (ids.split(",").length == 0) {
        alert("请选择行再操作!");
        return;
    }

    var isDel = window.confirm("您确定要删除关键词吗?");
    if (isDel == false) {
        return;
    }

    $.ajax({
        url: "/assistantKeyword/deleteById",
        type: "post",
        data: {"kwids": ids},
        dataType: "json",
        success: function (data) {
            $("#tbodyClick").find(".list2_box3 td:last").html("<span class='error' step='3'></span>");
        }
    });

    setKwdValue($("#tbodyClick tr:eq(0)"), $("#tbodyClick tr:eq(0)").find("input[type=hidden]").val());
}


/**
 * 失去焦点
 */
function missBlur(even, obj) {
    if (even.keyCode == 13) {
        obj.blur();
    }
}


$("#addOrUpdateKwd").livequery('click', function () {
    batchAddOrUpdate();
});

function batchAddOrUpdate() {
    top.dialog({title: "批量添加/更新",
        padding: "5px",
        content: "<iframe src='/assistantKeyword/showAddOrUpdateKeywordDialog' width='900' height='550' marginwidth='0' marginheight='0' scrolling='no' frameborder='0'></iframe>",
        oniframeload: function () {
        },
        onclose: function () {
//              if (this.returnValue) {
//                  $('#value').html(this.returnValue);
//              }
            whenClickTreeLoadData(getCurrentTabName(), getNowChooseCidAndAid());
        },
        onremove: function () {
        }
    }).showModal();
    return false;
}

$("#batchDelKwd").livequery('click', function () {
    batchDelKeyword();
});

function batchDelKeyword() {
    top.dialog({title: "批量删除",
        padding: "5px",
        content: "<iframe src='/assistantKeyword/showBatchDelDialog' width='900' height='550' marginwidth='0' marginheight='0' scrolling='no' frameborder='0'></iframe>",
        oniframeload: function () {
        },
        onclose: function () {
//              if (this.returnValue) {
//                  $('#value').html(this.returnValue);
//              }
            whenClickTreeLoadData(getCurrentTabName(), getNowChooseCidAndAid());
        },
        onremove: function () {
        }
    }).showModal();
    return false;
}


$(".searchwordReport").livequery('click', function () {
    searchword();
});

function searchword() {
    top.dialog({title: "搜索词报告",
        padding: "5px",
        content: "<iframe src='/assistantKeyword/showSearchWordDialog' width='900' height='570' marginwidth='0' marginheight='0' scrolling='no' frameborder='0'></iframe>",
        oniframeload: function () {
        },
        onclose: function () {
//              if (this.returnValue) {
//                  $('#value').html(this.returnValue);
//              }
            // window.location.reload(true);
        },
        onremove: function () {
        }
    }).showModal();
    return false;
}


/**
 * 还原按钮的事件
 */
$("#reduction").click(function () {
    reductionKeyword();
});

//还原关键词
function reductionKeyword() {
    var choose = $("#tbodyClick").find(".list2_box3");
    if (choose != undefined && choose.find("td:last").html() != "&nbsp;") {
        if (confirm("是否还原选择的数据?") == false) {
            return;
        }
        var step = choose.find("td:last span").attr("step");
        var id = $("#tbodyClick").find(".list2_box3").find("input[type=hidden]").val();
        switch (parseInt(step)) {
            case 1:
                reducKwd_Add(id);
                break;
            case 2:
                reducKwd_update(id);
                break;
            case 3:
                reducKwd_del(id);
                break;
            case 4:
                alert("属于单元级联删除，如果要恢复该数据，则必须恢复单元即可！");
                break;
        }
    }
}


/**
 * 还原新增的关键词(localStatus为1的)
 * @param id
 */
function reducKwd_Add(id) {
    $.ajax({
        url: "/assistantKeyword/reducAdd",
        type: "post",
        data: {"id": id},
        dataType: "json",
        success: function (data) {
            $("#tbodyClick").find(".list2_box3").remove();
        }
    });
}

/**
 * 还原修改的关键词(localStatus为2的)
 * @param id
 */
function reducKwd_update(id) {
    $.ajax({
        url: "/assistantKeyword/reducUpdate",
        type: "post",
        data: {"id": id},
        dataType: "json",
        success: function (data) {
            var jsonData = {};
            jsonData["object"] = data;
            jsonData["campaignName"] = $("#tbodyClick").find(".list2_box3 td:eq(9)").html();
            jsonData["quality"] = $("#tbodyClick").find(".list2_box3 td:eq(4)").attr("cname");
            jsonData["mobileQuality"] = $("#tbodyClick").find(".list2_box3 td:eq(5)").attr("cname");

            var html = keywordDataToHtml(jsonData, 0);
            var tr = $("#tbodyClick").find(".list2_box3");
            tr.replaceWith(html);
        }
    });
}


/**
 * 还原软删除
 * @param id
 */
function reducKwd_del(id) {
    $.ajax({
        url: "/assistantKeyword/reducDel",
        type: "post",
        data: {"id": id},
        dataType: "json",
        success: function (data) {
            $("#tbodyClick").find(".list2_box3 td:last").html("&nbsp;");
        }
    });
}






/************************************************************关键词的右击菜单************************************************************/
/**
 * 菜单名，方法
 * @type {{text: string, func: func}}
 */
var menu_keyword_add = {
        text: "添加关键词",
        func: function () {
            top.dialog({title: "关键词工具",
                padding: "5px",
                content: "<iframe src='/toAddPage' width='900' height='500' marginwidth='0' marginheight='0' scrolling='no' frameborder='0'></iframe>",
                onclose: function () {
                    whenClickTreeLoadData(getCurrentTabName(), getNowChooseCidAndAid());
                }
            }).showModal();
            return false;
        }
    }, menu_keyword_del = {
        text: "删除关键词",
        func: function () {
            deleteKwd();
        }
    }, menu_keyword_batchAddOrUpdate = {
        text: "批量添加/更新",
        func: function () {
            batchAddOrUpdate();
        }
    },menu_keyword_batchDel={
        text:"批量删除",
        func: function () {
            batchDelKeyword();
        }
    },menu_keyword_redu = {
        text:"还原",
        func: function () {
            reductionKeyword();
        }
    },menu_keyword_searchWord = {
    text:"搜索词",
    func: function () {
        searchword();
    }
}
/**
 * 右键菜单显示的选项
 * @type {*[]}
 */
var keywordMenuData = [
    [menu_keyword_add, menu_keyword_del, menu_keyword_batchAddOrUpdate,menu_keyword_batchDel,menu_keyword_redu,menu_keyword_searchWord]
];
/**
 * 用户缓存右键点击的对象
 * @type {null}
 */
var tmp = null;
/**
 * 菜单name值，标识唯一，beforeShow显示完成后方法
 * @type {{name: string, beforeShow: beforeShow}}
 */
var keywordMenuExt = {
    name: "keyword",
    beforeShow: function () {
        var _this = $(this);
        tmp = _this;
        $.smartMenu.remove();
    }
};

$("#tbodyClick").on("mousedown", "tr", function () {
    $(this).smartMenu(keywordMenuData, keywordMenuExt);
});
