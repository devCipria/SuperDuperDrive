package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public void createCredential(Credential credential) {
        this.credentialMapper.addCredential(credential);
    }

    public void updateCredential(Credential credential) {
        this.credentialMapper.updateCredential(credential);
    }

    public List<Credential> displayCredentials(Integer userId) {
        return this.credentialMapper.displayCredentials(userId);
    }

    public void deleteCredential(Integer credentialId, Integer userId) {
        this.credentialMapper.deleteCredential(credentialId, userId);
    }
}
