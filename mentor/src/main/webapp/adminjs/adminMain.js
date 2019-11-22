$(function(){
    const $body = $('body'); // 바디
    const $menu_toggle = $('#menu_toggle'); // 상단 삼지창 bar
    const $sidebar_menu = $('#sidebar-menu'); // 왼쪽 메뉴 토글전체
    const $sidebar_footer = $('.sidebar-footer'); // 왼쪽 메뉴 아래 숨기기 기능
    const $left_col = $('.left_col'); // 왼쪽 메뉴 전체
    const $right_col = $('.right_col'); // 중앙 info
    const $nav_menu = $('.nav_menu'); // 상단 메뉴
    const $footer = $('footer'); // 맨 아래

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
    
    $('.x_panel').removeAttr('style');

}); // function
