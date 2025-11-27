package com.walterjwhite.picovoice;

import lombok.Data;

@Data
public class PorcupineConfiguration {
    protected String accessKey;
    protected String libraryPath;
    protected String modelPath;
    protected String[] keywords;
    protected float[] sensitivities;
    protected int audioDeviceIndex;
}
