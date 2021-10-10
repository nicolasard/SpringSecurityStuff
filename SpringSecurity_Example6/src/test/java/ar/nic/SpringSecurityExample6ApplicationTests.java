package ar.nic;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.bouncycastle.operator.OperatorCreationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ar.nic.crypto.KeyStoreService;

@SpringBootTest
class SpringSecurityExample6ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void createServerJKS() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, OperatorCreationException {
		KeyStoreService ksController = new KeyStoreService();
		KeyStore ks = ksController.openKS("password");
		ksController.generateAsymetricCredentials(ks, "server");
		ksController.saveKS(ks,"password");
	}
}
