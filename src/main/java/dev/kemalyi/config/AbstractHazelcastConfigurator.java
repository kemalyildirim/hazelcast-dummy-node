package dev.kemalyi.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import dev.kemalyi.HazelcastConstants;

public abstract class AbstractHazelcastConfigurator implements HazelcastConfigurator {
    private Config config = new Config();
    private int nodePort = Integer.parseInt(System.getProperty(HazelcastConstants.HAZELCAST_PORT_KEY,
            HazelcastConstants.DEFAULT_PORT));
    private boolean init;

    public Config getConfig() {
        if (!init)
            initConfig();
        return config;
    }
    protected void initConfig() {
        config.setProperty("hazelcast.http.healthcheck.enabled", "true");
        config.setProperty("hazelcast.phone.home.enabled", "false");
        config.getNetworkConfig().setPort(nodePort);

        JoinConfig joinConfig =  config.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(true);
        joinConfig.getMulticastConfig().setEnabled(false);
        config.setInstanceName(HazelcastConstants.CLUSTER_NAME);
        init = true;
    }
}
