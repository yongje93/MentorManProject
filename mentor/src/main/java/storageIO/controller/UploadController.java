package storageIO.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import member.bean.MemberDTO;
import member.service.MemberService;
import member.storageIO.S3Util;
import member.storageIO.UploadFileUtils;

/**
 * 
 * @Title : S3 파일 컨트롤러
 * @author : yangjaewoo
 * @date : 2019. 12. 6.
 */

@Controller
@RequestMapping(value="storageIO")
public class UploadController {
	
	private S3Util s3 = new S3Util();
	private String bucketName = "mentors-bucket";
	@Autowired
	private MemberService memberService;
    @Resource(name="uploadPath")
    private String uploadPath;
    private static final String amazonURLPath = "https://mentors-bucket.s3.ap-northeast-2.amazonaws.com/";
    
  //서버에 파일 업로드
    @RequestMapping(value ="uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String uploadAjax(@RequestParam Map<String, String> map, @RequestParam(value="member_profile") MultipartFile file, HttpSession session) throws Exception{
    	if(!file.isEmpty()){
    		map.put("member_profile", amazonURLPath+UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(), map.get("member_email")));
    		memberService.write(map);
    		
    	}else {
    		map.put("member_profile", amazonURLPath+"defaultuser.png" );
    		UploadFileUtils.uploadFolder(uploadPath, map.get("member_email"));
    		memberService.write(map);
    	}
    	return "sucess";
    }
    
    
    //파일 표시
//    @RequestMapping("/displayFile")
//    @ResponseBody
//    public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
//        InputStream in = null;
//        ResponseEntity<byte[]> entity = null;
//        HttpURLConnection uCon = null;
//        System.out.println("FILE NAME: " + fileName);
//        //System.out.println("FileName : "+fileName);
//        try{
//            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
//            MediaType mType = MediaUtils.getMediaType(formatName);
//            HttpHeaders headers = new HttpHeaders();
//            String inputDirectory = "faint1122";
//            URL url;
//
//            try {
//                url = new URL(s3.getFileURL(bucketName, inputDirectory+fileName));
//                System.out.println(url);
//                uCon = (HttpURLConnection) url.openConnection();
//                in = uCon.getInputStream(); // 이미지를 불러옴
//            } catch (Exception e) {
//                url = new URL(s3.getFileURL(bucketName, "default.jpg"));
//                uCon = (HttpURLConnection) url.openConnection();
//                in = uCon.getInputStream();
//            }
//            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
//            headers,
//            HttpStatus.CREATED);
//        }catch (FileNotFoundException effe){
//            System.out.println("File Not found Exception");
//            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
//            MediaType mType = MediaUtils.getMediaType(formatName);
//            HttpHeaders headers = new HttpHeaders();
//            in = new FileInputStream(uploadPath+"/noimage.jpeg");
//
//                headers.setContentType(mType);
//
//            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
//                    headers,
//                    HttpStatus.CREATED);
//        }catch (Exception e){
//            e.printStackTrace();
//            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
//        }finally {
//            in.close();
//        }
//        return entity;
//    }

    
    //파일 삭제
//    @ResponseBody
//    @RequestMapping(value="deleteFile", method=RequestMethod.POST)
//    public ResponseEntity<String> deleteFile(String fileName){
//        String inputDirectory = "mentors-bucket";
//        s3.fileDelete(inputDirectory+fileName);
//        return new ResponseEntity<String>("deleted", HttpStatus.OK);
//    }
	

}
