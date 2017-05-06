package de.haw.eled.model;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.function.Function;

/**
 * Created by Tim on 29.04.2017.
 */
public class TransformMessage {
    public static Function<Message, MailMessage> javaxMessageToMailMessage = new Function<Message, MailMessage>() {
        @Override
        public MailMessage apply(Message message) {
            MyMailMessage mailMessage = new MyMailMessage();
            try {
                mailMessage.setSubject(message.getSubject());
            } catch (MessagingException e) {
                mailMessage.setSubject(null);
            }
            try {
                mailMessage.setReceiver(message.getRecipients(Message.RecipientType.TO));
            } catch (MessagingException e) {
               mailMessage.setReceiver(null);
            }
            try {
                mailMessage.setSender(message.getFrom());
            } catch (MessagingException e) {
                mailMessage.setReceiver(null);
            }
            try {
                mailMessage.setMessage(message.getContent().toString());
            } catch (IOException | MessagingException e) {
                mailMessage.setMessage(null);
            }
            return mailMessage;
        }
    };
}
