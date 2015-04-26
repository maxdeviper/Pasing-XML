
package com.example.simpumind.fragment2;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GoalServer {

    // We don't use namespaces
    private static final String ns = null;

    //after creating an instance of the this object use this method to parse the stream response gotten from
    //goal.com http request
    public List<Category> parse(InputStream in) throws XmlPullParserException, IOException {
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


// static class Category{
// 	public String name;
// 	public String id;
// 	public String fileGroup;
// 	public Match matches;
// 	public Category(String id,String name, String fileGroup, Match matches){
// 		this.name=name;
// 		this.id=id;
// 		this.fileGroup=fileGroup;
// 	}


// static class Match{
// 	public String id;
// 	public String fixId;
// 	public String status;
// 	public String formatted_date;
// 	public String time;
// 	public Team localTeam;
// 	public Team vistorTeam;
// 	public Event[] events;
// 	public Match( String id,String fixId, String status,String formatted_date,String time,Team localTeam,Team vistorTeam){
// 		this.id=id;
// 		this.fixId = fixId;
// 		this.status=status;
// 		this.formatted_date=formatted_date;
// 		this.time = time;
// 		this.localTeam=localTeam;
// 		this.vistorTeam=vistorTeam;
// 	}


    public static class Event {
        public String type;
        public int minute;
        public Team team;
        public String player;
        public String result;
        public int playerId;
        public int id;

        public Event(
                int id,
                String type,
                int minute,
                Team team,
                String player,
                String result,
                int playerId
        ) {
            this.id = id;
            this.type = type;
            this.minute = minute;
            this.team = team;
            this.player = player;
            this.result = result;
            this.playerId = playerId;

        }
    }
// static class Team{
// 	public String name;
// 	public String goal;
// 	public String id;

    // }
    private List<Category> readScore(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Category> categories = new ArrayList<Category>();

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
        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
        String name = parser.getAttributeValue(null, "name");
        String fileGroup = parser.getAttributeValue(null, "file_group");
        List<Match> matches=null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name1 = parser.getName();
            if (name1.equals("matches")) {
                matches = readMatches(parser);
            } else {
                skip(parser);
            }
        }
        return new Category(id, name, fileGroup, matches);
    }

    private List<Match> readMatches(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "matches");
        List<Match> matches = new ArrayList<Match>();
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
        return matches;

    }

    private Match readMatch(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "match");
        Match match;
        int id =Integer.parseInt(parser.getAttributeValue(null, "id"));
        ;
        String fixId = parser.getAttributeValue(null, "fixId");
        ;
        String status = parser.getAttributeValue(null, "status");
        ;
        String formatted_date = parser.getAttributeValue(null, "formated_date");
        ;
        String time = parser.getAttributeValue(null, "time");
        ;
        Team localTeam=null;
        Team visitorTeam=null;
        List<Event> events;
        // Ht ht;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("localTeam")) {
                localTeam = readLocalTeam(parser);
            } else if (name.equals("visitorTeam")) {
                visitorTeam = readVisitorTeam(parser);
            }
            // if (name.equals("events")) {
            //     events = readEvents(parser);
            // }
            else {
                skip(parser);
            }
        }
        match = new Match(id, fixId, status, formatted_date, time, localTeam, visitorTeam);
        return match;
    }

    private Team readLocalTeam(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "localteam");
        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
        String goals = parser.getAttributeValue(null, "goals");
        String name = parser.getAttributeValue(null, "name");
        Team team = new Team(id, name, goals);

        return team;
    }

    private Team readVisitorTeam(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "visitorteam");
        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
        String goals = parser.getAttributeValue(null, "goals");
        String name = parser.getAttributeValue(null, "name");
        Team team = new Team(id, name, goals);
        return team;
    }

// private Event[] readEvents(XmlPullParser parser) throws XmlPullParserException, IOException{

//     parser.require(XmlPullParser.START_TAG, ns, "events");
//     String id = parser.getAttributeValue(null, "id");
//     String goals = parser.getAttributeValue(null, "goals");
//     String name = parser.getAttributeValue(null, "name");

//     return team;
// }
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

    }
}
