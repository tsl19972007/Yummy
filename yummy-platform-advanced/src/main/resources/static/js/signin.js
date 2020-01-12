$().ready(function () {
    $("#email_signup").click(emailReq);
    $("#password_signup").click(passwordReq);
    $("#password2_signup").click(checkPasswordReq);
    $("#name_signup").click(nameReq);
    $("#phone_signup").click(phoneReq);

    $("#email_signup").change(checkEmail);
    $("#password_signup").change(checkPasswordForm);
    $("#password2_signup").change(checkpasswordSame);
    $("#name_signup").change(checkName);
    $("#phone_signup").change(checkPhone);

    $('.form').find('input, textarea').on('keyup blur focus', function (e) {
        var $this = $(this),
            label = $this.prev('label');

        if (e.type === 'keyup') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.removeClass('highlight');
            }
        } else if (e.type === 'focus') {

            if ($this.val() === '') {
                label.removeClass('highlight');
            } else if ($this.val() !== '') {
                label.addClass('highlight');
            }
        }
    });

    $('.tab a').on('click', function (e) {
        e.preventDefault();

        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');

        target = $(this).attr('href');

        $('.tab-content > div').not(target).hide();

        $(target).fadeIn(600);
    });
});
function emailReq() {
    $("#errorInfo").attr("class","err");
    $("#errorInfo").text("合法可用的邮箱地址");
}
function passwordReq() {
    $("#errorInfo").attr("class", "err");
    $("#errorInfo").text("6~12位数字、大小写字母、中划线、下划线")
}
function checkPasswordReq() {
    $("#errorInfo").attr("class", "err");
    $("#errorInfo").text("请确认密码")
}
function nameReq() {
    $("#errorInfo").attr("class","err");
    $("#errorInfo").text("请输入姓名");
}
function phoneReq() {
    $("#errorInfo").attr("class","err");
    $("#errorInfo").text("请输入电话号码，5-15位数字");
}
function checkEmail() {
    var patten = new RegExp(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/);
    var name = $(this).val();
    if (!patten.test(name)){
        $(this).attr("class", "red");
    }else {
        $("#errorInfo").text("请输入信息");
        $("#errorInfo").attr("class","correct");
        $(this).attr("class", "green");
    }
}
function checkPasswordForm() {
    var patten = new RegExp(/^[a-z0-9_-]{6,12}$/);
    var name = $(this).val();
    if (!patten.test(name)) {
        $(this).attr("class", "red");
    } else {
        $("#errorInfo").text("请输入信息");
        $("#errorInfo").attr("class","correct");
        $(this).attr("class", "green");
    }
}
function checkpasswordSame() {
    var password = $("#password_signup").val();
    var check = $(this).val();
    if (password != check) {
        $(this).attr("class", "red");
        $("#errorInfo").text("密码不一致");
    } else {
        $("#errorInfo").text("请输入信息");
        $("#errorInfo").attr("class","correct");
        $(this).attr("class", "green");
    }
}
function checkName(){
    var name = $(this).val();
    if (name=="") {
        $(this).attr("class", "red");
    } else {
        $("#errorInfo").text("请输入信息");
        $("#errorInfo").attr("class","correct");
        $(this).attr("class", "green");
    }
}
function checkPhone(){
    var patten = new RegExp(/^[0-9]{5,15}$/);
    var name = $(this).val();
    if (!patten.test(name)) {
        $(this).attr("class", "red");
    } else {
        $("#errorInfo").text("请输入信息");
        $("#errorInfo").attr("class","correct");
        $(this).attr("class", "green");
    }
}
