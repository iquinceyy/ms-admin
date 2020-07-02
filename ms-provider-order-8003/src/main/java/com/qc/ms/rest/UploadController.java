package com.qc.ms.rest;

import com.qc.ms.config.fdfs.FastdfsClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * quincey
 * Date 2020/6/30 16:16
 *
 * */

@RestController
@RequestMapping("/pages/back/upload")
public class UploadController {

    @Resource
    FastdfsClient fastdfsClient;

    @RequestMapping("uploadFiles")// 上传单张图片和多张图片
    String uploadFiles(MultipartHttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        Collection<List<MultipartFile>> values = multiFileMap.values();
        for (List<MultipartFile> value : values) {
            for (MultipartFile m : value) {
                String s = fastdfsClient.uploadFile(m, 50);// 上传图片，并且压缩到50KB以内
                builder.append(s).append(",");
            }
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());// 去掉最后一个逗号
        }
        return builder.toString();
    }
    // wangeditor上传 // 它需要返回的格式：code:0 ,data:["src1","src2"]
    @RequestMapping("wangEditorUploadFiles")
    Object wangEditorUploadFiles(MultipartHttpServletRequest request) {
        String o = this.uploadFiles(request);
        if (o.contains(",")) {
            String[] data = o.split(",");
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("code", 0);
            resMap.put("data", data);
            return resMap;
        }
        return null;
    }

}
