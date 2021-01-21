var pageVm = new function () {
    var control = {
       mainContent : "#main_content"
    }
    this.init = function () {
        load();
    };

    var load = function () {
        $(control.mainContent).load("./page/controlpage.html");
    }
};

$(document).ready(function () {
    pageVm.init();
});

