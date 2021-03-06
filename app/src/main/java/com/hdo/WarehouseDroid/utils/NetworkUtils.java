package com.hdo.WarehouseDroid.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.hdo.WarehouseDroid.bean.HouseChange;
import com.hdo.WarehouseDroid.global.URL;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * description 网络请求工具类
 * author 张建银
 * version 1.0
 * created 2017/9/6
 */

public class NetworkUtils {
    /**
     * 本类静态变量
     */
    private static NetworkUtils newtWorkUtils;
    private OkHttpClient client;
    private NetworkUtils(){
        client=new OkHttpClient.Builder().build();
    }

    /**
     * 得到本类对象
     * @return 本类对象
     */
    public static NetworkUtils getInstance(){
        if(newtWorkUtils==null){
            newtWorkUtils=new NetworkUtils();
        }
        return newtWorkUtils;
    }

    /**
     * 登录请求
     * @param account 用户名
     * @param password 密码
     * @return call模型
     */
    public Call login(String account, String password){
        RequestBody requestBody=new FormBody.Builder()
                .add("number",account)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url(URL.loginUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }
    /**
     * 获取入库单号
     * @param token
     * @return call模型
     */
    public Call applyId(String token){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .build();
        Request request=new Request.Builder()
                .url(URL.applyIdUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }
    /**
     * 获取出库单号
     * @param token
     * @return call模型
     */
    public Call applyOUtId(String token){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .build();
        Request request=new Request.Builder()
                .url(URL.outIdUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 获取入库物品列表
     * @param token
     * @return
     */
    public Call inGoodsList(String token,int page,String search){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("page",String.valueOf(page))
                .add("searchCont",search)
                .build();
        Request request=new Request.Builder()
                .url(URL.applyInListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);

    }
    /**
     * 获取出库物品列表
     * @param token
     * @return
     */
    public Call outGoodsList(String token,int page,String search,String customerId){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("customerId",customerId)
                .add("page",String.valueOf(page))
                .add("searchCont",search)
                .build();
        Request request=new Request.Builder()
                .url(URL.applyOutListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);

    }
    /**
     * 入库申请
     * @param token
     * @param change
     * @return
     */
    public Call inHouseSubmit(String token, HouseChange change,String customerId,String costTypeId){
        RequestBody requestBody=new FormBody.Builder()
                .add("goodsData", new Gson().toJson(change.getGoodsData()))
                .add("remark",change.getRemark())
                .add("number",change.getNumber())
                .add("applyInUser",change.getApplyInUser())
                .add("whereFrom",change.getWhereFrom())
                .add("token",token)
                .add("customerId",customerId)
                .add("costTypeId",costTypeId)
                .build();
        Request request=new Request.Builder()
                .url(URL.applyUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }
    /**
     * 出库申请
     * @param token
     * @param change
     * @return
     */
    public Call outHouseSubmit(String token, HouseChange change,String customerId){
        RequestBody requestBody=new FormBody.Builder()
                .add("goodsData", new Gson().toJson(change.getGoodsData()))
                .add("remark",change.getRemark())
                .add("number",change.getNumber())
                .add("applyOutUser",change.getApplyOutUser())
                .add("whereGo",change.getWhereGo())
                .add("customerId",customerId)
                .add("token",token)
                .build();
        Request request=new Request.Builder()
                .url(URL.outApplyUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 入库申请列表
     * @param token
     * @return
     */
    public Call inAppList(Context context,String token,int page,String search){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("page",String.valueOf(page))
                .add("goodsSearchWord",search)
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.inMainListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 出库申请列表
     * @param token
     * @return
     */
    public Call outAppList(Context context,String token,int page,String search){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("page",String.valueOf(page))
                .add("goodsSearchWord",search)
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.outMainListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 入库申请列表详细
     * @param token
     * @return
     */
    public Call inAppListDetail(Context context,String token,int id){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("goodsInStorageId",String.valueOf(id))
                .add("rows",String.valueOf(50))
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.inDetailListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 出库申请列表详细
     * @param token
     * @return
     */
    public Call outAppListDetail(Context context,String token,int id){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("goodsOutStorageId",String.valueOf(id))
                .add("rows",String.valueOf(50))
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.outDetailListUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 入库申请审批
     * @param token
     * @return
     */
    public Call inAppCheck(String token,int id){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("ids",String.valueOf(id))
                .build();
        Request request=new Request.Builder()
                .url(URL.inExamineUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 出库申请审批
     * @param token
     * @return
     */
    public Call outAppCheck(String token,int id){
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("ids",String.valueOf(id))
                .build();
        Request request=new Request.Builder()
                .url(URL.outExamineUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 库存主列表
     * @param token
     * @return
     */
    public Call stateMain(Context context,String token,int page,String search){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("page",String.valueOf(page))
                .add("searchCont",search)
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.allList)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 库存详细表
     * @param token
     * @return
     */
    public Call stateDetail(Context context,String token,int id){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("goodsId",String.valueOf(id))
                .add("rows",String.valueOf(50))
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.allListDetail)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 账单记录
     * @param token
     * @return
     */
    public Call getSettlement(Context context,String token,int page){
        String userType = SpUtils.getString(context,"userType","customer");
        RequestBody requestBody=new FormBody.Builder()
                .add("token",token)
                .add("page",String.valueOf(page))
                .add("customerId",userType.equals("admin")?"":SpUtils.getInt(context,"customerId",0)+"")
                .add("userType",userType)
                .build();
        Request request=new Request.Builder()
                .url(URL.settlementList)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 获取个人资料请求
     * @param context 上下文
     * @return call模型
     */
    public Call getPersonalInfo(Context context){
        RequestBody requestBody=new FormBody.Builder()
                .add("account",SpUtils.getString(context,"account",""))
                .build();
        Request request=new Request.Builder()
                .url(URL.getPersonalInfoUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 修改个人资料请求
     * @param context 上下文
     * @param newAccount 新帐号
     * @param newNickName 新昵称
     * @param newPhone 新电话
     * @param newCompany 新公司
     * @return call模型
     */
    public Call changePersonalInfo(Context context, String newAccount, String newNickName, String newPhone, String newCompany){
        RequestBody requestBody=new FormBody.Builder()
                .add("account",SpUtils.getString(context,"account",""))
                .add("newAccount",newAccount)
                .add("newNickName",newNickName)
                .add("newPhone",newPhone)
                .add("newCompany",newCompany)
                .build();
        Request request=new Request.Builder()
                .url(URL.changePersonalInfoUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 修改密码请求
     * @param context 上下文
     * @param oldPsw 旧密码
     * @param newPsw 新密码
     * @return call模型
     */
    public Call changePsw(Context context, String oldPsw, String newPsw){
        RequestBody requestBody=new FormBody.Builder()
                .add("id",SpUtils.getString(context,"number",""))
                .add("oldPsw",oldPsw)
                .add("newPsw",newPsw)
                .add("token",SpUtils.getString(context,"token",""))
                .build();
        Request request=new Request.Builder()
                .url(URL.changePswUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

    /**
     * 检查更新请求
     * @return call模型
     */
    public Call getLatestVersionCode(){
        RequestBody requestBody=new FormBody.Builder()
                .build();
        Request request=new Request.Builder()
                .url(URL.checkUpdateUrl)
                .post(requestBody)
                .build();
        return client.newCall(request);
    }

}
