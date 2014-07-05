
one-map
=======

GRAILS + HTML5/CSS3 Interactive Map for Cleveland Office


```
Public API
   + GET /getHotspots?floor=#
   		A floor without hotspots:
   			{}
   		A floor with hotspots:
   			{"h#":String}

   + GET /getHotspot?hotspotID=h#
   		If no pin:
   			{"floor":String, "type":String, "claimed":false, "name":String, "craft":String, "level":String, "phone":String, "email":String}
   		If pin is unclaimed desk:
   			{"floor":String, "type":String, "claimed":false, "name":String, "craft":String, "level":String, "phone":String, "email":String}
   		If pin is claimed desk by another:
   			{"name":String, "craft":String, "level":String, "phone":String, "email":String, "type":"desk", "isOwn":false, "claimed":true}
   		If pin is claimed desk by yourself:
   			{"name":String, "craft":String, "level":String, "phone":String, "email":String, "type":"desk", "isOwn":true, "claimed":false}
   		If pin is room
   			{"name":String, "number":String, "phone":String, "project":String, "type":"room", "members":[{"name":String, "craft":String, "level":String, "phone":String, "email":String}]}

   + GET /claimHotspot?hotspotID=h#
         {"userinformation":{"name":String, "level":String, "craft":String, "phone":String, "email":String}}

   * GET /unclaimHotspot
         {"success":boolean}

   + GET /createWarRoom?hotspotID=h#&projectName=STRING
   		{"success":boolean}

   * GET /closeWarRoom?hotspotID=h#
   		{"success":boolean}

   + GET /runSearch?searchquery=STRING
   		[ {"name":String, "level":String, "craft":String, "location":String}, {"name":String, "level":"", "craft":"", "location":String, "floor":String, "hotspotId":String} ]
```
