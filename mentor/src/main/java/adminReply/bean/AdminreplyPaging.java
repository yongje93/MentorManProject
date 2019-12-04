package adminReply.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @Title : 관리자 댓글관리  고맙습니다/멘티게시판댓글
 * @author : 안상구
 * @date : 2019. 12. 03.
 */

@Data
@Component
public class AdminreplyPaging {

	private int currentPage; // 현재페이지
	private int pageBlock; // [이전][1][2][3][다음] 3
	private int pageSize; // 1페이지당 5개씩 10
	private int totalA; // 총글수 1 
	private StringBuffer pagingHTML;
	
	/* description : 고맙습니다 댓글 리스트 페이징 */
	public void tankyouPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminThankyou?pg="+(startPage-1)+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminThankyou?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminThankyou?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminThankyou?pg="+(endPage+1)+"'>Next</a>");
	}
	/* description : 멘티게시판 댓글 리스트 페이징 */
	public void menteeReplyPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmenteeReply?pg="+(startPage-1)+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmenteeReply?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmenteeReply?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmenteeReply?pg="+(endPage+1)+"'>Next</a>");
	}
}
