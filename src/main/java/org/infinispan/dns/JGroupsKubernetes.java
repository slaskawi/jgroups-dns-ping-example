package org.infinispan.dns;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class JGroupsKubernetes {

   private static final String CLUSTER_NAME = "CLUSTER_NAME";

   public static void main(String[] args) throws Exception {

      Logger rootLog = Logger.getLogger("");
      rootLog.setLevel( Level.FINE );
      rootLog.getHandlers()[0].setLevel( Level.FINE );

      InputStream configuration = JGroupsKubernetes.class.getResourceAsStream("/config-test.xml");
      JChannel channel = new JChannel(configuration);

      System.out.println("#### " + channel.getProtocolStack().getProtocols());

      channel.setReceiver(new ReceiverAdapter(){
         @Override
         public void viewAccepted(View view) {
            System.out.println("#### VIEW: " + view);
         }
      });

      channel.connect(CLUSTER_NAME);

      TimeUnit.MINUTES.sleep(10);

      channel.close();
   }
}
