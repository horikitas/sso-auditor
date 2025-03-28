package org.horikita.sso_auditor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSOAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(SSOAuditApplication.class, args);
	}

}



/*
public class SAMLMetadataLoader {

	public InputStream loadFromUrl(String metadataUrl) throws Exception {
		URL url = new URL(metadataUrl);
		return url.openStream();
	}

	// TODO: Add loadFromFile, parse XML into DOM, etc.
}  */
