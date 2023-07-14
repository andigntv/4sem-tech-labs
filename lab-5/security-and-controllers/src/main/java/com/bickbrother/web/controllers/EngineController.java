package com.bickbrother.web.controllers;

import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.messages.interfaces.MessageService;
import com.bickbrother.web.security.dos.DomainSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/engine")
public class EngineController {
    private final MessageService<EngineDTO> engineService;

    private final DomainSecurity domainSecurity;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('edit')")
    public EngineDTO save(@RequestBody EngineDTO engineDTO) {
        if (domainSecurity.engineIsAllowed(username(), engineDTO))
            return engineService.save(engineDTO);
        throw new AccessDeniedException("403 Forbidden");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public EngineDTO getById(@PathVariable("id") Long id) {
        if (domainSecurity.engineIsAllowed(username(), id))
            return engineService.getById(id);
        throw new AccessDeniedException("403 Forbidden");
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('edit')")
    public EngineDTO update(@RequestBody EngineDTO engineDTO) {
        if (domainSecurity.engineIsAllowed(username(), engineDTO))
            return engineService.update(engineDTO);
        throw new AccessDeniedException("403 Forbidden");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('edit')")
    public void deleteById(@PathVariable("id") Long id) {
        if (domainSecurity.engineIsAllowed(username(), id))
            engineService.deleteById(id);
        throw new AccessDeniedException("403 Forbidden");
    }

    private String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
