import org.easymock.samples.BasicClassMockTest;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.sun.tools.classfile.Opcode.get;
import static com.sun.tools.doclint.Entity.or;
import static sun.misc.Version.println;

/**
 * Created by Susie on 2017/4/21.
 */
public class xmlReader {

    public void allRead()
    {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("src/main/webapp/test/xmlReadTest.xml");
            Element root=document.getDocumentElement();
            //获得名为user的节点
            NodeList userList=root.getChildNodes();
            //NodeList userList=document.getElementsByTagName("user");
            System.out.println("all node number:"+userList.getLength());
            //遍历所有的user节点
            for(int i=0;i<userList.getLength();i++)
            {
                //获取一个节点
                Node user = userList.item(i);
                if(user.getNodeType()==Node.ELEMENT_NODE)
                {
                    String id=user.getAttributes().getNamedItem("id").getNodeValue();
                }
                for (Node node=user.getFirstChild();node!=null;node=node.getNextSibling())
                {
                    if(node.getNodeType()==Node.ELEMENT_NODE)
                    {

                            String userName=node.getFirstChild().getNodeValue();
                            System.out.println("UserName="+userName);

                    }
                }
                //System.out.println("UserName:"+user.getAttribute("userName"));
                //System.out.println("Password:"+user.getAttribute("password"));
                /*NamedNodeMap attrs=node.getAttributes();
                System.out.println("nodeList: "+ (i+1));
                for(int j=0;j<attrs.getLength();j++)
                {
                    Node a=attrs.item(j);
                    System.out.println(a.getAttributes());
                    NodeList childNodes=a.getChildNodes();
                    for (int n=0;n<childNodes.getLength();n++)
                    {
                        System.out.println(childNodes.item(n));

                    }
                }*/
            }
        }
        catch (ParserConfigurationException e) { e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public void testRead()
    {
        String url="src/main/webapp/test/xmlReadTest.xml";
        String node="user";
        ArrayList<String> paras=new ArrayList<String>();
        paras.add("userName");
        paras.add("password");
        ArrayList result=read(url,node,paras);
        for(int i=0;i<result.size();i++)
        {
            System.out.println("-----"+result.get(i));
        }
    }

    //url="src/main/webapp/test/xmlReadTest.xml"
    //node为需要找的node名称
    //paras为需要找的节点的名字数组
    public ArrayList read(String url,String node, ArrayList paras)
    {
        ArrayList list=new ArrayList();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url);
            Element root=doc.getDocumentElement();
            //获得名为user的节点
            //NodeList userList=root.getChildNodes();
            NodeList userList=doc.getElementsByTagName(node);
            System.out.println("all node number:"+userList.getLength());
            //遍历所有的user节点
            for(int i=0;i<userList.getLength();i++)
            {
                //获取一个节点
                Element user =(Element) userList.item(i);
                HashMap<String,String> map=new HashMap<String ,String>();
                for (int j=0;j<paras.size();j++) {
                    String cur=paras.get(j).toString();
                    String cur_Value=user.getElementsByTagName(cur).item(0).getFirstChild().getNodeValue();
                    map.put(cur,cur_Value);
                }
                list.add(map);
            }
        }
        catch (ParserConfigurationException e) { e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (Exception e){e.printStackTrace();}
        return list;
    }
}
