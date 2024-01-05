package com.walterjwhite.compression.modules;

// @Configuration
// public class CompressorFactory {
//    @Value("${compression.algorithm}")
//    protected CompressionAlgorithm compressionAlgorithm;
//    @Bean
//    public Class<CompressorOutputStream> produceCompressorOutputStream(){
//        final CompressionAlgorithm actualCompressionAlgorithm = getCompressionAlgorithm();
//        if(actualCompressionAlgorithm.equals(CompressionAlgorithm.XZ)){
//            return(new XZCompressorOutputStream());
//        }
//        throw(new IllegalStateException("Unsupported compression algorithm or none specified."));
//    }
//    protected CompressionAlgorithm getCompressionAlgorithm(){
//        if(compressionAlgorithm == null){
//            return(CompressionAlgorithm.XZ);
//        }
//    }
// }
