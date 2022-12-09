package dev.kemalyi;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import dev.kemalyi.config.HazelcastConfigurator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClusterController {

    private HazelcastConfigurator hazelcastConfigurator;

    public ClusterController(HazelcastConfigurator hazelcastConfigurator) {
        this.hazelcastConfigurator = hazelcastConfigurator;
    }

    public void startNode() {
        HazelcastInstance instance = Hazelcast.getOrCreateHazelcastInstance(hazelcastConfigurator.getConfig());
        log.info("[startNode] Hazelcast instance is : {}", instance.getName());
        addLifecycleListenerToHazelcastInstance(instance);

    }
    private void addLifecycleListenerToHazelcastInstance(HazelcastInstance instance) {
        instance.getLifecycleService().addLifecycleListener(lifecycleEvent -> {
                log.info("[addLifecycleListenerToHazelcastInstance] State: {} for: {}", lifecycleEvent.getState(),
                        instance.getName());
        });
    }
}
