package dev.kemalyi;

import dev.kemalyi.config.DefaultHazelcastConfigurator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClusterController clusterController = new ClusterController(new DefaultHazelcastConfigurator());
        clusterController.startNode();
        log.info("[main] Node started.");
    }
}