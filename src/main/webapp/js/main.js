//下拉框
(function(){
    var num = 0;

    $('[data-toggle=arrowdown]').hover(function(){
        var _id = $(this).attr('id');
        num = _id.substring(5, _id.length);
        $(this).find('span')
            .removeClass('run-down')
            .addClass('run-up');
        $('#nav-box'+num).slideDown(100);
    }, function(){
        $(this).find('span')
            .removeClass('run-up')
            .addClass('run-down');
        $('#nav-box'+num).hide();
    });
    $('[data-toggle=hidden-box]').hover(function(){
        var _id =  $(this).attr('id');
        num = _id.substring(7, _id.length);
        $('#arrow'+num).addClass('nav-hover')
            .find('span').removeClass('run-down')
            .addClass('run-up');
        $(this).show();
    }, function(){
        $('#arrow'+num).removeClass('nav-hover')
            .find('span').removeClass('run-up')
            .addClass('run-down');
        setTimeout(function(){
            $('#arrow'+num).find('span').removeClass('run-down');
        }, 500);
        $(this).slideUp(100);
    });
})(jQuery);




//search-box
(function(){
    $('.search-box').hover(function(){
        $(this).find('span')
            .addClass('down-icon-circle').css({
                background: '#f50 url(img/arrow-white.png) no-repeat center'
            });
    }, function(){
        $(this).find('span')
            .removeClass('down-icon-circle').css({
                background: ''
            });
    });
})(jQuery);

//滚动出现固定导航
(function(){
    $(window).scroll(function(){
        var scTop = $(window).scrollTop(),
            $scS = $('.scroll-search'),
            $frS = $('.fix-right-sub'),
            rW;
        rW = ($(window).width() - 1190)/2;

        scTop >= 200 ? $scS.slideDown(200) : $scS.slideUp(200);
        if(scTop >= 2700 && scTop < 4400){
            $frS.css({position: 'fixed', top: '-541px', right: rW, marginTop: ''});
        }
        else if(scTop >= 4400){
            $frS.css({position: '', marginTop: 1728});
        }
        else{
            $frS.css({position: ''});
        }
    });
})(jQuery);

//sidebar
(function(){
    var scTop = 0,
        beginH = 138,
        wW = $(window).width(),
        classN,
        num;
    $(window).scroll(function(){
        scTop = $(window).scrollTop();
        beginH = 138;
        switch (scTop){
            case 600: beginH -= 45;break;
            case 500: beginH -= 50;break;
            case 400: beginH -= 55;break;
            case 300: beginH -= 60;break;
            case 200: beginH -= 65;break;
            default : beginH = 138;break;
        }
    });
    $('.side-li > li').hover(function(){
        $(this).find('h3').css({border: 'none'})
            .end().find('span').css({color: "#f40"});
        classN = $(this).attr('class');
        num = classN.substring(2, classN.length);

        switch (scTop){
            case 0: if(num > 14){ beginH += 120}else if(num >= 12){beginH += 41}; break;
            case 100: if(num == 1){beginH -= 27}else if(num == 16){beginH += 7}; break;
            case 200: num < 5 ? beginH -= 60 : beginH -= 30; break;
            case 300: num < 8 ? beginH -= 60 : beginH -= 40; break;
            case 400: num <= 11 ? beginH -= 50 : beginH += 10; break;
            case 500: num < 14 ? beginH -= 50 : ''; break;
            case 600: num <= 16 ? beginH -= 50 : ''; break;
            default : beginH = 138;break;
        }

        $('.hiden-box').show()
            .css({
                left: ((wW - 1190)/2 + 149),
                top:  beginH
            }).animate({width: '729px'}, 300);
        $('.hiden-box > li').hide();
        $('#hiden-'+num).fadeIn(200);
        beginH = 138;
    }, function(){
        $(this).find('h3').css({border: ''})
            .end().find('span').css({color: ""});
        $('.hiden-box').hide().css({width: '0'});
    });
    $('.hiden-box').hover(function(){
        $('.s_'+num).css({
            border: '1px solid #f40',
            borderRight: '1px solid #fff'
        }).find('h3').css({border: 'none'})
            .end().find('span').css({color: "#f40"});
        $(this).show().css({width: '729px'});
    }, function(){
        $('.s_'+num).css({
            border: '',
            borderRight: ''
        }).find('h3').css({border: ''})
            .end().find('span').css({color: ""});
        $(this).animate({
            width: 0
        }, 200).hide(200);
    });

})();


//关二维码
(function(){
    $('.close-code').click(function(){
        $('.two-code').fadeOut(200);
    });
})();

//service
(function(){
    $('.service-cell').hover(function(){
        $(this).children('.service-i').find('img').animate({
            marginTop: '-10px'
        }, 100);
        $(this).children('.service-i').find('img').animate({
            marginTop: '6px'
        }, 100);
    }, function(){
        return false;
    });
})();
//product-box
(function(){
    $('.product-box').hover(function(){
        $(this).css({
            zIndex: '3000'
        });
    }, function(){
        $(this).css({
            zIndex: ''
        });
    });
})();


//product-show
(function(){
    $('.product-box').hover(function(){
        $(this).find('.share-weitao').fadeIn(200);
    }, function(){
        $(this).find('.share-weitao').fadeOut(200);
    });
})();

//dynamic
(function(){
    $('.dynamic').hover(function(){
        $(this).find('.follow').fadeIn(200);
    }, function(){
        $(this).find('.follow').fadeOut(200);
    })
})();

//more-btn
(function(){
    var flag = 1;

    $('.more-btn').click(function(){
        if(flag){
            $(this).css({
                background: '#f40 url(img/up.png) no-repeat 90% center'
            });
            $('.sub-nav').css({
                height: '145px'
            });
            flag = 0;
        }else{
            $('.sub-nav').css({
                height: ''
            });
            $(this).css({
                background: ''
            });
            flag = 1;
        }
    });
})();


/**
 * backToTop1
 */
$(document).ready(function(){
    $(window).scroll( function() {               //滚动时触发
        var top = $(document).scrollTop(),       //获取滚动条到顶部的垂直高度
            height = $(window).height();         //获得可视浏览器的高度
        if(top > 100){
            $("#backToTop1").show(200, function(){
                $("#backToTop1").css({
                    top: height + top - 100
                });
            });
        }
    });
    /*点击回到顶部*/
    $('#backToTop-up').click(function(){
        $('html, body').animate({
            scrollTop: 0
        }, 500);
    });
    /*点击到底部*/
    $('#backToTop-down').click(function(){
        $('html, body').animate({
            scrollTop: $(document).height()
        }, 500);
    });
});


//my-card
(function(){
    $('.arrow-right').click(function(){
        $('.cards-info ul').animate({
            marginLeft: '-205px'
        }, 500);
    });
    $('.arrow-left').click(function(){
        $('.cards-info ul').animate({
            marginLeft: '0'
        }, 500);
    });
})();

//msg-close
(function(){
    $('.close-msg').click(function(){
        $('.read-info').css({
            textAlign: 'center'
        }).html('还没有新的消息...');
    });
})();

//tab-nav
(function(){
    $('.li-nav').hover(function(){
        var _id, num;
        _id = $(this).attr('id');
        num = _id.substring(3, _id.length);
        $('.li-nav').removeClass('li-nav-hover');
        $('.hiddenBox').hide();
        $('#box-'+num).fadeIn(200);
    }, function(){
        $(this).addClass('li-nav-hover');
    });
})();

//service-cell
(function(){
    $('.service-cell[id]').hover(function(){
        var _id, num;

        _id = $(this).attr('id');
        num = _id.substring(8, _id.length);
        $('.service-box').css({
            bottom: -50
        }).hide();
        $('#service-box-'+num).show().animate({
            bottom: '0'
        }, 200);
        $('.service-cell[id]').css({
            borderBottomColor: ''
        });
        $(this).css({
            borderBottomColor: '#fff'
        });
    }, function(){

    });
})();

//close-service
(function(){
    $('.service-head > .fa-times').click(function(){
          $('.service-box').fadeOut(200);
    });
})();

//hidden-title
(function(){
    $('.hidden-title > a').hover(function(){
        $(this).next().css({
            color: '#f40'
        });
    }, function(){
        $(this).next().css({
            color: ''
        });
    });
})();









