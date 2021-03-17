package ma.aui.sse.paradigms.integration.xs.calculator.provider;

import javax.jws.WebService;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.lang.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@WebService
public class Controller{

    public OperatingSystem getOsInfo(){

        String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");

        OperatingSystem os = new OperatingSystem(osName, osVersion);

        return os;
    }

    public Screenshot getScreenshot(){
        try{
        File tmp = new File("screenshot.png");
        int i = 1;
        //This part takes care of naming the file in a way that it does not override an exisiting file with the same name
        while(tmp.exists()){
            tmp = new File("screenshot" + Integer.toString(i) + ".png");
            i++;
        }
        tmp.createNewFile();

        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", tmp);
        
        FileInputStream fileIn = new FileInputStream(tmp);
        int fileSize = fileIn.available();
        
        byte[] bytes = new byte[fileSize];
        fileIn.read(bytes);
        fileIn.close();

        Screenshot sc = new Screenshot(bytes, fileSize);
        return sc;
        }
        catch(Exception ex){
            Screenshot sc = new Screenshot(null, 0); // Initialize the Screenshot object's bytes and size to null and zero respectively, this will set the status to be equal to false
            return sc;
        }
    }

    public void rebootSystem(){
        try {
            String osName = System.getProperty("os.name");
            String reboot;

            if (osName.equals("Linux") || osName.equals("Mac OS X")){
                reboot = "shutdown -r now";
            }
            else if(osName.equals("Windows")) {
                reboot = "shutdown.exe -r";
            }
            else {
                return;
            }
            Runtime.getRuntime().exec(reboot); 
        } catch (IOException e) {
        }
    }

}