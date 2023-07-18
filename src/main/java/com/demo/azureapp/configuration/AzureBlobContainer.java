package com.demo.azureapp.configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobContainer {

    @Value("${azure.blob.string}")
    private String azureUrl;

    @Value("${azure.blob.container}")
    private String containerName;

    @Bean
    public BlobContainerClient blobContainerClient(){
        return new BlobServiceClientBuilder().connectionString(azureUrl).buildClient()
                .getBlobContainerClient(containerName);
    }
}
