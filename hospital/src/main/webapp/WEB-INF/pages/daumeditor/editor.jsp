<%--
  - Author		:	강 민석(Minseok Kang)
  - Date		:	2013.03.14
  - Copyright (c) ACTSoft All rights reserved
  - @(#)
  - Description	:	daumEditor 적용/ 모듈로드를 위해서 formId 값을 필수적으로 받는다.
  -	대상:			
  --%>
  
<%@ page language="java" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<!-- 
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
 -->
	<!-- 에디터 컨테이너 시작 -->
    <div id="tx_trex_container" class="tx-editor-container">
    	<!-- 툴바 - 기본 시작 --><!--
		@decsription
		툴바 버튼의 그룹핑의 변경이 필요할 때는 위치(왼쪽, 가운데, 오른쪽) 에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
		tx-btn-lbg: 왼쪽, tx-btn-bg: 가운데, tx-btn-rbg: 오른쪽, tx-btn-lrbg: 독립적인 그룹
		드롭다운 버튼의 크기를 변경하고자 할 경우에는 넓이에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
		tx-slt-70bg, tx-slt-59bg, tx-slt-42bg, tx-btn-43lrbg, tx-btn-52lrbg, tx-btn-57lrbg, tx-btn-71lrbg
		tx-btn-48lbg, tx-btn-48rbg, tx-btn-30lrbg, tx-btn-46lrbg, tx-btn-67lrbg, tx-btn-49lbg, tx-btn-58bg, tx-btn-46bg, tx-btn-49rbg
        -->
        <div id="tx_toolbar_basic" class="tx-toolbar tx-toolbar-basic">
            <div class="tx-toolbar-boundary">
                <ul class="tx-bar tx-bar-left">
                    <li class="tx-list">
                        <div id="tx_fontfamily" unselectable="on" class="tx-slt-70bg tx-fontfamily">
                            <a href="javascript:;" title="글꼴">굴림</a>
                        </div>
                        <div id="tx_fontfamily_menu" class="tx-fontfamily-menu tx-menu" unselectable="on">
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-left">
                    <li class="tx-list">
                        <div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsize">
                            <a href="javascript:;" title="글자크기">9pt</a>
                        </div>
                        <div id="tx_fontsize_menu" class="tx-fontsize-menu tx-menu" unselectable="on">
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-left tx-group-font">
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_bold">
                            <a href="javascript:;" class="tx-icon" title="굵게 (Ctrl+B)">굵게</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underline">
                            <a href="javascript:;" class="tx-icon" title="밑줄 (Ctrl+U)">밑줄</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italic">
                            <a href="javascript:;" class="tx-icon" title="기울임 (Ctrl+I)">기울임</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strike">
                            <a href="javascript:;" class="tx-icon" title="취소선 (Ctrl+D)">취소선</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolor">
                            <a href="javascript:;" class="tx-icon" title="글자색">글자색</a>
                            <a href="javascript:;" class="tx-arrow" title="글자색 선택">글자색 선택</a>
                        </div>
                        <div id="tx_forecolor_menu" class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on">
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolor">
                            <a href="javascript:;" class="tx-icon" title="글자 배경색">글자 배경색</a>
                            <a href="javascript:;" class="tx-arrow" title="글자 배경색 선택">글자 배경색 선택</a>
                        </div>
                        <div id="tx_backcolor_menu" class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on">
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-left tx-group-align">
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleft">
                            <a href="javascript:;" class="tx-icon" title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenter">
                            <a href="javascript:;" class="tx-icon" title="가운데정렬 (Ctrl+.)">가운데정렬</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignright">
                            <a href="javascript:;" class="tx-icon" title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfull">
                            <a href="javascript:;" class="tx-icon" title="양쪽정렬">양쪽정렬</a>
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-left tx-group-etc">
                	<li class="tx-list">
                        <div unselectable="on" class="tx-btn-lbg tx-link" id="tx_link">
                            <a href="javascript:;" class="tx-icon" title="링크 (Ctrl+K)">링크</a>
                        </div>
                        <div id="tx_link_menu" class="tx-link-menu tx-menu">
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-rbg tx-dictionary" id="tx_dictionary">
                            <a href="javascript:;" class="tx-icon" title="사전">사전</a>
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-left tx-group-undo">
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-lbg tx-undo" id="tx_undo">
                            <a href="javascript:;" class="tx-icon" title="실행취소 (Ctrl+Z)">실행취소</a>
                        </div>
                    </li>
                    <li class="tx-list">
                        <div unselectable="on" class="tx-btn-rbg tx-redo" id="tx_redo">
                            <a href="javascript:;" class="tx-icon" title="다시실행 (Ctrl+Y)">다시실행</a>
                        </div>
                    </li>
                    
                    <li class="tx-list">
                        <div unselectable="on" id="tx_image" class="tx-image tx-btn-trans">
                            <a href="javascript:;" title="사진" class="tx-text">사진</a>
                        </div>
                    </li>
                </ul>
                <ul class="tx-bar tx-bar-right">
                	<li class="tx-list">
                        <div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggle">
                            <a href="javascript:;" title="에디터 타입">에디터</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 툴바 - 기본 끝 -->
         
        <!-- 편집영역 시작 -->
        <div id="tx_canvas" class="tx-canvas">
            <div id="tx_loading" class="tx-loading">
                <div>
                    <img src="<c:url value="${dirScripts}/daumeditor/images/icon/editor/loading2.png" />" width="113" height="21" align="absmiddle"/>
                </div>
            </div>
            <div id="tx_canvas_wysiwyg_holder" class="tx-holder" style="display:block;">
                <iframe id="tx_canvas_wysiwyg" name="tx_canvas_wysiwyg" allowtransparency="true" frameborder="0">
                </iframe>
            </div>
            <div class="tx-source-deco">
                <div id="tx_canvas_source_holder" class="tx-holder">
                    <textarea id="tx_canvas_source" rows="30" cols="30">
                    </textarea>
                </div>
            </div>
            <div id="tx_canvas_text_holder" class="tx-holder">
                <textarea id="tx_canvas_text" rows="30" cols="30">
                </textarea>
            </div>
        </div>
        <!-- 높이조절 Start -->
        <div id="tx_resizer" class="tx-resize-bar">
            <div class="tx-resize-bar-bg">
            </div>
            <img id="tx_resize_holder" src="<c:url value="${dirScripts}/daumeditor/images/icon/editor/skin/01/btn_drag01.gif" />" width="58" height="12" unselectable="on" alt="" />
        </div>
        <!-- 편집영역 끝 -->
         
        <!-- 첨부박스 시작 --><!-- 파일첨부박스 Start -->
        <div id="tx_attach_div" class="tx-attach-div">
            <div id="tx_attach_txt" class="tx-attach-txt">
                	파일 첨부
            </div>
            <div id="tx_attach_box" class="tx-attach-box">
                <div class="tx-attach-box-inner">
                    <div id="tx_attach_preview" class="tx-attach-preview">
                        <p>
                        </p>
                        <img src="<c:url value="${dirScripts}/daumeditor/images/icon/editor/pn_preview.gif" />" width="147" height="108" unselectable="on"/>
                    </div>
                    <div class="tx-attach-main">
                        <div id="tx_upload_progress" class="tx-upload-progress">
                            <div>
                                0%
                            </div>
                            <p>
                                	파일을 업로드하는 중입니다.
                            </p>
                        </div>
                        <ul class="tx-attach-top">
                            <li id="tx_attach_delete" class="tx-attach-delete">
                                <a>전체삭제</a>
                            </li>
                            <li id="tx_attach_size" class="tx-attach-size">
                                	파일: <span id="tx_attach_up_size" class="tx-attach-size-up"></span>/<span id="tx_attach_max_size"></span>
                            </li>
                            <li id="tx_attach_tools" class="tx-attach-tools">
                            </li>
                        </ul>
                        <ul id="tx_attach_list" class="tx-attach-list">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- 첨부박스 끝 -->
        
    </div>
    <!-- 에디터 컨테이너 끝 -->

<script type="text/javascript">
	var config = {
	    txHost: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
	    txPath: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
	    txService: 'sample', /* 수정필요없음. */
	    txProject: 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
	    initializedId: "", /* 대부분의 경우에 빈문자열 */
	    wrapper: "tx_trex_container", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
	    form: '${targetFormId}' + "", /* 등록하기 위한 Form 이름 */
	    txIconPath: "<c:url value="${dirScripts}/daumeditor/images/icon/editor/" />", /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
	    txDecoPath: "<c:url value="${dirScripts}/daumeditor/images/deco/contents/" />", /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
	    canvas: {
	        styles: {
	            color: "#123456", /* 기본 글자색 */
	            fontFamily: "돋움", /* 기본 글자체 */
	            fontSize: "10pt", /* 기본 글자크기 */
	            backgroundColor: "#fff", /*기본 배경색 */
	            lineHeight: "1.5", /*기본 줄간격 */
	            padding: "8px" /* 위지윅 영역의 여백 */
	        },
	        showGuideArea: false
	    },
	    events: {
	        preventUnload: false
	    },
	    sidebar: {
	        attachbox: {
	            show: false
	        },
	        attacher: {
				image: {
	      		multiple: true,
	      		multipleuse: false,
	      		checksize: false,
	      		boxonly: false,
	      		wysiwygonly: true,
	      		features: { left:250, top:65, width:450, height:180 },
	      		popPageUrl: "<c:url value="/daumeditor/popup/image.do"/>"
	      		}
			}
	    },
	    size: {
	        contentWidth: 900 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
	    }
	};

	EditorJSLoader.ready(function(Editor) {
	    var editor = new Editor(config);
	});
</script>

<script type="text/javascript">
	/* 예제용 함수 */
	function saveContent() {
		Editor.save(); // 이 함수를 호출하여 글을 등록하면 된다.
	}
	
	/**
	 * Editor.save()를 호출한 경우 데이터가 유효한지 검사하기 위해 부르는 콜백함수로
	 * 상황에 맞게 수정하여 사용한다.
	 * 모든 데이터가 유효할 경우에 true를 리턴한다.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		// Place your validation logic here
			
		// sample : validate that content exists
		var validator = new Trex.Validator();
		var content = editor.getContent();
		if (!validator.exists(content)) {
			alert('내용을 입력하세요');
			return false;
		}
		return true;
	}
	
	/**
	 * Editor.save()를 호출한 경우 validForm callback 이 수행된 이후
	 * 실제 form submit을 위해 form 필드를 생성, 변경하기 위해 부르는 콜백함수로
	 * 각자 상황에 맞게 적절히 응용하여 사용한다.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 정상적인 경우에 true
	 */
	function setForm(editor) {
		var formGenerator = editor.getForm();
		var content = editor.getContent();
		formGenerator.createField(
				tx.textarea({
					'name': '${targetInputId}', // 본문 내용을 필드를 생성하여 값을 할당하는 부분
					'style': { 'display': "none" }
				}, content)
		);
		/* 아래의 코드는 첨부된 데이터를 필드를 생성하여 값을 할당하는 부분으로 상황에 맞게 수정하여 사용한다.
		 첨부된 데이터 중에 주어진 종류(image,file..)에 해당하는 것만 배열로 넘겨준다. */
		var images = editor.getAttachments('image');
		for (var i = 0, len = images.length; i < len; i++) {
			// existStage는 현재 본문에 존재하는지 여부
			if (images[i].existStage) {
				// data는 팝업에서 execAttach 등을 통해 넘긴 데이터
				alert('attachment information - image[' + i + '] \r\n' + JSON.stringify(images[i].data));
				formGenerator.createField(
						tx.input({
							'type': "hidden",
							'name': 'tx_attach_image',
							'value': images[i].data.imageurl // 예에서는 이미지경로만 받아서 사용
						})
				);
			}
		}
		return true;
	}
	
	function loadContent() {
		/* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
		//alert('${targetInputId}');
		Editor.modify({
			"content": document.getElementById('${targetInputId}') /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
		});
	}
</script>