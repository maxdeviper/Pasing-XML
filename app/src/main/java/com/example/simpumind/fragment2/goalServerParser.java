/*
package com.example.simpumind.fragment2;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class GoalServer {

    // We don't use namespaces
    private static final String ns = null;
   
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readScore(parser);
        } finally {
            in.close();
        }
    }



static class Category{
	public String name;
	public String id;
	public String fileGroup;
	public Match matches;
	public Category(String id,String name, String fileGroup, Match matches){
		this.name=name;
		this.id=id;
		this.fileGroup=fileGroup;
	}


}
static class Match{
	public String id;
	public String fixId;
	public String status;
	public String formatted_date;
	public String time;
	public Team localTeam;
	public Team vistorTeam;
	public Event[] events;
	public Match( String id,String fixId, String status,String formatted_date,String time,Team localTeam,Team vistorTeam){
		this.id=id;
		this.fixId = fixId;
		this.status=status;
		this.formatted_date=formatted_date;
		this.time = time;
		this.localTeam=localTeam;
		this.vistorTeam=vistorTeam;
	}


}
static class Event{
	public String type;
	public int minute;
	public Team team;
	public String player;
	public String result;
	public int playerId;
	public int id;
}
static class Team{
	public String name;
	public String goal;
	public String id;

}
private List readScore(XmlPullParser parser) throws XmlPullParserException, IOException {
    List categories = new ArrayList();

    parser.require(XmlPullParser.START_TAG, ns, "score");
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        // Starts by looking for the entry tag
        if (name.equals("category")) {
            categories.add(readCategory(parser));
        } else {
            skip(parser);
        }
    }  
    return categories;
}
// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
private Category readCategory(XmlPullParser parser) throws XmlPullParserException, IOException {


    parser.require(XmlPullParser.START_TAG, ns, "category");
    String id = parser.getAttributeValue(null, "id");
    String name = parser.getAttributeValue(null, "name");
    String fileGroup = parser.getAttributeValue(null, "file_group");
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name1 = parser.getName();
        if (name1.equals("matches")) {
            //matches = readMatches(parser);
        } else {
            skip(parser);
        }
    }
    return new Category(id, name, fileGroup,//matches);
}
private Category readMatches(XmlPullParser parser) throws XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ns, "matches");
    ArrayList matches=new ArrayList();
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        if (name.equals("match")) {
            matches.add(readMatch(parser));
        } else {
            skip(parser);
        }
    }
    /*return matches;*/
    /*
}
private Category readMatch(XmlPullParser parser) throws XmlPullParserException, IOException {

    parser.require(XmlPullParser.START_TAG, ns, "match");
 	String id = parser.getAttributeValue(null, "id");;
	String fixId = parser.getAttributeValue(null, "fixId");;
	String status = parser.getAttributeValue(null, "status");;
	String formatted_date = parser.getAttributeValue(null, "formated_date");;
	String time = parser.getAttributeValue(null, "time");;
	Team localTeam;
	Team visitorTeam;
	Event[] events;
	Ht ht;
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        if (name.equals("localTeam")) {
            localTeam = readLocalTeam(parser);
        } 
        if (name.equals("vistorTeam")) {
            visitorTeam=readvisitorTeam(parser);
        }
        if (name.equals("events")) {
            events = readEvents(parser);
        }
        else {
            skip(parser);
        }
    }
    return matches;
}

    private Team readLocalTeam(XmlPullParser parser) throws XmlPullParserException, IOException{

        parser.require(XmlPullParser.START_TAG, ns, "localteam");
        String id = parser.getAttributeValue(null, "id");
        String goals = parser.getAttributeValue(null, "goals");
        String name = parser.getAttributeValue(null, "name");

        return team;
    }

    private Team readvisitorTeam(XmlPullParser parser) throws XmlPullParserException, IOException{

        parser.require(XmlPullParser.START_TAG, ns, "visitorteam");
        String id = parser.getAttributeValue(null, "id");
        String goals = parser.getAttributeValue(null, "goals");
        String name = parser.getAttributeValue(null, "name");

        return team;
    }

    private Event[] readEvents(XmlPullParser parser) throws XmlPullParserException, IOException{

        parser.require(XmlPullParser.START_TAG, ns, "events");
        String id = parser.getAttributeValue(null, "id");
        String goals = parser.getAttributeValue(null, "goals");
        String name = parser.getAttributeValue(null, "name");

        return team;
    }
// // Processes title tags in the feed.
// private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
//     parser.require(XmlPullParser.START_TAG, ns, "title");
//     String title = readText(parser);
//     parser.require(XmlPullParser.END_TAG, ns, "title");
//     return title;
// }
  
// // Processes link tags in the feed.
// private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
//     String link = "";
//     parser.require(XmlPullParser.START_TAG, ns, "link");
//     String tag = parser.getName();
//     String relType = parser.getAttributeValue(null, "rel");  
//     if (tag.equals("link")) {
//         if (relType.equals("alternate")){
//             link = parser.getAttributeValue(null, "href");
//             parser.nextTag();
//         } 
//     }
//     parser.require(XmlPullParser.END_TAG, ns, "link");
//     return link;
// }

// // Processes summary tags in the feed.
// private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
//     parser.require(XmlPullParser.START_TAG, ns, "summary");
//     String summary = readText(parser);
//     parser.require(XmlPullParser.END_TAG, ns, "summary");
//     return summary;
// }

// For the tags title and summary, extracts their text values.
private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
    String result = "";
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.getText();
        parser.nextTag();
    }
    return result;
}
private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
        throw new IllegalStateException();
    }
    int depth = 1;
    while (depth != 0) {
        switch (parser.next()) {
        case XmlPullParser.END_TAG:
            depth--;
            break;
        case XmlPullParser.START_TAG:
            depth++;
            break;
        }
    }
 }*/
