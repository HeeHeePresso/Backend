package org.heeheepresso.menu.domain.menu.model

enum class MenuStatus (val desc: String){
    SELLING("판매중"),
    SOLD_OUT("판매마감"),
    CLOSED("매장마감"),
    HIDE("메뉴숨김"),
}