/**
 * function    : StringUtils()
 * author      : ByungChan Hong
 * date        : 2007-07-18
 * description : 
 */
function StringUtils() {
}

StringUtils.prototype = {

	/*
	 * 공백 제거
	 */
	trim : function (source) {
		var pattern = /(^\s*)|(\s*$)/g;
		var result = source.replace(pattern, ""); 
		
		return result;
	},
	
	/*
	 * HTML 태그 대체
	 */
	escapeHtml : function (source) {
		var result = null;
		
		result = source.replace(/&/gm, "&amp;");
		result = result.replace(/</gm, "&lt;");
		result = result.replace(/>/gm, "&gt;");
		result = result.replace(/"/gm, "&quot;");
		result = result.replace(/'/gm, "&#39;");		
		
		return result;
	},
	
	/*
	 * NULL 여부
	 */
	isNull : function (source) {
		
		return (source === null) ? true : false;
	},

	/*
	 * 공백 문장 여부
	 */
	isEmpty : function (source) {
		if(source === "") {
			return true;
		}
		
		return false;
	},

	/*
	 * 이메일 형식 여부
	 */
	isEmail : function (source) {
		var pattern = /^[_\-\.0-9a-zA-Z]{3,}@[-.0-9a-zA-z]{2,}\.[a-zA-Z]{2,4}$/;
		
		return pattern.test(source);
	},
	
	/*
	 * 주민번호 형식 여부
	 */
	isJuminNo : function (source) {
	    var pattern = /^([0-9]{6})-?([0-9]{7})$/;
	    
		if(!pattern.test(source)) {
			return false;
		} else {
			return true;
		}
	},

	/*
	 * 외국인 등록번호 형식 여부
	 */
	isForeignerNo : function (source) {
	    var pattern = /^(\d{6})-?(\d{5}[7-9]\d{1})$/;
	    
		if(!pattern.test(source)) {
			return false;
		}

	    source = RegExp.$1 + RegExp.$2;
	    
	    if ((source[7]*10 + source[8]) %2) {
	    	return false;
	    }
	
	    var sum = 0;
	    var last = source.charCodeAt(12) - 0x30;
	    var bases = "234567892345";
	    
	    for (var i=0; i<12; i = i + 1) {
	        if (isNaN(source.substring(i,i+1))) {
	        	return false;
	        }
	        
	        sum += (source.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
	    }
	    
	    var mod = sum % 11;
	    
	    return (11 - mod + 2) % 10 == last ? true : false; 
	},
	
	/*
	 * 유효하 날짜 형식 여부
	 */
	isDate : function (source, sep) {
		var temp = source.split(sep);
		
		var year = temp[0];
		var month = temp[1];
		var day = temp[2];
		
		var yearNum = parseInt(year);
		var monthNum = parseInt(month);
		var dayNum = parseInt(day);
		
		if(year.length != 4) {
			return false;
		}
		
		if((month.length != 2) || !(monthNum >= 1 && monthNum <= 12)) {
			return false;
		}
		
		var ends = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		
		if ((yearNum % 4 === 0 && yearNum % 100 !== 0) || (yearNum % 400 === 0)) {
			ends[1] = 29;
		}
		
		if((day.length != 2) || !(dayNum >= 1 && dayNum <= ends[monthNum - 1])) {
			return false;
		}
		
		return true;	
	},

	/*
	 * 전화번호 형식 여부
	 */
	isPhoneNumber : function (source) {
		var pattern = /^0[0-9]{1,2}-[1-9]{1}[0-9]{2,3}-[0-9]{4}$/;
		
		return pattern.test(source);
	},
	
	/*
	 * 전화번호 형식 여부
	 * -, .을 구분자로 전화번호 입력
	 */
	isPhoneNumber2 : function (source) {
		var pattern = /^\d{1,3}([\s\-\.]|)\d{1,4}([\s\-\.]|)\d{1,4}$/;
		
		return pattern.test(source);
	},

	/*
	 * 이동전화번호 형식 여부 
	 */
	isCellularPhoneNumber : function (source) {
		var pattern = /^01[016789]{1}-[1-9]{1}[0-9]{2,3}-[0-9]{4}$/;
		
		return pattern.test(source);
	},	
	
	/*
	 * 숫자(정수) 형식 여부
	 */
	isOnlyNumber : function (source) {
		var pattern = /^[0-9]+$/;
		
		return pattern.test(source);
	},

	/*
	 * 알파벳 형식 여부
	 */
	isOnlyAlphabet : function (source) {
		var pattern = /^[a-zA-Z]+$/;
		
		return pattern.test(source);
	},

	/*
	 * 알파벳 또는 숫자(정수) 형식 여부
	 */
	isAlphabetOrNumber : function (source) {
		var pattern = /^[0-9a-zA-Z]+$/;
		
		return pattern.test(source);
	},

	/*
	 * 알파벳 그리고 숫자(정수) 형식 여부
	 */
	isAlphabetAndNumber : function (source) {
		var pattern1 = /^[0-9]+$/;
		var pattern2 = /^[a-zA-Z]+$/;
		
		return ((pattern1.test(source)) && (pattern2.test(source))) ? true : false;
	},

	/*
	 * 숫자(실수) 형식 여부
	 */
	isDecimalNumber : function (source) {
		var pattern = /^(\-)?[0-9]*(\.[0-9]*)?$/;
		
		return pattern.test(source);
	},

	/*
	 * 인터넷 주소(IP) 형식 여부
	 */
	isIp : function (source) {
		var pattern = /^(([0-9]{1,3})\.([0-9]{1,3})\.([0-9]{1,3})\.([0-9]{1,3}))$/;
		
		return pattern.test(source);
	},
	
	/*
	 * 인터넷 주소(URL) 형식 여부
	 */
	isUrl : function (source) {
		var pattern = /^(([a-z]+):\/\/|)((?:[a-z\d\-]{2,}\.)+[a-z]{2,})(:\d{1,5})?(\/[^\?]*)?(\?.+)?$/i;
		return pattern.test(source);
	},

	/*
	 * 크기 반환
	 */
	getSize : function (source) {
		
		return source.length;
	},
	
	/*
	 * 바이트 크기 반환
	 *
	 * 1 : 영어, 숫자
	 * 3 : 특수 문자
	 * 6 : 2 바이트 문자
	 */
	computeSize : function (source) {
		var size = 0;
		var encoding = null;
		
		for (var i=0; i<source.length; i = i + 1) {
			//encoding = escape(source.substring(i, (i + 1)));
			encoding = escape(source.charAt(i));
			
			if(encoding == "%0D" || encoding == "%0A") {
				size += 4;
			}
			else if ((encoding.length == 1) || (encoding.length == 3)) {
				size += 1;
			}
			else {
				size += 3;
			}
		}

		return size;
	},
	
	/*
	 * 
	 *
	 */
	cuttingText : function (source, limit) {
		var size = 0;
		var encoding = null;
		var cutt = 0;
		
		for (var i=0; i<source.length; i = i + 1) {
			//encoding = escape(source.substring(i, (i + 1)));
			encoding = escape(source.charAt(i));
			
			if(encoding == "%0D" || encoding == "%0A") {
				size += 4;
			}
			else if ((encoding.length == 1) || (encoding.length == 3)) {
				size += 1;
			}
			else {
				size += 3;
			}
			
   			if(size > limit) {
   				cutt = i;
   				break;
   			}
		}

		return source.substring(0, cutt);
	},
	
	/*
	 * 바이트 크기 반환
	 *
	 * 1 : 영어, 숫자
	 * 3 : 특수 문자
	 * 6 : 2 바이트 문자
	 */
	getByteSize : function (source) {
		var result = 0;
		var encoding = null;
		
		for (var i=0; i<source.length; i = i + 1) {
			encoding = escape(source.slice(i, (i + 1)));
			
			if ((encoding.length == 1) || (encoding.length == 3)) {
				result += 1;
			}
			else {
				result += 2;
			}
		}

		return result;
	},
	
	/*
	 * 이미지 형식 여부
	 */
	isImage : function (source) {
		return (/.gif$/i.test(source) || /.jpg$/i.test(source) || /.bmp$/i.test(source) || /.png$/i.test(source));
	},
	
	/*
	 * 사업자 등록 번호로 포맷
	 */
	biznum_format : function(biznum) 
	{ 
		return biznum.replace(/([0-9]{3})([0-9]{2})([0-9]{5}$)/,"$1-$2-$3");
	}		
	
	
	
};

/**
 * function    : FormUtils()
 * author      : ByungChan Hong
 * date        : 2007-07-18
 * description : 
 */
function FormUtils() {
}

FormUtils.prototype = {

	/*
	 * 태그 형식 반환
	 */
	getType : function (el) {
		var result = null;
		
		switch(el.tagName.toLowerCase()) {
		case "select" :
			if(el.multiple) {
				result = "selectMulti";
			}
			else {
				result = "selectSingle";
			}
			break;
		case "textarea" :
			result = "textarea";
			break;
		case "input" :
			switch(el.type.toLowerCase()) {
			case "radio" :
				result = "radio";
				break;
			case "checkbox" :
				result = "checkbox";
				break;
			case "file" :
				result = "file";
				break;
			case "text" :
				result = "text";
				break;
			case "password" :
				result = "password";
				break;
			case "hidden" :
				result = "hidden";
				break;
			}
			break;
		}
		
		return result;
	}
	
};



function jqueryModalUtil() {
}

jqueryModalUtil.prototype = {
		callPopUp : function(url, height, width, title){

	    iframe = $('<iframe id="ipopup" src="' + url + '" frameborder="0" style="border:0" scrolling="Yes"></iframe>').dialog({
			title:title,
			autoOpen: true,
			height: height,
			width: width,
			position:[100,100],
			modal: true,
			closeOnEscape:true,
			resizable: false,
			autoResize: true,				
			
			 show: {effect: 'fade', speed: 1000},
			overlay: {
				opacity: 0.5,
				background: "black"
			},		
			buttons: {

			}
		}).width(width-10).height(height);			
	    //$(".ui-widget-overlay").css({'background-image': 'url("http://www.mydomain.com/images/ftp-page-bg.gif")','opacity':'1'})
	}
};
	
	

function check_busino(biznum) {
    var sum = 0;
    var getlist =new Array(10);
    var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
    for(var i=0; i<10; i++) { getlist[i] = biznum.substring(i, i+1); }
    for(var i=0; i<9; i++) { sum += getlist[i]*chkvalue[i]; }
    sum = sum + parseInt((getlist[8]*5)/10);
    sidliy = sum % 10;
    sidchk = 0;
    if(sidliy != 0) { sidchk = 10 - sidliy; }
    else { sidchk = 0; }
    if(sidchk != getlist[9]) { return false; }
    return true;
}

