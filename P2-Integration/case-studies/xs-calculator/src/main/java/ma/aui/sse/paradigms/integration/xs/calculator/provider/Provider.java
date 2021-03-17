package ma.aui.sse.paradigms.integration.xs.calculator.provider;

import javax.xml.ws.Endpoint;

public class Provider {

    private static final String URL = "http://localhost:9000/controller";

    public static void main(String[] args) {

        Controller controller = new Controller();
        System.out.println("Publishing Calculator Service");
        Endpoint.publish(URL, controller);
        System.out.println("Calculator Service Published");
    }
    
}