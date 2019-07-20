package com.diboot.example.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

/**
 * <Description>
 *
 * @author Mazhicheng
 * @version v2.0
 * @date 2019/07/19
 */
@Controller
public class CustomErrorController implements ErrorController {
    private final static Logger log = LoggerFactory.getLogger(CustomErrorController.class);

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }
}