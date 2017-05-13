package de.haw.eled.sink;

import de.haw.eled.model.LEDEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Tim on 29.04.2017.
 */
public class LED implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(LED.class);

    public void blink() throws IOException, InterruptedException {
        sendBlinkToLED(0,255,0,0,300);
    }

    public void triggerEffect(LEDEffect ledEffect)
    {
        try {
            sendBlinkToLED(ledEffect.getR(),ledEffect.getG(),ledEffect.getB(),ledEffect.getW(),ledEffect.getTime());
        } catch (IOException e) {
            LOG.error("Error while triggering LED effect",e);
        }
    }

    private void sendBlinkToLED(int r, int g, int b, int w, int time) throws IOException {
        URL url = new URL("http://localhost:8080/led/trigger/effect/blink/"+time+"/"+r+","+g+","+b+","+w);

        //make connection
        URLConnection urlc = url.openConnection();

        //use post mode
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);

        LOG.info("Send {} to LED",url.toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                .getInputStream()));
        String l = null;
        while ((l=br.readLine())!=null) {
            LOG.info("LED Response: {}",l);
        }

        br.close();
    }
}
