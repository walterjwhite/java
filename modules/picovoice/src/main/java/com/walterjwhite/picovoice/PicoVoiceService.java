package com.walterjwhite.picovoice;

import ai.picovoice.porcupine.Porcupine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Slf4j
@RequiredArgsConstructor
public class PicoVoiceService implements AutoCloseable {
    protected final PorcupineConfiguration porcupineConfiguration;
    protected final OnKeywordHandler onKeywordHandler;
    protected final transient Porcupine porcupine;
    protected transient TargetDataLine micDataLine;
    protected boolean processing = true;

    public void close() {
        porcupine.delete();
    }

    public void run(final OutputStream outputStream) throws LineUnavailableException {
        micDataLine = PorcupineUtil.getMicrophone(porcupineConfiguration);

        long totalBytesCaptured = 0;

        try {
            int frameLength = porcupine.getFrameLength();
            ByteBuffer captureBuffer = ByteBuffer.allocate(frameLength * 2);
            captureBuffer.order(ByteOrder.LITTLE_ENDIAN);
            short[] porcupineBuffer = new short[frameLength];

            int numBytesRead;
            while (processing) {
                numBytesRead = micDataLine.read(captureBuffer.array(), 0, captureBuffer.capacity());
                totalBytesCaptured += numBytesRead;

                if (outputStream != null) {
                    outputStream.write(captureBuffer.array(), 0, numBytesRead);
                }

                if (numBytesRead != frameLength * 2) {
                    continue;
                }

                captureBuffer.asShortBuffer().get(porcupineBuffer);

                int result = porcupine.process(porcupineBuffer);
                if (result >= 0) {
                    LOGGER.debug("[%s] Detected '%s'\n", porcupineConfiguration.getKeywords()[result]);
                    onKeywordHandler.onKeyword(porcupineConfiguration.getKeywords()[result], outputStream);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error handling keywords", e);
        }
    }

    public void stop() {
        processing = false;
    }
}
