package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.*;
import com.jme3.network.serializing.Serializable;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain extends SimpleApplication {
    private Server myServer;
    private static ServerMain app;
    
    public static void main(String[] args) {
        app = new ServerMain();
        app.start(JmeContext.Type.Headless); // headless type for servers!
    }

    @Override
   public void simpleInitApp(){
     
        try {
            
            myServer = Network.createServer(56122);
            myServer.start();
            myServer.addMessageListener(new ServerListener(), message.class);
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Serializer.registerClass(message.class);
  }
    
  public class ServerListener implements MessageListener<HostedConnection> {
    public void messageReceived(HostedConnection source, Message message) {
      if (message instanceof message) {
        // do something with the message
        message helloMessage = (message) message;
        System.out.println("Server received '" +helloMessage.getMessage() +"' from client #"+source.getId() );
        myServer.broadcast(new message("NO!"));
      }
    }
  }
  @Override
  public void destroy() {
     
      myServer.close();
      app.stop();
  }
  public void simpleUpdate(){
      
  }
  
}

