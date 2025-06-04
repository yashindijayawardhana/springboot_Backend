package com.example.finalcoursework_lostfound.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    private class JavaMailSender {
        public void send(SimpleMailMessage message) {
        }
    }

    private class SimpleMailMessage {
        public void setTo(String to) {

        }

        public void setSubject(String subject) {

        }

        public void setText(String text) {

        }
    }
}