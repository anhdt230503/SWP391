/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function openModal() {
    var modal = document.getElementById("uploadModal");
    modal.style.display = "block";
}
function closeModal() {
    var modal = document.getElementById("uploadModal");
    modal.style.display = "none";
}

function createReport() {
    var title = document.getElementById("reportTitle").value;
    var file = document.getElementById("reportFile").value;
    var date = document.getElementById("reportDate").value;

    var table = document.getElementById("reportTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.rows.length);
    var titleCell = newRow.insertCell(0);
    var fileCell = newRow.insertCell(1);
    var dateCell = newRow.insertCell(2);

    titleCell.innerHTML = title;
    fileCell.innerHTML = file;
    dateCell.innerHTML = date;
    closeModal();


    titleCell.innerHTML = title;
    fileCell.innerHTML = file;
    dateCell.innerHTML = date;

    // Close the modal after creating the report
    closeModal();
    document.getElementById("reportTitle").value = "";
    document.getElementById("reportFile").value = "";
    document.getElementById("reportDate").value = "";
}

function chooseDownloadFolder() {
    var selectedFile = document.querySelector('input[type=file]').files[0];
    if (!selectedFile) {
        alert('Please upload a file first.');
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "DownloadServlet?fileName=" + selectedFile.name, true);
    xhr.responseType = "blob";

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "DownloadServlet?fileName=" + selectedFile.name, true);
    xhr.responseType = "blob";

    xhr.onload = function (event) {
        var blob = xhr.response;
        var link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = selectedFile.name;
        link.click();
    };
    xhr.send();
}
function filterReportList() {
    var selectedOption = document.getElementById("reportTitle").value;
    var rows = document.querySelectorAll("#reportTable tbody tr");
    rows.forEach(function (row) {
        var reportType = row.querySelector("td:first-child").innerText.trim();
        if (selectedOption === "Midterm Report" && reportType !== "Midterm Report") {
            row.style.display = "none";
        } else if (selectedOption === "Final Report" && reportType !== "Final Report") {
            row.style.display = "none";
        } else {
            row.style.display = "";
        }
    });
}
    xhr.send();
