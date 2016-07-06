package tgb.taotao.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tgb.taotao.common.utils.FtpUtil;
import tgb.taotao.common.utils.IDUtils;
import tgb.taotao.service.PictureService;
@Service
public class PictureServieimpl implements PictureService {
 //获取配置文件中的值
	@Value("${FTP_ADDRESS}")
   private String FTP_ADDRESS;
   
   @Value("${FTP_PORT}")
   private Integer FTP_PORT;
   
   @Value("${FTP_USERNAME}")
   private String FTP_USERNAME;
   
   @Value("${FTP_PASSWORD}")
   private String FTP_PASSWORD;
   
   @Value("${FTP_BASE_PATH}")
   private String FTP_BASE_PATH;
   
   
   @Value("${IMAGE_BASE_PATH}")
   private String IMAGE_BASE_PATH;
   
	@Override
	public Map uploadPicture(MultipartFile uploadFile)  {
		Map resultMap= new HashMap<>();
		try{
		//生成一个新的文件名
		//取得原始文件名
		String oldName=uploadFile.getOriginalFilename();
		
		//去除文件名称里的空格
		String oldNamed=URLEncoder.encode(oldName,"UTF-8");
		String oldNamed2=oldNamed.replaceAll("\\+", "%20");
		//生成新文件名
		//UUID.rundomUUID();
		String newName= IDUtils.genImageName();
		newName=newName + oldNamed2.substring(oldNamed2.lastIndexOf("."));
		
		//image path
		String imagePath=new DateTime().toString("/yyyy/MM/dd");
		//图片上传
	 boolean result=	FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,imagePath, newName, uploadFile.getInputStream());
		//返回结果
		if(!result){
			resultMap.put("error", 1);
			resultMap.put("message", "上传文件失败");
		    return resultMap;
		}
		resultMap.put("error", 0);
		resultMap.put("url", IMAGE_BASE_PATH +imagePath +"/" + newName);
		return resultMap;
		}catch(Exception e){
			resultMap.put("error", 1);
			resultMap.put("message", "上传文件发生异常");
		    return resultMap;
		}
	}

}
