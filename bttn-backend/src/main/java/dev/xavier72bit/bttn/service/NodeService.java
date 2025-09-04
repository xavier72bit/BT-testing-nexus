package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    /**
     * 存在时，标记为在线，不存在时，新建
     */
    @Transactional
    public Node upsertNode(Node node) {
        Node existedNode = nodeRepository.findByApiAddress(node.getApiAddress());

        if (existedNode != null) {
            existedNode.setIsOnline(true);
            return nodeRepository.save(existedNode);
        } else {
            node.setIsOnline(true);
            return nodeRepository.save(node);
        }
    }
}
