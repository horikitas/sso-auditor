package org.horikita.sso_auditor.engine;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horikita.sso_auditor.mapper.AuditResultMapper;
import org.horikita.sso_auditor.mapper.sso.SSOAuditRequestMapper;
import org.horikita.sso_auditor.model.sso.RiskScore;
import org.horikita.sso_auditor.model.sso.SSOConfig;
import org.horikita.sso_auditor_api.model.AuditResult;
import org.horikita.sso_auditor_api.model.SSOAuditRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditEngine {

    private final @NonNull KieContainer kieContainer;
    private final @NonNull RuleLogger logger;

    private final @NonNull SSOAuditRequestMapper requestMapper;
    private final @NonNull AuditResultMapper resultMapper;

    private final @NonNull Clock clock;

    private static final String SSO_KIE_SESSION = "ssoKSession";

    public AuditResult audit(final @NonNull SSOAuditRequest auditRequest) {
        SSOConfig ssoConfig = requestMapper.apply(auditRequest);
        RiskScore riskScore = strictAudit(auditRequest.getApplicationId(), ssoConfig);
        return resultMapper.apply(riskScore, clock);
    }


    protected RiskScore strictAudit(final @NonNull String applicationId,
                            final @NonNull SSOConfig ssoConfig) {
        log.info("Audit Engine initialized");
        log.info("Kie bases: {}", kieContainer.getKieBaseNames());
        log.info("Kie sessions: {}", kieContainer.getKieSessionNamesInKieBase("ssoKBase"));

        log.info("Auditing SSO Configuration for application {}", applicationId);
        final RiskScore riskScore = new RiskScore(applicationId);
        final KieSession kieSession = kieContainer.newKieSession(SSO_KIE_SESSION);
        kieSession.setGlobal("logger", logger);
        kieSession.setGlobal("result", riskScore);

        kieSession.insert(ssoConfig);
        kieSession.fireAllRules();
        kieSession.dispose();

        return riskScore;
    }



}
