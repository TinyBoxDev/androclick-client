package com.tinybox.androclickclient.resources;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.util.Log;

public class StarredFile {
	
	private final String _CLASS_NAME = this.getClass().getName();

	private static StarredFile myself = null;
	
	private String FILENAME = "starred.xml";
	
	private Context context;
	private ArrayList<StarredItem> starredList;
	
	private StarredFile(Context ctx){
		this.context = ctx;
		this.starredList =  new ArrayList<StarredItem>();
		if(!this.loadList()){
			this.starredList.clear();
			StarredItem addItem = new StarredItem();
			addItem.setLabel("Add new item");
			addItem.setInfos("click here to add a new preferred stop");
			this.starredList.add(addItem);
			this.saveList();
			this.loadList();
		}
		
	}
	
	public static StarredFile getFile(Context activity){
		if(StarredFile.myself == null)
			StarredFile.myself = new StarredFile(activity);
	
		return StarredFile.myself;
	}
	
	public synchronized void addItem(String label, String infos, String number){
		StarredItem item = new StarredItem();
		item.setInfos(infos);
		item.setLabel(label);
		item.setNumber(number);
		
		this.starredList.add(1, item);
		this.saveList();
	
	}
	
	public synchronized void removeItem(int position){
		this.starredList.remove(position);
		this.saveList();
		
	}
	
	public synchronized ArrayList<StarredItem> getItemsList(){
		return starredList;

	}
	

	private synchronized boolean loadList(){
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.context.openFileInput(this.FILENAME));
			Element root = doc.getDocumentElement();
			NodeList starredNodeList = root.getChildNodes();
			this.starredList.clear();
			
			Log.v(this._CLASS_NAME, "lenght: " + starredNodeList.getLength());
			for(int index=0; index<starredNodeList.getLength(); index++){
				Log.v(this._CLASS_NAME, starredNodeList.item(index).getNodeName());
				NodeList starredNode = starredNodeList.item(index).getChildNodes();
				Log.v(this._CLASS_NAME, "lenght: " + starredNode.getLength());
				Log.v(this._CLASS_NAME, starredNode.item(0).getNodeName() + ": " + starredNode.item(0).getTextContent());
				Log.v(this._CLASS_NAME, starredNode.item(1).getNodeName() + ": " + starredNode.item(1).getTextContent());
				Log.v(this._CLASS_NAME, starredNode.item(2).getNodeName() + ": " + starredNode.item(2).getTextContent());
				StarredItem starred = new StarredItem();
				starred.setLabel(starredNode.item(0).getTextContent());
				Log.v(this._CLASS_NAME, "Ho settato " + starredNode.item(0).getTextContent());
				starred.setInfos(starredNode.item(1).getTextContent());
				Log.v(this._CLASS_NAME, "Ho settato " + starredNode.item(1).getTextContent());
				starred.setNumber(starredNode.item(2).getTextContent());
				Log.v(this._CLASS_NAME, "Ho settato " + starredNode.item(2).getTextContent());
				this.starredList.add(starred);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	
	}
	
	private synchronized boolean saveList(){
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element rootElement = doc.createElement("starred");
			doc.appendChild(rootElement);
			
			for(StarredItem item : this.starredList){
				Element itemElement = doc.createElement("item");
				rootElement.appendChild(itemElement);
				
				Element titleElement = doc.createElement("title");
				Log.v(this._CLASS_NAME, "Label: " + item.getLabel());
				titleElement.setTextContent(item.getLabel());
				itemElement.appendChild(titleElement);
				
				Element subtitleElement = doc.createElement("subtitle");
				subtitleElement.setTextContent(item.getInfos());
				Log.v(this._CLASS_NAME, "Infos: " + item.getInfos());
				itemElement.appendChild(subtitleElement);
				
				Element numberElement = doc.createElement("number");
				numberElement.setTextContent(item.getNumber());
				Log.v(this._CLASS_NAME, "Number: " + item.getNumber());
				itemElement.appendChild(numberElement);
			}
			
			Transformer toXmlTransformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			FileOutputStream xmlFile = this.context.openFileOutput(this.FILENAME, Context.MODE_PRIVATE);
			StreamResult result  = new StreamResult(xmlFile);
			
			toXmlTransformer.transform(source, result);

			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
