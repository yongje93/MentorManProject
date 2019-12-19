//package member.storageIO;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import javax.servlet.ServletContext;
//
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.context.ServletContextAware;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
///**
// * 
// * @Title : 또 다른 방법으로 s3 업로드
// * @author : yangjaewoo
// * @date : 2019. 12. 5.
// */
//@Component
//public class UploadService implements ServletContextAware {
//	
//	private AmazonS3 s3Client;
//	private static final String accessKey = "";
//	private static final String secretKey = "";
//	private static final String bucketName = "";
//	//앤드포인트 - 아태
//	private Regions regions = Regions.AP_NORTHEAST_2;
//	private ServletContext servletContext;
//	
//	
//	public UploadService() {
//		// 생성 및 정보 등록
//		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//		s3Client = AmazonS3ClientBuilder
//				.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(credentials))
//				.withRegion(regions)
//				.build();
//	}
//	
//	//파일저장 경로
//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		this.servletContext = servletContext;
//	}
//	
//	public String upload(MultipartFile multipartFile, String member_email) {
//		// 이미지 식별자 생성
//		UUID uid = UUID.randomUUID();
//		// 이미지 확장자 명 .png , .jpg 와 같은
//		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//		// 업로드 할 새로운 이미지 이름
//		String imageName = uid.toString() + "." + extension;
//		
//		
//		File file = new File(servletContext.getRealPath("/storage/image"), "/test." + extension);
//		try {
//			FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
//			
//			//버킷에 올릴 파일 등록 및 공개설정 및 업로드
//			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, member_email + "/" + imageName, file);
//			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
//			s3Client.putObject(putObjectRequest);
//			
//			// 저장한 이미지 삭제
//			file.delete();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(imageName);
//		
//		return imageName;
//	}
//	
//	
//
//}
