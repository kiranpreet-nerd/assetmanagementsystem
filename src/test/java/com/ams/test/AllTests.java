package com.ams.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Add_UserBySuperTest.class, AssetDefaultEmail_Test.class,
		Create_New_Asset_Test.class, Forgot_Password_Test.class, Invalid_Login_Test.class,
		LoginFunctionality_Test.class, RequestAssetUITest.class, SignInTest.class,
		SignUp_ValidationTest.class, SignUpFieldEmptyTest.class, SignUpFieldsVisibleTest.class, SignUpLinkTest.class,
		SignUpPlaceHolderTest.class, Test_Logout.class, Test_SignUp.class })
public class AllTests {

}
