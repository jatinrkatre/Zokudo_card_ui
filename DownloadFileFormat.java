package com.ui.product.zokudo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/download/")
public class DownloadFileFormat {

	@RequestMapping(value = "{fileType}", method = RequestMethod.GET)
	public void getDownloadBulkCardLoadFile(HttpServletRequest request, HttpServletResponse res, HttpSession session,
			@PathVariable("fileType") String fileType) throws IOException {
		String contextPath = request.getRealPath("/");
		PrintWriter out = res.getWriter();
		String fileName = fileType + ".csv";
		res.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
		final File filePrivateKey = new File(new File("").getCanonicalPath() + "/src/main/resources/file_format/" + fileName);
		int i;
		FileInputStream file = new FileInputStream(filePrivateKey);
		while ((i = file.read()) != -1) {
			out.write(i);
		}
		file.close();
		out.close();
	}

}
