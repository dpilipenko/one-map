
one-map
=======

GRAILS + HTML5/CSS3 Interactive Map for Cleveland Office


```
Public API
   + [ GET ] /getHotspots?floor=#
   		A floor without hotspots:
   			{}
   		A floor with hotspots:
   			{"h4":"M264.156,169.956 262.355,225.036 214.956,228.516 182.406,222.367 175.116,219.156 161.979,210.562 165.036,195.876 169.835,172.716 170.315,170.796z","h1":"M327.276,443.556 337.835,489.156 414.755,470.916 403.835,425.436z","h3":"M450.996,300.876 481.295,348.391 429.755,360.516 428.196,365.436 389.076,370.116 368.316,321.516z","h2":"M435.996,384.636 487.835,372.996 508.317,448.939 453.276,461.907z"}

   				
   + [ GET ] /getHotspot?hotspotID=h#
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


   + [ GET ] /claimHotspot?hotspotID=h#
         {"userinformation":{"name":String, "level":String, "craft":String, "phone":String, "email":String}}


   * [ GET ] /unclaimHotspot
         {"success":boolean}


   + [ GET ] /createWarRoom?hotspotID=h#&projectName=STRING
   		{"success":boolean}


   * [ GET ] /closeWarRoom?hotspotID=h#
   		{"success":boolean}


   + [ GET ] /runSearch?searchquery=STRING
   		[ {"name":String, "level":String, "craft":String, "location":String}, {"name":String, "level":"", "craft":"", "location":String, "floor":String, "hotspotId":String} ]
```

