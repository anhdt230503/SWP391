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
                    <form action="EditFeedbackServlet" method="post">
                        <input type="hidden" name="mentorId" value="${mentor.mentorId}">
                        <input type="hidden" name="labroomId" value="${labRoom.roomId}">
                        <input type="hidden" name="feedbackId" value="${feedback.feedbackId}">
                        <h1 style="text-align: center;">STUDENT FEEDBACK FORM FOR TEACHER</h1>
                        <strong>Class: ${labRoom.roomName}</strong>
                        <br/>
                        <strong>Mentor : ${mentor.fullname}</strong>
                        <p>Tick the phrase, which best suits the teacher (Đánh dấu vào ô thích hợp)</p>
                        <div class="container">
                            <div class="section">
                                <h3>Regarding the teacher's punctuality</h3>
                                <p>(Sự đúng giờ của giảng viên trong giờ học)</p>
                                <label><input type="radio" name="punctuality" value="Always punctual" ${feedback.punctuality == 'Always punctual' ? 'checked' : ''} required> Always punctual (Luôn đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Mostly punctual" ${feedback.punctuality == 'Mostly punctual' ? 'checked' : ''} required> Mostly punctual (Phần lớn đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Rarely punctual" ${feedback.punctuality == 'Rarely punctual' ? 'checked' : ''} required> Rarely punctual (Ít khi đúng giờ)</label>
                                <label><input type="radio" name="punctuality" value="Not at all punctual" ${feedback.punctuality == 'Not at all punctual' ? 'checked' : ''} required> Not at all punctual (Không bao giờ đúng giờ)</label>
                                
                                <h3>The teacher adequately covers the topics required by the syllabus</h3>
                                <p>(Dám sát công việc của học viên)</p>
                                <label><input type="radio" name="coverage" value="Fully covered" ${feedback.coverage == 'Fully covered' ? 'checked' : ''} required> Fully covered (Đảm bảo hoàn toàn)</label>
                                <label><input type="radio" name="coverage" value="Mostly covered" ${feedback.coverage == 'Mostly covered' ? 'checked' : ''} required> Mostly covered (Đảm bảo phần lớn)</label>
                                <label><input type="radio" name="coverage" value="Partially covered" ${feedback.coverage == 'Partially covered' ? 'checked' : ''} required> Partially covered (Chỉ đảm bảo một phần)</label>
                                <label><input type="radio" name="coverage" value="Not at all covered" ${feedback.coverage == 'Not at all covered' ? 'checked' : ''} required> Not at all covered (Không đảm bảo)</label>
                                
                                <h3>The teacher's response to students' questions</h3>
                                <p>(Khả năng trả lời câu hỏi của giảng viên)</p>
                                <label><input type="radio" name="response" value="Adequate" ${feedback.response == 'Adequate' ? 'checked' : ''} required> Adequate (Đầy đủ)</label>
                                <label><input type="radio" name="response" value="Answered in the next session" ${feedback.response == 'Answered in the next session' ? 'checked' : ''} required> Answered in the next session (Trả lời vào buổi học sau)</label>
                                <label><input type="radio" name="response" value="Some queries left unanswered" ${feedback.response == 'Some queries left unanswered' ? 'checked' : ''} required> Some queries left unanswered (Một số câu hỏi không được trả lời)</label>
                                <label><input type="radio" name="response" value="Most queries left unanswered" ${feedback.response == 'Most queries left unanswered' ? 'checked' : ''} required> Most queries left unanswered (Phần lớn câu hỏi không được trả lời)</label>
                            </div>
                            <div class="section">
                                <h3>Teaching skills of teacher</h3>
                                <p>(Kỹ năng sư phạm của giảng viên)</p>
                                <label><input type="radio" name="teachingSkills" value="Very Good" ${feedback.teachingSkills == 'Very Good' ? 'checked' : ''} required> Very Good (Rất tốt)</label><br/>
                                <label><input type="radio" name="teachingSkills" value="Good" ${feedback.teachingSkills == 'Good' ? 'checked' : ''} required> Good (Tốt)</label><br/>
                                <label><input type="radio" name="teachingSkills" value="Average" ${feedback.teachingSkills == 'Average' ? 'checked' : ''} required> Average (Trung bình)</label><br/>
                                <label><input type="radio" name="teachingSkills" value="Poor" ${feedback.teachingSkills == 'Poor' ? 'checked' : ''} required> Poor (Kém)</label>
                                
                                <h3>Support from the teacher - guidance for practical exercises, answering questions outside of class</h3>
                                <p>(Hỗ trợ của giảng viên trong và ngoài giờ - trả lời Email, hướng dẫn thực hành, giải đáp thắc mắc...)</p>
                                <label><input type="radio" name="support" value="Very Good" ${feedback.support == 'Very Good' ? 'checked' : ''} required> Very Good (Rất tốt)</label><br/>
                                <label><input type="radio" name="support" value="Good" ${feedback.support == 'Good' ? 'checked' : ''} required> Good (Tốt)</label><br/>
                                <label><input type="radio" name="support" value="Average" ${feedback.support == 'Average' ? 'checked' : ''} required> Average (Trung bình)</label><br/>
                                <label><input type="radio" name="support" value="Poor" ${feedback.support == 'Poor' ? 'checked' : ''} required> Poor (Kém)</label><br/>
                            </div>
                        </div>
                        <h4>Feedback</h4>
                        <textarea name="feedbackText">${feedback.feedbackText}</textarea><br>
                        <button type="submit">Change Feedback</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
