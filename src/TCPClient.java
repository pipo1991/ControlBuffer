import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient
{
 public static void main(String argv[]) throws Exception
 {
  Socket clientSocket = new Socket("localhost", 6789);
  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  Scanner sc=new Scanner(new InputStreamReader(clientSocket.getInputStream()));
  MailProtos.mail mail= MailProtos.mail.newBuilder()
                .setId(123)
                .setFrom("Pipo")
                .setToIP("Kimo")
                .setSubject("Urgent")
                .setCategory("High")
                .setBody("We need to talk!!").build();
  outToServer.write(mail.toByteArray());
  outToServer.writeBytes("\n");
  String response = sc.nextLine(); 
  clientSocket.close();
  System.out.println("FROM SERVER: " + response);
 }
}