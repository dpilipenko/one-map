package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User
import com.rosetta.onemap.pintypes.Desk
import com.rosetta.onemap.pintypes.Room

@Transactional
class UserService {

    def deskService
	def roomService 
	
	Hotspot getCurrentHotspot(User user) {
		Room room = roomService.getRoomContainingUser(user)
		Desk desk = deskService.getDesk(user);
		
		if (room == null && desk == null) {
			return null;
		} else {
			if (room != null) {
				return room;
			}
			if (desk != null) {
				return desk;
			}
		}
	}
	
}
