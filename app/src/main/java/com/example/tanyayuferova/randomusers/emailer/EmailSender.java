package com.example.tanyayuferova.randomusers.emailer;

import android.util.Log;

import java.net.URL;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Sends email
 * Created by Tanya Yuferova on 6/4/2017.
 */

public class EmailSender extends javax.mail.Authenticator {
    public static String LOG_TAG = EmailSender.class.getSimpleName();

    private String mailhost = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;

    private Multipart _multipart;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public EmailSender(String user, String password) {
        this.user = user;
        this.password = password;

        _multipart = new MimeMultipart();

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized boolean sendMail(String subject, String body, String sender, String recipients, URL urlFile, String fileName)
            throws Exception {
        try {
            MimeMessage message = new MimeMessage(session);
            //Sender
            message.setSender(new InternetAddress(sender));
            //Subject
            message.setSubject(subject);
            //Addressees
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            //Body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            _multipart.addBodyPart(messageBodyPart);

            //Attachment
            if(urlFile != null) {
                BodyPart attachBodyPart = new MimeBodyPart();
                DataSource source = new URLDataSource(urlFile);
                attachBodyPart.setDataHandler(new DataHandler(source));
                attachBodyPart.setFileName(fileName);
                _multipart.addBodyPart(attachBodyPart);
            }

            message.setContent(_multipart);
            Transport.send(message);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            return false;
        }
        return true;
    }
}

