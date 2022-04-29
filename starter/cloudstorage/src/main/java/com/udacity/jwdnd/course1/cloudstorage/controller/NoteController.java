package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/notes")
    public String createOrEditNote(@ModelAttribute("Note") Note note, Authentication auth, RedirectAttributes redirectAttributes) {
        String username = auth.getName();
        Integer userId = userService.getUser(username).getUserId();
        note.setUserId(userId);

        if (note.getNoteId() == null) {
            this.noteService.createNote(note);
            redirectAttributes.addAttribute("success", true);
            redirectAttributes.addAttribute("message", "Your note has been successfully added!");

        } else {
            this.noteService.updateNote(note);
            redirectAttributes.addAttribute("success", true);
            redirectAttributes.addAttribute("message", "Your note has been successfully updated!");
        }
        return "redirect:/home";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, Authentication auth, RedirectAttributes redirectAttributes) {
        Integer userId = userService.getUser(auth.getName()).getUserId();
        noteService.deleteNote(noteId, userId);
        redirectAttributes.addAttribute("success", true);
        redirectAttributes.addAttribute("message", "Your note has been successfully deleted!");
        return "redirect:/home";
    }
}
