package com.example.springweb3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public  String provideUploadInfo() {
        return "upload_form";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam(value = "filename", required = false) String name,
                                   @RequestParam("xfile") MultipartFile file, Model model){
        System.out.println("test "+name + file);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));

                stream.write(bytes);
                stream.close();
                model.addAttribute("msg", "Вы удачно загрузили " + name + " в " + name + "-uploaded !");
            } catch (Exception e) {
                model.addAttribute("msg", "Вам не удалось загрузить " + name + " => " + e.getMessage());
            }
        } else {
            model.addAttribute("msg", "Вам не удалось загрузить " + name + " потому что файл пустой.");
        }
        //model.addAttribute("msg", "Хотим загрузить файл " + name);
        return "upload_result";
    }

}