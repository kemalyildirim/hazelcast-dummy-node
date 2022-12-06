package dev.kemalyi.config;

import com.hazelcast.config.Config;

public interface HazelcastConfigurator {
    Config getConfig();
}
