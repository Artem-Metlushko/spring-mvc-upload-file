package com.metlushko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class DownloadController {
    private final ServletContext servletContext;
    private static final int BUFFER_SIZE = 4096;

    private String filePath = "/downloads/example.pdf";

    public DownloadController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/downloadForm")
    public ModelAndView downloadForm() {
        return new ModelAndView("downloadFile");
    }

    @GetMapping("/downloadFile")
    public void doDownload(HttpServletResponse response) throws IOException {
        // get the absolute path of the application
        String realPath = servletContext.getRealPath("");
        String fullPath = realPath + filePath;

        // absolute path of the file
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // MIME type of the file
        String mimeType = servletContext.getMimeType(fullPath);
        if(mimeType == null){
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response object
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());


        // set headers for the response object
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write each byte of data  read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

}
