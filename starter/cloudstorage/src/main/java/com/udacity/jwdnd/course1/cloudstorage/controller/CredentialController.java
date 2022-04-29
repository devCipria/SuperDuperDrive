package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CredentialController {
    private EncryptionService encryptionService;
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(EncryptionService encryptionService, CredentialService credentialService, UserService userService) {
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credentials")
    public String createOrEditCredential(@ModelAttribute("credential") Credential credential, Authentication auth, RedirectAttributes redirectAttributes) {
        User user = userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        credential.setUserId(userId);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), user.getSalt()));

        if (credential.getCredentialId() == null) {
            this.credentialService.createCredential(credential);
            redirectAttributes.addAttribute("success", true);
            redirectAttributes.addAttribute("message", "Your credential has been successfully added!");
        } else {
            this.credentialService.updateCredential(credential);
            redirectAttributes.addAttribute("success", true);
            redirectAttributes.addAttribute("message", "Your credential has been successfully updated!");
        }
        return "redirect:/home";
    }

    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Authentication auth, RedirectAttributes redirectAttributes) {
        Integer userId = userService.getUser(auth.getName()).getUserId();
        credentialService.deleteCredential(credentialId, userId);
        redirectAttributes.addAttribute("success", true);
        redirectAttributes.addAttribute("message", "Your credential has been successfully deleted!");
        return "redirect:/home";
    }
}
