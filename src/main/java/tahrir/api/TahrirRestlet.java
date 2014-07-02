package tahrir.api;

import org.restlet.*;
import org.restlet.data.MediaType;
import org.restlet.routing.VirtualHost;


/*created by Oliver Lee */

public class TahrirRestlet extends org.restlet.Component{


    public TahrirRestlet(VirtualHost host){

        host.attach("/branch1", new Restlet() {
            @Override
            public void handle(Request request, Response response){

                response.setEntity("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<p>This is branch1</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>", MediaType.TEXT_HTML);
            }
        });

    }

    @Override
    public void handle(Request request, Response response) {
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