package com.margotekstil.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = "/error")
    public String error() {
        return "main/secure/error404_2";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
