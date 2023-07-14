package com.bickbrother.web.controllers;

import com.bickbrother.data.dtos.BrandDTO;
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
@RequestMapping("/brand")
public class BrandController {

    private final MessageService<BrandDTO> brandService;
    private final DomainSecurity domainSecurity;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('write')")
    public BrandDTO save(@RequestBody BrandDTO brandDTO) { return brandService.save(brandDTO); }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public BrandDTO getById(@PathVariable("id") Long id) {
        if (domainSecurity.brandIsAllowed(username(), id))
            return brandService.getById(id);
        throw new AccessDeniedException("403 Forbidden");
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('edit')")
    public BrandDTO update(@RequestBody BrandDTO brandDTO) {
        if (domainSecurity.brandIsAllowed(username(), brandDTO))
            return brandService.update(brandDTO);
        throw new AccessDeniedException("403 Forbidden");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public void deleteById(@PathVariable("id") Long id) { brandService.deleteById(id);}

    private String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
