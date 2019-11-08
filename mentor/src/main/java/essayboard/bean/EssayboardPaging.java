package essayboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EssayboardPaging {
	private int currentPage; // 현제페이지
	private int pageBlock; // [이전][1][2][3][다음]
	private int pageSize; // 1페이지당 5개씩
	private int totalA; // 총 글 수
	private StringBuffer pagingHTML;

	public void makePagingHTML() {
		pagingHTML = new StringBuffer();

		int totalP = (totalA + pageSize - 1) / pageSize;// 총 페이지 수n

		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;

		int endPage = startPage + pageBlock - 1;

		if (endPage > totalP) { // 마지막 페이지
			endPage = totalP;
		}
		pagingHTML.append("<ul class='pagination'>");
	      
	      // [이전]
	      if (startPage > pageBlock)
	         pagingHTML.append("<li><a rel='prev' data-reload='true' type='external' href='essayboardList?pg=" + (startPage - 1) + "'>이전</a></li>");

	      for (int i = startPage; i <= endPage; i++) {
	         if (i == currentPage)
	            pagingHTML.append("<li class='active'><a remote='false' href='essayboardList?pg=" + i + "'>" + i + "</a></li>");
	         else
	            pagingHTML.append("<li><a rel='prev' data-reload='true' type='external' href='essayboardList?pg=" + i + "'>" + i + "</a></li>");
	      }
	      // [다음]
	      if (endPage < totalP)
	         pagingHTML.append("<li class='next'><a rel='next' data-reload='true' type='external' href='essayboardList?pg=" + (endPage + 1) + "'>다음</a></li>");
	   
	      pagingHTML.append("</ul>");
	}
	
	public void makeSearchPagingHTML() {
		pagingHTML = new StringBuffer();

		int totalP = (totalA + pageSize - 1) / pageSize;// 총페이지수

		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > totalP)
			endPage = totalP;

		if (startPage > pageBlock)
			pagingHTML.append("[<span id='paging' onclick='boardSearch(" + (startPage - 1) + ")'>이전</span>]");

		for (int i = startPage; i <= endPage; i++) {
			if (i == currentPage)
				pagingHTML.append("[<span id='currentPaging' onclick='boardSearch(" + i + ")'>" + i + "</span>]");
			else
				pagingHTML.append("[<span id='paging' onclick='boardSearch(" + i + ")'>" + i + "</span>]");
		}

		if (endPage < totalP)
			pagingHTML.append("[<span id='paging' onclick='boardSearch(" + (endPage + 1) + ")'>다음</span>]");
	}
}
