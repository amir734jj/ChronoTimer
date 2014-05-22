import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * The ImportExport class
 * 
 * Note that this class handles the import or export of XML code
 *
 */
public class ImportExport {

	public static String SYSTEM_XML = Main.TempFolder + "\\System.XML";

	/**
	 * ExportSystemToXML  exports system state to XML file
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void ExportSystemToXML() {
		Document doc = new Document();
		Element theRoot = new Element("System");
		theRoot.setAttribute("NumberOfRaces",
				Integer.toString(RaceCollection.array.size()));
		theRoot.setAttribute("CurrentRace", Integer
				.toString(getCurrentRaceORjustFinished.get().getRaceID()));
		doc.setRootElement(theRoot);
		Iterator<Race> it = RaceCollection.array.iterator();
		while (it.hasNext())
			Race.XMLExport(it.next(), theRoot);
		ExportChannel(theRoot);
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		StringWriter out = new StringWriter();
		try {
			xmlOutput.output(doc, out);

			if (!new File(SYSTEM_XML).exists())
				new File(SYSTEM_XML).createNewFile();
			else {
				PrintWriter writer;
				writer = new PrintWriter(SYSTEM_XML);
				writer.print("");
				writer.close();

			}
			FileUtils.writeStringToFile(new File(SYSTEM_XML), out.toString(),
					true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ImportSystemFromXML  imports new system state from XML file
	 * 
	 * @param   none
	 * @return  void
	 */
	public static void ImportSystemFromXML() {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document readDoc = builder.build(new File(SYSTEM_XML));
			RaceCollection.initialize();
			Channel.initializeEveryThing();
			List<Element> list = readDoc.getRootElement().getChildren();
			Iterator<Element> it = list.iterator();
			while (it.hasNext()) {
				Element temp = it.next();
				if (!temp.getName().equals("Channel"))
					RaceCollection.addRace(Race.XMLImport(temp));
				else
					ImportChannel(temp);
			}

			RaceCollection.CurrentRace = RaceCollection.getRaceByRaceID(Integer
					.parseInt(readDoc.getRootElement().getAttributeValue(
							"CurrentRace")));
			if (Integer.parseInt(readDoc.getRootElement().getAttributeValue(
					"NumberOfRaces")) != RaceCollection.array.size())
				throw new IllegalStateException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		/**
	 * ImportChannel imports a channel state from XML code
	 * 
	 * @param   element	 XML child 
	 * @return  void
	 */
	public static void ImportChannel(Element element) {
		List<Element> list = element.getChildren();
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) {
			Element temp = it.next();

			if (temp.getName().contains("isConnected"))
				Channel.setChannelConnected(
						Integer.parseInt(temp.getName()
								.replaceAll("Channel_", "")
								.replaceAll("_isConnected", "")),
						Boolean.parseBoolean(temp.getText()));
			else
				Channel.setChannelEnabled(
						Integer.parseInt(temp.getName()
								.replaceAll("Channel_", "")
								.replaceAll("_isEnabled", "")),
						Boolean.parseBoolean(temp.getText()));
		}
	}

	/**
	 * ExportChannel  exports channel states to XML
	 * 
	 * @param   element XML element to have channel states added
	 * @return  void
	 */
	public static void ExportChannel(Element element) {
		Element channel = new Element("Channel");
		int i;
		for (i = 0; i <= 11; i++)
			channel.addContent(new Element("Channel_" + i + "_isConnected")
					.addContent(new Text(Boolean.toString(Channel
							.getConnectState(i)))));
		for (i = 0; i <= 11; i++)
			channel.addContent(new Element("Channel_" + i + "_isEnabled")
					.addContent(new Text(Boolean.toString(Channel
							.getEnableState(i)))));
		element.addContent(channel);

	}
}
