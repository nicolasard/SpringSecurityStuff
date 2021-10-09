package ar.nic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.bouncycastle.operator.OperatorCreationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ar.nic.crypto.KeyStoreController;

@SpringBootTest
class SpringSecurityExample6ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void createServerJKS() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, OperatorCreationException {
		KeyStoreController ksController = new KeyStoreController();
		KeyStore ks = ksController.openKS();
		ksController.generateAsymetricCredentials(ks, "test");
		ksController.saveKS(ks);
	}

}
