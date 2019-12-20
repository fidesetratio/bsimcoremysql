package com.ekalife.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UncheckedIOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;




@RestController 
@RequestMapping("/api/pdf") 
public class RestGenerationPDF {
	
	
	
	public static String readFileToString(String path) {
	    ResourceLoader resourceLoader = new DefaultResourceLoader();
	    Resource resource = resourceLoader.getResource(path);
	    return asString(resource);
	}
	
	@Bean
	public String resourceString() {
	    return RestGenerationPDF.readFileToString("templates/helloworld.vm");
	}

	public static String asString(Resource resource) {
	    try (Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8")) {
	        return FileCopyUtils.copyToString(reader);
	    } catch (IOException e) {
	        throw new UncheckedIOException(e);
	    }
	}
	
	
	@GetMapping("/genpdf/{fileName}")
	HttpEntity<byte[]> createPdf(
	        @PathVariable("fileName") String fileName) throws IOException {
		 VelocityEngine ve = new VelocityEngine();
		 ve.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
		 ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		 ve.setProperty(Velocity.RESOURCE_LOADER, "string");
		 ve.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
		 ve.addProperty("string.resource.loader.repository.static", "false");
		ve.init();
		StringResourceRepository repo = (StringResourceRepository) ve.getApplicationAttribute(StringResourceLoader.REPOSITORY_NAME_DEFAULT);
		repo.putStringResource("woogie2", resourceString());
		Template t = ve.getTemplate("woogie2");
		VelocityContext context = new VelocityContext();
		context.put("name", "World");
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = generatePdf(writer.toString());
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileName.replace(" ", "_"));
	    header.setContentLength(baos.toByteArray().length);
	    return new HttpEntity<byte[]>(baos.toByteArray(), header);

	}
	
	public ByteArrayOutputStream generatePdf(String html) {
		String pdfFilePath = "";
		PdfWriter pdfWriter = null;
		Document document = new Document();
		try {
			document = new Document();
			document.addAuthor("Kiran Dhongade");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("kinns123.github.io");
			document.addTitle("HTML to PDF using itext");
			document.setPageSize(PageSize.LETTER);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.getDefaultCssResolver(true);
			xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(
					html));
			document.close();
			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}		
}
