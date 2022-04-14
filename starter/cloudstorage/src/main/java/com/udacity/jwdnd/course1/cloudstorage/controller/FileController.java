package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    // https://knowledge.udacity.com/questions/382441
    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, File file, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        if (fileUpload.isEmpty()) {
            model.addAttribute("errorMessage", "Please choose a file.");
        } else {
            String fileName = fileUpload.getOriginalFilename();
            if (fileService.isFileNameAvailable(userId, fileName)) {
                try {
                    file.setUserId(userId);
                    file.setFileName(fileName);
                    file.setFileData(fileUpload.getBytes());
                    file.setContentType(fileUpload.getContentType());
                    file.setFileSize(String.valueOf(fileUpload.getSize()));

                    this.fileService.addFile(file);
                    List<File> files = this.fileService.displayFiles(file.getUserId());
                    model.addAttribute("files", files);
                    model.addAttribute("successMessage", "Your file has been successfully uploaded!");
                } catch (Exception ex) {
                    model.addAttribute("errorMessage", "Oops, something went wrong. Your file did not upload!");
                }
            } else {
                model.addAttribute("errorMessage", "A file with the same name already exists. Please choose another file name.");
            }
        }
        return "result";
    }

    // https://knowledge.udacity.com/questions/288143
    // https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service/60351773#60351773
    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam String fileName, Authentication auth) {
        Integer userId = userService.getUser(auth.getName()).getUserId();
        File file = fileService.getFileByUserIdAndFileName(userId, fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file);
    }

    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, Authentication auth, Model model) {
        Integer userId = userService.getUser(auth.getName()).getUserId();
        this.fileService.deleteFile(fileId, userId);
        model.addAttribute("successMessage", "Successfully deleted!");
        return "result";
    }
}
