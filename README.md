
# Introduction  (WIP)
This project is a Spring Boot application that uses Drools to validate SSO configurations.
The application exposes a REST API to upload SSO configurations and get feedback on the validation results.

**TODO**
1. SAML Parser
2. Validate OIDC Configurations


**Why Did I Start Writing This**
Kind of Fun Story - I was tired of answering same set of questions every year to Audit team.
So, I thought of automating the process.
This doesn't cover all areas of Audit. I randomly picked SSO as a starting point.

## Security Policy Evaluator – Rule-Driven SSO Config Validator
**Problem**
Enterprise SSO setups are complex and error-prone. Teams often misconfigure identity flows, enforce inconsistent MFA rules, or leave gaps across environments.

**Goal**
Build a Drools-powered validation engine that:
Accepts SSO configuration objects (JSON/XML/etc.)
Applies security rules to flag missing, unsafe, or deprecated settings
Provides clear recommendations to fix them

**Tech Stack**
Java + Drools (core engine)
Spring Boot REST API to upload configs and get feedback
Optional: CLI for MVP

**Output**
Security rule violations in human-readable format
Severity levels (Info / Warning / Error)
Optional: Rule ID mapping to policy document references

###  What Exactly Is It Validating?
You're looking at security-critical aspects of how authentication and session policies are set up in enterprise SSO. Here’s a breakdown of real-world categories your engine could evaluate:

**1. Protocol Settings**
Validates the correctness and strength of protocol-level details.
samlVersion – Should be 2.0
encryptionAlgorithm – Should be SHA-256 or stronger
signingAlgorithm – Should avoid SHA-1
assertionEncryptionEnabled – Must be true

**2. Assertion & Claim Configuration**
Ensures identity assertions aren’t leaking sensitive or incorrect data.
attributeStatements – Ensure correct mapping (email, firstName, lastName, groups)
audienceRestriction – Required and must match app
inResponseTo – Should be enforced
notBefore/notOnOrAfter – Correctly scoped, not too long

**3. Authentication & MFA Policies**
Validates whether multi-factor and secure auth practices are enforced.
mfaEnabled – Must be true for external apps
allowedAuthMethods – Should restrict weak ones (e.g., password-only)
mfaType – Should include TOTP, WebAuthn, etc.
fallbackAuth – Flag if SMS fallback is allowed (less secure)

**4. Session Management**
Critical for reducing attack surface via idle sessions.
sessionTimeout – Should be under 60 minutes for most apps
idleTimeout – Set and enforced
rememberMeDuration – If enabled, must be scoped reasonably
forceReauthentication – Should be true for critical flows

**5. Misc Policy Gaps**
Catch misconfigurations or missing practices.
logoutURL missing → session might not terminate cleanly
disableSingleLogout is true → risky for federated setups
clockSkew handling missing → can break real-world SAML flows