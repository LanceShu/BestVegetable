package com.example.lanceshu.bestvegetable.DataBean

/**
 * Created by Lance on 2017/12/13.
 */
class GuestBean private constructor(){

    /*用户id*/
    var gid : String = ""
    /*用户密码*/
    var gpwd : String = ""
    /*用户账户*/
    var gname : String = ""
    /*用户地址*/
    var gaddr : String = ""

    private object Inner{
        var instance : GuestBean? = null
    }

    companion object {
        fun getInstance() : GuestBean {
            if(Inner.instance == null){
                Inner.instance = GuestBean()
            }
            return Inner.instance!!
        }

        fun clearInstance(){
            Inner.instance = null
        }
    }

}