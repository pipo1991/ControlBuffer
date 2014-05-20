import com.google.protobuf.ByteString;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
class TCPServer
{
    public static void main(String argv[]) throws Exception{
        ServerSocket welcomeSocket = new ServerSocket(6789);
 
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            if (connectionSocket != null)
            {
                Client client = new Client(connectionSocket);
                client.start();
            }
        }
    }
}
 
class Client extends Thread
{
    private Socket connectionSocket;
    private String clientSentence;
    private String capitalizedSentence;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
 
    public Client(Socket c) throws IOException
    {
        connectionSocket = c;
        capitalizedSentence="";
    }
 
    @Override
    public void run() 
    {
        try
        {    
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String m=inFromClient.readLine();
            byte[]arr=m.getBytes();
            ByteString bs=ByteString.copyFrom(arr);
            int lol=0;
            MailProtos.mail mail=MailProtos.mail.parseFrom(bs);
            if(mail.isInitialized()){
                capitalizedSentence="Message Received Successfully";
                System.out.println("Message is \n"+mail.toString());
            }
            else{
                capitalizedSentence="Message not well formatted or required field doens't exist";
            }
            outToClient.writeBytes(capitalizedSentence);
            connectionSocket.close();
        }
        catch(IOException e)
        {
            try {
                System.out.println("Error: " + e);
                outToClient.writeBytes("Error: " + e);
                connectionSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}