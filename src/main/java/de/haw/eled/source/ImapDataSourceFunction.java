package de.haw.eled.source;

import de.haw.eled.model.MailMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.com.google.common.io.Files;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Tim on 18.04.2017.
 *
 * example: http://bytefish.de/blog/stream_data_processing_flink/
 */
public class ImapDataSourceFunction implements SourceFunction<MailMessage>, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ImapDataSourceFunction.class);
    private volatile boolean isRunning = true;

    private ImapClient client;

    public ImapDataSourceFunction(String imapHost, String imapLoginName, String imapLoginPassword) {
        this.client = new ImapClient(imapHost, imapLoginName, imapLoginPassword);
    }

    public ImapDataSourceFunction(String propertyFile) throws IOException {
        Properties properties = new Properties();
        InputStream in = getClass().getResourceAsStream("/" + propertyFile);
        properties.load(in);
        this.client = new ImapClient(properties.getProperty("host"),properties.getProperty("user"),properties.getProperty("password"));
        //ParameterTool parameters = ParameterTool.fromPropertiesFile(propertyFile);
        //this.client = new ImapClient(parameters.get("host"),parameters.get("user"),parameters.get("password"));
    }

    @Override
    public void run(final SourceContext<MailMessage> ctx) throws Exception {
        client.init();
        client.setListener(new ImapClient.ImapEventListener() {
            @Override
            public void newMessageEvent(MailMessage msg) {
                LOG.debug("New Message received from ImapClient: subject={}",msg.getSubject());
                try {
                    ctx.collect(msg);
                } catch (Exception e) {
                    LOG.error("Error collecting messages from ImapClient",e);
                }
            }
        });
        client.run();
    }

    @Override
    public void cancel() {
        client.stop();
    }

//    private Stream<Message> getNewMessages() throws IOException {
//        client.
//    }
//TODO message als stream?!
}
