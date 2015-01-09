import urllib2 				# working with url
import json
from bs4 import BeautifulSoup


#def getCourseInfo(url):

url = urllib2.urlopen('http://students.yale.edu/oci/resultDetail.jsp?course=24052&term=201501')

content = url.read()

soup = BeautifulSoup(content)



fullcourseinfowithcomments = soup.get_text()
j = len(fullcourseinfowithcomments)

k = fullcourseinfowithcomments.rfind("-->",0,j) + 3

fullcourseinfo = fullcourseinfowithcomments[k:j]



print fullcourseinfo

infoarray = fullcourseinfo.split("\n")
courseinfoarray = []
check = False

for element in infoarray:
	if element != "":
		for character in element:
			if character.isalpha() or character.isdigit():
 				check = True
 	if check == True:			
		courseinfoarray.append(element)
		check = False

print courseinfoarray
