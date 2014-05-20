/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MailProtos.mail mail= MailProtos.mail.newBuilder()
                .setId(123)
                .setFrom("Pipo")
                .setToIP("Kimo")
                .setSubject("Urgent")
                .setCategory("High")
                .setBody("We need to talk!!").build();
       System.out.println(mail.toString());
       
    }
}
