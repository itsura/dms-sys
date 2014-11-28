package org.dms.sys.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import info.aduna.iteration.Iteration;
import info.aduna.iteration.Iterations;

import org.openrdf.model.Model;
import org.openrdf.model.Namespace;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LinkedHashModel;

/**
 * The Class StatementsUtil.
 * 
 * @author hlungov
 * 
 */
public class StatementsUtil {

	
	/**
	 * Test.
	 *
	 * @param statements the statements
	 * @param models the models
	 * @param namespaces the namespaces
	 */
	public static void test (Iteration<? extends Statement, Exception> statements, Map<String,String> models, List<Namespace> namespaces) {
		try {
			Model model = Iterations.addAll(statements, new LinkedHashModel());
			if (!models.isEmpty())
			{
				for (Entry<String, String> entry : models.entrySet())
				{
					model.setNamespace(entry.getKey(), entry.getValue());
				}
			}
			if (!namespaces.isEmpty())
			{
				for (Namespace namespace : namespaces)
				{
					model.setNamespace(namespace);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
