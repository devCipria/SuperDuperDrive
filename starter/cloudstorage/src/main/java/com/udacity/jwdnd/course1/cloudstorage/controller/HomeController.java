package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/home")
    public String homeView(File file, Note note, Credential credential, Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());
        Integer userId = user.getUserId();

        model.addAttribute("files", fileService.displayFiles(userId));
        model.addAttribute("notes", noteService.displayNotes(userId));
        model.addAttribute("credentials", credentialService.displayCredentials(userId));
        model.addAttribute("key", user.getSalt());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("note", new Note());
        model.addAttribute("credential", new Credential());

        return "home";
    }
}
