package org.horikita.sso_auditor.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class SSOConfig {

    private String applicationId;

    private String protocol; // "SAML", "OIDC", "OAuth"
    private String samlVersion;
    private String encryptionAlgorithm;
    private String signingAlgorithm;
    private boolean assertionEncryptionEnabled;

    private boolean mfaEnabled;
    private List<String> mfaMethodsAllowed;

    private int sessionTimeoutMinutes;
    private boolean isExternalApp;
    private boolean isCriticalApp;

    private boolean singleLogoutEnabled;
    private String logoutURL;

    private Map<String, String> attributeMappings;

    private boolean bypassSSOAllowed;

    private final Map<String, String> errors = new HashMap<>();
    private final  Map<String, String> warnings = new HashMap<>();

    public void addError(String field, String error) {
        errors.put(field, error);
    }
    public void addWarning(String field, String warning) {
        warnings.put(field, warning);
    }

}
