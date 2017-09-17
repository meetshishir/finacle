/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.adobe.poc.finacle.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */

@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes=" + "sling/servlet/default",
		"sling.servlet.paths=" + "/bin/account" })
public class AccountInfoServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		final Resource resource = req.getResource();
		resp.setContentType("text/plain");
		// TODO populate Data based on API call
		String jsonData="{}";
		try {
			jsonData = getAccountInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Return the JSON formatted data
		resp.getWriter().write(jsonData);

		/*
		 * resp.getWriter().write( "Title = " +
		 * resource.adaptTo(ValueMap.class).get("jcr:title"));
		 */
	}

	@SuppressWarnings("unchecked")
	private static String getAccountInfo() throws JSONException {
		JSONObject obj = new JSONObject();
		//obj.append(key, value)

		obj.append("netWorth", 5009339);
		obj.append("assets", 200012);

		obj.append("liabilities", 33232);
		obj.append("creditAvailability", 21313);

		// Get the JSON formatted data
		String jsonData = obj.toString();
		return jsonData;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getAccountInfo());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
