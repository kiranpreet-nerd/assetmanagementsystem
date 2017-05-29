package com.nerdapplabs.registration.listener;

import java.util.Locale;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.nerdapplabs.model.User;
import com.nerdapplabs.registerEvent.*;
import com.nerdapplabs.service.UserServiceImplement;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{
	
	    @Autowired
	    private UserServiceImplement userService;
	    
	    @Value("${server.port}")
	    int port;

	    @Autowired
	    private MessageSource messages;

	    @Autowired
	    private JavaMailSender mailSender;

	    @Autowired
	    private Environment env;
	    
	   ServerProperties serverproperties;

		@Override
		public void onApplicationEvent(OnRegistrationCompleteEvent event) {
			try {
				this.confirmRegistration(event);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	    
		private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException {
	       
			final User user = event.getUser();
			final String token = UUID.randomUUID().toString();
			userService.createVerificationToken(user,token);
			
			String recipientAddress = user.getEmail();
			String subject = "Registration Confirmation";
			String confirmationUrl = event.getAppUrl() + "/registrationconfirm?token=" + token;
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper ;
			helper = new MimeMessageHelper(message, true);
			helper.setTo(recipientAddress);
			helper.setSubject(subject);
			helper.setText("hello", "<a href = http://localhost:"+port+ confirmationUrl + ">"+" confirmation link "+"</a>");//( "http://localhost:8090" + confirmationUrl);
			mailSender.send(message);
			
	    }
	    
	

}
