$( document ).ready(function() {


    console.log( "ready!" );


    $("#book-form-submit").click(function (event) {
        console.log("add book");

        var bookName = $("#book-form-name").val();
        var bookAuthor = $("#book-form-author").val();

        //var jsonData = JSON.stringify({ "name": bookName, "author"});

        $.post("http://127.0.0.1:8080/usersengine/books",
            {
                'book': 'gosia',
                'author': 'sukces'
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            }, "json");

        // $.ajax({
        //     type: "POST",
        //     url: "http://127.0.0.1:8080/usersengine/books",
        //     dataType: "json",
        //     data: {name: bookName, author: bookAuthor},
        //     success: function (l) {
        //
        //         console.log(l);
        //         alert("Done");
        //     }
        // });

        // $.ajax({
        //     type: "POST",
        //     url: url,
        //     data: data,
        //     success: success,
        //     dataType: dataType
        // });

    });


});