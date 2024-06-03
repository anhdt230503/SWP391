function limitSelection() {
    var checkboxes = document.querySelectorAll('input[name="selectedInterns"]:checked');
    if (checkboxes.length > 3) {
        alert("You can only select up to 3 interns.");
        return false;
    }
    return true;
}

function checkSelectionCount() {
    var checkboxes = document.querySelectorAll('input[name="selectedInterns"]');
    var selectedCount = 0;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedCount++;
        }
        if (selectedCount > 3) {
            checkboxes[i].checked = false;
            alert("You can only select up to 3 interns.");
        }
    }
}
