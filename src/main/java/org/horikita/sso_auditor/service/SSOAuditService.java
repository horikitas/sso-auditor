package org.horikita.sso_auditor.service;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horikita.sso_auditor.model.SSOConfig;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SSOAuditService {

    private final @NonNull KieSession kieSession;
    private final @NonNull RuleLogger logger;

    @PostConstruct
    private void init() {
        kieSession.setGlobal("logger", logger);
    }

    public void strictAudit(final @NonNull String applicationId,
                            final @NonNull SSOConfig ssoConfig) {
        log.info("Auditing SSO Configuration for application {}", applicationId);
        kieSession.insert(ssoConfig);
        kieSession.fireAllRules();
    }

    @PreDestroy
    private void destroy() {
        kieSession.dispose();
    }
}
