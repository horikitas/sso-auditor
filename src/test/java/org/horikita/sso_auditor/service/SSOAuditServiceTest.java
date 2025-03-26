package org.horikita.sso_auditor.service;

import lombok.extern.slf4j.Slf4j;
import org.horikita.sso_auditor.model.SSOConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration-level testing for Drools rules.
 */
@SpringBootTest
@Slf4j
public class SSOAuditServiceTest {

    @Autowired
    SSOAuditService ssoAuditService;

    @Test
    public void shouldCheckProtocol() {
        // given
        SSOConfig ssoConfig = SSOConfig.builder()
                .protocol("https")
                .build();
        //when
        ssoAuditService.strictAudit("Horikita-App-1", ssoConfig);

        // then
        assertTrue(ssoConfig.getErrors().containsKey("protocol"));
    }

    @Test
    public void shouldCheckEncryptionAlgo() {
        // given
        SSOConfig ssoConfig = SSOConfig.builder()
                .protocol("SAML")
                .encryptionAlgorithm("SHA-1")
                .build();
        //when
        ssoAuditService.strictAudit("Horikita-App-2", ssoConfig);

        // then
        assertTrue(ssoConfig.getErrors().containsKey("encryptionAlgorithm"));
    }


}