package de.haw.eled;

import de.haw.eled.model.LEDEffect;
import de.haw.eled.model.MailMessage;
import de.haw.eled.sink.LEDSinkFunction;
import de.haw.eled.source.ImapDataSourceFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Address;
import javax.servlet.ServletContext;

/**
 * Created by Tim on 26.04.2017.
 */
public class ImapLEDJob {

    private static final Logger LOG = LoggerFactory.getLogger(ImapLEDJob.class);

    public static void main(String[] args) throws Exception {
        LOG.info("============== Start Imap LED Job ===========");


        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment().setParallelism(1);
                //.createLocalEnvironment(1);

        //Add Source
        //SourceFunction<MailMessage> mailMessageSourceFunction = new ImapDataSourceFunction("host","user","password");
        SourceFunction<MailMessage> imapDataSourceFunction = new ImapDataSourceFunction("imap-client.properties");

        DataStream<LEDEffect> imapDataStream = env.addSource(imapDataSourceFunction, "ImapDataStream")
        .flatMap(new FlatMapFunction<MailMessage, LEDEffect>() {
            @Override
            public void flatMap(MailMessage value, Collector<LEDEffect> out) throws Exception {
                LEDEffect ledEffect;
                if(value.getSender()[0].toString().contains("tim@ksit.org"))
                    ledEffect = new LEDEffect(0,0,255,200);
                else
                    ledEffect = new LEDEffect(0,255,0,200);
                out.collect(ledEffect);
            }
        });


        SinkFunction<LEDEffect> ledSinkFunction = new LEDSinkFunction();
        imapDataStream.addSink(ledSinkFunction).name("LED Sink");
        env.execute("ImapLEDJob");
    }
}
