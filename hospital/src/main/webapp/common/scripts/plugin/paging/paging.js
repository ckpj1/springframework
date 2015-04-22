/*
	- 사용 방법				
	추가 - 검색을 하는 form 안에 추가
	<input type="hidden" id="currPage" name="currPage" value="1" />
	<input type="hidden" id="rowCnt" name="rowCnt" value="10" />
	
	구현 - 페이징 버튼을 눌렀을 때 호출되는 fn
	function movePage(pageIdx){}
*/
var rsCnt, viewPageCnt=10;//총 데이터 개수, 페이지 블럭의 개수
var webAppNm = "";
//var webAppNm = "/FCMS";
function paging(pageIdx){
	
	if(pageIdx){
		$("#currPage").val(pageIdx);	
	}
	
	var pageCnt = Math.ceil(rsCnt/$("#rowCnt").val());
	$("#pageWrap").html("");
	if(rsCnt != 0){
		var tmpTxt = "";
		var pageStartNum = (Math.ceil($("#currPage").val()/viewPageCnt)-1)*viewPageCnt;
		var pageEndNum = pageStartNum+viewPageCnt;
		if(pageEndNum>pageCnt){
			pageEndNum = pageCnt;
		}
		if(pageStartNum > viewPageCnt){						
			tmpTxt+="<img src='"+webAppNm+"/commons/scripts/plugin/paging/NumIconPP.gif' style='margin-right:5px;' width='13' height='13' onclick='javascript:movePage(1);'>";
		}
		if(pageStartNum != 0){
			tmpTxt+="<img src='"+webAppNm+"/commons/scripts/plugin/paging/NumIconP.gif' style='margin-right:5px;' width='13' height='13' onclick='javascript:movePage("+(pageStartNum-viewPageCnt+1)+");'>";						
		}
		
		for(var i=pageStartNum+1;i<=pageEndNum;i++){
			
			if(i == $("#currPage").val()){//선택되어 있는 페이지 번호 강조
				tmpTxt+="<a href='javascript:movePage("+i+");' style='color:#000000; font-weight:bold;'>["+i+"]</a> ";
			}else{
				tmpTxt+="<a href='javascript:movePage("+i+");'>["+i+"]</a> ";
			}
		}
		
		if(viewPageCnt < pageCnt && pageEndNum < pageCnt){
			tmpTxt+="<img src='"+webAppNm+"/commons/scripts/plugin/paging/NumIconN.gif' style='margin-right:5px;' width='13' height='13' onclick='javascript:movePage("+(pageEndNum+1)+");'>";
			tmpTxt+="<img src='"+webAppNm+"/commons/scripts/plugin/paging/NumIconNN.gif' width='13' height='13' onclick='javascript:movePage("+pageCnt+");'>";
		}
		$("#pageWrap").html(tmpTxt);
	}
}