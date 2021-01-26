var pageVm = new function () {
    var control = {
       mainContent : "#main_content"
    }
    this.init = function () {
        pageVm.load("./page/maitemainmanage.html");
    };

    this.load = function (url) {
        $(control.mainContent).empty();
        $(control.mainContent).load(url);
    }
};

$(document).ready(function () {
    pageVm.init();
});

