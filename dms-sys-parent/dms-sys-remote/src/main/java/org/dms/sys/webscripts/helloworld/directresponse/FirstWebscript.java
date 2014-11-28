package org.dms.sys.webscripts.helloworld.directresponse;

import org.dms.sys.semantic.db.SemanticDBDao;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.XMLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class FirstWebscript.
 */
@Controller
public class FirstWebscript {
	
	 /** The namespace boyan. */
 	String NAMESPACE_BOYAN = "http://com.sirma.itt.boyan/";
	 
 	/** The value boyan. */
 	String VALUE_BOYAN = "Boyan";

	
	/** The semantic db service. */
	@Autowired
	private SemanticDBDao semanticDBService;
	
	/**
	 * Hello world.
	 *
	 * @param name the name
	 * @return the string
	 */
	@RequestMapping(value="/hi-direct-response", method = RequestMethod.GET)
	public @ResponseBody String helloWorld(@RequestParam(value="name", required=true, defaultValue="Guest")String name){
		
		ValueFactoryImpl valueFactory = ValueFactoryImpl.getInstance();
		
		URI boyanURI = valueFactory.createURI(NAMESPACE_BOYAN, VALUE_BOYAN);
		Literal labelLiteral = valueFactory.createLiteral("Boyan", XMLSchema.STRING);
		
		Statement createStatement = valueFactory.createStatement(boyanURI, RDF.TYPE, FOAF.PERSON);
		semanticDBService.insert(createStatement);
		
		return "";
	}	
}
