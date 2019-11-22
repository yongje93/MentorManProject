package adminmember.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminmemberPaging {
	private int currentPage; // 현재페이지
	private int pageBlock; // [이전][1][2][3][다음] 3
	private int pageSize; // 1페이지당 5개씩 10
	private int totalA; // 총글수 1 
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1))/pageSize;// 1
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; 
		int endPage = startPage+pageBlock-1; // 
		if(endPage>totalP)
			endPage = totalP;
//		if(startPage>pageBlock)
			pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+(startPage-1)+"'>Previous</a>");
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage)
				pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+i+"'>"+i+"</a>");
			else
				pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+i+"'>"+i+"</a>");
		}
//		if(endPage<totalP)
			pagingHTML.append("<a class='page-link' href='adminmemberList?pg="+(endPage+1)+"'>Next</a>");
	}
	
	public void makeSearchPagingHTML() {
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
}
