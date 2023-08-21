let  address=document.getElementById("add");
let  mail= document.getElementById("email");
let  tel=document.getElementById("tel");
let social=document.getElementById("social");
let  btnCancel=document.getElementById("cancel");

function clearField()   {
    address.clear();
    mail.clear();
    tel.clear();
    social.clear();
}
function onError() {
    address.setAttribute("class","input is-danger");
    mail.setAttribute("class","input is-danger");
    tel.setAttribute("class","input is-danger");
    social.setAttribute("class","input is-danger");
}