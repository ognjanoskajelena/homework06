$(document).ready(function () {
    $("#feedback").hide();
    $(".empty-comment").hide();

    $("#btn-yes").click(function () {
        let pollId = $("#send-poll").attr("pollId");
        let username = $("#send-poll").attr("user");
        let openResponses = [];
        $(".textarea-response").each(function () {
            let tempObj = {
                "id": parseInt($(this).attr("id")),
                "response": $(this).val()
            }
            openResponses.push(tempObj);
        });

        let singleChoices = [];
        $(".radio-poll:checked").each(function () {
            singleChoices.push(parseInt($(this).val()));
        });

        let multipleChoices = [];
        $(".checkbox-poll:checked").each(function () {
            multipleChoices.push(parseInt($(this).val()));
        });

        let dataToGo = {
            "username": username,
            "open": openResponses,
            "single": singleChoices,
            "multiple": multipleChoices
        }
        //console.log(dataToGo);

        $.ajax({
            type: "POST",
            url: "/api/polls/" + pollId + "/fill",
            data: JSON.stringify(dataToGo),
            contentType: "application/json",
            success: function (response) {
                $("#active-poll").hide();
                $("#feedback").show();
            },
            error: function (req, status, error) {
                alert("An error occurred :( Please try again!")
            }
        });
    });

    $("#btn-post").click(function () {
        let comment = $("#textarea-comment").val();
        if (comment !== "") {
            let discussionId = $("#textarea-comment").attr("discussion");
            let author = $("#btn-post").attr("user");

            let dataToGo = {
                "content": comment,
                "author": author
            };
            // console.log(dataToGo);

            $.ajax({
                type: "POST",
                url: "/api/discussions/" + discussionId + "/add-comment",
                data: JSON.stringify(dataToGo),
                contentType: "application/json",
                success: function (response) {
                    $("#textarea-comment").val("");
                    fetchDiscussionComments(discussionId);
                },
                error: function (req, status, error) {
                    console.log(req);
                    alert("An error occurred :( Please try again!")
                }
            });
        } else {
            $(".empty-comment").show();
        }
    });

    $(".all-comments").on("click", ".delete-comment", function (event) {
        let commentId = event.currentTarget.attributes.id.nodeValue;
        console.log(commentId);
        $.ajax({
            type: "DELETE",
            url: "/api/comments/" + commentId + "/delete",
            success: function (response) {
                let commentCard = $(event.currentTarget).parent().parent();
                $(commentCard).hide();
            },
            error: function (req, status, error) {
                console.log(req);
            }
        });

    })
});

function fetchDiscussionComments(discussionId) {
    $.ajax({
        type: "GET",
        url: "/api/discussions/" + discussionId + "/all-comments",
        success: function (response) {
            refreshDiscussionComments(response);
        },
        error: function (req, status, error) {
            console.log(req);
        }
    });
}

function refreshDiscussionComments(data) {
    $(".all-comments").html("");
    for (let i = 0; i < data.length; i++) {
        let card = $("<div>").addClass("card mb-1");

        let cardHeader = $("<div>").addClass("card-header text-end py-1");
        if (data[i].author.username === $("#btn-post").attr("user")) {
            cardHeader.append("<i class='fa fa-trash text-danger fs-5 pt-1 delete-comment' id=" + data[i].id + "></i>");
        } else {
            cardHeader.append("<i class='fa fs-5 pt-1'></i>");
        }
        card.append(cardHeader);

        let bodyDiv = $("<div>").addClass("card-body");
        let textP = $("<p>").addClass("card-text");
        let authorSpan = $("<span>").addClass("d-block pb-2").append("<i class='fa fa-user'></i> &nbsp;" + data[i].author.username);
        let contentSpan = $("<span>").addClass("d-block pb-2").text(data[i].content);
        textP.append(authorSpan);
        textP.append(contentSpan);
        bodyDiv.append(textP);
        let footerDiv = $("<div>").addClass("text-end");
        let small = $("<small>").addClass("text-muted")

        let splitedDate = data[i].datePosted.split("-");
        let dateAndTime = splitedDate.reverse().join("/") + " " + data[i].timePosted;

        small.text(dateAndTime);
        footerDiv.append(small);
        bodyDiv.append(footerDiv);

        card.append(bodyDiv);
        $(".all-comments").append(card);
    }
}