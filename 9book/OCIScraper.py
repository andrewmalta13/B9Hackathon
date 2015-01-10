import urllib2 				# working with url
import json
import re
from bs4 import BeautifulSoup
#import webapp2
#import courses
#def getCourseInfo(url):

url = urllib2.urlopen('http://students.yale.edu/oci/resultDetail.jsp?course=20642&term=201501')
content = url.read()

soup = BeautifulSoup(content)


fullcourseinfowithcomments = soup.get_text()
lengthincharacters = len(fullcourseinfowithcomments)  

endcomments = fullcourseinfowithcomments.rfind("-->",0,lengthincharacters) + 3

fullcourseinfo = fullcourseinfowithcomments[endcomments:lengthincharacters]



infoarray = fullcourseinfo.split("\n")
courseinfoarray = []
check = False

for element in infoarray: #Removes all blank fields and whitespace
	if element != "":
		for character in element:
			if character.isalpha() or character.isdigit():
 				check = True
 	if check:			
		courseinfoarray.append(element)
		check = False





########################################################################################################
#Andrew, delete above here to integrate with connor





list_of_possible_fields = ["Department","Course Number", "alternateDept","courseName","professor","timeLocation","term",
"departmentPermissionRequired","final","Areas","Skills","instructorPermissionRequired","description"]


list_of_items = courseinfoarray

#print list_of_items

def f(item):
	return (not ("Spring" in item or ("Fall" in item or "Summer" in item)))

#print f("TTH 1.00-2.15 LOM 214")


bool_dict = {
"Department" : lambda item: True,
"Course Number": lambda item: True,
"alternateDept": lambda item: item.find("/") >= 0,
"courseName": lambda item: True, #Always present
"professor": lambda item: re.match(r"\D(\D*\s)*\D*$",item) != None,
"timeLocation": lambda item: not ("Spring" in item or ("Fall" in item or "Summer" in item)),
#"location" :lambda item:
"term": lambda item: "Spring" in item or ("Fall" in item or "Summer" in item),
"departmentPermissionRequired": lambda item: "Pre-Approval" in item,
"final":lambda item: "Final" in item or "final" in item,
"Areas":lambda item: "Areas" in item,
"Skills": lambda item: "Skills" in item,
"instructorPermissionRequired": lambda item: "require" in item,
"description": lambda item: True
}


dictionary = {}



def itemCheck(item,field):
	return bool_dict[field](item)

index = 0

for element in list_of_items:
	while index < (len(list_of_possible_fields)):
		#print element+ " " + list_of_possible_fields[index]
		if itemCheck(element,list_of_possible_fields[index]):
			dictionary[list_of_possible_fields[index]] = element
			if list_of_possible_fields[index] != "professor":
				list_of_possible_fields.remove(list_of_possible_fields[index])
			elif list_of_possible_fields[index] == "timeLocation":
				list_of_possible_fields.remove("professor")
				list_of_possible_fields.remove("timeLocation")
			break
		else:
			list_of_possible_fields.remove(list_of_possible_fields[index])
			index -= 1
		index += 1

#cleaning up dictionary output
clean_dict = [value.strip() for value in dictionary.values()]

print clean_dict