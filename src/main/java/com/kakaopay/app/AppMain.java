package com.kakaopay.app;


import com.kakaopay.AppSpringBootInterface;
import com.kakaopay.AppSpringBootMain;
import com.kakaopay.config.RuleConfig;
import com.kakaopay.service.RuleEngineManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppMain
    extends AppSpringBootMain
    implements AppSpringBootInterface {

    @Autowired
    private RuleConfig ruleConfig;

    @Autowired
    private RuleEngineManager ruleEngineManager;

    @Override
    public void start() {
        ruleConfig.setUpBasicRules();
        log.info("[START] start App server...");
    }

    @Override
    public void stop() {
        ruleEngineManager.stop();
        log.info("[SHUTDOWN] shutdown App server...");
    }

    public AppMain(){    }

    // AppMain - main()
    public static void main(String[] args) {
        AppSpringBootMain.main(args);
    }



}
