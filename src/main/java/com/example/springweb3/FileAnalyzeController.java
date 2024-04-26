package com.example.springweb3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;

@Controller
public class FileAnalyzeController {
    @GetMapping("/analyze")
    public String showForm(){
        return "analyze";
    }

    @PostMapping("/analyze")
    public String makeAnalyze(@RequestParam("textfile") MultipartFile file,
                              Model model){
        model.addAttribute("message", "читаем файл "+file.getOriginalFilename() +" размером "+file.getSize());
        try{
            String s = getFirstLine( file);
            model.addAttribute("firstline", s);
        }
        catch (IOException e){
            model.addAttribute("errorMessage", "а он не читается");
        }
        return "analyze_result";
    }

    public String getFirstLine(MultipartFile file) throws IOException {
        //System.out.println("зашли читать файл");
        Scanner scan = new Scanner(file.getInputStream());
        //System.out.println("открыли файл сканером");
        if(scan.hasNext()) {
            String s = scan.nextLine();
         //   System.out.println(s);
            return s;
        }
        return "там ничего нет";
    }
}
