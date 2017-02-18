package com.sttx.bookmanager.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.vcode.utils.VerifyCode;

@Controller
@RequestMapping("/util")
public class UtilController {
    /**
     * @Title: verifyCode
     * @Description: 验证码
     * @param request
     * @param response
     * @throws IOException
     * @return: void
     */
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        request.getSession().setAttribute("session_vcode", vc.getText());// 保存图片上的文本到session域
        VerifyCode.output(image, response.getOutputStream());
    }

    @RequestMapping("/top")
    public String top() {
        return "public/top";
    }
}
