///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package service;
//import controller.attendance.UpdateAttendanceStatus;
//import it.sauronsoftware.cron4j.Scheduler;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import java.io.IOException;
//
///**
// *
// * @author haidu
// */
//public class AttendanceScheduler extends HttpServlet {
//    
//    public static void main(String[] args) {
//        Scheduler scheduler = new Scheduler();
//        scheduler.schedule(" 5 16 * * * ?", new Runnable() { // Biểu thức cron cho 18:00 hàng ngày
//            @Override
//            public void run() {
//                UpdateAttendanceStatus servlet = new UpdateAttendanceStatus();
//                try {
//                    servlet.doGet(null, null);
//                } catch (ServletException | IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        scheduler.start();
//    }
//}
