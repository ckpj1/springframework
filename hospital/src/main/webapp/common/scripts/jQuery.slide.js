
/* Simple J-vaScript Inheritance

* By John Resig http://ejohn.org/

* MIT Licensed.

*/

// Inspired by base2 and Prototype

(function(){

  var initializing = false, fnTest = /xyz/.test(function(){xyz;}) ? /\b_super\b/ : /./;
  // The base Class implementation (does nothing)

  this.Class = function(){};  // Create a new Class _this inherits from this class

  Class.extend = function(prop) {

    var _super = this.prototype;
    // Instantiate a base class (but only create the instance,

    // don't run the init constructor)

    initializing = true;

    var prototype = new this();

    initializing = false;
    // Copy the properties over onto the new prototype

    for (var name in prop) {

      // Check if we're overwriting an existing function

      prototype[name] = typeof prop[name] == "function" &&

        typeof _super[name] == "function" && fnTest.test(prop[name]) ?

        (function(name, fn){

          return function() {

            var tmp = this._super;
            // Add a new ._super() method _this is the same method

            // but on the super-class

            this._super = _super[name];
            // The method only need to be bound temporarily, so we

            // remove it when we're done executing

            var ret = fn.apply(this, arguments);

            this._super = tmp;
            return ret;

          };

        })(name, prop[name]) :

        prop[name];

    }
    // The dummy class constructor

    function Class() {

      // All construction is actually done in the init method

      if ( !initializing && this.init )

        this.init.apply(this, arguments);

    }
    // Populate our constructed prototype object

    Class.prototype = prototype;
    // Enforce the constructor to be what we expect

    Class.prototype.constructor = Class;
    // And make this class extendable

    Class.extend = arguments.callee;
    return Class;

  };

})();

$.config = {
	currentSlide: 0,
	timer: null,
	myNn: []
};

;(function($) {
	var swipeCommon=Class.extend({
		elem:null,
		currentPosition:0,
		positionRange:100,
		autoRolling:false,
		rollingTime:0,
		animationTime:0,
		currentPaging:0,
		timer:null,
		paging:null,
		imgWidth:0,
		imgHeight:0,
		count:3,
		animationStatus:true,
		init: function(settings, options, elem) {
			this.options = jQuery.extend({}, settings, options);
			this.rollingTime=this.options.rollingTime;
			this.animationTime=this.options.animationTime;
			this.autoRolling=this.options.autoRolling;
			this.paging=this.options.paging;
			this.pagingActive=this.options.pagingActive;
			this.currentPaging=this.options.currentPaging;
			this.elem=elem;
		},

		getSize:function(el,fn) {
			var img = new Image();
			img.onload=function() { fn(img.width,img.height); };
			img.src = el.attr('src');
		},

		fix: function (event) {
			return event || window.event;
		},

		matrixToArray : function(matrix) {
			return matrix.substr(7, matrix.length - 8).split(', ');
		}
	});

	swipeUI=swipeCommon.extend({
		navigation:null,
		navigationWidth:null,
		navigationHeight:null,
		pagingHeight:null,
		currentPaging:0,
		nSwipeThreshold:null, //at android 30, not android 25
		nCurrentWidth:null,
		currentSlide:null,
		bautoRolling:true,
		init: function(settings, options, elem) {
			// Call the inherited version of swipeCommon
			var _this=this;

			this._super(settings, options, elem);

			this.navigation=jQuery(this.elem).find('li');

			this.pagingHeight=jQuery(this.elem).find(this.paging).innerHeight();
			this.createSwipe();
			this.setEachPos(this.currentPaging);
			this.currentSlide=this.currentPaging;
			jQuery(this.paging).find('a').removeClass('on').eq(this.currentSlide).addClass('on');
		},

		createSwipe:function() {
			var _this=this;

			this.setEachPos([0,jQuery('.slide_wrap .slide_inner ul').width(),jQuery('.slide_wrap .slide_inner ul').width()*2,jQuery('.slide_wrap .slide_inner ul').width()*-1]);
			this.bAutoRolling();
		},

		bAutoRolling:function(){

			var _this=this;
			if(this.autoRolling) {
				if(this.bautoRolling) {
					this.timer=null;
					this.timer=setInterval(
						function() {
						_this.rolling(jQuery('.slide_wrap .slide_inner ul').width()*-1,jQuery('.slide_wrap .slide_inner ul').width(), this.currentSlide,'','auto');
					},this.rollingTime);
				}
			}

		},

		resizeEvent:function() {
			this.setEachPos([0,jQuery('.slide_wrap .slide_inner ul').width(),jQuery('.slide_wrap .slide_inner ul').width()*2,jQuery('.slide_wrap .slide_inner ul').width()*-1]);
		},

		setEachPos:function(currentPosition) {
			var _this=this,pos=this.currentPosition;
			this.navigationWidth =this.elem.width();
			this.nCurrentWidth =this.elem.width();//jQuery('.slide_wrap .slide_inner ul').width();

			this.navigation.each(
				function(idx) {
				//console.log(currentPosition);
					_this.navigation.eq(idx).css({'left':currentPosition[idx]+'px'});
				 }

			);
			this.navigation.eq(this.currentPaging).css({'visibility':'visible'});

			//webkit browser�먯꽌�� img �ъ씠利� 援ы빐�ㅻ뒗 ��대컢�� � 釉뚮씪�곗졇� �곸씠�섏뿬 �대�吏 濡쒕뵫 �� �ъ씠利덈� 媛�몄삤�� �뺥깭濡� 援ы쁽
			
			if(parseInt(this.elem.find('li').eq(0).css('width')) == 0){
				this.getSize(this.elem.find('img').eq(0),jQuery.proxy(function(w,h){
					this.navigationWidth=(this.isMobile) ?this.elem.width() : parseInt(w);
					this.navigationHeight=parseInt(h);

					this.elem.css({
						'width':jQuery('.slide_wrap .slide_inner ul').width(),
						'height':h+this.pagingHeight
					});

					this.navigation.css({
						'width':jQuery('.slide_wrap .slide_inner ul').width(),
						'height':this.navigationHeight
					});
				},this));

			} else {
				this.navigationWidth=jQuery('.slide_wrap .slide_inner ul').width();
				this.navigationHeight=parseInt(this.elem.find('li').eq(0).css('height'));

				this.elem.css({
					'width':jQuery('.slide_wrap .slide_inner ul').width(),
					'height':this.navigationHeight
				});

				this.navigation.css({
					'width':jQuery('.slide_wrap .slide_inner ul').width(),
					'height':this.navigationHeight
				});

			}

			this.nCurrentWidth =this.elem.width();

			this.bIsIphone=this.isIthing;

		},

		setAnimation:function(currentSlide) {
			var _this=this;

			this.navigation.each(function(idx) {
				if(jQuery(this).css('visibility')=='visible'){
					this.currentSlide=idx;
				}
			});

			if(this.currentSlide==currentSlide) return;

			if(this.currentSlide < currentSlide) {
				//prev rolling
				//this.rolling(jQuery('.slide_wrap .slide_inner ul').width()*-1,jQuery('.slide_wrap .slide_inner ul').width(),currentSlide);
			} else {
				//next rolling
				//this.rolling(jQuery('.slide_wrap .slide_inner ul').width(),jQuery('.slide_wrap .slide_inner ul').width()*-1,currentSlide);
			}

		},
		rolling:function(prevLeft,nextLeft,currentSlide,paging,flag){

			var _this=this;
			if($.config.currentSlide==currentSlide&&paging=='paging') return;
			
			if($.config.currentSlide>currentSlide) {
				prevLeft=prevLeft*-1;
				nextLeft=nextLeft*-1;
			}
			
			if(this.animationStatus==true){
				this.animationStatus=false;
				this.navigation.eq(this.currentSlide)
					.stop(true,true).animate({'left':prevLeft+'px'},this.animationTime,function(){});

				if(typeof currentSlide != 'undefined') {
						this.currentSlide=currentSlide;
				} else {
					if(paging!='touch') {
						if(prevLeft>0) {
							if(this.currentSlide<1) {
								this.currentSlide = this.navigation.size()-1;
							} else {
								this.currentSlide = this.currentSlide -1;
							}
						} else {
							this.currentSlide = this.currentSlide +1;
							if(this.navigation.size()==this.currentSlide) {
								this.currentSlide=0;
							}
						}
					} else {
						if(prevLeft<0) {
							if(this.currentSlide<1) {
								this.currentSlide = this.navigation.size()-1;
							} else {
								this.currentSlide = this.currentSlide -1;
							}
						} else {
							this.currentSlide = this.currentSlide +1;
							if(this.navigation.size()==this.currentSlide) {
								this.currentSlide=0;
							}
						}
					}

				}
				// paging --
				this.currentPage(this.currentSlide);
					
				this.navigation.eq(this.currentSlide)
					.css({'left':nextLeft+'px'})
					.stop(true,true)
					.css({'visibility':'visible'}).animate({'left':0},this.animationTime,function() {
						_this.animationStatus=true;
					});
			};

			$.config.currentSlide=this.currentSlide;

			/* �ㅻ뱶 異붽� �댁슜 */
			jQuery('.slide_nav .on').removeClass('on').parents('.slide_nav').find('li').eq(this.currentSlide).addClass('on');


		},

		movePrev:function() {

			var _this = this;
			if(this.bIsIphone) {
				setTimeout(function() {
					_this.setEachPos([0,100,-100]);
					_this.elem.find('li').eq(2).insertBefore(_this.elem.find('li').eq(0));
				},200);
			} else {
				this.setEachPos([0,100,-100]);
				this.elem.find('li').eq(2).insertBefore(_this.elem.find('li').eq(0));
			}
		},

		moveNext:function() {

			var _this = this;

			if(this.bIsIphone) {
				setTimeout(function() {
					_this.setEachPos([100,-100,0]);
					_this.elem.find('li').eq(0).insertAfter(_this.elem.find('li').eq(2));
				},200);

			} else {
				this.setEachPos([100,-100,0]);
				this.elem.find('li').eq(0).insertAfter(_this.elem.find('li').eq(2));
			}

		},

		currentPage:function(currentSlide){

				jQuery(this.paging).find('a').removeClass('on').eq(currentSlide).addClass('on');

		}
	});
	swipeEvent=swipeUI.extend({

		init: function(settings, options, elem) {

			// Call the inherited version of swipeCommon and swipeUI;
			this._super(settings, options, elem);
			this.options = jQuery.extend({}, settings, options);
			var _this=this;

			// addEvent orientation or resize
			(typeof window.orientationchange != 'undefined') ? jQuery(window).bind('orientation', function() {
				_this.resizeEvent();
			})
			: jQuery(window).bind('resize', function() {
				_this.resizeEvent();
			});
			// web Event --

			jQuery(this.options.paging+' a').bind('click',function() {
				_this.setAnimation(jQuery(this).index());
			})
			
			elem.bind('mouseenter',function() {
				clearInterval(_this.timer);
				_this.bautoRolling=false;
				_this.bAutoRolling();
			}).bind('mouseout',function() {
				clearInterval(_this.timer);
				_this.bautoRolling=true;
				_this.bAutoRolling();
				
			});


			jQuery('.slide_nav').bind('mouseover',function() {
				
				clearInterval(_this.timer);
				_this.bautoRolling=false;
				_this.bAutoRolling();
			}).bind('mouseout',function() {
				
					clearInterval(_this.timer);
					_this.bautoRolling=true;
					_this.bAutoRolling();
			});


			$('.button-prev').click(function() {
				//prev rolling
				_this.rolling(jQuery('.slide_wrap .slide_inner ul').width(),jQuery('.slide_wrap .slide_inner ul').width()*-1,this.currentSlide);
			});

			$('.button-next').click(function() {
				//next rolling
				_this.rolling(jQuery('.slide_wrap .slide_inner ul').width()*-1,jQuery('.slide_wrap .slide_inner ul').width(),this.currentSlide);
			});

			jQuery('.slide_nav li').on('click',function(e) {
				jQuery('.slide_nav li.on').removeClass('on');
				jQuery(this).addClass('on');
				_this.rolling(jQuery('.slide_wrap .slide_inner ul').width()*-1,jQuery('.slide_wrap .slide_inner ul').width(),jQuery(this).index(),'paging');
				e.stopImmediatePropagation();
			});

		}

	});
	// Start a plugin

	jQuery.fn.slide = function(options) {
		// Don't act on absent elements -via Paul Irish's advice
		if ( this.length ) {

			return this.each(function(){
				// Create a new nn object via the Prototypal Object create
				var settings = $.extend({
					paging:'div.paging-navi',
					currentPaging:0,
					pagingActive:'on',
					rollingTime:2000,
					animationTime:400,
					autoRolling:true
				},options);
				var myNn = new swipeEvent(settings,options,jQuery(this));
				$.config.myNn.push(myNn);
			});
		}
	};
})(jQuery);