package com.kandidato.manager.search;

public interface BlobManager {
    String upload(String blobName, byte[] resume, String contentType) throws Exception;
    byte[] download(String url) throws Exception;
}