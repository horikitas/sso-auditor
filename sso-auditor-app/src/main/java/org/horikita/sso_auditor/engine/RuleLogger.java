package org.horikita.sso_auditor.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuleLogger {
    public void log(String rule) {
        log.info("Rule Triggered: {}", rule);
    }
}
