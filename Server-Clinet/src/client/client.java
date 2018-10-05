package client;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


public class client {

   public static void main(String args[]) throws URISyntaxException {
	   
//	   socket connection = new socket(new URI("ws://localhost:8887"));
//	   connection.connect();
//	   
	   
      URI uri = null; // URI parameter of the request
      Scanner scan = new Scanner(System.in);
      String input;
      if (args.length > 0) {
         
         // input URI from command line arguments
         try {
            uri = new URI(args[0]);
         } catch (URISyntaxException e) {
            System.err.println("Invalid URI: " + e.getMessage());
            System.exit(-1);
         }
         
         
         CoapClient client = new CoapClient(uri);
         CoapResponse response;
         while(true)
         {
        	 System.out.print("Input the message:");
        	 input = scan.nextLine();
        	 if(input.equals("get")) {
        		 response = client.get();
        	 }else {
        		 response = client.put(input, MediaTypeRegistry.TEXT_PLAIN);
        	 }
             if(response!=null) {
                
               System.out.println(response.getCode());
               System.out.println(response.getOptions());
                if (args.length > 1) {
                   try (FileOutputStream out = new FileOutputStream(args[1])) {
                      out.write(response.getPayload());
                   } catch (IOException e) {
                      e.printStackTrace();
                   }
                } else {
                   System.out.println(response.getResponseText());
                   System.out.println(System.lineSeparator() + "ADVANCED" + System.lineSeparator());
                   // access advanced API with access to more details through
                   // .advanced()
                   System.out.println(Utils.prettyPrint(response));
                }
             } else {
                System.out.println("No response received.");
             }
         }
      

 //        CoapResponse response = client.get();
         
/*         if (response!=null) {
            
            System.out.println(response.getCode());
            System.out.println(response.getOptions());
            if (args.length > 1) {
               try (FileOutputStream out = new FileOutputStream(args[1])) {
                  out.write(response.getPayload());
               } catch (IOException e) {
                  e.printStackTrace();
               }
            } else {
               System.out.println(response.getResponseText());
               
               System.out.println(System.lineSeparator() + "ADVANCED" + System.lineSeparator());
               // access advanced API with access to more details through
               // .advanced()
               System.out.println(Utils.prettyPrint(response));
            }
         } else {
            System.out.println("No response received.");
         }*/
         
      } else {
         // display help
         System.out.println("Californium (Cf) GET Client");
         System.out.println("(c) 2014, Institute for Pervasive Computing, ETH Zurich");
         System.out.println();
         System.out.println("Usage : " + client.class.getSimpleName() + " URI [file]");
         System.out.println("  URI : The CoAP URI of the remote resource to GET");
         System.out.println("  file: optional filename to save the received payload");
      }
   }
   
}
