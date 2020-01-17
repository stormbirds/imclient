package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.listener.IMClientInterface;
import cn.stormbirds.stormim.imclient.listener.IMConnectStatusListener;
import cn.stormbirds.stormim.imclient.listener.IMEventListener;
import cn.stormbirds.stormim.imclient.netty.NettyTcpClient;
import cn.stormbirds.stormim.imclient.protobuf.MessageProtobuf;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;

import java.util.UUID;
import java.util.Vector;

/**
 * <p>
 * cn.stormbirds.stormim.imserver.protobuf
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 11:53
 */
public class IMClientBootstrap {
    private static final String SERVER_HOST = "wifi.stormbirds.cn";

    public static void main(String[] args) {
        IMClientBootstrap.getInstance().init(UUID.randomUUID().toString().replace("-",""),"","wifi.stormbirds.cn:8855",0);
    }
    private static final String TAG = IMClientBootstrap.class.getSimpleName();

    private static final IMClientBootstrap INSTANCE = new IMClientBootstrap();
    private IMClientInterface imsClient;
    private boolean isActive;
    private static String SERVERURLLIST = "";

    private IMClientBootstrap() {

    }

    public static IMClientBootstrap getInstance() {
        return INSTANCE;
    }

    public synchronized void init(String userId, String token, String hosts, int appStatus) {
        if (!isActive()) {

            if (StringUtil.isNullOrEmpty(hosts)) {
//                Log.e(TAG, "初始化启动器错误 IMClientBootstrap，服务器地址不能为null");
                return;
            }

            SERVERURLLIST=hosts;

            isActive = true;
//            Log.i(TAG, "初始化启动器 IMClientBootstrap 成功, servers=" + hosts);
            if (null != imsClient) {
                imsClient.close();
            }
            imsClient = NettyTcpClient.getInstance();
            updateAppStatus(appStatus);
//            imsClient.login(userId,token);
            imsClient.init(hosts, new IMEventListener(userId,token), new IMConnectStatusListener());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(MessageProtobuf.Msg msg) {
        if (isActive) {
            imsClient.sendMsg(msg);
        }
    }

    private Vector<String> convertHosts(String hosts) {
        if (hosts != null && hosts.length() > 0) {
            JSONArray hostArray = JSONArray.parseArray(hosts);
            if (null != hostArray && hostArray.size() > 0) {
                Vector<String> serverUrlList = new Vector<String>();
                JSONObject host;
                for (int i = 0; i < hostArray.size(); i++) {
                    host = JSON.parseObject(hostArray.get(i).toString());
                    serverUrlList.add(host.getString("host") + " "
                            + host.getInteger("port"));
                }
                return serverUrlList;
            }
        }
        return null;
    }

    public void updateAppStatus(int appStatus) {
        if (imsClient == null) {
            return;
        }

        imsClient.setAppStatus(appStatus);
    }

    public void login(String userId, String token){
        if(imsClient!=null){
//            imsClient.login(userId,token);
        }else
            init(userId,token,SERVERURLLIST,0);
    }

}
