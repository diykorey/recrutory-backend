package com.kandidato.manager.search;

import com.microsoft.windowsazure.services.blob.client.CloudBlobClient;
import com.microsoft.windowsazure.services.blob.client.CloudBlobContainer;
import com.microsoft.windowsazure.services.blob.client.CloudBlockBlob;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.UUID;

@Component
public class BlobManagerImpl implements BlobManager{

    @Autowired
    private Environment env;

    private CloudBlobContainer container;

    @PostConstruct
    public void init() throws URISyntaxException, StorageException, InvalidKeyException {
        String accountKey = env.getProperty("storage.accountKey");
        String accountName = env.getProperty("storage.accountName");
        String containerName = env.getProperty("storage.containerName");

        final String connectionString = String.format(
                "DefaultEndpointsProtocol=http;AccountName=%s;AccountKey=%s",
                accountName, accountKey);

        CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
        CloudBlobClient serviceClient = account.createCloudBlobClient();
        container = serviceClient.getContainerReference(containerName);
    }

    @Override
    public String upload(String blobName, byte[] resume, String contentType) throws Exception {
        CloudBlockBlob blob = container.getBlockBlobReference(blobName);

        blob.getProperties().setContentType(contentType);
        blob.upload(new ByteArrayInputStream(resume), resume.length);

        return blob.getUri().toString();
    }

    @Override
    public byte[] download(String url) throws Exception {
        CloudBlockBlob blob = container.getBlockBlobReference(url);

        ByteArrayOutputStream resume = new ByteArrayOutputStream();
        blob.download(resume);

        return resume.toByteArray();
    }
}