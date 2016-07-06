package tgb.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tgb.taotao.common.utils.JsonUtils;
import tgb.taotao.service.PictureService;

@Controller
public class PictureController {
	
	@Autowired
    private PictureService pictureService;
	
	
	//@ResponseBody相当于调用response的write方法写到浏览器，默认行为将对象转换成json字符串
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String  pictureUpload(MultipartFile uploadFile){
	
		Map result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把Result转换成json
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
