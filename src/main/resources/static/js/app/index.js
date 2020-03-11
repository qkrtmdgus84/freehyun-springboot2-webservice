var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
                _this.save();
            });

        $('#btn-update').on('click', function () { //btn-update 란 id를 가진 HTML 엘리먼트에 click 이벤트가 발생할 때 update function 을 실행하도록 이벤트를 등록
                _this.update();
            });

        $('#btn-delete').on('click', function () {
                _this.delete();
            });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/'; //글 등록이 성공하면 메인페이지(/)로 이동합니다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () { //신규로 추가될 update function
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', //여러 HTTP Method 중 PUT 메소드를 선택, PostsApiController 에 있는 API 에서 이미 @PutMapping 으로 선언했기 때문에 PUT 을 사용, REST 에서 CRUD 는 HTTP Method 에 매핑, 생성(create)-POST, 읽기(Read)-GET, 수정(Update)-PUT, 삭제(Delete)-DELETE
            url: '/api/v1/posts/'+id, //어느 게시글을 수정할지 URL Path 로 구분하기 위해 Path 에 id 를 추가
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/'; //수정이 성공하면 메인페이지(/)로 이동합니다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE', //REST 에서 CRUD 는 HTTP Method 에 매핑, 생성(create)-POST, 읽기(Read)-GET, 수정(Update)-PUT, 삭제(Delete)-DELETE
            url: '/api/v1/posts/'+id, //어느 게시글을 삭제할지 URL Path 로 구분하기 위해 Path 에 id 를 추가
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/'; //수정이 성공하면 메인페이지(/)로 이동합니다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();