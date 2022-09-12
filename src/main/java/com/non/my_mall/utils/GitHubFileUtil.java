package com.non.my_mall.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import com.non.my_mall.dto.PicGoUplodeParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class GitHubFileUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(GitHubFileUtil.class);
    /**
     * 上传文件
     *
     * @param filepath    文件地址
     * @param filePostfix 文件后缀
     * @param message     提交描述
     * @return 文件访问地址
     */
    public static String uploading(String filepath, String filePostfix, String message) {
        // 转位base64
        String fileBase64 = encryptToBase64(filepath);
        // 防止文件名重复
        String fileName = UUID.randomUUID().toString().replace("-", "")+filePostfix;
        JSONObject param = new JSONObject();
        param.put("message", message);
        param.put("content", fileBase64);
        param.put("branch", PicGoUplodeParams.branch);
        JSONObject committer = new JSONObject();
        committer.put("name", PicGoUplodeParams.name);
        committer.put("email", PicGoUplodeParams.email);
        param.put("committer",committer);
        param.put("sha","");
        String url = "https://api.github.com/repos/OWNER/REPO/contents/PATH";
        url = url.replace("OWNER", PicGoUplodeParams.OWNER)
                .replace("REPO",PicGoUplodeParams.REPO)
                .replace("PATH", PicGoUplodeParams.PATH);
        url = url + fileName;
        LOGGER.info("url： {}", url);

        HttpResponse response = HttpRequest.put(url)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", PicGoUplodeParams.Authorization)
                .body(param.toString()).execute();
        LOGGER.info("响应结果： {}", response.body());
        JSONObject jsonObject = JSONObject.parseObject(response.body());
        //访问地址 获取
        JSONObject content = jsonObject.getJSONObject("content");
        return content.getString("download_url");
    }

    /**
     * 文件转base64
     *
     * @param filePath 文件地址
     * @return
     */
    public static String encryptToBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
