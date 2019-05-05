$( document ).ready(function() {


    console.log( "ready!" );


    $("#book-form-submit").click(function (event) {
        console.log("add book");

        var bookName = $("#book-form-name").val();
        var bookAuthor = $("#book-form-author").val();


            $.ajax({
                type: 'post',
                url: "http://127.0.0.1:8080/usersengine/books",
                dataType: "json",
                data: {name: bookName, author: bookAuthor},
                success: function (l) {

                    console.log(l);
                    alert("Done");
                }
            });

    });


});