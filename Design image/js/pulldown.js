$(function () {
    // プルダウン変更時に遷移
    $('select[name=pulldown1]').change(function () {
        if ($(this).val() != '') {
            window.location.href = $(this).val();
        }
    });
    $('select[name=pulldown2]').change(function () {
        if ($(this).val() != '') {
            window.location.href = $(this).val();
        }
    });
    $('select[name=language]').change(function () {
        if ($(this).val() != '') {
            window.location.href = $(this).val();
        }
    });
});
