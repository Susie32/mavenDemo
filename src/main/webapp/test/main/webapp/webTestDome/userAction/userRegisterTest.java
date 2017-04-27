package main.webapp.webTestDome.userAction;

import main.webapp.webTestDome.dbcpConn.DBCPAction;
import org.junit.Test;
import org.junit.*;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.SystemColor.text;


/**
 * Created by Susie on 2017/4/20.
 */
public class userRegisterTest {
    @Test
    public void registerFailWithDuplicateUserName_get(){
        String expect="0用户已存在！";
        httpCon httpCon=new httpCon();
        String responseS=httpCon.sendGet("http://localhost:4100/userRegist","userName=susie&password=111111");
        System.out.println("response string: "+responseS);
        Assert.assertTrue(responseS.contains(expect));
    }
    @Test
    public void registerFailWithDuplicateUserName_post(){
        String expect="0用户已存在！";
        httpCon httpCon=new httpCon();
        String responseS=httpCon.sendPost("http://localhost:4100/userRegist","userName=susie&password=111111");
        System.out.println("response string: "+responseS);
        Assert.assertTrue(responseS.contains(expect));
    }
    @Test
    public void registerSuccess_get(){
        String expect="注册成功！";
        httpCon httpCon=new httpCon();
        String testUser="autoTestUser";
        deleteExistTestUser(testUser);
        String responseS=httpCon.sendGet("http://localhost:4100/userRegist","userName=autoTestUser&password=111111");
        System.out.println("response string: "+responseS);
        Assert.assertTrue(responseS.contains(expect));
    }
    @Test
    public void registerSuccess_post(){
        String expect="注册成功！";
        httpCon httpCon=new httpCon();
        String testUser="autoTestUser";
        deleteExistTestUser(testUser);
        String responseS=httpCon.sendPost("http://localhost:4100/userRegist","userName="+testUser+"&password=111111");
        System.out.println("response string: "+responseS);
        Assert.assertTrue(responseS.contains(expect));
    }

    public void deleteExistTestUser(String userName)
    {
        String sql;
        sql="select * from userBaseInfo where userName='"+userName+"'";
        System.out.println("sql= "+sql);
        if(new DBCPAction().queryString(sql).equals("[]")==false) {
            String deleteSql="delete from userBaseInfo where userName='"+userName+"'";
            System.out.println("deleteSql= "+deleteSql);
            if(new DBCPAction().update(deleteSql)!=0)
            {
                System.out.println("delete exist test user("+userName+") success!");
            }
            else System.out.println("delete exist test user("+userName+") fail!");
        }
        else System.out.println("database not exist test user: "+userName);
    }



    @Test
    public void userRegistTest()
    {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("src/main/webapp/test/main/webapp/webTestDome/userAction/registUser.xml");
            Element root=doc.getDocumentElement();
            //获得名为user的节点
            NodeList userList=doc.getElementsByTagName("user");
            System.out.println("all node number:"+userList.getLength());
            for(int i=0;i<userList.getLength();i++)
            {
                //获取一个节点
                Element user =(Element) userList.item(i);
                String expect=user.getElementsByTagName("expect").item(0).getFirstChild().getNodeValue();
                String userName=user.getElementsByTagName("userName").item(0).getFirstChild().getNodeValue();
                String password=user.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
                httpCon httpCon=new httpCon();
                deleteExistTestUser(userName);
                String responseS=httpCon.sendPost("http://localhost:4100/userRegist","userName="+userName+"&password=111111");
                System.out.println("response string: "+responseS);
                Assert.assertTrue(responseS.contains(expect));
            }
        }
        catch (ParserConfigurationException e) { e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (Exception e){e.printStackTrace();}
    }
}
