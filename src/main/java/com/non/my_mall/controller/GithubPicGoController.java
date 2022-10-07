package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.PicGoUplodeParams;
import com.non.my_mall.utils.GitHubFileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/upload")
public class GithubPicGoController {
    @ResponseBody
    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    public CommonResult add(MultipartFile file) {
        System.out.println("file"+file);
        // 获取文件原本的名字
        String originName = file.getOriginalFilename();
        // 判断文件是否是pdf文件
        Set<String> set = new HashSet<>();
        set.add(".pdf");
        set.add(".doc");
        set.add(".docx");
        set.add(".png");
        set.add(".jpg");
        // 取出文件的后缀
        int count = 0;
        for(int i = 0; i < originName.length(); i++){
            if(originName.charAt(i) == '.'){
                count = i;
                break;
            }
        }
        String endName = originName.substring(count); //取出文件类型
        String fileType = originName.substring(count + 1); //文件类型
        if(!set.contains(endName)){
            return CommonResult.failed(new String("上传的文件类型错误,只能上传pdf,doc,docx类型的文件")) ;
        }
        // 创建保存路径
        //日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        String savePath = "F:" + "\\" + "files" +   "\\" + fileType + "\\" + format;
        System.out.println("savePath"+savePath);
        // 保存文件的文件夹
        File folder = new File(savePath);
        // 判断路径是否存在,不存在则自动创建
        if(!folder.exists()){
            folder.mkdirs();
        }
        String saveName = originName;


        try {
            file.transferTo(new File(folder,saveName));
            String filePath = savePath + "\\" + saveName;
            GitHubFileUtil gitHubFileUtil = new GitHubFileUtil();
            System.out.println("filePath:"+filePath);
            String uploading = gitHubFileUtil.uploading(filePath, endName, saveName);
            System.out.println("uploading:"+CommonResult.success(uploading));

            return CommonResult.success(uploading);
        } catch (IOException e){
            return CommonResult.failed(new String(e.getMessage())) ;
        }


    }
}
