$(function(){
    var $body = $('body'); // 바디
    var $menu_toggle = $('#menu_toggle'); // 상단 삼지창 bar
    var $sidebar_menu = $('#sidebar-menu'); // 왼쪽 메뉴 토글전체
    var $sidebar_footer = $('.sidebar-footer'); // 왼쪽 메뉴 아래 숨기기 기능
    var $left_col = $('.left_col'); // 왼쪽 메뉴 전체
    var $right_col = $('.right_col'); // 중앙 info
    var $nav_menu = $('.nav_menu'); // 상단 메뉴
    var $footer = $('footer'); // 맨 아래

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



}); // function
