package org.ijueebola.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ijueebola.model.Mail;
import org.ijueebola.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublicController {
	
	@Autowired
	private FileUtil fileService;
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${mail.from}")
	private String FROM;

	@RequestMapping("/")
	public String index(Model model) {
		
		return "index";
	}

	@RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
	public void getFile(@PathVariable("fileId") Integer fileId,
			HttpServletResponse response) {
		try {
			fileService.getFile(fileId, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/mail/send", method = RequestMethod.POST)
	public String sendEmail(@Valid Mail email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(FROM);
		mailMessage.setReplyTo(email.getEmail());
		mailMessage.setFrom(FROM);
		mailMessage.setSubject("Super Cloud Website");
		mailMessage.setText(email.getMessage());
		javaMailSender.send(mailMessage);
		return "index";
	}
}
