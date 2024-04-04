package com.project.adminmns.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('ROLE_INSCRIPTIONMANAGER','ROLE_ADMIN','ROLE_DIRETOR')")
public @interface InscriptionManagerPermission {
}
