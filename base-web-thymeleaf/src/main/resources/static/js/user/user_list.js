$(function () {
    $("#query").on("click",function () {
        user_list.getUserList();
    })

    $('#example').bootstrapPaginator(user_list.options);
    // alert("user信息");
})

var user_list = {
    "options": {
        currentPage: 3,
        totalPages: 10,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        }
    },
    "getUserList": function () {
        $.ajax({
            type: "GET",
            url: "/user/query",
            data: {"name": null},
            async: true,
            dataType: 'json',
            success: function (json) {
                alert("hehe" + json[0].userName);
                $('#example').bootstrapPaginator(user_list.options);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR);
            }
        });
    }
}