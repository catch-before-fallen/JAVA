package com.ssm.web.controller;



import com.ssm.web.pojo.Course;
import com.ssm.web.pojo.Courseplus;
import com.ssm.web.pojo.User;
import com.ssm.web.service.ClassResourceService;
import com.ssm.web.utils.CaptionTransformUtil;
import com.ssm.web.utils.UploadUtils;
import fr.noop.subtitle.srt.SrtWriter;
import org.apache.commons.io.ByteOrderMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

@Controller
public class ClassResourceController {

    @Autowired
    private ClassResourceService classResourceService;
    @Autowired
    private HttpServletRequest httpServletRequest;



    @RequestMapping("/classresource.do")
    public ModelAndView returnClassResouse(){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = httpServletRequest.getSession();


        List<Course> courseList = classResourceService.getAllCourse();
        List<Courseplus> courseplusList = classResourceService.getCourseplusListByCourseList(courseList);
        List<Courseplus> sort_courseList=classResourceService.sortCourseList(courseplusList);

        session.setAttribute("sort_courseList",sort_courseList);

        modelAndView.setViewName("redirect:/classresource.do/1");
        return modelAndView;
    }
    @RequestMapping("/course-show.do/{Id}")
    public ModelAndView showCoursedetail(@PathVariable String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        Courseplus courseplus=classResourceService.getCourseplusByCourseId(id);

        modelAndView.addObject("course",courseplus);
        modelAndView.setViewName("classresource-detail");
        return modelAndView;

    }
    @RequestMapping("/download_course.do/{Id}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadClassResouse(@PathVariable("Id") String ClassId) throws IOException {

//        获取上下文路径
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = session.getServletContext();
//        获取要下载的文件路径以及字幕路径
        String filePath = servletContext.getRealPath("/video");
//        获取要下载的文件文件名
        Integer id=Integer.parseInt(ClassId);
       String filename= classResourceService.getCourseTitleById(id)+".mp4";
//       最终路径
        String finalPath=filePath+ File.separator +filename;
//      将文件读到输入流中
        InputStream inputStream = new FileInputStream(finalPath);
//        创建字节数组
        byte[] bytes = new byte[inputStream.available()];
//        将输入流写入到字节数组中
        inputStream.read(bytes);
//    创建HTTP Header,设置响应头信息
        MultiValueMap<String,String> headers=new HttpHeaders();
//        设置要下载的方式和要下载的名称
        headers.add("Content-Disposition","attachment;filename="+filename);
//        设置响应状态码码
        HttpStatus httpStatus= HttpStatus.OK;
//        创建ResposeEntry对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, httpStatus);

        return  responseEntity;

    }
    @RequestMapping("/download_caption.do/{Id}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadCaptionResouse(@PathVariable("Id") String ClassId) throws IOException {

//        获取上下文路径
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = session.getServletContext();
//        获取要下载的文件路径以及字幕路径
        String filePath = servletContext.getRealPath("/video/captions/");
//        获取要下载的文件文件名
        Integer id=Integer.parseInt(ClassId);
        String filename1= "CHSEN_"+classResourceService.getCourseTitleById(id)+".vtt";
//       最终路径
        String finalPath=filePath+ File.separator +filename1;
//      将文件读到输入流中
        InputStream inputStream = new FileInputStream(finalPath);
//        创建字节数组
        byte[] bytes = new byte[inputStream.available()];
//        将输入流写入到字节数组中
        inputStream.read(bytes);
//    创建HTTP Header,设置响应头信息
        MultiValueMap<String,String> headers=new HttpHeaders();
//        设置要下载的方式和要下载的名称
        headers.add("Content-Disposition","attachment;filename="+filename1);
//        设置响应状态码码
        HttpStatus httpStatus= HttpStatus.OK;
//        创建ResposeEntry对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, httpStatus);

        return  responseEntity;

    }

    @RequestMapping("/upload.do")
    public  ModelAndView uploadClassResource(MultipartFile multipartFile) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //处理空上传情况
        if(multipartFile.getSize()==0){
            modelAndView.setViewName("redirect:/classresource.do/1");
            return modelAndView;

        }
        HttpSession session = httpServletRequest.getSession();

        UploadUtils.transfromMutliflie(multipartFile,session);


        classResourceService.addCourse(multipartFile,session);
        modelAndView.setViewName("redirect:/classresource.do/1");
        return modelAndView;
    }
}
