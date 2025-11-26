package com.walterjwhite.picovoice;

import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PorcupineUtil {
    public static Porcupine build(final PorcupineConfiguration porcupineConfiguration) throws PorcupineException {
        LOGGER.debug("Listening for {");
        for (int i = 0; i < porcupineConfiguration.getKeywords().length; i++) {
            LOGGER.debug(" %s(%.02f)", porcupineConfiguration.getKeywords()[i], porcupineConfiguration.getSensitivities()[i]);
        }

        return new Porcupine.Builder()
                .setAccessKey(porcupineConfiguration.getAccessKey())
                .setSensitivities(porcupineConfiguration.getSensitivities())
                .build();
    }

    public static AudioFormat getAudioFormat() {
        return new AudioFormat(16000f, 16, 1, true, false);
    }

    public static TargetDataLine getMicrophone(final PorcupineConfiguration porcupineConfiguration) throws LineUnavailableException {
        final AudioFormat format = getAudioFormat();
        final TargetDataLine targetDataLine = getAudioDevice(porcupineConfiguration.getAudioDeviceIndex(), new DataLine.Info(TargetDataLine.class, format));
        targetDataLine.open(format);
        targetDataLine.start();

        return targetDataLine;
    }


    public static void showAudioDevices() {
        Mixer.Info[] allMixerInfo = AudioSystem.getMixerInfo();
        Line.Info captureLine = new Line.Info(TargetDataLine.class);

        for (int i = 0; i < allMixerInfo.length; i++) {

            Mixer mixer = AudioSystem.getMixer(allMixerInfo[i]);
            if (mixer.isLineSupported(captureLine)) {
                LOGGER.debug("Device %d: %s\n", i, allMixerInfo[i].getName());
            }
        }
    }

    public static TargetDataLine getDefaultCaptureDevice(DataLine.Info dataLineInfo)
            throws LineUnavailableException {
        if (!AudioSystem.isLineSupported(dataLineInfo)) {
            throw new LineUnavailableException(
                    "Default capture device does not support the format required " +
                            "by Picovoice (16kHz, 16-bit, linearly-encoded, single-channel PCM).");
        }

        return (TargetDataLine) AudioSystem.getLine(dataLineInfo);
    }
    public static TargetDataLine getAudioDevice(int deviceIndex, DataLine.Info dataLineInfo)
            throws LineUnavailableException {
        if (deviceIndex < 0) {
            return getDefaultCaptureDevice(dataLineInfo);
        }

        Mixer.Info mixerInfo = AudioSystem.getMixerInfo()[deviceIndex];
        Mixer mixer = AudioSystem.getMixer(mixerInfo);

        if (mixer.isLineSupported(dataLineInfo)) {
            return (TargetDataLine) mixer.getLine(dataLineInfo);
        }

        throw new UnsupportedOperationException("Audio capture device at index %s does not support the " +
                "audio format required by Picovoice.");
    }

}
