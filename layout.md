```dtd
ssoauditor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/horikita/
│   │   │       ├── config/              # Spring & Drools setup
│   │   │       ├── model/               # SSOConfig, enums, types
│   │   │       ├── engine/              # RuleEngineService, RuleExecutor
│   │   │       ├── output/              # OutputDisplay, loggers
│   │   │       └── controller/          # (Optional) REST API
│   │   └── resources/
│   │       ├── rules/
│   │       │   ├── sso/
│   │       │   │   └── sso-core.drl
│   │       │   ├── oidc/
│   │       │   │   └── oidc-mfa-rules.drl
│   │       └── META-INF/
│   │           └── kmodule.xml
├── test/
│   └── java/org/horikita/
│       └── tests/
│           ├── RuleExecutionTest.java
│           ├── RuleCoverageTracker.java (optional)
├── README.md
├── pom.xml
└── .github/
    └── workflows/ci.yml   # GitHub Actions (optional)

```