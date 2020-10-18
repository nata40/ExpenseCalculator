package pl.danielsiwulec.calculator.verification;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.Random;

public class EmailVerification {
    public int generateKey(){
        Random random = new Random();
        int key =0;
        key = random.nextInt((10_0000-10_000)+1)+10_000;
        return key;
    }
    public void sendEmail(String emailToSend,int verificationCode) throws EmailException {
        Email email1 = new SimpleEmail();
        email1.setHostName("poczta.interia.pl");
        email1.setSmtpPort(465);
        email1.setAuthenticator(new DefaultAuthenticator("java_developer", "leinad1995"));
        email1.setSSLOnConnect(true);
        email1.setFrom("java_developer@interia.pl");
        email1.setSubject("Witamy na stronie Kalkulator wydatków");
        email1.setMsg("Twój kod weryfikacyjny: "+verificationCode);
        email1.addTo(emailToSend);
        System.out.println("Wysłałem email");
        email1.send();
    }


}
