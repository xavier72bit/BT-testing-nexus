package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private VersionManager versionManager;

    /**
     * 存在时，标记为在线，不存在时，新建
     */
    @Transactional
    public Node upsertNode(Node node) {
        Version currentVersion = versionManager.getCurrentVersion();

        Node existedNode = nodeRepository.findByApiAddressAndVersion(node.getApiAddress(), currentVersion);

        if (existedNode != null) {
            existedNode.setIsOnline(true);
            return nodeRepository.save(existedNode);
        } else {
            node.setIsOnline(true);
            node.setVersion(currentVersion);
            return nodeRepository.save(node);
        }
    }
}
