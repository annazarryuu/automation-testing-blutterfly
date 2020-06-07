package com.id.perpus.common;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class Common {
	public static String encodeB64(String password) throws Exception {
		return Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
	}

	public static String decodeB64(String password) throws Exception {
		byte[] base64decodedBytes = Base64.getDecoder().decode(password);
		return new String(base64decodedBytes, "utf-8");
	}

	public static boolean emailValidation(String email) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(EMAIL_REGEX);
	}

	public static boolean passwordValidation(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!*.,?@#$%^&+=])(?=\\S+$).{8,}";
		return password.matches(pattern);
	}

	public static String MD5(String md5){
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String generatePassword(){
		String VALID_SPECIAL_CHARACTERS = "!#$"; 
		SecureRandom random = new SecureRandom();
	    StringBuilder password = new StringBuilder();
	    while (password.length() < 8) {
	        char character = (char) random.nextInt(Character.MAX_VALUE);
	        if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || (character >= '0' && character <= '9') || VALID_SPECIAL_CHARACTERS.contains(String.valueOf(character))) {
	            password.append(character);
	        }
	    }
	    return password.toString();
	}
	
	public static String toString(String s){
		try {
			if(s != null){
				return s.trim();
			}else{
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String toString(Object s){
		try {
			if(s != null){
				return s.toString().trim();
			}else{
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String toStrip(String s){
		try {
			if(s != null){
				return s.trim();
			}else{
				return "-";
			}
		} catch (Exception e) {
			return "-";
		}
	}
	
	public static String toListJobsTemplate(List<String> jobs){
		StringBuilder sb = new StringBuilder();
		sb.append("<ol>");
		if(jobs != null && jobs.size() > 0){
			for (String job : jobs) {
				sb.append("<li>"+job+"</li>");
			}
		}
		sb.append("</ol>");
		return sb.toString();
	}
	
	public static byte[] doConvertFileToByte(File f) throws Exception{
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
			return bos.toByteArray();
		} catch (Exception e) {
			throw e;
		}finally {
			if(fis != null){
				fis.close();
			}
		}
		
	}
	
	public static void convertFromByteToFile(byte[] bit, String dest)throws Exception{
		FileOutputStream fos = null;
		try {
			byte[] buffer = bit;
			fos = new FileOutputStream(dest);
			fos.write(buffer);
		} catch (Exception e) {
			throw e;
		}finally {
			if(fos != null){
				fos.close();
			}
		}
		
	}
	
	public static String emptyToNull (String input){
		if("".equals(input)){
			return null;
		}else{
			return input;
		}
	}
	
	public static String nullToEmpty (String input){
		if(input == null){
			return "";
		}else{
			return input;
		}
	}
	public static String nullToEmpty (Object input){
		if(input == null){
			return "";
		}else{
			return input.toString();
		}
	}
	
	public static int stringToInteger (String input){
		try{
			return Integer.parseInt(input);
		}catch (Exception e){
			return 0;
		}
	}
	
	public static String doUpload(HttpServletRequest request, MultipartFile file,  String dest, String userId, String source) {
        String root = request.getServletContext().getRealPath("/");
        try {
        	if (file != null && !file.isEmpty()) {
    			try {
    				String filename = file.getOriginalFilename();
    				String ext = FilenameUtils.getExtension(filename); // returns "txt"
    				filename = userId + "." +ext;
    				
    				byte[] bytes = file.getBytes();
    				
    				String file_location = root + Constanta.DIST + dest;
    				File dir = new File(file_location);
    				if (!dir.exists()){
    					dir.mkdirs();
    				}

    				// Create the file on server
    				File serverFile = new File(dir.getAbsolutePath() +"\\"+ filename);
    				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
    				stream.write(bytes);
    				stream.close();
    				
    				File copyTo = new File(source + "\\" + filename);
    				
    				copyFile(serverFile, copyTo);
    				System.out.println("Server File Location=" + serverFile.getAbsolutePath());

    				 return filename;
    			} catch (Exception e) {
    				return "no-pp.jpg";
    			}
    			
    			
    		} else {
    			return "no-pp.jpg";
    		}
           
        } catch (Exception e) {
        	return null;
        }
	}
	
	public static String doUploadPicture(HttpServletRequest request, MultipartFile file,  String dest, String id, String source) {
        String root = request.getServletContext().getRealPath("/");
        try {
        	if (file != null && !file.isEmpty()) {
    			try {
    				String filename = file.getOriginalFilename();
    				String ext = FilenameUtils.getExtension(filename); // returns "txt"
    				filename = id + "." +ext;
    				
    				byte[] bytes = file.getBytes();
    				
    				String file_location = root + Constanta.DIST + dest;
    				File dir = new File(file_location);
    				if (!dir.exists()){
    					dir.mkdirs();
    				}

    				// Create the file on server
    				File serverFile = new File(dir.getAbsolutePath() +"\\"+ filename);
    				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
    				stream.write(bytes);
    				stream.close();
    				
    				File copyTo = new File(source + "\\" + filename);
    				
    				copyFile(serverFile, copyTo);
    				System.out.println("Server File Location=" + serverFile.getAbsolutePath());

    				 return filename;
    			} catch (Exception e) {
    				return null;
    			}
    			
    			
    		} else {
    			return null;
    		}
           
        } catch (Exception e) {
        	return null;
        }
	}

	
	public static String doUploadAttachment(HttpServletRequest request, MultipartFile file,  String dest,  String source) {
        String root = request.getServletContext().getRealPath("/");
        try {
        	if (file != null && !file.isEmpty()) {
    			try {
    				String filename = file.getOriginalFilename();
    				//String ext = FilenameUtils.getExtension(filename); // returns "txt"
    				
    				//byte[] bytes = file.getBytes();
    				
    				String file_location = root + Constanta.DIST + dest;
    				File dir = new File(file_location);
    				if (!dir.exists()){
    					dir.mkdirs();
    				}

    				// Create the file on server
    				File serverFile = new File(dir.getAbsolutePath() +"\\"+ filename);
    				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
    				// new process
    				InputStream dataUploadFile = file.getInputStream();
    				int bytes;
    				while ((bytes=dataUploadFile.read()) != -1){
    					stream.write(bytes);
    				}
    				// end new process
    				//stream.write(bytes);
    				stream.close();
    				dataUploadFile.close(); // new
    				
    				File copyTo = new File(source + "\\" + filename);
    				
    				copyFile(serverFile, copyTo);
    				if(serverFile.delete()){
    					System.out.println("temp File deleted" + serverFile.getAbsolutePath());
					}
    				System.out.println("Server File Location=" + serverFile.getAbsolutePath());

    				 return filename;
    			} catch (Exception e) {
    				return "";
    			}
    			
    			
    		} else {
    			return "";
    		}
           
        } catch (Exception e) {
        	return null;
        }
	}
	
	public static void copyFile(File source, File dest) throws IOException {
		try {			
			FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static long toLong(String s){
		try {
			return Long.parseLong(s);
		} catch (Exception e) {
			return (long)0;
		}
	}
	
	public static String formatDateYearOnly(Date s){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			return sdf.format(s);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String formatDateYYYYMMDD(Date s){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(s);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String integerToString03Format(int number){
		return String.format("%03d", number);
	}
}
