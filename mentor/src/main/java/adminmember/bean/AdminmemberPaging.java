package adminmember.bean;

import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * 
 * @Title : 관리자 회원관리  모든회원리스트/멘토리스트/멘토신청리스트/멘티리스트 페이징
 * @author : 안상구
 * @date : 2019. 11. 21.
 */

@Data
@Component
public class AdminmemberPaging {
	private int currentPage; // 현재페이지
	private int pageBlock; // [이전][1][2][3][다음] 3
	private int pageSize; // 1페이지당 5개씩 10
	private int totalA; // 총글수 1 
	private int state; // 내림차순, 오름차순
	private StringBuffer pagingHTML;
	
	/* description : 회원리스트 페이징 */
	public void memberPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+(startPage-1)+"&state="+state+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+i+"&state="+state+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+i+"&state="+state+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+(endPage+1)+"&state="+state+"'>Next</a>");
	}
	
	/* description : 회원서치리스트 페이징 */
	public void memberSearchPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//총페이지수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) 
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmemberSearch("+(startPage-1)+")'>Previous</span>");
		
		for (int i = startPage; i <= endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<span class='page-link' style='cursor: pointer;'onclick='adminmemberSearch("+i+")'>"+i+"</span>");
			else
				pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmemberSearch("+i+")'>"+i+"</span>");
		}
		
		if(endPage<totalP)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmemberSearch("+(startPage-1)+")'>Next</span>");
	}
	
	/* description : 멘토리스트 페이징*/
	public void mentorPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmentorList?pg="+(startPage-1)+"'>Previous</a>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmentorList?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmentorList?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmentorList?pg="+(endPage+1)+"'>Next</a>");
	}
	/* description : 멘토서치  페이징*/
	public void mentorSearchPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//총페이지수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) 
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmentorSearch("+(startPage-1)+")'>Previous</span>");
		
		for (int i = startPage; i <= endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<span class='page-link' style='cursor: pointer;'onclick='adminmentorSearch("+i+")'>"+i+"</span>");
			else
				pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmentorSearch("+i+")'>"+i+"</span>");
		}
		if(endPage<totalP)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmentorSearch("+(startPage-1)+")'>Next</span>");
	}
	
	/* description : 멘토신청  페이징*/
	public void mentorApplyPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmentorApplyList?pg="+(startPage-1)+"'>Previous</a>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmentorApplyList?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmentorApplyList?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmentorApplyList?pg="+(endPage+1)+"'>Next</a>");
	}
	/* description : 멘토신청서치  페이징*/
	public void mentorApplySearchPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//총페이지수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) 
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("[<span id='paging' onclick='boardSearch("+(startPage-1)+")'>이전</span>]");                                             
				
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("[<span id='currentPaging' onclick='boardSearch("+i+")'>"+i+"</span>]");
			else
				pagingHTML.append("[<span id='paging' onclick='boardSearch("+i+")'>"+i+"</span>]");
		}
		
		if(endPage < totalP)
			pagingHTML.append("[<span id='paging' onclick='boardSearch("+(endPage+1)+")'>다음</span>]");
	}

	/* description : 멘티리스트 페이징 */
	public void menteePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmenteeList?pg="+(startPage-1)+"'>Previous</a>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmenteeList?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmenteeList?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmenteeList?pg="+(endPage+1)+"'>Next</a>");
		
	}
	
	/* description : 멘티서치리스트 페이징 */
	public void menteeSearchPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//총페이지수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) 
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmenteeSearch("+(startPage-1)+")'>Previous</span>");
		
		for (int i = startPage; i <= endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<span class='page-link' style='cursor: pointer;'onclick='adminmenteeSearch("+i+")'>"+i+"</span>");
			else
				pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmenteeSearch("+i+")'>"+i+"</span>");
		}
		if(endPage<totalP)
			pagingHTML.append("<span class='page-link' style='cursor: pointer;' onclick='adminmenteeSearch("+(startPage-1)+")'>Next</span>");
	}

	/* description : 매출리스트 페이징 */
	public void salesPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='mentorSales?pg="+(startPage-1)+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='mentorSales?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='mentorSales?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='mentorSales?pg="+(endPage+1)+"'>Next</a>");
	}
	
	/* description : 매출리스트 페이징 */
	public void honorPagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
		
		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmentorSales?pg="+(startPage-1)+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmentorSales?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmentorSales?pg="+i+"'>"+i+"</a>");
		}
		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmentorSales?pg="+(endPage+1)+"'>Next</a>");
	}
	
}
