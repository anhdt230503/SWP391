<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/addMentorAndManager.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
        <title>Manager Management</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="container">
                        <div class="row">
                            <div class="col-8 h3">List of Managers</div>
                            <div class="col-4 text-right">
                                <button class="btn btn-primary" onclick="openModal()">Add Manager</button>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div id="uploadModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModal()">&times;</span>
                                <div class="container">
                                    <h2>Add Manager</h2>
                                    <form action="AddManager" method="post" class="mt-4">
                                        <div class="mb-3">
                                            <label for="fullname" class="form-label">Tên:</label>
                                            <input type="text" class="form-control" id="fullname" name="fullname" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email:</label>
                                            <input type="email" class="form-control" id="email" name="email" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Add Manager</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p>${errorMessage}</p><!--
                <p>${successMessage}</p>-->
            </div> 
            <table class="table caption-top table-bordered">

                <thead class="table-light">
                    <tr>
                        <th scope="col">Manager ID</th>
                        <th scope="col">Email</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">BirthDate</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  items="${managersWithStatus}" var="managerWithStatus">
                        <tr>
                            <td>${managerWithStatus.manager.managerId}</td>
                            <td>${managerWithStatus.manager.email}</td>
                            <td>${managerWithStatus.manager.fullname}</td>
                            <td>${managerWithStatus.manager.birthDate}</td>
                            <td>${managerWithStatus.manager.phoneNumber}</td>
                            <td>${managerWithStatus.status == 1 ? 'Active' : 'Blocked'}</td>
                            <td>
                                <form action="DeleteManagerController" method="post" style="display: inline-block">
                                    <input type="hidden" name="managerId" value="${managerWithStatus.manager.managerId}">
                                    <button type="button" class="btn btn-danger" onclick="confirmDelete(this)">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </form>
                                <a class="btn btn-sm btn-primary" href="EditManagerProfileByHR?managerId=${managerWithStatus.manager.managerId}">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <form action="BlockUserController" method="get" style="display: inline-block;">
                                    <input type="hidden" name="managerId" value="${managerWithStatus.manager.managerId}">
                                    <input type="hidden" name="status" value="${managerWithStatus.status}">
                                    <button type="submit" class="btn ${managerWithStatus.status == 1 ? 'btn-warning' : 'btn-success'}">
                                        ${managerWithStatus.status == 1 ? 'Block' : 'Unblock'}
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <script src="js/sidebar.js"></script>
    <script src="js/AddMentorAndManager.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <script>
                    function confirmDelete(button) {
                        Swal.fire({
                            title: 'Confirmation',
                            text: 'Are you sure you want to delete this product?',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: 'Yes',
                            cancelButtonText: 'Cancel'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                const form = button.closest('form');
                                form.submit();
                            }
                        });
                    }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
</body>
</html>
