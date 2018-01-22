package com.kakaopay.app;


import com.kakaopay.AppSpringBootInterface;
import com.kakaopay.AppSpringBootMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppMain
    extends AppSpringBootMain
    implements AppSpringBootInterface {

//    @Autowired
//    private IpInfoManager ipInfoManager;

    @Override
    public void start() {
//        this.ipInfoManager.updateGeolocationIpInfo();
//        this.ipInfoManager.updateBlackListIpInfo();
//        this.ipInfoManager.updateWhitelistIpInfo();
        log.info("[START] start App server...");
    }

    @Override
    public void stop() {
        log.info("[SHUTDOWN] shutdown App server...");
    }

    public AppMain(){    }

    // AppMain - main()
    public static void main(String[] args) {
        AppSpringBootMain.main(args);
    }



}
