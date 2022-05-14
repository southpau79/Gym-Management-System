package Payment;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Mailer {

    static final String User_Email = "noreplytest.gymvale@gmail.com"; //your email
    static final String Password = "GymVale8068@"; // your email password
    static final String Sender = "noreplytest.gymvale@gmail.com"; // Insert Your email again


    public void Send_Email(String Receiver_mail, String Subject, String Body)
    {
        final Session newsession = Session.getInstance(this.Mail_Properties(), new Authenticator()
        {
            @Override
            // password authentication
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(User_Email, Password);
            }
        });

        Message msg = new MimeMessage(newsession);
        // MimeMessage is used to create the email message
        try
        {
            msg.setFrom(new InternetAddress(Sender));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(Receiver_mail));
            msg.setSubject(Subject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(Body);

            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource("Payment/PDF Invoice/Invoice.pdf"); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("Receipt.pdf"); // ex : "test.pdf

            multipart.addBodyPart(textBodyPart);  // add the text part
            multipart.addBodyPart(attachmentBodyPart); // add the attachement part

            msg.setContent(multipart);
            Transport.send(msg);
            System.out.println("Your Email has been sent successfully!");
        } catch (final MessagingException e)
        { // exception to catch the errors
            System.out.println("Email Sending Failed"); // failed
            e.printStackTrace();
        }
    }

    // The permanent  set of properties containing string keys, the following
    // setting the properties for SMPT function
    public Properties Mail_Properties() {
        Properties Mail_Prop = new Properties();
        Mail_Prop.put("mail.smtp.host", "smtp.gmail.com");
        Mail_Prop.put("mail.smtp.socketFactory.port", "465");
        Mail_Prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Mail_Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Mail_Prop.put("mail.smtp.auth", "true");
        Mail_Prop.put("mail.smtp.port", "465");
        return Mail_Prop;
    }

    public static void main(String[] args) {
        Mailer obj = new Mailer();
        obj.Send_Email("vikaasaboy@gmail.com", "Test Subject", "Test Body");
    }

}