import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;



public class Testjson_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		try {
			// create new message as a copy of the input
			MbMessage outMessage = new MbMessage(inMessage);
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);

			
			Document SoapmessageString = inMessage.getDOMDocument();//"<qq>asas</qq>";
			JSONObject soapDatainJsonObject;
			
			SoapmessageString.getDocumentElement().normalize();
			
			NodeList nList = SoapmessageString.getElementsByTagName("processGroupLoanApplicationRequest");
			
			//NodeList soaphead = doc.getElementsByTagName("soaphead");
			StringWriter sw = new StringWriter();
			Transformer serializer = TransformerFactory.newInstance().newTransformer();
			serializer.transform(new DOMSource(nList.item(0)), new StreamResult(sw));
			String result = sw.toString();
			
			
			try {
			soapDatainJsonObject = XML.toJSONObject(result);
			System.out.println(soapDatainJsonObject);
			
			//SoapmessageString = SoapmessageString.crea
			
			String myMsg = soapDatainJsonObject.toString();
			MbElement outRoot = outMessage.getRootElement();
			// Create the Broker Blob Parser element
			MbElement outParser = outRoot
			.createElementAsFirstChild(MbJSON.PARSER_NAME);
			// Create the BLOB element in the Blob parser domain with the
			// required text
			MbElement outBody = outParser.createElementAsFirstChild(//(arg0, arg1, arg2)  .createElementAsLastChild(
			MbElement.TYPE_NAME_VALUE, "jsonStr", myMsg);
			
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			// End of user code
			// ----------------------------------------------------------
		} catch (MbException e) {
			// Re-throw to allow Broker handling of MbException
			throw e;
		} catch (RuntimeException e) {
			// Re-throw to allow Broker handling of RuntimeException
			throw e;
		} catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be handled in the flow
			throw new MbUserException(this, "evaluate()", "", "", e.toString(),
					null);
		}
		// The following should only be changed
		// if not propagating message to the 'out' terminal
		out.propagate(outAssembly);

	}

}
