/**
 * Created by diyko on 14.05.2014.
 */


function confirmCloseDialog() {
    var id = "vacancyCloseConfirmation";
    var yesBtn = createControlButton("Yes", "saveVacancy()");
    var noBtn = createControlButton("No", " selectVacancy()", true);
    var dialog = createModalDialog(id, "Warning!", "Would you like to save changes before switch to another vacancy?", [yesBtn, noBtn]);
    return dialog;
}


function closeModalDialog(id) {
    $('#' + id).modal('hide');
}

function createControlButton(text, func) {
    var dismissHandler = "";
    if (dismiss === true) {
        dismissHandler = 'data-dismiss = "modal"';
    }
    var clickHandler = "";
    if (func) {
        clickHandler = ' onclick="' + func;
    }
    var button = '<button type="button" class="btn btn-primary btn-xs" data-dismiss = "modal"' + clickHandler + '">' + text + '</button>';
    return button;
}


function createModalDialog(id, title, message, controlButtons) {
    var controlButtonsHtml = "";
    if (controlButtons) {
        $.each(controlButtons, function (key, controlButton) {
            controlButtonsHtml = controlButtonsHtml + controlButton
        });
    }

    var modalDialog = '<div class="modal fade" id="' + id + '" tabindex="-1" role="dialog"  aria-hidden="true">' +
        '<div class="modal-dialog modal-sm">' +
        '<div class="modal-content">' +
        '<div class="modal-header alert alert-success" style="padding:10px;">' + title +
        '<button id="" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
        '</div>' +
        '<div  class="modal-body">' + message + '</div>' +
        '<div class="modal-footer" style="padding:10px;" id="btnContainerId">' +
        controlButtons +
        '<button type="button" class="btn btn-default btn-xs"  data-dismiss="modal">Cancel</button>' +
        '</div>' +
        '</div>' +
        <!-- /.modal-content -->
        '</div>' +
        '</div>';

    return modalDialog;
}

$(document).ready(function () {
    var dialog = confirmCloseDialog();
    $("#vacancyDashboardBodyId").append(dialog);
});
