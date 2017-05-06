package de.haw.eled.sink;

import de.haw.eled.model.LEDEffect;
import de.haw.eled.model.MailMessage;
import org.apache.commons.io.FileUtils;
import org.apache.flink.runtime.executiongraph.Execution;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;

/**
 * Created by Tim on 26.04.2017.
 */
public class LEDSinkFunction implements SinkFunction<LEDEffect>, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(LEDSinkFunction.class);

    LED led;

    public LEDSinkFunction() {
        this.led = new LED();
    }

    public LEDSinkFunction(LED led) {
        this.led = led;
    }

    @Override
    public void invoke(LEDEffect value) throws Exception {
        LOG.debug("New LEDEffect received: {}",value);
        //led.blink();
        led.triggerEffect(value);
    }

}
