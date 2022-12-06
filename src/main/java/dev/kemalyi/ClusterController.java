package dev.kemalyi;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;
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
            if (LifecycleEvent.LifecycleState.SHUTTING_DOWN == lifecycleEvent.getState()) {
                log.error("[addLifecycleListenerToHazelcastInstance] State: {} for: {}", lifecycleEvent.getState(),
                        instance.getName());
            }
        });
    }
}
