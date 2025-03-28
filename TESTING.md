**Testing**

```bash
curl --location 'http://localhost:8080/api/v1/sso/audit' --header 'Content-Type: application/json' --data '{
  "applicationId": "horikita-suzune-1",
  "ssoAuditConfig": {
    "protocol": "SAML",
    "encryptionAlgorithm": "SHA256",
    "signingAlgorithm": "SHA256",
    "assertionEncryptionEnabled": false,
    "bypassSSOAllowed": true
  }
}';echo;

{"applicationId":"horikita-suzune-1","riskScore":14.5,"auditMessages":["Allow only approved encryption algorithms - SHA-256, SHA-384, SHA-512","Allow only approved signing algorithms - SHA-256, SHA-384, SHA-512","Assertion encryption must be set","Bypass SSO Must Not Be Allowed"],"auditDate":"2025-03-28T22:07:04.948794787Z"}

```