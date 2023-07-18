package com.demo.azureapp.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import com.demo.azureapp.model.Document;
import com.demo.azureapp.repository.DocumentRepository;
import com.demo.azureapp.service.Consumer;
import com.demo.azureapp.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AzureRestController {

    @Autowired
    private BlobContainerClient container;

    @Autowired
    private DocumentRepository repository;

    @GetMapping("/hi")
    public String greetings() {
        return "greetings!";
    }

    @GetMapping("/send")
    public String send() {
        Sender.publishEvents();
        return "OK!";
    }

    @GetMapping("/read")
    public String read() throws InterruptedException {
        Consumer.consume();
        return "OK!";
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

    @GetMapping("/save")
    public String save(){
        Document document = new Document();
        document.setName("FileName");
        repository.save(document);
        return "OK";
    }

    @GetMapping("/documents")
    public List<Document> documents(){
        return repository.findAll();
    }
}
