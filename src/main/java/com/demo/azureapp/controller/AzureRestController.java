package com.demo.azureapp.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        for (BlobItem i : container.listBlobs()
        ) {
            list.add(i.getName());
        }
        return list.toString();
    }

    @GetMapping("/info")
    public String info() {
        return container.getBlobClient("No ithernet.txt").downloadContent().toString();
    }

    @GetMapping("load/{filename}")
    public byte[] load(@PathVariable String filename){
        return container.getBlobClient(filename).downloadContent().toBytes();
    }

    @PostMapping("/share")
    public String share(MultipartFile file) throws IOException {
        BlobClient client = container.getBlobClient(file.getOriginalFilename());
        if (client.exists()) {
            return file.getOriginalFilename() + " file is already exists!";
        } else {
            client.upload(file.getInputStream(), file.getSize());
            return file.getOriginalFilename() + " file shared!";
        }

    }
}
