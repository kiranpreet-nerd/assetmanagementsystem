package com.nerdapplabs.test;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.AssetDefaultEmail;

public class AssetDefaultEmail_Test extends AssetDefaultEmail {

	@Test
	public void verifyEmailFieldFilledWithRegisteredEmail() throws IOException {
		Assert.assertTrue("Email field not contains default registered email",
				verifyEmailFieldContainsRegisteredEmail());
	}
}
