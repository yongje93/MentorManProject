
//var URL = window.location,  	
//	 $body = $('body'), // 바디
//     $menu_toggle = $('#menu_toggle'), // 상단 삼지창 bar
//     $sidebar_menu = $('#sidebar-menu'), // 왼쪽 메뉴 토글전체
//     $sidebar_footer = $('.sidebar-footer'), // 왼쪽 메뉴 아래 숨기기 기능
//     $left_col = $('.left_col'), // 왼쪽 메뉴 전체
//     $right_col = $('.right_col'), // 중앙 info
//     $nav_menu = $('.nav_menu'), // 상단 메뉴
//     $footer = $('footer'); // 맨 아래
//const $body = $('body'); // 바디
//	const $menu_toggle = $('#menu_toggle');// 상단 삼지창 bar
//	const $sidebar_menu = $('#sidebar-menu'); // 왼쪽 메뉴 토글전체
//	const $sidebar_footer = $('.sidebar-footer'); // 왼쪽 메뉴 아래 숨기기 기능
//	const $left_col = $('.left_col'); // 왼쪽 메뉴 전체
//	const $right_col = $('.right_col'); // 중앙 info
//	const $nav_menu = $('.nav_menu'); // 상단 메뉴
//	const $footer = $('footer'); // 맨 아래
$(function(){
	var URL = window.location,  	
	$body = $('body'), // 바디
    $menu_toggle = $('#menu_toggle'), // 상단 삼지창 bar
    $sidebar_menu = $('#sidebar-menu'), // 왼쪽 메뉴 토글전체
    $sidebar_footer = $('.sidebar-footer'), // 왼쪽 메뉴 아래 숨기기 기능
    $left_col = $('.left_col'), // 왼쪽 메뉴 전체
    $right_col = $('.right_col'), // 중앙 info
    $nav_menu = $('.nav_menu'), // 상단 메뉴
    $footer = $('footer'); // 맨 아래
	
	$('.x_panel').removeAttr('style');
	
    //  this is same kind of easy fix, maybe we can improve this
    var setContentHeight = function(){
        // reset height
      $right_col.css('min-height',$(window).height());
      
      var bodyHeight = $body.height();
      var leftColHeight = $left_col.eq(1).height() + $sidebar_footer.height();
      var contentHeigt = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;
      //nomalize content
      contentHeigt -= $nav_menu.height() + $footer.height();

      $right_col.css('min-height',$(window).height());
    }; // 창크기 얻는 메소드

    //  왼쪽메뉴 생겼다 없어졌다
    $sidebar_menu.find('a').on('click', function(ev) {
        //사이드바 메뉴 a태그 부모 li
        var $li = $(this).parent();

        if ($li.is('.active')) {
            $li.removeClass('active');
            $('ul:first', $li).slideUp(function() {
                setContentHeight();
            });
        } else {
            // prevent closing menu if we are on child menu
            if (!$li.parent().is('.child_menu')) {
                $sidebar_menu.find('li').removeClass('active');
                $sidebar_menu.find('li ul').slideUp();
            }
            
            $li.addClass('active');
            $('ul:first', $li).slideDown(function() {
                setContentHeight();
            });
        }
    });
    
    // small or large menu
    $menu_toggle.on('click',function(){
    	// 바디에 nav-md라는 클래스가있다면
    	if($body.hasClass('nav-md')){
    		//nav-md 클래스를 지우고 nav-sm클래스를 만들어라
    		$body.removeClass('nav-md').addClass('nav-sm');
    		$left_col.removeClass('scroll-view').removeAttr('style');
    		
    		if($sidebar_menu.find('li').hasClass('active')){
    			$sidebar_menu.find('li.active').addClass('active-sm').removeClass('active');
    		}
    	} else{
    		$body.removeClass('nav-sm').addClass('nav-md');

            if ($sidebar_menu.find('li').hasClass('active-sm')) {
                $sidebar_menu.find('li.active-sm').addClass('active').removeClass('active-sm');
            }
    	}
    	setContentHeight();
    });
    
 // check active menu
    $sidebar_menu.find('a[href="' + URL + '"]').parent('li').addClass('current-page');

    $sidebar_menu.find('a').filter(function () {
        return this.href == URL;
    }).parent('li').addClass('current-page').parents('ul').slideDown(function() {
        setContentHeight();
    }).parent().addClass('active');

    // recompute content when resizing
    $(window).smartresize(function(){  
        setContentHeight();
    });

}); // function
//Panel toolbox
$(function () {
    $('.collapse-link').on('click', function() {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');
        
        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function(){
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200); 
            $BOX_PANEL.css('height', 'auto');  
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
});
/**
 * Resize function without multiple trigger
 * 
 * Usage:
 * $(window).smartresize(function(){  
 *     // code here
 * });
 */
(function($,sr){
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
      var timeout;

        return function debounced () {
            var obj = this, args = arguments;
            function delayed () {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null; 
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100); 
        };
    };

    // smartresize 
    jQuery.fn[sr] = function(fn){  return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); };

})(jQuery,'smartresize');




