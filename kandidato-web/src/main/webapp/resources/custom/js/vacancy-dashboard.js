var activeVacancy = new Vacancy();
var pickedVacancy;
var selectedVacancyModified = false;

function getRowId(rowNumber) {
    return 'raw_' + rowNumber;
}
function getVacancyPanelHeaderId(vacancyId) {
    return 'vacancyPanelHeader_' + vacancyId;
}
function getVacancyPanelId(vacancyId) {
    return 'vacancyPanel_' + vacancyId;
}


//add vacancy management
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
    this.number = null;
    this.name = null;
    this.state = "OPEN";
    this.hot = false;
    this.requirements = '';
    this.tags = [];
    this.project = new Project()
    if (vacancyJson) {
        for (var prop in vacancyJson) this[prop] = vacancyJson[prop];
    }
}
/*   alternative constructor
 Vacancy.prototype.init = function (vacancyJson) {
 for (var prop in vacancyJson) this[prop] = vacancyJson[prop];
 };     */

function getTags(vacancy) {

    if (!vacancy) {
        return "";
    }
    if (!vacancy.tags) {
        return "";
    }
    var tags = "";
    $.each(vacancy.tags, function (key, tagJson) {
        if (tags) {
            tags = tags + ", ";
        }
        tags = tags + tagJson["keyword"];
    });
    return tags;
}
function getProjectName(vacancy) {

    if (!vacancy) {
        return "";
    }
    if (!vacancy.project) {
        return "";
    }
    if (!vacancy.project.name) {
        return "";
    }
    return vacancy.project.name;
}


function getVacancyRequirements(vacancy) {

    if (!vacancy) {
        return "";
    }
    if (!vacancy.requirements) {
        return "";
    }

    return vacancy.requirements;
}
function getVacancyName(vacancy) {

    if (!vacancy) {
        return "";
    }
    if (!vacancy.name) {
        return "";
    }

    return vacancy.name;
}

function getVacancyNumber(vacancy) {

    if (!vacancy) {
        return "";
    }
    if (!vacancy.number) {
        return "";
    }

    return vacancy.number;
}

function initVacancyCloseConfirmationWindow() {
    $('#vacancyCloseConfirmationWindow').on('hidden.bs.modal', function (e) {
        selectVacancy();
    });
}
function addVacancyChangeListener(object) {
    object.on('input propertychange', function () {
        selectedVacancyModified = true;
    });
}



function addSelect2ProjectName(name, objectId, sourceUrl) {
    function projectFormatResult(project) {
        return project.name;
    }

    function projectFormatSelection(project) {
        return project.name;
    }

    function requestPaging(query, page) { // page is the one-based page number tracked by Select2
        return {
            query: query, //search term
            limit: 10, // page size
            page: page // page number
        };
    };
    function dataFormatter(data, page) {

        var more = false;//(page * 10) < data.total; // whether or not there are more results available
        if (data && data.length >= 10) {
            more = true;
        }
        // notice we return the value of more so Select2 knows if more results can be loaded
        return {results: data, more: more};
    };
    function initProjectSelection(element, callback) {
        if (activeVacancy.project) {
            callback(activeVacancy.project)
        }
    }

    $(objectId).select2({
        placeholder: "Choose project",
        ajax: {
            url: sourceUrl,
            dataType: 'json',
            quietMillis: 100,
            data: requestPaging,
            results: dataFormatter
        },
        initSelection: initProjectSelection,
        formatResult: projectFormatResult,
        formatSelection: projectFormatSelection,
        dropdownAutoWidth: true,
        escapeMarkup: function (m) {
            return m;
        }
    });

    $(objectId)
        .on("change", function (e) {
            activeVacancy.project = e.added;
        })
}

function addSelect2Tags(name, objectId, sourceUrl) {
    function projectFormatResult(project) {
        return project.name;
    }

    function projectFormatSelection(project) {
        return project.name;
    }

    function requestPaging(query, page) { // page is the one-based page number tracked by Select2
        return {
            query: query, //search term
            limit: 10, // page size
            page: page // page number
        };
    };
    function dataFormatter(data, page) {

        var more = false;//(page * 10) < data.total; // whether or not there are more results available
        if (data && data.length >= 10) {
            more = true;
        }
        // notice we return the value of more so Select2 knows if more results can be loaded
        return {results: data, more: more};
    };
    function initProjectSelection(element, callback) {
        if (activeVacancy.project) {
            callback(activeVacancy.project)
        }
    }

    $(objectId).select2({
        placeholder: "Choose project",
        ajax: {
            url: sourceUrl,
            dataType: 'json',
            quietMillis: 100,
            data: requestPaging,
            results: dataFormatter
        },
        initSelection: initProjectSelection,
        formatResult: projectFormatResult,
        formatSelection: projectFormatSelection,
        dropdownAutoWidth: true,
        escapeMarkup: function (m) {
            return m;
        }
    });

    $(objectId)
        .on("change", function (e) {
            activeVacancy.project = e.added;
        })
}


function createRow(id) {
    var vacancyHtml = '<div class="row-fluid" id="' + id + '"></div>';
    return vacancyHtml;
}
function createVacancy(vacancyJson) {
    var labelType = (vacancyJson.state == "OPEN") ? "success" : "info";
    var vacancyHtml = '<div class="col-xs-3">' +
        '<div class="panel panel-default" id="vacancyPanel_' + vacancyJson.id + '">' +
        '<div class="panel-heading clickable" style="padding: 2px 2px;" id="vacancyPanelHeader_' + vacancyJson.id + '">&nbsp;' +
        '<div class="label label-' + labelType + '  pull-right">' + vacancyJson.state + '</div>' +
        '</div>' +
        '<div class="panel-body" contenteditable="false">' + getVacancyRequirements(vacancyJson) + '</div>' +
        '</div>' +
        '</div>';

    return vacancyHtml;
}
function selectVacancy() {
    var pickedVacancyPanelId = getVacancyPanelId(pickedVacancy.id);
    $("#" + pickedVacancyPanelId).toggleClass("toogle");
    if (activeVacancy.id === pickedVacancy.id) {
        activeVacancy = new Vacancy();
        createVacancyManager();
    } else {
        var activeVacancyPanelId = getVacancyPanelId(activeVacancy.id);
        $("#" + activeVacancyPanelId).toggleClass("toogle");
        activeVacancy = pickedVacancy;
        createVacancyManager(activeVacancy);
    }
}
function saveVacancyChanges() {
    alert("save");
    activeVacancy.requirements = $('#vacancyRequirements').value;
    activeVacancy
    $('#vacancyCloseConfirmationWindow').modal('hide');
}

function readActiveVacancies() {
    $.getJSON("http://localhost:8080/kandidato-web-0.1/vacancy/byState/open", function (vacancies) {
        var items = [];
        var vacancyNumber = 0;
        var rowNumber = 0
        var currentRowId = '';
        console.log(vacancies);
        $.each(vacancies, function (key, vacancyJson) {
            if (vacancyNumber % 4 == 0) {
                currentRowId = getRowId(rowNumber);
                var rowHtml = createRow(currentRowId);
                $("#vacancyBoardHolder").append(rowHtml);
                rowNumber = rowNumber + 1;
            }
            vacancyNumber = vacancyNumber + 1;
            var vacancyHtml = createVacancy(vacancyJson);
            $("#" + currentRowId).append(vacancyHtml);
            var vacancyPanelHeaderId = getVacancyPanelHeaderId(vacancyJson.id);
            $("#" + vacancyPanelHeaderId).click(function (eventObject) {
                pickedVacancy = new Vacancy(vacancyJson);
                if (selectedVacancyModified) {
                    $('#vacancyCloseConfirmationWindow').modal('show')
                } else {
                    selectVacancy();
                }

            });
        });
    });
}

function vacancyView(vacancy) {
    selectedVacancyModified = false;
    var vacancyNumber = getVacancyNumber();
    var title = (vacancy) ? vacancyNumber : 'New';
    var saveBtn = 'Save'
    var vacancyFlowsPanel = '';
    var tags = getTags(vacancy);
    var projectName = getProjectName(vacancy);
    var requirements = getVacancyRequirements(vacancy);
    var vacancyName = getVacancyName();


    var vacancyInfoPanel = '<form role="form">' +
        '<div class="form-group">' +
        '<label for="vacancyName">Project Name:</label>' +
        '<div > <input id="vacancyName" class="form-control input-sm select2-multiple" value="' + vacancyName + '"/></div > ' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="vacancyRequirements">Requirements*:</label>' +
        '<textarea id="vacancyRequirements" class="form-control" rows="3" placeholder="Text input">' + requirements +
        '</textarea>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="projectName">Project Name:</label>' +
        '<div > <input type="hidden" id="projectName" class="form-control input-sm select2-multiple" value="' + projectName + '"/></div > ' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="vacancyTags">Tags:</label>' +
        '<textarea id="vacancyTags" class="form-control" rows="3" placeholder="Text input">' + tags +
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
        '<div class="panel-heading">&nbsp;' + title +
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
    var view = vacancyView(vacancyJson);

    $("#vacancyManagementHolder").html(view);
    addVacancyChangeListener($('#vacancyRequirements'));
    addVacancyChangeListener($('#projectName'));
    addSelect2ProjectName("projectName", "#projectName", "http://localhost:8080/kandidato-web-0.1/project/find");
    addSelect2Tags("vacancyTags", "#vacancyTagse", "http://localhost:8080/kandidato-web-0.1/project/find");
}

function readProjects() {
    $.getJSON("http://localhost:8080/kandidato-web-0.1/project/find", function (projects) {
        console.log(projects);

    });
}

$(document).ready(function () {
    readActiveVacancies();
    createVacancyManager();
    readProjects();
    initVacancyCloseConfirmationWindow();
});


