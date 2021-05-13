import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {

    public XMLHandler(){
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                DBConnection.countVoter(attributes.getValue("name"), attributes.getValue("birthDay"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
    }
}
