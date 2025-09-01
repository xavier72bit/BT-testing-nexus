package dev.xavier72bit.bttn.controller;

import dev.xavier72bit.bttn.entity.Config;
import dev.xavier72bit.bttn.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigRepository configRepository;

    @GetMapping("/all")
    public List<Config> getAllConfigs() {
        return configRepository.findAll();
    }
}
