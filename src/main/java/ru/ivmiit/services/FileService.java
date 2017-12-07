package ru.ivmiit.services;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    String saveFile(Authentication authentication, MultipartFile file);
    void writeFileToResponse(String fileName, HttpServletResponse response);
}
