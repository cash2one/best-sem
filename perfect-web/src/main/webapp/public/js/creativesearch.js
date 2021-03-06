/**
 * Created by vbzer_000 on 2014/9/19.
 */
function search() {

    var type = $("input[name=searchType]:checked").val();

    var kw = $("#txt" + type).val();


    var region = $("#region").children('option:selected').attr("value");


    $.ajax({
        url: "/creative/q",
        data: {
            'q': kw,
            'p': 1,
            's': 10,
            'r': region
        },
        type: "GET",
        success: function (datas) {
            if (datas.rows == undefined || datas.rows.total == 0) {
                $("#creativeList").empty();
                return;
            } else {
                $("#creativeList").empty();
            }

            $.each(datas.rows.list, function (i, item) {
                var li = $("<li></li>");

//                li.append($("<div></div>").attr("id", "sPreview").append("<a href='" + item.host + "'><h3>" + item.title + "</h3></a>")
//                    .append("<br>" + item.body + "<br><a href=''" + item.host + "'>" + item.host + "</a>创意来源: " + item.region));

                li.append($("<div></div>")).append(item.title);

                li.append("<div><span class=\"cretiveunder fr\"><a href=\"#\">置顶</a>|<a href='javascript:void(0)' class=\"showbox\" onclick='reView(this)'>编辑</a>|<a href=\"#\">删除</a></span></div>");

                $("#creativeList").append(li);

            });

            $("#terms").empty();

            $.each(datas.rows.terms, function (i, item) {
                var termli = $("<li></li>");
                termli.append("<span>" + item.key + "</span><b>" + item.value + "%</b>");
                $("#terms").append(termli);
            });


            $("#hosts").empty();

            $.each(datas.rows.hosts, function (i, item) {
                var termli = $("<li></li>");
                termli.append("<span>" + item.key + "</span><b>" + item.value + "%</b>");
                $("#hosts").append(termli);
            });

            $("#regions").empty();

            $.each(datas.rows.regions, function (i, item) {
                var termli = $("<li></li>");
                termli.append("<span>" + item.key + "</span><b>" + item.value + "%</b>");
                $("#regions").append(termli);
            })
        }
    })

}


function findKeywords(aid) {
    if (aid == -1)
        return;

    $.ajax({
        url: "/keyword/all/" + aid,
        type: "GET",
        async: false,
        success: function (datas) {
            if (datas.rows == undefined) {
                return;
            }
            $('#txt1').val("");

            var value = "";
            $.each(datas.rows, function (i, item) {
                value = value + item.keyword + "\n";
            });
            $("#txt1").val(value);

        }
    })

}


function findAdgroup(cid) {
    if (cid == -1)
        return;
    $.ajax({
        url: "/adgroup/getAdgroupByCampaignId/" + cid,
        type: "GET",
        async: false,
        success: function (datas) {
            if (datas.rows == undefined) {
                return;
            }
            $('#adgroup').empty();
            $('#adgroup').append("<option value=\"-1\">请选择单元</option>");

            $.each(datas.rows, function (i, item) {
                var opt = $("<option></option>").attr("value", item.adgroupId);
                opt.append(item.adgroupName);
                $('#adgroup').append(opt);
            });
        }
    })


}

function load() {

    $("#campagin").change(function () {
        var cid = $(this).children('option:selected').val();
        findAdgroup(cid);
    });

    $("#adgroup").change(function () {
        var aid = $(this).children('option:selected').val();
        findKeywords(aid);
    });


    $.ajax({
        url: "/regional/getProvince",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (datas) {
            if (datas.rows == undefined) {
                return;
            }

            $('#region').empty();
            $.each(datas.rows, function (i, item) {
                var opt = $("<option></option>").attr("value", item.regionalId);
                opt.append(item.regionalName);
                $('#region').append(opt);
            })
        }
    })


    $.ajax({
        url: "/campaign/getAllCampaign",
        type: "GET",
        async: false,
        success: function (datas) {
            if (datas.rows == undefined) {
                return;
            }

            $('#campagin').empty();
            $('#campagin').append("<option value=\"-1\">请选择计划</option>");

            $.each(datas.rows, function (i, item) {
                var opt = $("<option></option>").attr("value", item.campaignId);
                opt.append(item.campaignName);
                $('#campagin').append(opt);
            });

        }
    })


}


$(function () {
    load();
});