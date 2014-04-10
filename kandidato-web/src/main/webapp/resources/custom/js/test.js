function createRow(id) {
    var vacancyHtml = '<div class="row-fluid" id="' + id + '"></div>';
    return vacancyHtml;
}
function createVacancy(vacancyJson) {
    var labelType = (vacancyJson.state == "OPEN") ? "success" : "info";
    var vacancyHtml = '<div class="col-xs-3">' +
        '<div class="panel panel-default" draggable="true">' +
        '<div class="panel-heading" style="padding: 2px 2px;">&nbsp;' +
        '<div class="label label-' + labelType + '  pull-right">' + vacancyJson.state + '</div>' +
        '</div>' +
        '<div class="panel-body" contenteditable="true">' + vacancyJson.requirements + '</div>' +
        '</div>' +
        '</div>';
    return vacancyHtml;
}

function readActiveVacancies() {
    $.getJSON("http://localhost:8080/kandidato-web-0.1/vacancy/byState/open", function (vacancies) {
        var items = [];
        var vacancyNumber = 0;
        var rowNumber = 0
        var currentRawId = '';
        console.log(vacancies);
        $.each(vacancies, function (key, vacancyJson) {
            if (vacancyNumber % 4 == 0) {
                currentRawId = 'raw_' + rowNumber;
                var rawHtml = createRow(currentRawId);
                $("#vacancyBoardHolder").append(rawHtml);
                rowNumber = rowNumber + 1;
            }
            vacancyNumber = vacancyNumber + 1;
            var vacancyHtml = createVacancy(vacancyJson);
            $("#" + currentRawId).append(vacancyHtml);
        });
    });
}
$(document).ready(function () {
    readActiveVacancies();
});


