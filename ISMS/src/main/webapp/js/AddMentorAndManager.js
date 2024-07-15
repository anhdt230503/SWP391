        // Function to open the modal
        function openModal() {
            document.getElementById("uploadModal").style.display = "block";
        }

        // Function to close the modal
        function closeModal() {
            document.getElementById("uploadModal").style.display = "none";
        }

        // Close the modal if the user clicks outside of it
        window.onclick = function(event) {
            var modal = document.getElementById("uploadModal");
            if (event.target === modal) {
                modal.style.display = "none";
            }
        };