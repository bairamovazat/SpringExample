package ru.ivmiit.services;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ivmiit.models.File;
import ru.ivmiit.repository.FilesRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FilesRepository filesRepository;

    @Override
    public String saveFile(Authentication authentication, MultipartFile file) {
        return null;
    }

    @Override
    @SneakyThrows
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        File file = filesRepository.findFirstByStorageFileName(fileName);

        response.setContentType(file.getType());
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));

        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
