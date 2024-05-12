package com.acheron.flowers.mail.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @SneakyThrows //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void verifyEmail(String email,String ref) {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom("flowers@gmail.com");
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("Email verification");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/com/acheron/flowers/mail/templates/verifier.html"));
        String htmlTemplate = lines.stream().reduce((e1,e2)-> e1+"\n"+e2).orElseThrow();
        htmlTemplate = htmlTemplate.replace("${ref}", ref);
        message.setContent(htmlTemplate, "text/html; charset=utf-8");
        mailSender.send(message);
    }

    public void resetPassword(String email){

    }

}
