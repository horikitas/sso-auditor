package org.horikita.sso_auditor.model.sso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SSOConfig {

    String applicationId;

    String protocol;
    String encryptionAlgorithm;

    String signingAlgorithm;
    boolean assertionEncryptionEnabled;

    boolean bypassSSOAllowed;
}
