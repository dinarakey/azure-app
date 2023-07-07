package com.demo.azureapp.controller;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AzureRestController {

    @Autowired
    private BlobContainerClient container;

    @GetMapping("/hi")
    public String greetings() {
        return "greetings!";
    }

    @GetMapping("/blob")
    public String blobs() {
        ArrayList<String> list = new ArrayList<String>();
        for (BlobItem i: container.listBlobs()
             ) {
            list.add(i.getName());
        }
        return list.toString();
    }
}
