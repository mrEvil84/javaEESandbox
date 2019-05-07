$( document ).ready(function() {


    console.log( "ready!" );

    /*
    $("#book-form-submit").click(function (event) {
        console.log("add book");

        var bookName = $("#book-form-name").val();
        var bookAuthor = $("#book-form-author").val();

        var formData = JSON.stringify({ "bookName": bookName, "bookAuthor" : bookAuthor });

        $.post("http://127.0.0.1:8080/usersengine/books",
            formData,
            function(data, status){
                // var response = jQuery.parseJSON(data);

                console.log(data.bookAuthor);
                console.log(data.bookName)
                console.log(status);

                // $.each(response.images, function(index, item) {
                //     console.log(item);
                // });

            }, "json");

    });
    */

    $("form").submit(function (event) {
        // console.log("book forms submit");
        var booksData =  JSON.stringify($( this ).serializeArray());
        console.log(booksData);
        $.post("http://127.0.0.1:8080/usersengine/books",
            booksData,
            function(data, status){
                // var response = jQuery.parseJSON(data);
                console.log(data);
                // $.each(response.images, function(index, item) {
                //     console.log(item);
                // });
            }, "json");
        event.preventDefault();
    });


    // $( "form" ).submit(function( event ) {
    //     console.log( $( this ).serializeArray() );
    //     event.preventDefault();
    // });


});