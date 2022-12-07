package dev.kemalyi.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import dev.kemalyi.HazelcastConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHazelcastConfigurator implements HazelcastConfigurator {
    private Config config = new Config();
    private int nodePort = Integer.parseInt(System.getProperty(HazelcastConstants.HAZELCAST_PORT_KEY,
            HazelcastConstants.DEFAULT_PORT));
    private boolean init;

    protected AbstractHazelcastConfigurator() {
        config.setProperty("hazelcast.http.healthcheck.enabled", "true");
        config.setProperty("hazelcast.phone.home.enabled", "false");
        log.info("[AbstractHazelcastConfigurator] healthcheck enabled");
    }

    public Config getConfig() {
        if (!init)
            initConfig();
        return config;
    }
    protected void initConfig() {
        config.getNetworkConfig().setPort(nodePort);

        JoinConfig joinConfig =  config.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(true);
        joinConfig.getMulticastConfig().setEnabled(false);
        config.setInstanceName(HazelcastConstants.CLUSTER_NAME);
        init = true;
    }
}
