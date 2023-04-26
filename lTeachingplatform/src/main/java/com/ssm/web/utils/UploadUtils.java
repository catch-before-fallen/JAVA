package com.ssm.web.utils;

import org.apache.commons.io.ByteOrderMark;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class UploadUtils {

    public static void transfromMutliflie(MultipartFile multipartFile , HttpSession session) throws IOException {
        //      获取上传原文件的文件名
        String type = multipartFile.getContentType();
        //      获取要存放资源的全项目路径-1

        ServletContext servletContext = session.getServletContext();
        if(type.equals("video/mp4")){
            String originalFilename= multipartFile.getOriginalFilename();
            String filename=originalFilename.substring(4);

            //      获取要存放资源的全项目路径-2
            String realPath = servletContext.getRealPath("video");
//      根据选定的路径创建文件对象，如果路径在目录路径中不存在，就创建目录路径
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdir();
            }
//      将文件保存到finalPath指定的文件中
            String finalPath =realPath+File.separator +filename ;

            multipartFile.transferTo(new File(finalPath));


        }else if(type.equals("application/octet-stream")){
            String originalFilename = multipartFile.getOriginalFilename();
            //格式化一下传输进来的名称
            int index = originalFilename.indexOf("_");
            String substring = originalFilename.substring(index+1, index + 5);
            String filename = originalFilename.replaceAll(substring, "");
            System.out.println("filename = " + filename);
            //      获取要存放资源的全项目路径-2
            String realPath = servletContext.getRealPath("video/captions");
//      根据选定的路径创建文件对象，如果路径在目录路径中不存在，就创建目录路径
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdir();
            }

//      将文件保存到finalPath指定的文件中
            String finalPath =realPath+File.separator +filename ;
//      将srt文件转化为vtt文件
            if(finalPath.substring(finalPath.lastIndexOf(".")).equals(".srt")){
                File file1 = new File(finalPath);
//      将字幕文件读到字节数组中
                InputStream inputStream = multipartFile.getInputStream();
                byte[] bytes = multipartFile.getBytes();
                inputStream.read(bytes);
//       关闭输入流
                inputStream.close();
//            将读到的内容写到文件中
                FileOutputStream fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write(bytes);
//      关闭输出流
                fileOutputStream.close();
                String finalPath1 = finalPath.replace(".srt", ".vtt");
                File file2 = new File(finalPath1);


//      将文件传入，进行转换
                CaptionTransformUtil.srt2vtt(file1,file2, Charset.forName("UTF-8"), ByteOrderMark.UTF_8);

                file1.delete();


            }else {
                multipartFile.transferTo(new File(finalPath));

            }


        }
    }
}
