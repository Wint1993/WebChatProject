package com.sda.utils;

import com.sda.model.Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUserUtils {

    public static Client getLoggedUser(){

        /*Reprezentuje token dla żądania uwierzytelnienia lub użytkownika uwierzytelnionego
         po przetworzeniu żądania przez metodę AuthenticationManager.authenticate (Authentication).
          */
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = auth.getPrincipal();
        if (principal instanceof Client) {
            return (Client) principal;
        }
        return null;
    }
}
