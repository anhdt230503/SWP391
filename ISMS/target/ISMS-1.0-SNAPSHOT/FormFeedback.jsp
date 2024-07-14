<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>STUDENT FEEDBACK FORM FOR TEACHER</title>

        <style>
            body {
                font-family: sans-serif;
            }
            .container {
                display: flex;
                justify-content: space-between;
            }
            .section {
                width: 45%;
                padding: 20px;
                border: 1px solid #ccc;
            }
            h2 {
                margin-bottom: 10px;
            }
            label {
                display: block;
                margin-bottom: 5px;
            }
            input[type="radio"] {
                margin-right: 5px;
            }
            textarea {
                width: 47.5%;
                height: 150px;
                border: 1px solid #ccc;
                resize: vertical;
                margin-top: 20px;
            }
            button {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div>
                    <c:if test="${not empty requestScope.feedbackSubmittedError}">
                        <div class="alert alert-danger mt-3" role="alert">
                            ${requestScope.feedbackSubmittedError}
                        </div>
                    </c:if>      

                    <form action="SubmitFeedback" method="post">
                        <input type="hidden" name="mentorId" value="${mentor.mentorId}">
                        <input type="hidden" name="labroomId" value="${labRoom.roomId}">
                        <h1 style="text-align: center;">STUDENT FEEDBACK FORM FOR TEACHER</h1>
                        <strong>Class: ${labRoom.roomName}</strong>
                        <br/>
                        <strong>Mentor : ${mentor.fullname}</strong>
                        <p>Tick the phrase, which best suits the teacher (Đánh dấu vào ô thích hợp)</p>
                        <div class="container">
                            <div class="section">
                                <h3>Regarding the teacher's punctuality</h3>
                                <p>(Sự đúng giờ của giảng viên trong giờ học)</p>
                                <label><input type="radio" name="punctuality" value="Always punctual"required> Always punctual (Luôn đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Mostly punctual"required> Mostly punctual (Phần lớn đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Rarely punctual"required> Rarely punctual (Ít khi đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Not at all punctual"required> Not at all punctual (Không bao giờ đúng giờ)</label>
                                <h3>The teacher adequately covers the topics required by the syllabus</h3>
                                <p>(Dám sát công việc của học viên)</p>
                                <label><input type="radio" name="coverage" value="Fully covered"required> Fully covered ( Đảm bảo hoàn toàn )</label>
                                <label><input type="radio" name="coverage" value="Mostly covered"required> Mostly covered ( Đảm bảo phần lớn )</label>
                                <label><input type="radio" name="coverage" value="Partially covered"required> Partially covered ( Chỉ đảm bảo một phần )</label>
                                <label><input type="radio" name="coverage" value="Not at all covered"required> Not at all covered( Không đảm bảo )</label>
                                <h3>Teacher's response to student's questions in class</h3>
                                <p>(Đáp ứng của giảng viên về những thắc mắc của học viên trong giờ học)</p>
                                <label><input type="radio" name="response" value="Answered immediately"required> Answered immediately or just after the session (Trả lời ngay hoặc trả lời vào cuối buổi học)</label>
                                <label><input type="radio" name="response" value="Answered in the next session"required> Answered in the next session (Trả lời vào buổi học sau)</label>
                                <label><input type="radio" name="response" value="Some queries left unanswered"required> Some queries left unanswered (Một số câu hỏi không được trả lời)</label>
                                <label><input type="radio" name="response" value="Most queries left unanswered"required> Most queries left unanswered (Phần lớn câu hỏi không được trả lời)</label>
                            </div>
                            <div class="section">
                                <h3>Teaching skills of teacher</h3>
                                <p>(Kỹ năng sư phạm của giảng viên)</p>
                                <label><input type="radio" name="teaching_skills" value="Very Good" required> Very Good (Tốt)</label>
                                <br/>
                                <label><input type="radio" name="teaching_skills" value="Good" required> Good (Khá)</label>
                                <br/>
                                <label><input type="radio" name="teaching_skills" value="Average" required> Average (Trung bình)</label>
                                <br/>
                                <label><input type="radio" name="teaching_skills" value="Poor" required> Poor (Kém)</label>
                                <h3>Support from the teacher - guidance for practical exercises, answering questions outside of class</h3>
                                <p>(Hỗ trợ của giảng viên trong và ngoài giờ - trả lời Email, hướng dẫn thực hành, giải đáp thắc mắc...)</p>
                                <label><input type="radio" name="support" value="Very Good"required> Very Good (Tốt)</label>
                                <br/>
                                <label><input type="radio" name="support" value="Good"required> Good (Khá)</label>
                                <br/>
                                <label><input type="radio" name="support" value="Average"required> Average (Trung bình)</label>
                                <br/>
                                <label><input type="radio" name="support" value="Poor"required> Poor (Kém)</label>
                            </div>
                        </div>
                        <h4>Feedback</h4>

                        <textarea placeholder="Enter your feedback here..." name="feedbackText"></textarea>

                        <button>Send Feedback</button>
                    </form>

                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
