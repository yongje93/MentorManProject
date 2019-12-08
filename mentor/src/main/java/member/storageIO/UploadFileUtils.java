package member.storageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

/**
 * 
 * @Title : S3Util 의 메서드를 사용하는 클래스
 * @author : yangjaewoo
 * @date : 2019. 12. 6.
 */

public class UploadFileUtils {
	
    // String uploadPath 파일의 저장경로
    // String originalName 원본 팡리 이름
    // byte[] fileData 파일 데이터
    public static String uploadFile(String uploadPath, String originalName, byte[] fileData, String member_email) throws Exception {
        //S3 서버 관련 설정 
        S3Util s3 = new S3Util();
        String bucketName = "mentors-bucket";
        //랜덤의 uid 를 만들어준다.
        UUID uid = UUID.randomUUID();
        //savedName : 570d570a-7af1-4afe-8ed5-391d660084b7.JPG 같은 형식으로 만들어준다.
        String extension = FilenameUtils.getExtension(originalName);
        String savedName = uid.toString() + "." + extension;
        String savedPath = calcPath(uploadPath , member_email);

        String uploadedFileName =(savedPath+savedName).replace(File.separatorChar, '/');
        
        //S3Util 의 fileUpload 메서드로 파일을 업로드
        System.out.println(uploadPath);
        s3.fileUpload(bucketName, uploadedFileName, fileData); 
        return uploadedFileName;
    }
    //폴더만 생성
    public static void uploadFolder(String uploadPath, String member_email) throws Exception {
    	S3Util s3 = new S3Util();
    	
    	s3.createFolder(uploadPath, member_email);
    }
    
    
    
    // 경로 설정처리 
    private static String calcPath(String uploadPath , String member_email) {
        String datePath = member_email + File.separator;
    	S3Util s3 = new S3Util();
        String bucketName = "mentors-bucket";

        // 폴더 생성 호출
        makeDir(uploadPath, datePath );
        
        return datePath;
    }

    //폴더 생성 처리
    private static void makeDir(String uploadPath, String... paths) {
        //중복 파일 존재하면 아무처리 하지 않음
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }
        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }
	
    
}
