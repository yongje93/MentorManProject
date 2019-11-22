package mentor.bean;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MentorfindPaging {
	private int currentPage;//현재페이지로 이전과 다음
	private int pageBlock;//[이전][1][2][3][다음] 3개씩 
	private int pageSize;//1페이지당 5개씩
	private int totalA;//총글수
	private StringBuffer pagingHTML;//String - 편집이 불가 
									//StringBuffer/ StringBuilder - 편집이가능
	
	public void makePagingHTML(){
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//총페이지수
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage>totalP) endPage = totalP;
		
		pagingHTML.append("<ul class='pagination'>");
	      
	      // [이전]
	      if (startPage > pageBlock)
	         pagingHTML.append("<li><a rel='prev' data-reload='true' type='external' href='mentorfindForm?pg=" + (startPage - 1) + "'>이전</a></li>");

	      for (int i = startPage; i <= endPage; i++) {
	         if (i == currentPage)
	            pagingHTML.append("<li class='active'><a remote='false' href='mentorfindForm?pg=" + i + "'>" + i + "</a></li>");
	         else
	            pagingHTML.append("<li><a rel='prev' data-reload='true' type='external' href='mentorfindForm?pg=" + i + "'>" + i + "</a></li>");
	      }
	      // [다음]
	      if (endPage < totalP)
	         pagingHTML.append("<li class='next'><a rel='next' data-reload='true' type='external' href='mentorfindForm?pg=" + (endPage + 1) + "'>다음</a></li>");
	   
	      pagingHTML.append("</ul>");
	}	

}
