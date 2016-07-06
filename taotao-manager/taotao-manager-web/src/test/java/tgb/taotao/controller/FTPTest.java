package tgb.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import tgb.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception{
		//创建ftpClient对象
		FTPClient ftpClient= new FTPClient();
		//创建ftp链接，默认是21端口
		ftpClient.connect("192.168.21.198",21);
		
		//登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "liuxinyang");
		
		//上传文件
		//读取本地文件
		FileInputStream inputStream=new FileInputStream(new File("H:\\04 美丽记忆\\2015鸟巢\\IMG_20150716_010643.JPG"));
		
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		
		//修改上传格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数，上传文档的inputStream
		ftpClient.storeFile("rest.png", inputStream);
		//关闭链接
		ftpClient.logout();
		
		
	}
	/*@Test
	public void testFtpUtil() throws Exception{
		//上传文件
				//读取本地文件
				FileInputStream inputStream=new FileInputStream(new File("H:\\04 美丽记忆\\2015鸟巢\\IMG_20150716_010643.JPG"));
			FtpUtil.uploadFile("192.168.21.198", 21, "ftpuser", "liuxinyang", "/home/ftpuser/www/images", "/2016/06/29", "rest1.png", inputStream);
		
		
	}*/
}
