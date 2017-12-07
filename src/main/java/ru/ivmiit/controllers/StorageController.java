package ru.ivmiit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ivmiit.services.FileService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StorageController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response){
        fileService.writeFileToResponse(fileName, response);
    }
}
