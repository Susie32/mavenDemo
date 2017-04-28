/**
 * Created by Susie on 2017/3/31.
 */

function turnToRegistPage() {
    self.location.href="registPage.html";
}

function turnToLoginPage() {
    self.location.href="loginPage.html";
}

function turnToUserCenterPage() {
    self.location.href="userCenter.html";
}

function turnTolistTopicPage() {
    self.location.href="listTopic.html";
}

function logout() {
    //delCookie("userName")
    writeUserCookies("")
    turnToLoginPage();
}

//cookies: http://www.5idev.com/p-javascript_document_cookie.shtml
function writeUserCookies(userName) {
    var text="userName="+userName;
    var date = new Date();
    date.setTime(date.getTime() + 24 * 3600 * 1000);
    document.cookie = text + ";path=/;expire=" + date.toGMTString();
}

function delCookie(name) {
    var myDate=new Date();
    myDate.setTime(-1000);//设置时间
    document.cookie=name+"=''; expires="+myDate.toGMTString();
}

function getKeyfromCookie(key) {
    var str_cookie=document.cookie;
    var arr_cookie = str_cookie.split("; ");
    var key_value=null;
    //遍历 arr_cookie 数组
    for(var i=0;i<arr_cookie.length;i++){
        var arr = arr_cookie[i].split("=");
        //找到名称为key的cookie值
        if(key==arr[0]){
            key_value=arr[1];
            break;
        }
    }
    return key_value;
}