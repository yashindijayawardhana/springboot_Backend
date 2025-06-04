package com.example.finalcoursework_lostfound.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort();

        mailSender.setUsername("your-email@gmail.com");
        mailSender.setPassword("your-app-password");

        Properties props = mailSender.setPort();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    private class JavaMailSenderImpl {
        public void setHost(String s) {

        }

        public Properties setPort() {

            return null;
        }

        public void setUsername(String Propertiesmail) {

        }

        public void setPassword(String s) {

        }

        public void getJavaMailProperties() {
        }
    }
}