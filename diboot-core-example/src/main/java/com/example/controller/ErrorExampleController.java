package com.example.controller;

import com.diboot.core.exception.BusinessException;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <Description>
 *
 * @author Mazhicheng
 * @version v2.0
 * @date 2019/07/19
 */
@Controller
@RequestMapping("/mockerror")
public class ErrorExampleController {

    @RequestMapping("/html")
    public String htmlRequest() throws Exception{
        int mockError = 2/0;
        return "test";
    }

    @RequestMapping("/html2")
    public String htmlRequestWithBusinessException() throws Exception{
        try{
            int mockError = 2/0;
        }
        catch (Exception e){
            throw new BusinessException(Status.FAIL_INVALID_PARAM, e);
        }
        return "test";
    }

    @RequestMapping("/json")
    @ResponseBody
    public JsonResult jsonRequest() throws Exception{
        int mockError = 2/0;
        return new JsonResult(Status.OK);
    }

    @RequestMapping("/json2")
    @ResponseBody
    public JsonResult jsonRequestWithBusinessException() throws Exception{
        try{
            int mockError = 2/0;
        }
        catch (Exception e){
            throw new BusinessException(Status.FAIL_INVALID_PARAM, e);
        }
        return new JsonResult(Status.OK);
    }

}
