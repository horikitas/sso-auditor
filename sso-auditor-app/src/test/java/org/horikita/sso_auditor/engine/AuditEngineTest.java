package org.horikita.sso_auditor.engine;

import lombok.extern.slf4j.Slf4j;
import org.horikita.sso_auditor.model.sso.RiskScore;
import org.horikita.sso_auditor.model.sso.SSOConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class AuditEngineTest {

    @Autowired
    AuditEngine auditEngine;


    @Test
    public void shouldCheckProtocol() {
        SSOConfig ssoConfig = SSOConfig.builder().build();
        RiskScore riskScore = auditEngine.strictAudit("Horikita-App-1", ssoConfig);

        log.info("Risk Score: {}", riskScore.getScore());
        log.info("Risk Messages: {}", riskScore.getMessages());

        assertEquals("Horikita-App-1", riskScore.getApplicationId());
        assertEquals(10, riskScore.getScore());
        assertEquals(1, riskScore.getMessages().size());
    }

    @Test
    public void shouldCheckEncryptionAlgo() {
        SSOConfig ssoConfig = SSOConfig.builder()
                .protocol("SAML")
                .encryptionAlgorithm("SHA-1")
                .build();

        RiskScore riskScore = auditEngine.strictAudit("Horikita-App-2", ssoConfig);

        log.info("Risk Score: {}", riskScore.getScore());
        log.info("Risk Messages: {}", riskScore.getMessages());

        assertEquals("Horikita-App-2", riskScore.getApplicationId());
        assertEquals(9.0, riskScore.getScore());
        assertEquals(3, riskScore.getMessages().size());
    }

    @Test
    public void shouldCheckByPass() {
        SSOConfig ssoConfig = SSOConfig.builder()
                .protocol("SAML")
                .encryptionAlgorithm("SHA-256")
                .bypassSSOAllowed(true)
                .build();
        RiskScore riskScore = auditEngine.strictAudit("Horikita-App-3", ssoConfig);

        log.info("Risk Score: {}", riskScore.getScore());
        log.info("Risk Messages: {}", riskScore.getMessages());

        assertEquals("Horikita-App-3", riskScore.getApplicationId());
        assertEquals(11.5, riskScore.getScore());
        assertEquals(3, riskScore.getMessages().size());
    }


}