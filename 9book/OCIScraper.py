import sys
import urllib2              # working with url
import json
import re

sys.path.insert(0, 'libraries') # import the libraries that we need to the GAE project

from bs4 import BeautifulSoup


def courseNumberTest(courseNumber):                 #function will iterate through range of possible course numbers testing to see if they exist
    try:
        url = urllib2.urlopen("http://students.yale.edu/oci/resultDetail.jsp?course=%s&term=201501" % courseNumber)
        soup = BeautifulSoup(url.read())            #generates Beautiful Soup object for site
        pageTextWithExtraComments = soup.get_text()         #random commens seem to come along for the ride
        pageTextLength = len(pageTextWithExtraComments)
        endComments = pageTextWithExtraComments.rfind("-->",0,pageTextLength) + 3       #point in page when stops being comments
        return pageTextWithExtraComments[endComments:pageTextLength]        #get part of page that is actually text
    except:
        return None

##Will be deleted when we know it works

def parseCourseText(fullcourseinfo):
    
    infoarray = fullcourseinfo.split("\n")
    list_of_items = []
    check = False
    
    for element in infoarray: #Removes all blank fields and whitespace
        if element != "":
            for character in element:
                if character.isalpha() or character.isdigit():
                    check = True
        if check:           
            list_of_items.append(element)
            check = False




    list_of_possible_fields = ["Department","Course Number", "alternateDept","courseName","professor","timeLocation","term",
    "departmentPermissionRequired","final","Areas","Skills","instructorPermissionRequired","description"]

        #list_of_items defined in try. is courseinfoarray

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


    index = 0

    for element in list_of_items:
        while index < (len(list_of_possible_fields)):
            #print element+ " " + list_of_possible_fields[index]
            if bool_dict[list_of_possible_fields[index]](element):
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
    #clean_dict = {dictionary.keys(): value.strip() for value in dictionary.values()}
    
    return dictionary
