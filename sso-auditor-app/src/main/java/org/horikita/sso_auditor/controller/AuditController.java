package org.horikita.sso_auditor.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horikita.sso_auditor.engine.AuditEngine;
import org.horikita.sso_auditor_api.model.AuditResult;
import org.horikita.sso_auditor_api.model.SSOAuditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuditController {

     @Autowired
     private final @NonNull AuditEngine engine;

    @PostMapping(path = "/api/v1/sso/audit")
     public ResponseEntity<AuditResult> audit(@RequestBody SSOAuditRequest auditRequest) {
          log.info("Auditing SSO config for application {}", auditRequest.getApplicationId());;
          return ResponseEntity.ok(engine.audit(auditRequest));
     }
}
