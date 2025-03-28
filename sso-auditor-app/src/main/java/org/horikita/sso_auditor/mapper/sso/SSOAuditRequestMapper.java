package org.horikita.sso_auditor.mapper.sso;

import org.horikita.sso_auditor.model.sso.SSOConfig;
import org.horikita.sso_auditor_api.model.SSOAuditRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface SSOAuditRequestMapper {
    @Mapping(source = "applicationId", target = "applicationId")
    @Mapping(source = "ssoAuditConfig.protocol", target = "protocol")
    @Mapping(source = "ssoAuditConfig.encryptionAlgorithm", target = "encryptionAlgorithm")
    @Mapping(source = "ssoAuditConfig.signingAlgorithm", target = "signingAlgorithm")
    @Mapping(source = "ssoAuditConfig.assertionEncryptionEnabled", target = "assertionEncryptionEnabled")
    @Mapping(source = "ssoAuditConfig.bypassSSOAllowed", target = "bypassSSOAllowed")
    SSOConfig apply(SSOAuditRequest request);
}