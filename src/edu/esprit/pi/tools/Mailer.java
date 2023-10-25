/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.tools;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

public class Mailer {

    private MimeMessage message;

    public Mailer() throws AddressException {
        try {
            String from = "takwira.tunisie@gmail.com"; // sender's email address
            String password = "ohwrvdjlmpcpepsa"; // sender's password
            String host = "smtp.gmail.com"; // Gmail SMTP server address
            String port = "465"; // SMTP port
            
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");
            
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            
            this.message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMail(String to, String subject, String html) throws MessagingException {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(html, "text/html");
        Transport.send(message);
    }

    public void sendMailAsync(String to, String subject, String html) {
        new Thread(() -> {
            try {
                sendMail(to, subject, html);
            } catch (MessagingException ex) {
                Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

}
