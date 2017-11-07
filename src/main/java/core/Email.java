package core;

import imap.MessageContent;
import ru.yandex.qatools.allure.annotations.Step;

import javax.mail.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by Asta on 16.12.2016.
 */
public class Email {

    private String login;
    private String password;
    private Properties props = new Properties();
    private Store store;

    public Email(String login, String password) {
        this.login = login;
        this.password = password;
        System.out.println("Connecting to email: " + login + ", with password: " + password);
        props.setProperty("map.googlemail.com", "imaps");
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.connectiontimeout", "5000");
        props.setProperty("mail.imaps.timeout", "5000");
        Session session = Session.getDefaultInstance(props, null);

        try {
            store = session.getStore("imaps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            store.connect("imap.gmail.com", login, this.password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    private Message[] getAllMessages(){
        System.out.println("Connecting to email: " + login);
        try {
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            return folder.getMessages();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearMailBox(){
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", login, password);

            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            for (Message m: messages) {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            folder.close(true);
            System.out.println("Mail box is cleared!");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    @Step("Get last letter from Gmail with subject: {0} and timeout: {1} and date after {2}")
    public Message getLastLetterFromGmail(String subject, long timeout, Date afterDate) throws MessagingException {
        System.out.println("Searching for letters after with subject containing '" + subject + "' after " + afterDate + ", " + afterDate.getTime());
        long finish = Calendar.getInstance().getTimeInMillis() + timeout;
        Message[] messages;
        while (Calendar.getInstance().getTimeInMillis() < finish) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                messages = getAllMessages();
                int count = messages.length;
                for (int i = 1; i < 5 && i <= count; i++) {
                    if (messages[count - i].getSubject().contains(subject)) {
                        System.out.println("Found letter date: " + messages[count - i].getReceivedDate() + ", " + messages[count - i].getReceivedDate().getTime());
                        if (messages[count - i].getReceivedDate().after(afterDate))
                            return messages[count - i];
                    }
                }
            } catch (Exception e){
                throw new MessagingException("No mail with this subject <" + subject +">! Tried for " + timeout/1000 + " seconds");
            }
        }
            throw new MessagingException("No mail with this subject <" + subject +">! Tried for " + timeout/1000 + " seconds");
    }

    public List<Message> getLettersBySubjectContains(String text){
        Message[] messages = getAllMessages();
        List<Message> messageList = new ArrayList<>();
        assert messages != null;
        for (Message mes: messages) {
            try {
                if (mes.getSubject().contains(text)){
                    messageList.add(mes);
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return messageList;
    }

    public static String getLetterBody(Message message, int bodyPart) {
        if (message == null)
            return "";
        try {
            Multipart mp = (Multipart) message.getContent();
            BodyPart bp = mp.getBodyPart(bodyPart);
            return bp.getContent().toString();
        } catch (MessagingException e) {
            System.out.println("Can't read letter form email");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getEmailContent(Message message){
        try {
            return message.getContent().toString();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Step
    public String getUrlFromEmailContent(String content, String urlContains){  // возвращает URL, вырезанный из текста (для писем)
        MessageContent messageContent = new MessageContent(content);
        List<String> list = messageContent.getLinkUrls();
        String url = "";
        for (String s: list) {
            if (s.contains(urlContains)){
                url = s;
                break;
            }
        }
        return url;
    }

    @Step
    public String getPasswordFromEmailText(String content){
        content = content.substring(content.indexOf("Пароль:") + "Пароль:".length());
        content = content.trim();
        content = content.substring(0, 10);
        return content;
    }
}
