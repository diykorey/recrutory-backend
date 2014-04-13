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
//add vacncy management
function Project(projectJson) {
    this.id = null;
    this.name = "";
    this.description = "";
    if (projectJson) {
        for (var prop in projectJson) this[prop] = projectJson[prop];
    }
}
function Vacancy(vacancyJson) {
    this.id = null;
    this.state = "OPEN";
    this.hot = false;
    this.requirements = '';
    this.tag = [];
    this.project = new Project()
    if (vacancyJson) {
        for (var prop in vacancyJson) this[prop] = vacancyJson[prop];
    }
}
/*   alternative constructor
 Vacancy.prototype.init = function (vacancyJson) {
 for (var prop in vacancyJson) this[prop] = vacancyJson[prop];
 };     */

function getTags(tagsJson) {

    if (!tagsJson) {
        return "";
    }
    var tags = "";
    $.each(tagsJson, function (key, tagJson) {
        if (!tags) {
            tags = tags + ", ";
        }
        tags = tags + tagJson["keyword"];
    });
    return tags;
}
function vacancyView(vacancy) {
    var title = 'New Vacancy';
    var saveBtn = 'Save'
    var vacancyFlowsPanel = '';
    var tags = getTags(vacancy.tag);

    var vacancyInfoPanel = '<form role="form">' +
        '<div class="form-group">' +
        '<label for="vacancyRequirements">Requirements:</label>' +
        '<textarea id="vacancyRequirements" class="form-control" rows="3">' + vacancy.requirements +
        '</textarea>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="projectDescription">Project Description:</label>' +
        '<textarea id="projectDescription" class="form-control" rows="3">' + vacancy.project.description +
        '</textarea>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="vacancyTags">Tags:</label>' +
        '<textarea id="vacancyTags" class="form-control" rows="3">' + tags +
        '</textarea>' +
        '</div>' +
        '</form>';

    var vacancyTabPanel = '<div class="tab-content">' +
        '<div class="tab-pane active" id="vacancyInfoTab">' + vacancyInfoPanel +
        '</div>' +
        '<div class="tab-pane" id="vacancyFlowsTab">' + vacancyFlowsPanel +
        '</div>' +
        '<div class="tab-pane" id="vacancyCommentsTab">3...</div>' +
        '<div class="tab-pane" id="vacancyTaskTab">4...</div>' +
        '</div>';
    var vacancyNavbar = '<nav class="navbar navbar-default" role="navigation">' +
        '<div class="container-fluid">' +
        <!-- Collect the nav links, forms, and other content for toggling -->
        '<div class="collapse navbar-collapse" id="vacancyNavbar">' +
        '<ul class="nav navbar-nav" id="vacancyTabs">' +
        '<li class="active"><a href="#vacancyInfoTab" data-toggle="tab">Main</a></li>' +
        '<li><a href="#vacancyFlowsTab" data-toggle="tab">Flows</a></li>' +
        '<li><a href="#vacancyCommentsTab" data-toggle="tab">Comments</a></li>' +
        '<li><a href="#vacancyTaskTab" data-toggle="tab">Task</a></li>' +
        '</ul>' +
        '</div>' +
        <!-- /.navbar-collapse -->
        '</div>' +
        <!-- /.container-fluid -->
        '</nav>';
    var vacancyMainPanel = '<div class="panel panel-default">' +
        '<div class="panel-heading">' + title +
        '<button type="button" class="btn btn-default btn-xs  pull-right">' +
        '<span class="glyphicon glyphicon-save"></span>' + saveBtn +
        '</button>' +
        '</div>' +
        ' <div class="panel-body">' + vacancyTabPanel + vacancyNavbar +
        '</div>' +
        '</div>';
    return vacancyMainPanel;
}
function createVacancyManager(vacancyJson) {
    var view;
    if (vacancyJson) {
        var vacancy = new Vacancy(vacancyJson);
        view = vacancyView(vacancy);
    } else {
        var vacancy = new Vacancy();
        view = vacancyView(vacancy);
    }
    $("#vacancyManagementHolder").html(view);
}
$(document).ready(function () {
    readActiveVacancies();
    createVacancyManager();
});


