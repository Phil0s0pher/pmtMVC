$(function(){
    $(".Open-deleteRecordModal").click(function(){
        $('#u_id').val($(this).data('id'));
        $('#deleteRecordModal').modal('show');
    });
});

$(function(){
    $(".Open-deleteGroupModal").click(function(){
        $('#group_name').val($(this).data('id'));
        $('#deleteGroupModal').modal('show');
    });
});




