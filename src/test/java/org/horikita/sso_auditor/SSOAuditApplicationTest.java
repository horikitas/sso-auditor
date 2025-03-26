package org.horikita.sso_auditor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest
class SSOAuditApplicationTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
	}

	@Test
	void kieBeansLoad() {
		log.info("Kie beans:");
		for (String name : context.getBeanDefinitionNames()) {
			if (name.contains("kie") || name.contains("drool")) {
				log.info("🔍 " + name);
			}
		}

		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieBase kieBase = kieContainer.getKieBase("ssoKBase");

		log.info("KieBases: " + kieContainer.getKieBaseNames());
		log.info("KieSessions: " + kieContainer.getKieSessionNamesInKieBase(kieContainer.getKieBaseNames().iterator().next()));

		log.info("Available sessions: " + kieBase.getKiePackages());
	}
}
