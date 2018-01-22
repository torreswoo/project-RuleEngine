package com.kakaopay.app;


import com.kakaopay.AppSpringBootInterface;
import com.kakaopay.AppSpringBootMain;
import com.kakaopay.config.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppMain
    extends AppSpringBootMain
    implements AppSpringBootInterface {

    @Autowired
    private RuleConfig ruleConfig;

    @Override
    public void start() {
        log.info("[START] start App server...");
        this.ruleConfig.setUpBasicRules();
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
