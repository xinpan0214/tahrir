package tahrir.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.*;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.routing.VirtualHost;
import tahrir.io.crypto.TrCrypto;
import tahrir.tools.Tuple2;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/*created by Oliver Lee */

public class TahrirRestlet extends org.restlet.Component{


    public TahrirRestlet(VirtualHost host) {
        /* //example code
        host.attach("/branch1", new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                if(request.getMethod().getName().equals("GET")){
                    response.setEntity("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<p>This is branch1</p>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>", MediaType.TEXT_HTML);
                }
                else if(request.getMethod().getName().equals("POST")){
                    JSONObject j=new JSONObject();
                    try {
                        j.append("key", "val");
                    }
                    catch (org.json.JSONException e){
                        System.err.println("something wrong with json");
                    }

                    response.setEntity(j.toString(), MediaType.APPLICATION_JSON);
                }
                else{
                    System.err.println("method not recognized");
                }
            }
        });

        host.attach("/branch2", new Restlet() {
            @Override
            public void handle(Request request, Response response) {



                response.setEntity("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<p>This is branch2</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>", MediaType.TEXT_HTML);
            }
        });*/

        host.attach("/messages", new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                if(request.getMethod().getName().equals("GET")) {

                    response.setEntity("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<p>you are GETing /messages</p>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>", MediaType.TEXT_HTML);
                }
                else if(request.getMethod().getName().equals("POST")){

                    response.setEntity("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<p>you are POSTing /messages</p>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>", MediaType.TEXT_HTML);

                }
                else{
                    System.err.println("method not recognized, /messages only uses GET and POST");
                }
            }
        });

        host.attach("/messages/boost", new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                if(request.getMethod().getName().equals("GET")) {

                    response.setEntity("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<p>you are GETing /messages/boost</p>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>", MediaType.TEXT_HTML);

                }
                else{
                    System.err.println("method not recognized, /boost only uses GET");
                }
            }
        });


        host.attach("/identity", new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                /*if you go to the source code of Restlet.java, in the comment above the handle(request, response) method,
                    it says:
                    "Subclasses overriding this method should make sure that they call
                     super.handle(request, response) before adding their own logic."
                 */
                super.handle(request, response);


                if(request.getMethod().getName().equals("GET")){

                    Tuple2<RSAPublicKey, RSAPrivateKey> keyPair= TrCrypto.createRsaKeyPair();
                    JSONObject jsonResponseWithKeyPair=new JSONObject();
                    try {
                        jsonResponseWithKeyPair.append("public_key", keyPair.a);
                        jsonResponseWithKeyPair.append("private_key", keyPair.b);
                    } catch (JSONException e) {
                        System.err.println("something wrong with putting keypair in json");
                        e.printStackTrace();
                    }

                    /*TODO: right now, the private and public key are sent to the GUI in an unencrypted json object.  is this ok?
                        i know it's all on the local machine but still seems a bit insecure
                     */
                    response.setEntity(jsonResponseWithKeyPair.toString(), MediaType.APPLICATION_JSON);

                }
                else{
                    System.err.println("method not recognized, /identity only uses GET");
                }
            }
        });

    }


    @Override
    public void handle(Request request, Response response) {

        /*if you go to the source code of Restlet.java, in the comment above the handle(request, response) method,
                    it says:
                    "Subclasses overriding this method should make sure that they call
                     super.handle(request, response) before adding their own logic."
                 */
        super.handle(request, response);


        response.setEntity("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<p>This is the root</p>\n" +
            "\n" +
            "</body>\n" +
            "</html>", MediaType.TEXT_HTML);
    }
}
